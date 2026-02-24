package team2.BUILDWEEK_5.services;

import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import team2.BUILDWEEK_5.security.JWTTools;

@Service
@AllArgsConstructor
public class AuthService {

    private final UtentiService utentiService;
    private final PasswordEncoder passwordEncoder;
    private final JWTTools jwtTools;


}
