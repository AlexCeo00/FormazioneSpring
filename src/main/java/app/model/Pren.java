package app.model;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Objects;


@Entity
@Table(name = "prenotazione")
public class Pren implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull
    @Column(name = "dettagli", nullable = false)
    private String dettagli;

    @NotNull
    @Column(name = "stato", nullable = false)
    private String stato;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "datap")
    private LocalDate datap;

    @ManyToOne(fetch = FetchType.EAGER) //da prenotazione a utente, 1 utente può averne solo una
    @JoinColumn(name = "idut")
    private User user;

    @OneToMany(mappedBy = "pren") //1 prenotazione puo avere + problemi
    private List<Probl> probl;

    @ManyToOne() // 1 prenotazione può averne solo un veicolo
    @JoinColumn(name = "idveic")
    private Auto auto;

    @ManyToOne() // 1 prenotazione può averne solo un veicolo
    @JoinColumn(name = "idsc")
    private Codicesc codicesc;

    public Pren(String dettagli, LocalDate datap) {
        this.dettagli = dettagli;
        this.datap = datap;
    }

    public Pren(String dettagli, LocalDate datap, User user) {
        this.dettagli = dettagli;
        this.datap = datap;
        this.user = user;
    }

    public Pren(String dettagli, String stato, LocalDate datap, User user) {
        this.dettagli = dettagli;
        this.stato = stato;
        this.datap = datap;
        this.user = user;
    }

    public Pren(String dettagli, String stato, LocalDate datap, User user, List<Probl> probl, Auto auto, Codicesc codicesc) {
        this.dettagli = dettagli;
        this.stato = stato;
        this.datap = datap;
        this.user = user;
        this.probl = probl;
        this.auto = auto;
        this.codicesc = codicesc;
    }

    public Pren() {
    }

    public Pren(String dettagli, String stato, LocalDate datap) {
        this.dettagli = dettagli;
        this.stato = stato;
        this.datap = datap;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDettagli() {
        return dettagli;
    }

    public void setDettagli(String dettagli) {
        this.dettagli = dettagli;
    }

    public String getStato() {
        return stato;
    }

    public void setStato(String stato) {
        this.stato = stato;
    }

    public LocalDate getDatap() {
        return datap;
    }

    public void setDatap(LocalDate datap) {
        this.datap = datap;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Probl> getProbl() {
        return probl;
    }

    public void setProbl(List<Probl> probl) {
        this.probl = probl;
    }

    public Auto getAuto() {
        return auto;
    }

    public void setAuto(Auto auto) {
        this.auto = auto;
    }

    public Codicesc getCodicesc() {
        return codicesc;
    }

    public void setCodicesc(Codicesc codicesc) {
        this.codicesc = codicesc;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pren pren = (Pren) o;
        return id == pren.id &&
                Objects.equals(dettagli, pren.dettagli) &&
                Objects.equals(stato, pren.stato) &&
                Objects.equals(datap, pren.datap) &&
                Objects.equals(user, pren.user) &&
                Objects.equals(probl, pren.probl) &&
                Objects.equals(auto, pren.auto) &&
                Objects.equals(codicesc, pren.codicesc);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, dettagli, stato, datap, user, probl, auto, codicesc);
    }
}
