package team2.BUILDWEEK_5.services;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.io.input.BOMInputStream;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;

@Service
public class CSVService {

    public void leggiProvince() {

        try {

            InputStream is = new ClassPathResource("provinceItaliane.csv").getInputStream();

            BOMInputStream bis = BOMInputStream.builder()
                    .setInputStream(is)
                    .get();

            Reader reader = new InputStreamReader(bis, StandardCharsets.UTF_8);

            CSVParser parser = CSVFormat.DEFAULT
                    .builder()
                    .setDelimiter(';')
                    .setHeader()
                    .setSkipHeaderRecord(true)
                    .setTrim(true)
                    .build()
                    .parse(reader);

            System.out.println(parser.getHeaderMap());
            for (CSVRecord record : parser) {

                String sigla = record.get("Sigla");
                String provincia = record.get("Provincia");
                String regione = record.get("Regione");

                System.out.println(sigla + " - " + provincia + " - " + regione);
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
