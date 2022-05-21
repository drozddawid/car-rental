package db2.carrental.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "modele")
public class Modele implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "ID_MODELU", nullable = false)
    private String idModelu;

    @Column(name = "MARKA")
    private String MARKA;

    @Column(name = "MODEL_SAMOCHODU")
    private String modelSamochodu;

    @Column(name = "CENA_WYP")
    private Float cenaWyp;

    @Column(name = "SPALANIE")
    private Float SPALANIE;

    @Column(name = "MIEJSCA")
    private Long MIEJSCA;

    @Column(name = "DRZWI")
    private Long DRZWI;

    @Column(name = "POJEMNOSC_ZBIORNIKA")
    private Long pojemnoscZbiornika;

    @Column(name = "TYP_PALIWA")
    private String typPaliwa;

    @Column(name = "POJEMNOSC_SILNIKA")
    private Long pojemnoscSilnika;

    @Column(name = "MOC")
    private Long MOC;

    @Column(name = "RODZAJ_KAROSERII")
    private String rodzajKaroserii;

    @OneToMany(mappedBy = "idModelu", cascade = CascadeType.ALL)
    private List<Samochody> samochody;

    public void setIdModelu(String idModelu) {
        this.idModelu = idModelu;
    }

    public String getIdModelu() {
        return idModelu;
    }

    public void setMARKA(String MARKA) {
        this.MARKA = MARKA;
    }

    public String getMARKA() {
        return MARKA;
    }

    public void setModelSamochodu(String modelSamochodu) {
        this.modelSamochodu = modelSamochodu;
    }

    public String getModelSamochodu() {
        return modelSamochodu;
    }

    public void setCenaWyp(Float cenaWyp) {
        this.cenaWyp = cenaWyp;
    }

    public Float getCenaWyp() {
        return cenaWyp;
    }

    public void setSPALANIE(Float SPALANIE) {
        this.SPALANIE = SPALANIE;
    }

    public Float getSPALANIE() {
        return SPALANIE;
    }

    public void setMIEJSCA(Long MIEJSCA) {
        this.MIEJSCA = MIEJSCA;
    }

    public Long getMIEJSCA() {
        return MIEJSCA;
    }

    public void setDRZWI(Long DRZWI) {
        this.DRZWI = DRZWI;
    }

    public Long getDRZWI() {
        return DRZWI;
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

    public void setMOC(Long MOC) {
        this.MOC = MOC;
    }

    public Long getMOC() {
        return MOC;
    }

    public void setRodzajKaroserii(String rodzajKaroserii) {
        this.rodzajKaroserii = rodzajKaroserii;
    }

    public String getRodzajKaroserii() {
        return rodzajKaroserii;
    }

    @Override
    public String toString() {
        return "Modele{" +
                "idModelu=" + idModelu + '\'' +
                "MARKA=" + MARKA + '\'' +
                "modelSamochodu=" + modelSamochodu + '\'' +
                "cenaWyp=" + cenaWyp + '\'' +
                "SPALANIE=" + SPALANIE + '\'' +
                "MIEJSCA=" + MIEJSCA + '\'' +
                "DRZWI=" + DRZWI + '\'' +
                "pojemnoscZbiornika=" + pojemnoscZbiornika + '\'' +
                "typPaliwa=" + typPaliwa + '\'' +
                "pojemnoscSilnika=" + pojemnoscSilnika + '\'' +
                "MOC=" + MOC + '\'' +
                "rodzajKaroserii=" + rodzajKaroserii + '\'' +
                '}';
    }
}
