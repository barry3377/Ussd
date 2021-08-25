package com.ussd.app.Ussd.repository;

import com.ussd.app.Ussd.entities.Heure;
import com.ussd.app.Ussd.entities.Hopital;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface HeureRepository  extends JpaRepository<Heure,Long> {

    List<Heure> findAll(Sort sort);
    Heure findByNumero(Long numero);

}
