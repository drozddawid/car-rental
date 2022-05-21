package db2.carrental.repositories;

import db2.carrental.entities.DostepneSamochody;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DostepneSamochodyRepository extends JpaRepository<DostepneSamochody, Void>, JpaSpecificationExecutor<DostepneSamochody> {
    @Query(value = "select * from dostepne_samochody", nativeQuery = true)
    List<DostepneSamochody> findAll();
}