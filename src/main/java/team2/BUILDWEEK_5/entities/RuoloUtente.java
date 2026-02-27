package team2.BUILDWEEK_5.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "ruoli_utenti")
@NoArgsConstructor
@ToString
@Getter
@Setter
public class RuoloUtente {
    @Id
    @GeneratedValue
    @Setter(AccessLevel.NONE)
    private UUID idRuoloUtente;

    @ManyToOne
    @JoinColumn(name = "utente_id", nullable = false)
    private Utente utente;

    @ManyToOne
    @JoinColumn(name = "ruolo", nullable = false)
    private Ruolo ruolo;

    public RuoloUtente(Utente utente, Ruolo ruolo) {
        this.utente = utente;
        this.ruolo = ruolo;
    }
}
