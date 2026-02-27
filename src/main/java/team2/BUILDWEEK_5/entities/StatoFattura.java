package team2.BUILDWEEK_5.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Getter
@ToString
@NoArgsConstructor
public class StatoFattura {

    @Id
    private String nomeStato;

    public StatoFattura(String nomeStato) {
        this.nomeStato = nomeStato;
    }
}
