package com.ussd.app.Ussd.controller;

import com.ussd.app.Ussd.entities.Hopital;
import com.ussd.app.Ussd.entities.HopitalStat;
import com.ussd.app.Ussd.entities.RendezVous;
import com.ussd.app.Ussd.repository.RendezVousRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Collection;
import java.util.List;

@Controller
public class RendezVousController {
    @Autowired
    RendezVousRepository rendezVousRepository;
    @GetMapping(value="/rendezVous")
    public  String listHopital(Model model){
        List<RendezVous> rendezVous=rendezVousRepository.findAll();
        model.addAttribute("rendezVous",rendezVous);
        return "rendezvous/rendezVous.html";
    }
    @GetMapping(value="")
    public  String Statistique(Model model){
        List<HopitalStat> vall=rendezVousRepository.getStatistique();
        return "index";
    }
}
