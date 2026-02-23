package team2.BUILDWEEK_5.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Getter
public class Provincia {

    @Id
    private String codiceProvincia;

    private String nome;

    private String regione;

    public Provincia(String codiceProvincia, String nome, String regione) {
        this.codiceProvincia = codiceProvincia;
        this.nome = nome;
        this.regione = regione;
    }
}
