package team2.BUILDWEEK_5.entities;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Table(name = "utenti")
@NoArgsConstructor
public class Utente {

    @Id
    @GeneratedValue
    @Column(name = "utente_id")
    private UUID utenteId;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private String cognome;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String avatar;

    @Column(nullable = false)
    private Ruolo ruolo;

    public Utente(String nome, String cognome, String email, String password, String avatar) {
        this.nome = nome;
        this.cognome = cognome;
        this.email = email;
        this.password = password;
        this.avatar = "https://ui-avatars.com/api/?name=" + nome + "+" + cognome;
        this.ruolo = Ruolo.UTENTE;
    }

    public UUID getUtenteId() {
        return utenteId;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Ruolo getRuolo() {
        return ruolo;
    }

    public void setRuolo(Ruolo ruolo) {
        this.ruolo = ruolo;
    }

    @Override
    public String toString() {
        return "Utente{" +
                "utenteId=" + utenteId +
                ", nome='" + nome + '\'' +
                ", cognome='" + cognome + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", avatar='" + avatar + '\'' +
                ", ruolo=" + ruolo +
                '}';
    }
}
