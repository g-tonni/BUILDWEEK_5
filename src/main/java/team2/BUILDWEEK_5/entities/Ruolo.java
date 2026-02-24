package team2.BUILDWEEK_5.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "ruoli")
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class Ruolo {
    @Id
    private String nomeRuolo;
}
