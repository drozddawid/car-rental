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
@Table(name = "niedostepne_samochody")
public class NiedostepneSamochody implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "ID_SAM", nullable = false)
    private Integer idSam;

    @Column(name = "STATUS")
    private String status;

    @Column(name = "PLANOWANA_DATA_ZWROTU")
    private Date planowanaDataZwrotu;

    @Column(name = "MIASTO")
    private String miasto;

    @Column(name = "MARKA")
    private String marka;

    @Column(name = "MODEL")
    private String model;

    @Column(name = "DATA_PRODUKCJI")
    private Date dataProdukcji;

    @Column(name = "CENA")
    private Float cena;

    @Column(name = "SPALANIE")
    private Float spalanie;

    @Column(name = "L_MIEJSC")
    private Long lMiejsc;

    @Column(name = "L_DRZWI")
    private Long lDrzwi;

    @Column(name = "POJEMNOSC_ZBIORNIKA")
    private Long pojemnoscZbiornika;

    @Column(name = "TYP_PALIWA")
    private String typPaliwa;

    @Column(name = "POJEMNOSC_SILNIKA")
    private Long pojemnoscSilnika;

    @Column(name = "MOC")
    private Long moc;

    @Column(name = "RODZAJ_KAROSERII")
    private String rodzajKaroserii;

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

    public void setPlanowanaDataZwrotu(Date planowanaDataZwrotu) {
        this.planowanaDataZwrotu = planowanaDataZwrotu;
    }

    public Date getPlanowanaDataZwrotu() {
        return planowanaDataZwrotu;
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

    public void setSpalanie(Float spalanie) {
        this.spalanie = spalanie;
    }

    public Float getSpalanie() {
        return spalanie;
    }

    public void setLMiejsc(Long lMiejsc) {
        this.lMiejsc = lMiejsc;
    }

    public Long getLMiejsc() {
        return lMiejsc;
    }

    public void setLDrzwi(Long lDrzwi) {
        this.lDrzwi = lDrzwi;
    }

    public Long getLDrzwi() {
        return lDrzwi;
    }

    public void setPojemnoscZbiornika(Long pojemnoscZbiornika) {
        this.pojemnoscZbiornika = pojemnoscZbiornika;
    }

    public Long getPojemnoscZbiornika() {
        return pojemnoscZbiornika;
    }

    public void setTypPaliwa(String typPaliwa) {
        this.typPaliwa = typPaliwa;
    }

    public String getTypPaliwa() {
        return typPaliwa;
    }

    public void setPojemnoscSilnika(Long pojemnoscSilnika) {
        this.pojemnoscSilnika = pojemnoscSilnika;
    }

    public Long getPojemnoscSilnika() {
        return pojemnoscSilnika;
    }

    public void setMoc(Long moc) {
        this.moc = moc;
    }

    public Long getMoc() {
        return moc;
    }

    public void setRodzajKaroserii(String rodzajKaroserii) {
        this.rodzajKaroserii = rodzajKaroserii;
    }

    public String getRodzajKaroserii() {
        return rodzajKaroserii;
    }

    @Override
    public String toString() {
        return "NiedostepneSamochody{" +
                "idSam=" + idSam + '\'' +
                "status=" + status + '\'' +
                "planowanaDataZwrotu=" + planowanaDataZwrotu + '\'' +
                "miasto=" + miasto + '\'' +
                "marka=" + marka + '\'' +
                "model=" + model + '\'' +
                "dataProdukcji=" + dataProdukcji + '\'' +
                "cena=" + cena + '\'' +
                "spalanie=" + spalanie + '\'' +
                "lMiejsc=" + lMiejsc + '\'' +
                "lDrzwi=" + lDrzwi + '\'' +
                "pojemnoscZbiornika=" + pojemnoscZbiornika + '\'' +
                "typPaliwa=" + typPaliwa + '\'' +
                "pojemnoscSilnika=" + pojemnoscSilnika + '\'' +
                "moc=" + moc + '\'' +
                "rodzajKaroserii=" + rodzajKaroserii + '\'' +
                '}';
    }
}
