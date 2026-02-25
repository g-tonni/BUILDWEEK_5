package team2.BUILDWEEK_5.security;


import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;
import team2.BUILDWEEK_5.entities.Utente;
import team2.BUILDWEEK_5.exceptions.UnauthorizedException;
import team2.BUILDWEEK_5.services.UtentiService;

import java.io.IOException;
import java.util.UUID;

@Component
public class JWTCheckerFilter extends OncePerRequestFilter {

    private final JWTTools jwtTools;
    private final UtentiService utentiService;

    public JWTCheckerFilter(JWTTools jwtTools, UtentiService utentiService) {
        this.jwtTools = jwtTools;
        this.utentiService = utentiService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String authorization = request.getHeader("Authorization");
        if (authorization == null || !authorization.startsWith("Bearer "))
            throw new UnauthorizedException("Inserire il token nell'header nel formato corretto" + authorization);

        String accessToken = authorization.replace("Bearer ", "");

        jwtTools.verifyToken(accessToken);

        UUID utenteId = jwtTools.getIdByToken(accessToken);

        Utente utente = utentiService.findUtenteById(utenteId);

        Authentication authentication = new UsernamePasswordAuthenticationToken(utente, null, utente.getAuthorities());

        SecurityContextHolder.getContext().setAuthentication(authentication);

        filterChain.doFilter(request, response);
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        return new AntPathMatcher().match("/auth/**", request.getServletPath());
    }
}
