package db2.carrental.repositories;

import db2.carrental.entities.DostepneSamochody;
import db2.carrental.entities.WypozyczoneSamochody;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface WypozyczoneSamochodyRepository extends JpaRepository<WypozyczoneSamochody, Void>, JpaSpecificationExecutor<WypozyczoneSamochody> {
    @Query(value = "select * from wypozyczone_samochody", nativeQuery = true)
    List<WypozyczoneSamochody> findAll();
}