package com.ussd.app.Ussd.repository;

import com.ussd.app.Ussd.entities.Hopital;
import com.ussd.app.Ussd.entities.HopitalStat;
import com.ussd.app.Ussd.entities.RendezVous;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Collection;
import java.util.List;


public interface RendezVousRepository extends JpaRepository<RendezVous,Long> {
    RendezVous findByTicket(Long ticket);

    @Query(value="SELECT h.nom_hopital as hopital, COUNT(rd.ticket) as nombreTicket FROM rendez_vous as rd INNER JOIN hopital as h ON(rd.hopital_id = h.hopital_id) " +
            "INNER JOIN departement d ON(d.departement_id = rd.departement_id) GROUP BY h.hopital_id", nativeQuery = true)
    List<HopitalStat> getStatistique();
}