package com.ussd.app.Ussd.repository;

import com.ussd.app.Ussd.entities.Hopital;
import com.ussd.app.Ussd.entities.RendezVous;
import com.ussd.app.Ussd.entities.Travail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface RendezVousRepository extends JpaRepository<RendezVous,Long> {

//    @Modifying
//   @Query(value = "insert into rendezVous (hopital_id, departement_id, jour_id, heure_id, userTransaction) "
//           +
//         "values (?1, ?2, ?3, ?4, ?5)")
//    @Transactional
//    int RendezVous(int hopital, int service, int jour, int heure, Long user, String numero);
        RendezVous findByTicket(long rendezVous);
}
