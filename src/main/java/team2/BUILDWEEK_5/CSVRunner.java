package team2.BUILDWEEK_5;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import team2.BUILDWEEK_5.services.CSVService;

@Component
@RequiredArgsConstructor
public class CSVRunner implements CommandLineRunner {

    private final CSVService csvService;

    @Override
    public void run(String... args) {
        csvService.leggiProvince();
    }
}
