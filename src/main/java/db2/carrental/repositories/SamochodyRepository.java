package db2.carrental.repositories;

import db2.carrental.entities.Samochody;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

import static org.hibernate.loader.Loader.SELECT;

@Repository
public interface SamochodyRepository extends JpaRepository<Samochody, Integer> {
    List<Samochody> findBynrRejestracyjny(String nrRejestracyjny);
}
