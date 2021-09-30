package com.ussd.app.Ussd.repository;

import com.ussd.app.Ussd.entities.RendezVous;

import org.springframework.data.jpa.repository.JpaRepository;


public interface RendezVousRepository extends JpaRepository<RendezVous,Long> {
    RendezVous findByTicket(Long ticket);

//    @Modifying
//   @Query(value = "insert into rendezVous (hopital_id, departement_id, jour_id, heure_id, userTransaction) "
//           +
//         "values (?1, ?2, ?3, ?4, ?5)")
//    @Transactional
//    int RendezVous(int hopital, int service, int jour, int heure, Long user, String numero);

}
