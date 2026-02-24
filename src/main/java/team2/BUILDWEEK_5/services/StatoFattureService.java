package team2.BUILDWEEK_5.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import team2.BUILDWEEK_5.entities.StatoFattura;
import team2.BUILDWEEK_5.exceptions.NotFoundException;
import team2.BUILDWEEK_5.payloads.StatoFattureDTO;
import team2.BUILDWEEK_5.repositories.StatoFattureRepository;

@Service
public class StatoFattureService {

    private final StatoFattureRepository statoFattureRepository;

    @Autowired
    public StatoFattureService(StatoFattureRepository statoFattureRepository) {
        this.statoFattureRepository = statoFattureRepository;
    }

    public StatoFattura saveStatoFattura(StatoFattureDTO payload) {
        StatoFattura newStatoFattura = new StatoFattura(payload.nomeStato());

        statoFattureRepository.save(newStatoFattura);

        return newStatoFattura;
    }

    public StatoFattura findStatoFatturaFindById(String nomeStato) {
        return statoFattureRepository.findById(nomeStato).orElseThrow(() -> new NotFoundException(nomeStato));
    }
}
