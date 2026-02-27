package team2.BUILDWEEK_5.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import team2.BUILDWEEK_5.entities.Cliente;
import team2.BUILDWEEK_5.entities.Contatto;
import team2.BUILDWEEK_5.entities.Indirizzo;
import team2.BUILDWEEK_5.exceptions.AlreadyExists;
import team2.BUILDWEEK_5.exceptions.NotFoundException;
import team2.BUILDWEEK_5.payloads.ClientiDTO;
import team2.BUILDWEEK_5.repositories.ClientiRepository;
import team2.BUILDWEEK_5.specifications.ClientiSpecifications;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service

public class ClientiService {

    private final ClientiRepository clientiRepository;
    private final ContattoService contattoService;
    private final IndirizziService indirizziService;


    @Autowired
    public ClientiService(ClientiRepository clientiRepository, ContattoService contattoService, IndirizziService indirizziService) {
        this.clientiRepository = clientiRepository;
        this.contattoService = contattoService;
        this.indirizziService = indirizziService;

    }

    public Page<Cliente> findAll(int page, int size, String orderBy, String sortCriteria) {
        Pageable pageable = PageRequest.of(page, size,
                sortCriteria.equals("desc") ? Sort.by(orderBy).descending() : Sort.by(orderBy));
        return this.clientiRepository.findAll(pageable);
    }

    public Cliente findByEmail(String email) {

        return this.clientiRepository.findByEmail(email)
                .orElseThrow(() -> new NotFoundException(email));
    }

    public Cliente findByTelefono(String telefono) {
        return this.clientiRepository.findByTelefono(telefono)
                .orElseThrow(() -> new NotFoundException(telefono));
    }

    public Cliente save(ClientiDTO clientiDTO) {

        if (clientiRepository.findByEmail(clientiDTO.email()).isPresent()) {
            throw new AlreadyExists("L'e-mail è già utilizzata.");
        }

        if (clientiRepository.findByPartitaIva(clientiDTO.partitaIva()).isPresent()) {
            throw new AlreadyExists("Il cliente con questa partita iva è già esistente");
        }

        Contatto found = contattoService.findById(clientiDTO.idContatto());

        Indirizzo sedeLegaleFound = indirizziService.findById(clientiDTO.sedeLegaleId());
        Indirizzo sedeOperativaFound = indirizziService.findById(clientiDTO.sedeOperativaId());

        Cliente newCliente = new Cliente(
                clientiDTO.nomeCliente(),
                clientiDTO.ragioneSociale(),
                clientiDTO.partitaIva(),
                clientiDTO.email(),
                clientiDTO.dataUltimoContatto(),
                clientiDTO.fatturatoAnnuale(),
                clientiDTO.pec(),
                clientiDTO.telefono(),
                sedeLegaleFound,
                sedeOperativaFound,
                found
        );

        return this.clientiRepository.save(newCliente);
    }

    public Cliente disattivaCliente(UUID id) {
        Cliente found = this.findById(id);

        found.setAttivo(false);

        found.setDataCancellazione(LocalDate.now());

        this.clientiRepository.save(found);

        System.out.println("Cliente disattivato");

        return found;
    }

    public Cliente update(UUID id, ClientiDTO payload) {
        Cliente found = this.findById(id);
        Indirizzo sedeLegaleFound = this.indirizziService.findById(payload.sedeLegaleId());
        Indirizzo sedeOperativaFound = this.indirizziService.findById(payload.sedeOperativaId());
        Contatto contattoFound = contattoService.findById(payload.idContatto());
        found.setNomeCliente(payload.nomeCliente());
        found.setRagioneSociale(payload.ragioneSociale());
        found.setEmail(payload.email());
        found.setDataUltimoContatto(payload.dataUltimoContatto());
        found.setFatturatoAnnuale(payload.fatturatoAnnuale());
        found.setPec(payload.pec());
        found.setTelefono(payload.telefono());
        found.setSedeLegale(sedeLegaleFound);
        found.setSedeOperativa(sedeOperativaFound);
        found.setContatto(contattoFound);

        return this.clientiRepository.save(found);
    }

    public Cliente findById(UUID idCliente) {
        return this.clientiRepository.findById(idCliente).orElseThrow(() -> new NotFoundException(idCliente));
    }

    public List<Cliente> findAllByDataInserimentoAsc(LocalDate data) {
        return clientiRepository.findAllByDataInserimento(data);
    }

    public List<Cliente> findByFatturatoAnnuale(String fatturato) {
        return this.clientiRepository.findAllByFatturatoAnnuale(fatturato);
    }

    public Contatto getContattoByIdCliente(UUID id) {
        Cliente found = this.findById(id);
        return found.getContatto();
    }

    public Page<Cliente> filtraClienti(int page, int size, String orderBy, String sortCriteria, Double minFatturato, Double maxFatturato, LocalDate dataInserimento, LocalDate dataUltimoContatto, String partialName, String provincia) {

        Pageable pageable = PageRequest.of(page, size,
                sortCriteria.equals("desc") ? Sort.by(orderBy).descending() : Sort.by(orderBy));

        Specification<Cliente> spec = (root, query, cb) -> cb.conjunction();

        if (minFatturato != null) {
            spec = spec.and(ClientiSpecifications.fatturatoGreaterThanOrEqualTo(minFatturato));
        }

        if (maxFatturato != null) {
            spec = spec.and(ClientiSpecifications.fatturatoLessThanOrEqualTo(maxFatturato));
        }

        if (dataInserimento != null) {
            spec = spec.and(ClientiSpecifications.dataDiInserimentoEqualsTo(dataInserimento));
        }

        if (dataUltimoContatto != null) {
            spec = spec.and(ClientiSpecifications.dataUltimoContattoEqualsTo(dataUltimoContatto));
        }

        if (partialName != null) {
            spec = spec.and(ClientiSpecifications.partialNameEqualsTo(partialName));
        }

        if (provincia != null) {
            spec = spec.and(ClientiSpecifications.provinciaEquals(provincia));
        }
        return clientiRepository.findAll(spec, pageable);
    }
}
