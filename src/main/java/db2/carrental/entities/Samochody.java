package db2.carrental.entities;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "samochody", indexes = {
        @Index(name = "ID_MODELU", columnList = "ID_MODELU"),
        @Index(name = "NR_REJESTRACYJNY", columnList = "NR_REJESTRACYJNY", unique = true),
        @Index(name = "ID_WYPOZYCZALNI", columnList = "ID_WYPOZYCZALNI")
})
public class Samochody {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_SAMOCHODU", nullable = false)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "ID_WYPOZYCZALNI")
    private Wypozyczalnie idWypozyczalni;

    @ManyToOne
    @JoinColumn(name = "ID_MODELU")
    private Modele idModelu;

    @Column(name = "NR_REJESTRACYJNY", length = 20)
    private String nrRejestracyjny;

    @Column(name = "DATA_PRODUKCJI")
    private LocalDate dataProdukcji;

    @Column(name = "DATA_REJESTRACJI")
    private LocalDate dataRejestracji;

    @Column(name = "DATA_PRZEGLADU")
    private LocalDate dataPrzegladu;

    @Column(name = "PRZEBIEG")
    private Integer przebieg;

    @OneToMany(mappedBy = "idSamochodu", cascade = CascadeType.ALL)
    private List<Wypozyczenia> wypozyczenia;

    @OneToMany(mappedBy = "idSamochodu", cascade = CascadeType.ALL)
    private List<LogWypozyczenia> logWypozyczenia;

    public Integer getPrzebieg() {
        return przebieg;
    }

    public void setPrzebieg(Integer przebieg) {
        this.przebieg = przebieg;
    }

    public LocalDate getDataPrzegladu() {
        return dataPrzegladu;
    }

    public void setDataPrzegladu(LocalDate dataPrzegladu) {
        this.dataPrzegladu = dataPrzegladu;
    }

    public LocalDate getDataRejestracji() {
        return dataRejestracji;
    }

    public void setDataRejestracji(LocalDate dataRejestracji) {
        this.dataRejestracji = dataRejestracji;
    }

    public LocalDate getDataProdukcji() {
        return dataProdukcji;
    }

    public void setDataProdukcji(LocalDate dataProdukcji) {
        this.dataProdukcji = dataProdukcji;
    }

    public String getNrRejestracyjny() {
        return nrRejestracyjny;
    }

    public void setNrRejestracyjny(String nrRejestracyjny) {
        this.nrRejestracyjny = nrRejestracyjny;
    }

    public Modele getIdModelu() {
        return idModelu;
    }

    public void setIdModelu(Modele idModelu) {
        this.idModelu = idModelu;
    }

    public Wypozyczalnie getIdWypozyczalni() {
        return idWypozyczalni;
    }

    public void setIdWypozyczalni(Wypozyczalnie idWypozyczalni) {
        this.idWypozyczalni = idWypozyczalni;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Samochody{" +
                "id=" + id +
                ", idWypozyczalni=" + idWypozyczalni +
                ", idModelu=" + idModelu +
                ", nrRejestracyjny='" + nrRejestracyjny + '\'' +
                ", dataProdukcji=" + dataProdukcji +
                ", dataRejestracji=" + dataRejestracji +
                ", dataPrzegladu=" + dataPrzegladu +
                ", przebieg=" + przebieg +
                '}';
    }
}