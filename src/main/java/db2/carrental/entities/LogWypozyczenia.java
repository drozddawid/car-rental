package db2.carrental.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;

@Entity
@Table(name = "log_wypozyczenia")
public class LogWypozyczenia implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "LOG_ID", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer logId;

    @Column(name = "DATA_LOGU")
    private Date dataLogu;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_WYPOZYCZENIA")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Wypozyczenia idWypozyczenia;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_KLIENTA")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Klienci idKlienta;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_SAMOCHODU")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Samochody idSamochodu;

    @Column(name = "STAN_WYP")
    private String stanWyp;

    @Column(name = "DATA_WYP")
    private Date dataWyp;

    @Column(name = "DATA_ZWROTU")
    private Date dataZwrotu;

    public void setLogId(Integer logId) {
        this.logId = logId;
    }

    public Integer getLogId() {
        return logId;
    }

    public void setDataLogu(Date dataLogu) {
        this.dataLogu = dataLogu;
    }

    public Date getDataLogu() {
        return dataLogu;
    }

    public void setIdWypozyczenia(Wypozyczenia idWypozyczenia) {
        this.idWypozyczenia = idWypozyczenia;
    }

    public Wypozyczenia getIdWypozyczenia() {
        return idWypozyczenia;
    }

    public void setIdKlienta(Klienci idKlienta) {
        this.idKlienta = idKlienta;
    }

    public Klienci getIdKlienta() {
        return idKlienta;
    }

    public void setIdSamochodu(Samochody idSamochodu) {
        this.idSamochodu = idSamochodu;
    }

    public Samochody getIdSamochodu() {
        return idSamochodu;
    }

    public void setStanWyp(String stanWyp) {
        this.stanWyp = stanWyp;
    }

    public String getStanWyp() {
        return stanWyp;
    }

    public void setDataWyp(Date dataWyp) {
        this.dataWyp = dataWyp;
    }

    public Date getDataWyp() {
        return dataWyp;
    }

    public void setDataZwrotu(Date dataZwrotu) {
        this.dataZwrotu = dataZwrotu;
    }

    public Date getDataZwrotu() {
        return dataZwrotu;
    }

    @Override
    public String toString() {
        return "LogWypozyczenia{" +
                "logId=" + logId + '\'' +
                "dataLogu=" + dataLogu + '\'' +
                "idWypozyczenia=" + idWypozyczenia + '\'' +
                "idKlienta=" + idKlienta + '\'' +
                "idSamochodu=" + idSamochodu + '\'' +
                "stanWyp=" + stanWyp + '\'' +
                "dataWyp=" + dataWyp + '\'' +
                "dataZwrotu=" + dataZwrotu + '\'' +
                '}';
    }
}
