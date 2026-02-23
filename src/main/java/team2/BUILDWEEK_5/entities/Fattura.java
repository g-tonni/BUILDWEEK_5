package team2.BUILDWEEK_5.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

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
    private Long idFattura;

    @Column(nullable = false)
    private LocalDate dataFattura;

    @Column(nullable = false)
    private int importoFattura;

    private String numeroFattura;

//    @ManyToOne
//    @JoinColumn(name = "id_cliente")
    //private Cliente cliente;

    public Fattura(LocalDate dataFattura, int importoFattura, String numeroFattura/*,Cliente cliente*/) {
        this.dataFattura = dataFattura;
        this.importoFattura = importoFattura;
        this.numeroFattura = idFattura + "/" + dataFattura.getYear();
        //this.cliente=cliente;
    }
}
