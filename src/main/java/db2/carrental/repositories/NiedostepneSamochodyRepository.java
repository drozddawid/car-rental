package db2.carrental.repositories;

import db2.carrental.entities.DostepneSamochody;
import db2.carrental.entities.NiedostepneSamochody;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface NiedostepneSamochodyRepository extends JpaRepository<NiedostepneSamochody, Void>, JpaSpecificationExecutor<NiedostepneSamochody> {
    @Query(value = "select * from niedostepne_samochody", nativeQuery = true)
    List<NiedostepneSamochody> findAll();
}