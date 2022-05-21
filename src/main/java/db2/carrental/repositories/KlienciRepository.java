package db2.carrental.repositories;

import db2.carrental.entities.Klienci;
import org.springframework.data.jpa.repository.JpaRepository;

public interface KlienciRepository extends JpaRepository<Klienci, Integer> {

}