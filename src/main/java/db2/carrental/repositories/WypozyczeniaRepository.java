package db2.carrental.repositories;

import db2.carrental.entities.Wypozyczenia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface WypozyczeniaRepository extends JpaRepository<Wypozyczenia, Integer>{

}