package team2.BUILDWEEK_5.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import team2.BUILDWEEK_5.entities.Comune;
import team2.BUILDWEEK_5.entities.Provincia;
import team2.BUILDWEEK_5.repositories.ComuniRepository;

import java.util.List;

@Service
public class ComuniService {

    private final ComuniRepository comuniRepository;
    private final ProvinceService provinceService;
    int codiceSassari = 0;

    @Autowired
    public ComuniService(ComuniRepository comuniRepository, ProvinceService provinceService) {
        this.comuniRepository = comuniRepository;
        this.provinceService = provinceService;
    }


    public void saveComune(String codiceProvincia, String progressivo, String nome, String nomeProvincia) {
        String progressivoDefinitivo;
        if (codiceProvincia.equals("090")) {
            codiceSassari += 1;
            progressivoDefinitivo = "0" + codiceSassari;
        } else {
            progressivoDefinitivo = progressivo;
        }
        Provincia found = provinceService.findProvinciaByNome(nomeProvincia);

        Comune newComune = new Comune(codiceProvincia, progressivoDefinitivo, nome, found);

        this.comuniRepository.save(newComune);

        System.out.println("Comune salvato" + newComune.getNomeComune());
    }

    public List<Comune> findAllComuni() {
        return comuniRepository.findAll();
    }


}
