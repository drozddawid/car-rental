package db2.carrental.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.sql.Date;

/**
 * VIEW
 */
@Entity
@Table(name = "wypozyczone_samochody")
public class WypozyczoneSamochody implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "ID_SAM", nullable = false)
    private Integer idSam;

    @Column(name = "STATUS")
    private String status;

    @Column(name = "MIASTO")
    private String miasto;

    @Column(name = "MARKA")
    private String marka;

    @Column(name = "MODEL")
    private String model;

    @Column(name = "NR_REJESTRACYJNY")
    private String nrRejestracyjny;

    @Column(name = "DATA_PRODUKCJI")
    private Date dataProdukcji;

    @Column(name = "CENA")
    private Float cena;

    @Column(name = "ID_KLIENTA", nullable = false)
    private Long idKlienta;

    @Column(name = "IMIE")
    private String imie;

    @Column(name = "NAZWISKO")
    private String nazwisko;

    @Column(name = "DATA_WYPOZYCZENIA")
    private Date dataWypozyczenia;

    @Column(name = "DATA_ZWROTU")
    private Date dataZwrotu;

    public void setIdSam(Integer idSam) {
        this.idSam = idSam;
    }

    public Integer getIdSam() {
        return idSam;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setMiasto(String miasto) {
        this.miasto = miasto;
    }

    public String getMiasto() {
        return miasto;
    }

    public void setMarka(String marka) {
        this.marka = marka;
    }

    public String getMarka() {
        return marka;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getModel() {
        return model;
    }

    public void setNrRejestracyjny(String nrRejestracyjny) {
        this.nrRejestracyjny = nrRejestracyjny;
    }

    public String getNrRejestracyjny() {
        return nrRejestracyjny;
    }

    public void setDataProdukcji(Date dataProdukcji) {
        this.dataProdukcji = dataProdukcji;
    }

    public Date getDataProdukcji() {
        return dataProdukcji;
    }

    public void setCena(Float cena) {
        this.cena = cena;
    }

    public Float getCena() {
        return cena;
    }

    public void setIdKlienta(Long idKlienta) {
        this.idKlienta = idKlienta;
    }

    public Long getIdKlienta() {
        return idKlienta;
    }

    public void setImie(String imie) {
        this.imie = imie;
    }

    public String getImie() {
        return imie;
    }

    public void setNazwisko(String nazwisko) {
        this.nazwisko = nazwisko;
    }

    public String getNazwisko() {
        return nazwisko;
    }

    public void setDataWypozyczenia(Date dataWypozyczenia) {
        this.dataWypozyczenia = dataWypozyczenia;
    }

    public Date getDataWypozyczenia() {
        return dataWypozyczenia;
    }

    public void setDataZwrotu(Date dataZwrotu) {
        this.dataZwrotu = dataZwrotu;
    }

    public Date getDataZwrotu() {
        return dataZwrotu;
    }

    @Override
    public String toString() {
        return "WypozyczoneSamochody{" +
                "idSam=" + idSam + '\'' +
                "status=" + status + '\'' +
                "miasto=" + miasto + '\'' +
                "marka=" + marka + '\'' +
                "model=" + model + '\'' +
                "nrRejestracyjny=" + nrRejestracyjny + '\'' +
                "dataProdukcji=" + dataProdukcji + '\'' +
                "cena=" + cena + '\'' +
                "idKlienta=" + idKlienta + '\'' +
                "imie=" + imie + '\'' +
                "nazwisko=" + nazwisko + '\'' +
                "dataWypozyczenia=" + dataWypozyczenia + '\'' +
                "dataZwrotu=" + dataZwrotu + '\'' +
                '}';
    }
}
