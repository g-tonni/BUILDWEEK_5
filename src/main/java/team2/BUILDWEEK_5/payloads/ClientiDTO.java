package team2.BUILDWEEK_5.payloads;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import team2.BUILDWEEK_5.entities.Contatto;
import team2.BUILDWEEK_5.entities.Indirizzo;
import team2.BUILDWEEK_5.entities.RagioneSociale;

import java.time.LocalDate;

public record ClientiDTO(
        @NotBlank(message = "Il campo Ragione Sociale non può essere vuoto.")
        RagioneSociale ragioneSociale,
        @NotBlank(message = "Il campo Partita Iva non può essere vuoto.")
        String partitaIva,
        @NotBlank(message = "Il campo e-mail non può essere vuoto.")
        @Email
        String email,
        @NotBlank(message = "Il campo Data d'Inserimento non può essere vuoto.")
        LocalDate dataInserimento,
//        @NotBlank(message = "")
        LocalDate dataUltimoContatto,
        @NotBlank(message = "Il campo Fatturato Annuo non può essere vuoto.")
        String fatturatoAnnuale,
        @NotBlank(message = "Il campo P.E.C. non può essere vuoto.")
        String pec,
        @NotBlank(message = "Il campo telefono non può essere vuoto.")
        String telefono,
        @NotBlank(message = "Il campo Sede Legale non può essere vuoto.")
        Indirizzo sedeLegale,
        @NotBlank(message = "Il campo Sede Operativo non può essere vuoto.")
        Indirizzo sedeOperativa,
        @NotBlank(message = "Il campo Contatto non può essere vuoto.")
        Contatto contatto
) {
}
