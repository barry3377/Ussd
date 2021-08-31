package com.ussd.app.Ussd.controller;

import com.ussd.app.Ussd.entities.Hopital;
import com.ussd.app.Ussd.entities.Travail;
import com.ussd.app.Ussd.repository.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class TravailController {
    TravailRepository travailRepository;
    HeureRepository heureRepository;
    JoursRepository joursRepository;
    DepartementRepository departementRepository;
    HopitalRepository hopitalRepository;
    @GetMapping(value="/travail")
    public  String addHopital(Model model){

        model.addAttribute("hopitals",hopitalRepository.findAll());
        model.addAttribute("depart",departementRepository.findAll());

        model.addAttribute("jours",joursRepository.findAll());

        model.addAttribute("heures",heureRepository.findAll());
        Travail travail =new Travail();
        model.addAttribute(" travail ", travail);
        return "travail/new";
    }

    @PostMapping(value="/saveTravail")
    public  String addHopi(Travail travail, Model model){
      // hopital.setNumero(hopitalRepository.count()+1);
        travailRepository.save(travail);

        List<Travail> travails = travailRepository.findAll();



        model.addAttribute("travails",     hopitalRepository.findAll());
        return  "redirect:/travails";
    }
}
