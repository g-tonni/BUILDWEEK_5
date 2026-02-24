package team2.BUILDWEEK_5.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import team2.BUILDWEEK_5.entities.Fattura;
import team2.BUILDWEEK_5.exceptions.NotFoundException;
import team2.BUILDWEEK_5.payloads.FattureDTO;
import team2.BUILDWEEK_5.repositories.FattureRepository;

import java.util.UUID;

@Service
@Slf4j
public class FattureService {

    private final FattureRepository fattureRepository;
    private final ClientiService clientiService;
    private final StatoFattureService statoFattureService;

    @Autowired
    public FattureService(FattureRepository fattureRepository, ClientiService clientiService, StatoFattureService statoFattureService) {
        this.fattureRepository = fattureRepository;
        this.clientiService = clientiService;
        this.statoFattureService = statoFattureService;
    }

    public Fattura saveFattura(FattureDTO payload) {
        Fattura newFattura = new Fattura(payload.importoFattura(), clientiService.findById(payload.idCliente()), statoFattureService.findStatoFatturaFindById("Da pagare"));

        fattureRepository.save(newFattura);

        log.info("Fattura per il cliente " + newFattura.getCliente().getIdCliente() + " emessa");

        return newFattura;
    }

    public Page<Fattura> findAllFatture(int page, int size, String orderBy, String sortCriteria) {
        if (size > 100 || size < 0) size = 10;
        if (page < 0) page = 0;
        Pageable pageable = PageRequest.of(page, size,
                sortCriteria.equals("desc") ? Sort.by(orderBy).descending() : Sort.by(orderBy));
        return this.fattureRepository.findAll(pageable);
    }

    public Fattura findById(UUID idFattura) {
        return this.fattureRepository.findById(idFattura)
                .orElseThrow(() -> new NotFoundException(idFattura));
    }

    public void findByIdAndDelete(UUID idFattura) {
        Fattura found = this.findById(idFattura);
        this.fattureRepository.delete(found);
    }
}
