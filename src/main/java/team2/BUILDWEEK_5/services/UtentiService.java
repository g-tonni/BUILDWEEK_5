package team2.BUILDWEEK_5.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import team2.BUILDWEEK_5.entities.Ruolo;
import team2.BUILDWEEK_5.entities.RuoloUtente;
import team2.BUILDWEEK_5.entities.Utente;
import team2.BUILDWEEK_5.exceptions.BadRequestException;
import team2.BUILDWEEK_5.exceptions.NotFoundException;
import team2.BUILDWEEK_5.payloads.RuoloDTO;
import team2.BUILDWEEK_5.payloads.RuoloUtenteDTO;
import team2.BUILDWEEK_5.payloads.UtenteDTO;
import team2.BUILDWEEK_5.repositories.RuoliRepository;
import team2.BUILDWEEK_5.repositories.RuoliUtentiRepository;
import team2.BUILDWEEK_5.repositories.UtentiRepository;

import java.util.UUID;

@Service
@AllArgsConstructor
public class UtentiService {

    private final UtentiRepository utentiRepository;
    private final RuoliRepository ruoliRepository;
    private final RuoliUtentiRepository ruoliUtentiRepository;

    public Ruolo findRuoloByNome(String nome) {
        return this.ruoliRepository.findById(nome).orElseThrow(() -> new NotFoundException(nome));
    }

    public Utente findUtenteById(UUID utenteId) {
        return this.utentiRepository.findById(utenteId).orElseThrow(() -> new NotFoundException(utenteId));
    }

    public Utente saveUtente(UtenteDTO body) {
        if (!this.utentiRepository.findByEmail(body.email()).isEmpty())
            throw new BadRequestException("Email è gia in uso");

        Utente nuovoUtente = new Utente(body.nome(), body.cognome(), body.email(), body.password());

        this.utentiRepository.save(nuovoUtente);

        System.out.println("Utente salvato");

        return nuovoUtente;
    }


    public Ruolo saveRuolo(RuoloDTO body) {
        if (this.findRuoloByNome(body.ruolo()) == null) throw new BadRequestException("Ruolo già esistente");

        Ruolo nuovoRuolo = new Ruolo(body.ruolo());

        System.out.println("Ruolo salvato");

        return nuovoRuolo;
    }

    public RuoloUtente saveRuoloUtente(RuoloUtenteDTO body) {
        Utente utenteTrovato = this.findUtenteById(body.idUtente());
        Ruolo ruoloTrovato = this.findRuoloByNome(body.nomeRuolo());

        RuoloUtente nuovoRuoloUtente = new RuoloUtente(utenteTrovato, ruoloTrovato);

        System.out.println("Ruolo associato all'utente");

        return nuovoRuoloUtente;
    }
}
