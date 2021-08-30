package com.ussd.app.Ussd.controller;


import com.ussd.app.Ussd.entities.Departement;
import com.ussd.app.Ussd.entities.Heure;

import com.ussd.app.Ussd.entities.Hopital;
import com.ussd.app.Ussd.repository.HeureRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Controller
public class HeureController {
    @Autowired
    private HeureRepository heureRepository;


    @GetMapping(value="/list")
    public String listHeure(Model model){
        List<Heure>heures= (List<Heure>) heureRepository.findAll();
        model.addAttribute("heures",heures);
        model.addAttribute("heure",new Heure());
        return "heure/heures";
    }
    @RequestMapping(value="/heure" ,method = RequestMethod.GET)
    public String addHeure(Model model){

        Heure heure=new Heure();
        model.addAttribute("heure",heure);
        return "heure/heure";
    }
    @RequestMapping(value="/saveHeure", method=RequestMethod.POST)
    public String saverTIME(Model model, @Valid Heure heure, BindingResult h ){
        if(h.hasErrors()){

            return "heure/heure";
        }
        heure.setNumero(heureRepository.count()+1);
       heureRepository.save(heure);

        model.addAttribute("heures",  heureRepository.findAll());


        return  "redirect:/list";
    }
    @GetMapping(value="/edit/{id}")
    public String heureEdite(@PathVariable("id") long id, Model model) {
        Heure heure = heureRepository.findById(id).get();
        model.addAttribute("heure", heure);
        return "heure/editHeure";

    }
    @GetMapping(value="/delete/{id}")
    public String deleteheure(@PathVariable("id") long id, Model model) {
       heureRepository.deleteById(id);
        Long i = 0l;
        List<Heure> heures = heureRepository
                .findAll()
                .stream()
                .sorted(Comparator.comparingLong(Heure::getId))
                .collect(Collectors.toList());
        for(Heure h: heures) {
            h.setNumero(++i);
            heureRepository.save(h);
        }
        return  "redirect:/list";
    }


}
