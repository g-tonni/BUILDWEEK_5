package team2.BUILDWEEK_5.payloads;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record FattureDTO(
        @NotNull(message = "L' importo della fattura é un campo obbligatorio!")
        @Min(value = 0, message = "L' importo della fattura deve essere positivo!")
        int importoFattura,

        @NotBlank(message = "L' id dell' utente é un campo obbligatorio!")
        UUID idCliente) {
}
