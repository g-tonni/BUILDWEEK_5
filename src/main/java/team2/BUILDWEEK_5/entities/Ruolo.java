package team2.BUILDWEEK_5.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Table(name = "ruoli")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Ruolo {
    @Id
    private String nomeRuolo;
}
