package team2.BUILDWEEK_5.services;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.io.input.BOMInputStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;

@Service
public class CSVComuniService {

    private final ComuniService comuniService;

    @Autowired
    public CSVComuniService(ComuniService comuniService) {
        this.comuniService = comuniService;
    }

    public void saveAllComuni() {

        try {

            InputStream is = new ClassPathResource("comuniItaliani.csv").getInputStream();

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

            for (CSVRecord record : parser) {

                String codiceProvincia = record.get("Codice Provincia (Storico)(1)");
                String progressivo = record.get("Progressivo del Comune (2)");
                String nome = record.get("Denominazione in italiano");
                String provincia = record.get(3);

                comuniService.saveComune(codiceProvincia, progressivo, nome, provincia);
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
