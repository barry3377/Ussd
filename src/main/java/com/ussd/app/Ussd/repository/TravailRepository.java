package com.ussd.app.Ussd.repository;

import com.ussd.app.Ussd.entities.Travail;
import com.ussd.app.Ussd.utils.HTravail;
import com.ussd.app.Ussd.utils.ITravail;
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

    @Query(value = "SELECT * FROM travail WHERE hopital_id = ?1 AND " +
            "departement_id = ?2 GROUP BY heure_id", nativeQuery = true)
    List<Travail> getByHopitalServiceAndJour(long id, long service);

    @Query(value = "SELECT departement_id as departementId FROM travail GROUP BY departement_id ", nativeQuery = true)
    List<ITravail> getGroup();

    @Query(value = "SELECT departement_id as departementId ,hopital_id as hopitalId FROM travail  WHERE travail.departement_id= ?1 GROUP BY hopital_id", nativeQuery = true)
    List<HTravail> getByService(int service);
}
