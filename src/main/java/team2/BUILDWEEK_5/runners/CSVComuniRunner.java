package team2.BUILDWEEK_5.runners;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import team2.BUILDWEEK_5.services.CSVComuniService;
import team2.BUILDWEEK_5.services.ComuniService;

@Component
@Order(2)
public class CSVComuniRunner implements CommandLineRunner {

    private final CSVComuniService csvService;
    private final ComuniService comuniService;

    @Autowired
    public CSVComuniRunner(CSVComuniService csvService, ComuniService comuniService) {
        this.csvService = csvService;
        this.comuniService = comuniService;
    }

    @Override
    public void run(String... args) {
        if (comuniService.findAllComuni().isEmpty()) {
            csvService.saveAllComuni();
        }
    }
}
