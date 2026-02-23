package team2.BUILDWEEK_5.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@NoArgsConstructor
@Table(name = "fatture")
@Getter
@Setter
@ToString
public class Fattura {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private Long idFattura;

    @Column(nullable = false)
    private LocalDate dataFattura;

    @Column(nullable = false)
    private int importoFattura;

    @Setter(AccessLevel.NONE)
    private String numeroFattura;

    @ManyToOne
    @JoinColumn(name = "id_cliente")
    @Setter(AccessLevel.NONE)
    private Cliente cliente;

    public Fattura(int importoFattura, String numeroFattura, Cliente cliente) {
        this.dataFattura = LocalDate.now();
        this.importoFattura = importoFattura;
        this.numeroFattura = idFattura + "/" + dataFattura.getYear();
        this.cliente = cliente;
    }
}
