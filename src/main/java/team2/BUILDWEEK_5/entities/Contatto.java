package team2.BUILDWEEK_5.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Getter
@Setter
@ToString
@Table(name = "contatti")
@NoArgsConstructor
public class Contatto {
    @Id
    @GeneratedValue
    @Setter(AccessLevel.NONE)
    private UUID idContatto;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private String cognome;

    @Column(nullable = false)
    private String telefono;

    private String logo;

    public Contatto(String email, String nome, String cognome, String telefono) {
        this.email = email;
        this.nome = nome;
        this.cognome = cognome;
        this.telefono = telefono;
        this.logo = "https://ui-avatars.com/api/?name=" + this.nome + "+" + this.cognome;

    }
}
