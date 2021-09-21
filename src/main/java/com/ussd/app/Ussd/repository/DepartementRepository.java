package com.ussd.app.Ussd.repository;

import com.ussd.app.Ussd.entities.Departement;
import com.ussd.app.Ussd.entities.Hopital;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

public interface DepartementRepository extends JpaRepository<Departement,Long> {

    @Query(value = "SELECT * FROM departement WHERE departement_id = ?1 LIMIT 1", nativeQuery = true)
    Departement getById(Long id);
}
