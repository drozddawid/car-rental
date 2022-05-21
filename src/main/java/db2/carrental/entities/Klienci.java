package db2.carrental.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "klienci")
public class Klienci implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "ID_KLIENTA", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idKlienta;

    @Column(name = "IMIE")
    private String IMIE;

    @Column(name = "NAZWISKO")
    private String NAZWISKO;

    @Column(name = "PESEL")
    private String PESEL;

    @Column(name = "NR_TEL")
    private String nrTel;

    @Column(name = "EMAIL")
    private String EMAIL;

    @OneToMany(mappedBy = "idKlienta", cascade = CascadeType.ALL)
    private List<Wypozyczenia> wypozyczenia;

    @OneToMany(mappedBy = "idKlienta", cascade = CascadeType.ALL)
    private List<LogWypozyczenia> logWypozyczenia;

    public void setIdKlienta(Integer idKlienta) {
        this.idKlienta = idKlienta;
    }

    public Integer getIdKlienta() {
        return idKlienta;
    }

    public void setIMIE(String IMIE) {
        this.IMIE = IMIE;
    }

    public String getIMIE() {
        return IMIE;
    }

    public void setNAZWISKO(String NAZWISKO) {
        this.NAZWISKO = NAZWISKO;
    }

    public String getNAZWISKO() {
        return NAZWISKO;
    }

    public void setPESEL(String PESEL) {
        this.PESEL = PESEL;
    }

    public String getPESEL() {
        return PESEL;
    }

    public void setNrTel(String nrTel) {
        this.nrTel = nrTel;
    }

    public String getNrTel() {
        return nrTel;
    }

    public void setEMAIL(String EMAIL) {
        this.EMAIL = EMAIL;
    }

    public String getEMAIL() {
        return EMAIL;
    }

    @Override
    public String toString() {
        return "Klienci{" +
                "idKlienta=" + idKlienta + '\'' +
                "IMIE=" + IMIE + '\'' +
                "NAZWISKO=" + NAZWISKO + '\'' +
                "PESEL=" + PESEL + '\'' +
                "nrTel=" + nrTel + '\'' +
                "EMAIL=" + EMAIL + '\'' +
                '}';
    }
}
