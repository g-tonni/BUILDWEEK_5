package team2.BUILDWEEK_5.runners;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import team2.BUILDWEEK_5.services.CSVProvinceService;
import team2.BUILDWEEK_5.services.ProvinceService;

@Component
@Order(1)
public class CSVProvinceRunner implements CommandLineRunner {

    private final CSVProvinceService csvService;
    private final ProvinceService provinceService;

    @Autowired
    public CSVProvinceRunner(CSVProvinceService csvService, ProvinceService provinceService) {
        this.csvService = csvService;
        this.provinceService = provinceService;
    }

    @Override
    public void run(String... args) {
        if (provinceService.findAllProvince().isEmpty()) {
            csvService.saveAllProvince();
        }
    }
}
