package team2.BUILDWEEK_5.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import team2.BUILDWEEK_5.entities.Contatto;
import team2.BUILDWEEK_5.exceptions.AlreadyExists;
import team2.BUILDWEEK_5.exceptions.NotFoundException;
import team2.BUILDWEEK_5.payloads.ContattoDTO;
import team2.BUILDWEEK_5.repositories.ContattoRepository;

import java.util.UUID;

@Service
public class ContattoService {
    private final ContattoRepository cr;

    @Autowired
    public ContattoService(ContattoRepository cr) {
        this.cr = cr;
    }

    public Page<Contatto> findAll(int page, int size, String orderBy, String sortCriteria) {
        Pageable pageable = PageRequest.of(page, size,
                sortCriteria.equals("desc") ? Sort.by(orderBy).descending() : Sort.by(orderBy));
        return this.cr.findAll(pageable);
    }

    public Contatto findById(UUID id) {
        return this.cr.findById(id).orElseThrow(() -> new NotFoundException(id));
    }

    public Contatto findByEmail(String email) {
        return this.cr.findByEmail(email).orElseThrow(() -> new NotFoundException(email));
    }

    public Contatto save(ContattoDTO payload) {
        Contatto nContatto = new Contatto(payload.email(), payload.nome(), payload.cognome(), payload.telefono());
        this.cr.save(nContatto);
        return nContatto;
    }

    public Contatto findByIdAndUpdate(UUID id, ContattoDTO payload) {
        Contatto f = this.findById(id);
        if (!payload.email().equals(f.getEmail()) &&
                this.cr.findByEmail(payload.email()).isPresent()) {
            throw new AlreadyExists("La mail è già registrata");
        }
        if (!payload.telefono().equals(f.getTelefono()) &&
                this.cr.findByTelefono(payload.telefono()).isPresent()) {
            throw new AlreadyExists("Il numero di telefono è già registrato");
        }
        ;
        f.setEmail(payload.email());
        f.setNome(payload.nome());
        f.setCognome(payload.cognome());
        f.setTelefono(payload.telefono());
        this.cr.save(f);
        return f;
    }

    public void deleteById(UUID id) {
        Contatto f = this.findById(id);
        this.cr.delete(f);

    }
}
