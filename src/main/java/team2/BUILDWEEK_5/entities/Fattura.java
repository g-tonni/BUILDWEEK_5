package team2.BUILDWEEK_5.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@NoArgsConstructor
@Table(name = "fatture")
@Getter
@Setter
@ToString
@JsonIgnoreProperties({"cliente"})
public class Fattura {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Setter(AccessLevel.NONE)
    private UUID idFattura;

    @Column(nullable = false)
    @Setter(AccessLevel.NONE)
    private LocalDate dataFattura;

    @Column(nullable = false)
    @Setter(AccessLevel.NONE)
    private int importoFattura;

    @Setter(AccessLevel.NONE)
    private String numeroFattura;

    @ManyToOne
    @JoinColumn(name = "id_cliente")
    @Setter(AccessLevel.NONE)
    private Cliente cliente;

    @ManyToOne
    private StatoFattura statoFattura;

    public Fattura(int importoFattura, Cliente cliente, StatoFattura statoFattura, long numeroFattura) {
        this.statoFattura = statoFattura;
        this.dataFattura = LocalDate.now();
        this.importoFattura = importoFattura;
        this.numeroFattura = numeroFattura + "/" + dataFattura.getYear();
        this.cliente = cliente;
    }
}
