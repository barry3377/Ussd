package com.ussd.app.Ussd.controller;

import com.ussd.app.Ussd.entities.HopitalStat;
import com.ussd.app.Ussd.entities.RendezVous;
import com.ussd.app.Ussd.repository.RendezVousRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Collection;
import java.util.List;

@Controller
public class HomeController {
    @Autowired
    RendezVousRepository rendezVousRepository;
    @RequestMapping(value="/")
    public  String home(Model model){
        List<HopitalStat> vall=rendezVousRepository.getStatistique();
            vall.stream().forEach(hp -> System.out.println(hp.getHopital() + " - " + hp.getNombreTicket()));


    model.addAttribute("vall",vall);
        return "index.html";
    }
    @RequestMapping(value="/login")
    public  String login(){
        return "login.html";
    }

}
