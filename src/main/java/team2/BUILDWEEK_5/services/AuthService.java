package team2.BUILDWEEK_5.services;

import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import team2.BUILDWEEK_5.entities.Utente;
import team2.BUILDWEEK_5.exceptions.UnauthorizedException;
import team2.BUILDWEEK_5.payloads.LoginDTO;
import team2.BUILDWEEK_5.security.JWTTools;

@Service
@AllArgsConstructor
public class AuthService {

    private final UtentiService utentiService;
    private final PasswordEncoder passwordEncoder;
    private final JWTTools jwtTools;

    public String checkCredentialsAndGenerateToken(LoginDTO body) {
        Utente f = this.utentiService.findByEmail(body.email());
        if (passwordEncoder.matches(body.password(), f.getPassword())) {
            String Token = jwtTools.generateToken(f);
            return Token;
        } else {
            throw new UnauthorizedException("Credenziali errate!");
        }


    }
}
