package team2.BUILDWEEK_5.payloads;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record RuoloDTO(
        @NotBlank(message = "Il ruolo deve essere inserito")
        @Size(min = 3, max = 30, message = "Il ruolo deve essere compreso tra 3 e 30 caratteri")
        String ruolo
) {
}
