package team2.BUILDWEEK_5.controllers;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import team2.BUILDWEEK_5.entities.Utente;
import team2.BUILDWEEK_5.exceptions.ValidationException;
import team2.BUILDWEEK_5.payloads.LoginDTO;
import team2.BUILDWEEK_5.payloads.LoginResponseDTO;
import team2.BUILDWEEK_5.payloads.UtenteDTO;
import team2.BUILDWEEK_5.services.AuthService;
import team2.BUILDWEEK_5.services.UtentiService;

import java.util.List;

@RestController
@RequestMapping("/auth")
@AllArgsConstructor
public class AuthController {

    private final UtentiService utentiService;
    private final AuthService authService;

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public Utente saveUtente(@RequestBody @Validated UtenteDTO body, BindingResult validationResults) {

        if (validationResults.hasErrors()) {

            List<String> errorsList = validationResults.getFieldErrors()
                    .stream().map(fieldError -> fieldError.getDefaultMessage()).toList();

            throw new ValidationException(errorsList);
        } else {
            return this.utentiService.saveUtente(body);
        }
    }

    @PostMapping("/login")
    public LoginResponseDTO login(@RequestBody LoginDTO body) {
        return new LoginResponseDTO(this.authService.checkCredentialsAndGenerateToken(body));
    }
}
