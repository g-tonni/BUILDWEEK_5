package team2.BUILDWEEK_5.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "indirizzi")
@Getter
@Setter
@NoArgsConstructor
@ToString(exclude = {"comune"})
public class Indirizzo {

    @Id
    @GeneratedValue
    @Column(name = "id_indirizzo", nullable = false, updatable = false)
    @Setter(AccessLevel.NONE)
    private UUID idIndirizzo;

    @Column(nullable = false)
    private String via;

    @Column(nullable = false)
    private String civico;

    @Column(nullable = false)
    private String localita;

    @Column(nullable = false)
    private String cap;

    // Relazione con Comune (anagrafica centralizzata)
    @ManyToOne(optional = false)
    @JoinColumn(name = "id_comune", nullable = false)
    private Comune comune;

    public Indirizzo(String via, String civico, String localita, String cap, Comune comune) {
        this.via = via;
        this.civico = civico;
        this.localita = localita;
        this.cap = cap;
        this.comune = comune;
    }
}
