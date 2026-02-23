package team2.BUILDWEEK_5.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import team2.BUILDWEEK_5.entities.Provincia;
import team2.BUILDWEEK_5.exceptions.NotFoundException;
import team2.BUILDWEEK_5.repositories.ProvinceRepository;

import java.util.List;

@Service
public class ProvinceService {

    private final ProvinceRepository provinceRepository;


    @Autowired
    public ProvinceService(ProvinceRepository provinceRepository) {
        this.provinceRepository = provinceRepository;
    }

    public void saveProvincia(String codiceProvincia, String nome, String regione) {
        String nomeDefinitivo;
        String codiceProvinciaDefinitivo;

        if (nome.equals("Verbania")) {
            nomeDefinitivo = "Verbano-Cusio-Ossola";
            codiceProvinciaDefinitivo = codiceProvincia;
        } else if (codiceProvincia.equals("CI")) {
            nomeDefinitivo = "Sud Sardegna";
            codiceProvinciaDefinitivo = "SU";
        } else {
            nomeDefinitivo = nome;
            codiceProvinciaDefinitivo = codiceProvincia;
        }
        Provincia newProvincia = new Provincia(codiceProvinciaDefinitivo, nomeDefinitivo, regione);

        this.provinceRepository.save(newProvincia);

        System.out.println(codiceProvincia + " " + nomeDefinitivo + " " + regione);
    }

    public List<Provincia> findAllProvince() {
        return provinceRepository.findAll();
    }

    public Provincia findProvinciaByNome(String nome) {
        return provinceRepository.findByNome(nome).orElseThrow(() -> new NotFoundException(nome));
    }
}
