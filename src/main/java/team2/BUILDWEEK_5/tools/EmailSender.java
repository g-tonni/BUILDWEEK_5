package team2.BUILDWEEK_5.tools;


import kong.unirest.core.HttpResponse;
import kong.unirest.core.JsonNode;
import kong.unirest.core.Unirest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import team2.BUILDWEEK_5.entities.Utente;

@Component
public class EmailSender {
    private String domain;
    private String apiKey;

    public EmailSender(@Value("${mailgun.domain}") String domain,
                       @Value("${mailgun.api}") String apiKey) {
        this.domain = domain;
        this.apiKey = apiKey;
    }

    public void sendRegistration(Utente recipient) {

        HttpResponse<JsonNode> response = Unirest.post("https://api.mailgun.net/v3/" + this.domain + "/messages")
                .basicAuth("api", apiKey)
                .queryString("from", "tonni.giada.2108@gmail.com")
                .queryString("to", recipient.getEmail())
                .queryString("subject", "Benvenuto sulla piattaforma")
                .queryString("text", "Ciao " + recipient.getNome() + ", la tua registrazione è andata a buon fine")
                .asJson();

        System.out.println(response.getBody()); // Consiglio questo log per ispezionare la risposta e poter debuggare più
        // facilmente


    }

    public void sendBillingEmail(Utente recipient) {

    }

    public void sendInvoiceEmail(Utente recipient) {
    }
}
