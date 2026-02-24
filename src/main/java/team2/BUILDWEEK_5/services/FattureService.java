package team2.BUILDWEEK_5.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import team2.BUILDWEEK_5.entities.Fattura;
import team2.BUILDWEEK_5.repositories.FattureRepository;

import java.util.List;

@Service
@Slf4j
public class FattureService {

    private final FattureRepository fattureRepository;
    //private final ClienteService clienteService;

    @Autowired
    public FattureService(FattureRepository fattureRepository/*,ClienteService clienteService*/) {
        this.fattureRepository = fattureRepository;
    }

    public void saveFattura(int importoFattura /*,UUID idCliente*/) {
        Fattura newFattura = new Fattura(importoFattura/*,cliente*/);

        fattureRepository.save(newFattura);
    }

    public List<Fattura> findAllFatture() {
        if (fattureRepository.findAll().isEmpty()) {
            log.info("Non ci sono fatture nel DB");
        }
        return fattureRepository.findAll();
    }
}
