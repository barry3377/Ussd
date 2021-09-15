package com.ussd.app.Ussd.repository;

import com.ussd.app.Ussd.entities.Hopital;
import com.ussd.app.Ussd.entities.Menu;
import com.ussd.app.Ussd.entities.Travail;
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

    @Modifying
    @Transactional
    @Query(value = "UPDATE hopital u SET u.count = ?1 WHERE hopital_id = ?2 ", nativeQuery = true)
    int updateHopital(int numero, int id);
}
