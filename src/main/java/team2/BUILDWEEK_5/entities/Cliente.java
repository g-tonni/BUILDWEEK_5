package team2.BUILDWEEK_5.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "clienti")
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Setter(AccessLevel.NONE)
    private UUID idCliente;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private RagioneSociale ragioneSociale;

    @Column(nullable = false)
    @Setter(AccessLevel.NONE)
    private String partitaIva;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    @Setter(AccessLevel.NONE)
    private LocalDate dataInserimento;

    @Column(nullable = false)
    private LocalDate dataUltimoContatto;

    @Column(nullable = false)
    private String fatturatoAnnuale;

    @Column(nullable = false)
    private String pec;

    @Column(nullable = false)
    private String telefono;

    @OneToOne
    @JoinColumn(name = "id_sede_legale")
    private Indirizzo sedeLegale;

    @OneToOne
    @JoinColumn(name = "id_sede_operativa")
    private Indirizzo sedeOperativa;

    @OneToOne
    @JoinColumn(name = "id_contatto")
    private Contatto contatto;

    public Cliente(
            RagioneSociale ragioneSociale,
            String partitaIva,
            String email,
            LocalDate dataInserimento,
            LocalDate dataUltimoContatto,
            String fatturatoAnnuale,
            String pec,
            String telefono,
            Indirizzo sedeLegale,
            Indirizzo sedeOperativa,
            Contatto contatto) {
        this.ragioneSociale = ragioneSociale;
        this.partitaIva = partitaIva;
        this.email = email;
        this.dataInserimento = dataInserimento;
        this.dataUltimoContatto = dataUltimoContatto;
        this.fatturatoAnnuale = fatturatoAnnuale;
        this.pec = pec;
        this.telefono = telefono;
        this.sedeLegale = sedeLegale;
        this.sedeOperativa = sedeOperativa;
        this.contatto = contatto;
    }

}
