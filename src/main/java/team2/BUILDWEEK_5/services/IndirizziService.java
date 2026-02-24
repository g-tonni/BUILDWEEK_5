package team2.BUILDWEEK_5.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import team2.BUILDWEEK_5.entities.Comune;
import team2.BUILDWEEK_5.entities.Indirizzo;
import team2.BUILDWEEK_5.exceptions.NotFoundException;
import team2.BUILDWEEK_5.payloads.IndirizzoDTO;
import team2.BUILDWEEK_5.repositories.ComuniRepository;
import team2.BUILDWEEK_5.repositories.IndirizziRepository;

import java.util.List;
import java.util.UUID;

@Service
public class IndirizziService {

    private final IndirizziRepository indirizziRepository;
    private final ComuniRepository comuniRepository;

    @Autowired
    public IndirizziService(IndirizziRepository indirizziRepository, ComuniRepository comuniRepository) {
        this.indirizziRepository = indirizziRepository;
        this.comuniRepository = comuniRepository;
    }

    // CREATE
    public Indirizzo saveIndirizzo(IndirizzoDTO body) {

        Comune comune = comuniRepository.findById(body.idComune())
                .orElseThrow(() -> new NotFoundException(body.idComune()));

        Indirizzo nuovo = new Indirizzo(
                body.via(),
                body.civico(),
                body.localita(),
                body.cap(),
                comune
        );

        return indirizziRepository.save(nuovo);
    }

    // READ ALL
    public List<Indirizzo> findAllIndirizzi() {
        return indirizziRepository.findAll();
    }

    // READ BY ID
    public Indirizzo findById(UUID id) {
        return indirizziRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(id));
    }

    // UPDATE
    public Indirizzo findByIdAndUpdate(UUID id, IndirizzoDTO body) {

        Indirizzo found = this.findById(id);

        Comune comune = comuniRepository.findById(body.idComune())
                .orElseThrow(() -> new NotFoundException(body.idComune()));

        found.setVia(body.via());
        found.setCivico(body.civico());
        found.setLocalita(body.localita());
        found.setCap(body.cap());
        found.setComune(comune);

        return indirizziRepository.save(found);
    }

    // DELETE
    public void findByIdAndDelete(UUID id) {
        Indirizzo found = this.findById(id);
        indirizziRepository.delete(found);
    }
}