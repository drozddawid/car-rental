package db2.carrental.entities;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import db2.carrental.repositories.WypozyczalnieRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "wypozyczalnie")
public class Wypozyczalnie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_WYPOZYCZALNI", nullable = false)
    private Integer id;

    @Column(name = "MIASTO")
    private String miasto;

    @Column(name = "ADRES")
    private String adres;

    @OneToMany(mappedBy = "idWypozyczalni", cascade = CascadeType.ALL)
    private List<Samochody> samochody;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }



    public String getMiasto() {
        return miasto;
    }

    public void setMiasto(String miasto) {
        this.miasto = miasto;
    }

    public String getAdres() {
        return adres;
    }

    public void setAdres(String adres) {
        this.adres = adres;
    }
}