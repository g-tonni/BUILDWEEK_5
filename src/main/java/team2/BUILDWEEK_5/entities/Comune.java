package team2.BUILDWEEK_5.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.UUID;

@Entity
@Table(name = "comuni")
@Getter
@ToString
@NoArgsConstructor
public class Comune {

    @Id
    @GeneratedValue
    private UUID idComune;

    private String codiceProvincia;

    private String progressivoCoumne;

    private String nomeComune;

    @ManyToOne
    @JoinColumn
    private Provincia provincia;

    public Comune(String codiceProvincia, String progressivoCoumne, String nomeComune, Provincia provincia) {
        this.codiceProvincia = codiceProvincia;
        this.progressivoCoumne = progressivoCoumne;
        this.nomeComune = nomeComune;
        this.provincia = provincia;
    }
}
