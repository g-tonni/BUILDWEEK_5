package team2.BUILDWEEK_5.services;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
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

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class UtentiService {

    private final UtentiRepository utentiRepository;
    private final RuoliRepository ruoliRepository;
    private final RuoliUtentiRepository ruoliUtentiRepository;
    private final PasswordEncoder passwordEncoder;

    public Ruolo findRuoloByNome(String nome) {
        return this.ruoliRepository.findById(nome).orElseThrow(() -> new NotFoundException(nome));
    }

    public Utente findUtenteById(UUID utenteId) {
        return this.utentiRepository.findById(utenteId).orElseThrow(() -> new NotFoundException(utenteId));
    }

    public Utente findByEmail(String email) {
        return this.utentiRepository.findByEmail(email).orElseThrow(() -> new NotFoundException(email));
    }

    public Page<Utente> findAllUtenti(int page, int size, String orderBy, String sortCriteria) {
        if (size > 100 || size < 0) size = 10;
        if (page < 0) page = 0;
        Pageable pageable = PageRequest.of(page, size,
                sortCriteria.equals("desc") ? Sort.by(orderBy).descending() : Sort.by(orderBy));
        return this.utentiRepository.findAll(pageable);
    }

    public Utente saveUtente(UtenteDTO body) {
        if (!this.utentiRepository.findByEmail(body.email()).isEmpty())
            throw new BadRequestException("Email è gia in uso");

        Ruolo ruolo = this.findRuoloByNome("USER");

        Utente nuovoUtente = new Utente(body.nome(), body.cognome(), body.email(), passwordEncoder.encode(body.password()));


        RuoloUtente ruoloUtente = new RuoloUtente(nuovoUtente, ruolo);

        nuovoUtente.getRuoli().add(ruoloUtente);

        this.utentiRepository.save(nuovoUtente);

        System.out.println("Utente salvato");

        return nuovoUtente;
    }

    public Utente saveAdmin(UtenteDTO body) {
        if (!this.utentiRepository.findByEmail(body.email()).isEmpty())
            throw new BadRequestException("Email è gia in uso");

        Ruolo ruolo = this.findRuoloByNome("ADMIN");

        Utente nuovoUtente = new Utente(body.nome(), body.cognome(), body.email(), passwordEncoder.encode(body.password()));


        RuoloUtente ruoloUtente = new RuoloUtente(nuovoUtente, ruolo);

        nuovoUtente.getRuoli().add(ruoloUtente);

        this.utentiRepository.save(nuovoUtente);

        System.out.println("Utente salvato");

        return nuovoUtente;
    }

    public Ruolo saveRuolo(RuoloDTO body) {

        Ruolo nuovoRuolo = new Ruolo(body.ruolo());

        this.ruoliRepository.save(nuovoRuolo);

        System.out.println("Ruolo salvato");

        return nuovoRuolo;
    }

    public RuoloUtente saveRuoloUtente(RuoloUtenteDTO body) {
        Utente utenteTrovato = this.findUtenteById(body.idUtente());
        Ruolo ruoloTrovato = this.findRuoloByNome(body.nomeRuolo());

        RuoloUtente nuovoRuoloUtente = new RuoloUtente(utenteTrovato, ruoloTrovato);

        this.ruoliUtentiRepository.save(nuovoRuoloUtente);

        System.out.println("Ruolo associato all'utente");

        return nuovoRuoloUtente;
    }

    public Page<Ruolo> findAllRuoliPage(int page, int size, String orderBy, String sortCriteria) {
        if (size > 100 || size < 0) size = 10;
        if (page < 0) page = 0;
        Pageable pageable = PageRequest.of(page, size,
                sortCriteria.equals("desc") ? Sort.by(orderBy).descending() : Sort.by(orderBy));
        return this.ruoliRepository.findAll(pageable);
    }

    public List<Ruolo> findAllRuoliList() {
        return this.ruoliRepository.findAll();
    }

    public List<Utente> findAllUtentiList() {
        return this.utentiRepository.findAll();
    }
}
