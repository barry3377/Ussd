package com.ussd.app.Ussd.controller;

import com.ussd.app.Ussd.entities.Heure;
import com.ussd.app.Ussd.entities.Hopital;
import com.ussd.app.Ussd.entities.Travail;
import com.ussd.app.Ussd.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class TravailController {
    @Autowired
    TravailRepository travailRepository;
    @Autowired
    HeureRepository heureRepository;
    @Autowired
    JoursRepository joursRepository;
    @Autowired
    DepartementRepository departementRepository;
    @Autowired
    HopitalRepository hopitalRepository;
    private HttpServletRequest request;

    @GetMapping(value="/travails")
    public  String listHopital(Model model){
        List<Travail> travails=travailRepository.findAll();
        model.addAttribute("travails",travails);
        return "Travail/travail";
    }
    @GetMapping(value="/travail")
    public  String addTravaill(Model model){

        model.addAttribute("hopitals",hopitalRepository.findAll());
        model.addAttribute("depart",departementRepository.findAll());

        model.addAttribute("jours",joursRepository.findAll());

        model.addAttribute("heures",heureRepository.findAll());
        Travail travail =new Travail();
        model.addAttribute(" travail ", travail);
        return "Travail/new";
    }

    @PostMapping(value="/saveTravail")
    public  String addTravailH(HttpServletRequest request, Travail travail, Model model){

        travailRepository.save(travail);

        List<Travail> travails = travailRepository.findAll();

        model.addAttribute("travails",     hopitalRepository.findAll());
        return  "redirect:/travails";
    }
}
