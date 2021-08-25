package com.ussd.app.Ussd.repository;

import com.ussd.app.Ussd.entities.RendezVous;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface RendezVousRepository extends JpaRepository<RendezVous,Long > {
}
