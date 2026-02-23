package team2.BUILDWEEK_5.entities;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "clienti")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID idCliente;

    @Column
    @Enumerated(EnumType.STRING)
    private RagioneSociale ragioneSociale;

    @Column
    private String partitaIva;

    @Column
    private String email;

    @Column
    private LocalDate dataInserimento;

    @Column
    private LocalDate dataUltimoContatto;

    @Column
    private String fatturatoAnnuale;

    @Column
    private String pec;

    @Column
    private long telefono;

    @Column
    private String sedeLegale;

    @Column
    private String sedeOperativa;

    @OneToOne
    @JoinColumn(name = "id_contatto")
    private UUID idContatto;

    public Cliente() {
    }

    public Cliente(UUID idCliente,
                   RagioneSociale ragioneSociale,
                   String partitaIva,
                   String email,
                   LocalDate dataInserimento,
                   LocalDate dataUltimoContatto,
                   String fatturatoAnnuale,
                   String pec,
                   long telefono,
                   String sedeLegale,
                   String sedeOperativa,
                   String idContatto) {
        this.idCliente = idCliente;
        this.ragioneSociale = ragioneSociale;
        this.partitaIva = partitaIva;
        this.email = email;
        this.dataInserimento = dataInserimento;
        this.dataUltimoContatto = dataUltimoContatto;
        this.fatturatoAnnuale = fatturatoAnnuale;
        this.pec = pec;
        this.telefono = telefono;
        this.sedeLegale = sedeLegale;
        this.sedeOperativa = sedeOperativa;
        this.idContatto = idContatto;
    }

    public UUID getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(UUID idCliente) {
        this.idCliente = idCliente;
    }

    public RagioneSociale getRagioneSociale() {
        return ragioneSociale;
    }

    public void setRagioneSociale(RagioneSociale ragioneSociale) {
        this.ragioneSociale = ragioneSociale;
    }

    public String getPartitaIva() {
        return partitaIva;
    }

    public void setPartitaIva(String partitaIva) {
        this.partitaIva = partitaIva;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getDataInserimento() {
        return dataInserimento;
    }

    public void setDataInserimento(LocalDate dataInserimento) {
        this.dataInserimento = dataInserimento;
    }

    public LocalDate getDataUltimoContatto() {
        return dataUltimoContatto;
    }

    public void setDataUltimoContatto(LocalDate dataUltimoContatto) {
        this.dataUltimoContatto = dataUltimoContatto;
    }

    public String getFatturatoAnnuale() {
        return fatturatoAnnuale;
    }

    public void setFatturatoAnnuale(String fatturatoAnnuale) {
        this.fatturatoAnnuale = fatturatoAnnuale;
    }

    public String getPec() {
        return pec;
    }

    public void setPec(String pec) {
        this.pec = pec;
    }

    public long getTelefono() {
        return telefono;
    }

    public void setTelefono(long telefono) {
        this.telefono = telefono;
    }

    public String getSedeLegale() {
        return sedeLegale;
    }

    public void setSedeLegale(String sedeLegale) {
        this.sedeLegale = sedeLegale;
    }

    public String getSedeOperativa() {
        return sedeOperativa;
    }

    public void setSedeOperativa(String sedeOperativa) {
        this.sedeOperativa = sedeOperativa;
    }

    public String getIdContatto() {
        return idContatto;
    }

    public void setIdContatto(String idContatto) {
        this.idContatto = idContatto;
    }

    @Override
    public String toString() {
        return "Cliente{" +
                "idCliente=" + idCliente +
                ", ragioneSociale=" + ragioneSociale +
                ", partitaIva='" + partitaIva + '\'' +
                ", email='" + email + '\'' +
                ", dataInserimento=" + dataInserimento +
                ", dataUltimoContatto=" + dataUltimoContatto +
                ", fatturatoAnnuale='" + fatturatoAnnuale + '\'' +
                ", pec='" + pec + '\'' +
                ", telefono=" + telefono +
                ", sedeLegale='" + sedeLegale + '\'' +
                ", sedeOperativa='" + sedeOperativa + '\'' +
                ", idContatto='" + idContatto + '\'' +
                '}';
    }
}
