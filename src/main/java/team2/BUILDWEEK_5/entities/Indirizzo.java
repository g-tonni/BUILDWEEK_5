package team2.BUILDWEEK_5.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Table(
        name = "indirizzo",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"cliente_id", "tipo"})
        }
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {"cliente", "comune"})
public class Indirizzo {

    @Id
    @GeneratedValue
    @Column(name = "id_indirizzo", nullable = false, updatable = false)
    private UUID idIndirizzo;

    @Column(nullable = false)
    private String via;

    @Column(nullable = false)
    private String civico;

    @Column(name = "localita")
    private String localita;

    @Column(nullable = false)
    private String cap;

    // Tipo indirizzo (SEDE_LEGALE / SEDE_OPERATIVA)
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoIndirizzo tipo;

    // Relazione con Cliente
    @ManyToOne(optional = false)
    @JoinColumn(name = "cliente_id", nullable = false)
    private Cliente cliente;

    // Relazione con Comune (anagrafica centralizzata)
    @ManyToOne(optional = false)
    @JoinColumn(name = "comune_id", nullable = false)
    private Comune comune;
}
