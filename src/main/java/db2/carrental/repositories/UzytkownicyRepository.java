package db2.carrental.repositories;

import db2.carrental.entities.Uzytkownicy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UzytkownicyRepository extends JpaRepository<Uzytkownicy, String> {
}
