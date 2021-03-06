package com.ussd.app.Ussd.repository;

import com.ussd.app.Ussd.entities.Departement;
import com.ussd.app.Ussd.entities.Hopital;
import com.ussd.app.Ussd.entities.Menu;
import com.ussd.app.Ussd.entities.Travail;
import com.ussd.app.Ussd.utils.HTravail;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface HopitalRepository extends JpaRepository<Hopital,Long> {

   // @Query(value = "SELECT * FROM hopital WHERE numero = ?1 LIMIT 1", nativeQuery = true)
    Hopital findByNumero(Long numero);

    List<Hopital> findAll(Sort sort);

    List<Hopital> findByDepartements(Departement departement);

    @Modifying
    @Transactional
    @Query(value = "UPDATE hopital u SET u.count = ?1 WHERE hopital_id = ?2 ", nativeQuery = true)
    int updateHopital(int numero, int id);

    @Query(value="SELECT * FROM hopital as h INNER JOIN hopital_service as hs ON(h.hopital_id = hs.hopital_id) " +
            "INNER JOIN departement as d INNER JOIN hopital_service as hss ON(hss.service_id = d.departement_id) WHERE d.departement_id= :service " +
            "GROUP BY h.hopital_id",nativeQuery = true)
    List<Hopital> getByService(@Param(value = "service")  int service);

}
