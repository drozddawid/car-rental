package db2.carrental.repositories;

import db2.carrental.entities.Wypozyczalnie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface WypozyczalnieRepository extends JpaRepository<Wypozyczalnie, Integer> {

}