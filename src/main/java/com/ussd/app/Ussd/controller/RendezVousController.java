package com.ussd.app.Ussd.controller;

import com.ussd.app.Ussd.entities.RendezVous;
import com.ussd.app.Ussd.repository.*;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
@RestController
public class RendezVousController {
    RendezVousRepository rendezVousRepository;
    HeureRepository heureRepository;
    JoursRepository jours;
    DepartementRepository departementRepository;
    HopitalRepository hopitalRepository;
    @PostMapping("/rendezvous")
    public RendezVous createRendezVous(@PathVariable(value = "heure") Long heure, Model model,
                                    @Valid @RequestBody RendezVous rendezVous) {
        return heureRepository.findById(heure).map(heures -> {
            rendezVous.setHeures(heures);
            return rendezVousRepository.save(rendezVous);
        }).orElseThrow(() -> new ResourceNotFoundException("heure" + heure + " not found"));
    }
}
