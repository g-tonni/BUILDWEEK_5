package team2.BUILDWEEK_5.payloads;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record IndirizzoDTO(

        @NotBlank(message = "La via è obbligatoria")
        String via,

        @NotBlank(message = "Il civico è obbligatorio")
        String civico,

        @NotBlank(message = "La località è obbligatoria")
        String localita,

        @NotBlank(message = "Il CAP è obbligatorio")
        String cap,

        @NotNull(message = "L'id del comune è obbligatorio")
        UUID idComune

) {
}
