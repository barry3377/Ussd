package com.ussd.app.Ussd.repository;

import com.ussd.app.Ussd.entities.Travail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface TravailRepository extends JpaRepository<Travail,Long > {

    @Query(value = "SELECT * FROM travail WHERE hopital_id = ?1 GROUP BY departement_id", nativeQuery = true)
    List<Travail> getByHopital(long id);

    @Query(value = "SELECT * FROM travail WHERE hopital_id = ?1 AND departement_id = ?2 GROUP BY jour_id", nativeQuery = true)
    List<Travail> getByHopitalAndService(long id, long service);

    @Query(value = "SELECT * FROM travail WHERE hopital_id = ?1 AND departement_id = ?2 GROUP BY heure_id", nativeQuery = true)
    List<Travail> getByHopitalServiceAndJour(long id, long service);

    @Query(value = "SELECT DISTINCT ON(departement_id), jour_id,heure_id,departement_id,hopital_id FROM travail  ", nativeQuery = true)
    List<Travail> getGroup();

    @Query(value = "SELECT * FROM travail WHERE travail.departement_id = ?1 GROUP BY hopital_id", nativeQuery = true)
    List<Travail> getByService(int service);
}
