package team2.BUILDWEEK_5.runners;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import team2.BUILDWEEK_5.payloads.RuoloDTO;
import team2.BUILDWEEK_5.payloads.StatoFattureDTO;
import team2.BUILDWEEK_5.payloads.UtenteDTO;
import team2.BUILDWEEK_5.services.CSVProvinceService;
import team2.BUILDWEEK_5.services.ProvinceService;
import team2.BUILDWEEK_5.services.StatoFattureService;
import team2.BUILDWEEK_5.services.UtentiService;

@Component
@Order(1)
public class CSVProvinceRunner implements CommandLineRunner {

    private final CSVProvinceService csvService;
    private final ProvinceService provinceService;
    private final UtentiService utentiService;
    private final StatoFattureService statoFattureService;
    private final String nomeAdmin;
    private final String cognomeAdmin;
    private final String emailAdmin;
    private final String passwordAdmin;

    @Autowired
    public CSVProvinceRunner(CSVProvinceService csvService, ProvinceService provinceService, UtentiService utentiService, StatoFattureService statoFattureService,
                             @Value("${NOME_ADMIN}") String nomeAdmin,
                             @Value("${COGNOME_ADMIN}") String cognomeAdmin,
                             @Value("${EMAIL_ADMIN}") String emailAdmin,
                             @Value("${PASSWORD_ADMIN}") String passwordAdmin) {
        this.csvService = csvService;
        this.provinceService = provinceService;
        this.utentiService = utentiService;
        this.statoFattureService = statoFattureService;
        this.nomeAdmin = nomeAdmin;
        this.cognomeAdmin = cognomeAdmin;
        this.emailAdmin = emailAdmin;
        this.passwordAdmin = passwordAdmin;
    }

    @Override
    public void run(String... args) {
        if (provinceService.findAllProvince().isEmpty()) {
            csvService.saveAllProvince();
        }
        if (utentiService.findAllRuoliList().isEmpty()) {
            utentiService.saveRuolo(new RuoloDTO("USER"));
            utentiService.saveRuolo(new RuoloDTO("ADMIN"));
        }
        if (utentiService.findAllUtentiList().isEmpty()) {
            utentiService.saveAdmin(new UtenteDTO(nomeAdmin, cognomeAdmin, emailAdmin, passwordAdmin));
        }
        if (statoFattureService.findAllStatiFatture().isEmpty()) {
            statoFattureService.saveStatoFattura(new StatoFattureDTO("Da pagare"));
            statoFattureService.saveStatoFattura(new StatoFattureDTO("Pagata"));
            statoFattureService.saveStatoFattura(new StatoFattureDTO("Scaduta"));
            statoFattureService.saveStatoFattura(new StatoFattureDTO("Contestata"));
        }
    }
}
