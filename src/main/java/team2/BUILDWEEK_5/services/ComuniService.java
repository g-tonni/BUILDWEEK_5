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

    @Autowired
    public ComuniService(ComuniRepository comuniRepository, ProvinceService provinceService) {
        this.comuniRepository = comuniRepository;
        this.provinceService = provinceService;
    }

    public void saveComune(String codiceProvincia, String progressivo, String nome, String nomeProvincia) {
        String nomeProvinciaDefinitivo;

        if (nomeProvincia.equals("Valle d'Aosta/Vallée d'Aoste")) {
            nomeProvinciaDefinitivo = "Aosta";
        } else if (nomeProvincia.equals("Monza e della Brianza")) {
            nomeProvinciaDefinitivo = "Monza-Brianza";
        } else if (nomeProvincia.equals("Bolzano/Bozen")) {
            nomeProvinciaDefinitivo = "Bolzano";
        } else if (nomeProvincia.equals("La Spezia")) {
            nomeProvinciaDefinitivo = "La-Spezia";
        } else if (nomeProvincia.equals("Ascoli Piceno")) {
            nomeProvinciaDefinitivo = "Ascoli-Piceno";
        } else if (nomeProvincia.equals("Reggio nell'Emilia")) {
            nomeProvinciaDefinitivo = "Reggio-Emilia";
        } else if (nomeProvincia.equals("Reggio Calabria")) {
            nomeProvinciaDefinitivo = "Reggio-Calabria";
        } else if (nomeProvincia.equals("Forlì-Cesena")) {
            nomeProvinciaDefinitivo = "Forli-Cesena";
        } else if (nomeProvincia.equals("Pesaro e Urbino")) {
            nomeProvinciaDefinitivo = "Pesaro-Urbino";
        } else if (nomeProvincia.equals("Vibo Valentia")) {
            nomeProvinciaDefinitivo = "Vibo-Valentia";
        } else {
            nomeProvinciaDefinitivo = nomeProvincia;
        }

        Provincia provincia = provinceService.findProvinciaByNome(nomeProvinciaDefinitivo);

        Comune newComune = new Comune(codiceProvincia, progressivo, nome, provincia);

        this.comuniRepository.save(newComune);

        System.out.println("Comune salvato" + newComune.getNomeComune());
    }

    public List<Comune> findAllComuni() {
        return comuniRepository.findAll();
    }


}
