package db2.carrental.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "wypozyczenia")
public class Wypozyczenia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_WYPOZYCZENIA", nullable = false)
    private Integer id;

    @ManyToOne(targetEntity = Klienci.class)
    @JoinColumn(name = "ID_KLIENTA")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Klienci idKlienta;

    @ManyToOne(targetEntity = Samochody.class)
    @JoinColumn(name = "ID_SAMOCHODU")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Samochody idSamochodu;

    @Column(name = "STAN_WYP", length = 20)
    private String stanWyp;

    @Column(name = "DATA_WYP")
    private LocalDate dataWyp;

    @Column(name = "DATA_ZWROTU")
    private LocalDate dataZwrotu;

    @OneToMany(mappedBy = "idSamochodu", cascade = CascadeType.ALL)
    private List<LogWypozyczenia> logWypozyczeniaList;

    public LocalDate getDataZwrotu() {
        return dataZwrotu;
    }

    public void setDataZwrotu(LocalDate dataZwrotu) {
        this.dataZwrotu = dataZwrotu;
    }

    public LocalDate getDataWyp() {
        return dataWyp;
    }

    public void setDataWyp(LocalDate dataWyp) {
        this.dataWyp = dataWyp;
    }

    public String getStanWyp() {
        return stanWyp;
    }

    public void setStanWyp(String stanWyp) {
        this.stanWyp = stanWyp;
    }

    public Samochody getIdSamochodu() {
        return idSamochodu;
    }

    public void setIdSamochodu(Samochody idSamochodu) {
        this.idSamochodu = idSamochodu;
    }

    public Klienci getIdKlienta() {
        return idKlienta;
    }

    public void setIdKlienta(Klienci idKlienta) {
        this.idKlienta = idKlienta;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}