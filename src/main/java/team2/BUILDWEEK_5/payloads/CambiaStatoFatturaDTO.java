package team2.BUILDWEEK_5.payloads;

import jakarta.validation.constraints.NotBlank;

public record CambiaStatoFatturaDTO(
        @NotBlank(message = "Il nome dello stato é un campo obbligatorio")
        String cambioStato) {
}
