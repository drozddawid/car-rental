package db2.carrental.repositories;

import db2.carrental.entities.LogWypozyczenia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface LogWypozyczeniaRepository extends JpaRepository<LogWypozyczenia, Integer> {

}