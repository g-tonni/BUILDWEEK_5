package team2.BUILDWEEK_5.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import team2.BUILDWEEK_5.entities.Utente;
import team2.BUILDWEEK_5.exceptions.UnauthorizedException;

import java.util.Date;
import java.util.UUID;

@Component
public class JWTTools {

    @Value("${jwt.secret}")
    private String secret;

    public String generateToken(Utente utente) {
        return Jwts.builder()
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24 * 7))
                .subject(String.valueOf(utente.getIdUtente()))
                .signWith(Keys.hmacShaKeyFor(secret.getBytes()))
                .compact();
    }

    public void verifyToken(String token) {
        try {
            Jwts.parser()
                    .verifyWith(Keys.hmacShaKeyFor(secret.getBytes()))
                    .build()
                    .parse(token);
        } catch (Exception ex) {
            throw new UnauthorizedException("Token non valido");
        }
    }

    public UUID getIdByToken(String token) {
        try {
            return UUID.fromString(Jwts.parser()
                    .verifyWith(Keys.hmacShaKeyFor(secret.getBytes()))
                    .build()
                    .parseSignedClaims(token)
                    .getPayload()
                    .getSubject());
        } catch (Exception ex) {
            throw new UnauthorizedException("Token non valido");
        }
    }
}
