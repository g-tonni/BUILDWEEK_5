package team2.BUILDWEEK_5.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.UUID;

@Entity
@Table(name = "Contatti")
public class Contatto {
    @Id
    @GeneratedValue
    private UUID id_contatto;
    private String email;
    private String nome;
    private String cognome;
    private String telefono;
    private String logo;

    public Contatto() {
    }

    ;

    public Contatto(String email, String nome, String cognome, String telefono) {
        this.email = email;
        this.nome = nome;
        this.cognome = cognome;
        this.telefono = telefono;
        this.logo = "https://ui-avatars.com/api/?name=" + this.nome + "+" + this.cognome;

    }

    public UUID getId() {
        return id_contatto;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    @Override
    public String toString() {
        return "Contatto{" +
                "id='" + id_contatto + '\'' +
                ", email='" + email + '\'' +
                ", nome='" + nome + '\'' +
                ", cognome='" + cognome + '\'' +
                ", telefono='" + telefono + '\'' +
                ", logo='" + logo + '\'' +
                '}';
    }
}
