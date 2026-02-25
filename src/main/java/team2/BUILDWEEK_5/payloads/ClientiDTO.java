package team2.BUILDWEEK_5.payloads;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import team2.BUILDWEEK_5.entities.RagioneSociale;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public record ClientiDTO(
        @NotNull(message = "Il campo Ragione Sociale non può essere vuoto.")
        RagioneSociale ragioneSociale,
        @NotBlank(message = "Il campo Partita Iva non può essere vuoto.")
        String partitaIva,
        @NotBlank(message = "Il campo e-mail non può essere vuoto.")
        @Email
        String email,
        LocalDate dataUltimoContatto,
        @NotNull(message = "Il campo Fatturato Annuo non può essere vuoto.")
        BigDecimal fatturatoAnnuale,
        @NotBlank(message = "Il campo P.E.C. non può essere vuoto.")
        String pec,
        @NotBlank(message = "Il campo telefono non può essere vuoto.")
        String telefono,
        @NotNull(message = "Il campo Sede Legale non può essere vuoto.")
        UUID sedeLegaleId,
        @NotNull(message = "Il campo Sede Operativo non può essere vuoto.")
        UUID sedeOperativaId,
        @NotNull(message = "Il campo Contatto non può essere vuoto.")
        UUID idContatto
) {
}
