package team2.BUILDWEEK_5.payloads;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record ContattoDTO(@Email(message = "Email inserita non valida!") String email,
                          @NotBlank(message = "Nome obbligatorio!") @Size(min = 2, max = 30, message = " il nome deve essere compreso tra i 2 e i 30 caratteri") String nome,
                          @NotBlank(message = "Cognome obbligatorio!") @Size(min = 2, max = 40, message = " Il cognome deve essere tra i 2 e i 40 caratteri") String cognome,
                          @NotBlank(message = "Telefono obbligatorio!") @Size(min = 2, max = 20, message = " Il numero deve essere compreso tra i 2 e i 20 caratteri") String telefono) {
}
