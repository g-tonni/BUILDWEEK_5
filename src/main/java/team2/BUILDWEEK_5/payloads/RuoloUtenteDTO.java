package team2.BUILDWEEK_5.payloads;

import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record RuoloUtenteDTO(
        @NotNull(message = "L'id utente deve essere inserito")
        UUID idUtente,
        @NotNull(message = "L'id ruolo deve essere inserito")
        UUID idRuolo
) {
}
