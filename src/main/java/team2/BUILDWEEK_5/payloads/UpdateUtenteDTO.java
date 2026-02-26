package team2.BUILDWEEK_5.payloads;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record UpdateUtenteDTO(@NotBlank(message = "L'email deve essere inserita")
                              @Email(message = "Indirizzo email non valido")
                              String email,

                              @NotBlank(message = "La password deve essere inserita")
                              @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$", message = "La password deve contenere almeno una maiuscola,un numero,un carattere speciale,e deve essere lunga almeno 8 caratteri!")
                              String password) {
}
