package team2.BUILDWEEK_5.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import java.util.UUID;

@Entity
public class Comune {
    @Id
    @GeneratedValue
    private UUID idComune;
}
