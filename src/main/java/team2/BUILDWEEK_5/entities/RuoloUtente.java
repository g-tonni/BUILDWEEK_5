package team2.BUILDWEEK_5.entities;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "ruoli_utenti")
public class RuoloUtente {
    @Id
    @GeneratedValue
    private UUID idRuoloUtente;

    @ManyToOne
    @JoinColumn(name = "utente_id", nullable = false)
    private Utente utente;

    @ManyToOne
    @JoinColumn(name = "ruolo_id", nullable = false)
    private Ruolo ruolo;
}
