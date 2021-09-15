package com.ussd.app.Ussd.controller;

import com.ussd.app.Ussd.entities.Departement;
import com.ussd.app.Ussd.entities.Hopital;

import com.ussd.app.Ussd.repository.DepartementRepository;
import com.ussd.app.Ussd.repository.HopitalRepository;
import com.ussd.app.Ussd.repository.JoursRepository;
import org.dom4j.rule.Mode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.querydsl.QSort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class HopitalController {
    @Autowired
    private HopitalRepository hopitalRepository;
    @Autowired
      private DepartementRepository departementRepository;
    @Autowired
    private JoursRepository joursRepository;
       @GetMapping(value="/hopitals")
    public  String listHopital(Model model){
           List<Hopital> hopitals=hopitalRepository.findAll();
           model.addAttribute("hopitals",hopitals);
           return "Hopital/hopital";
       }


       @GetMapping(value="/new")
    public  String addHopital(Model model){

           model.addAttribute("depart",departementRepository.findAll());
           model.addAttribute("jours",joursRepository.findAll());
           Hopital hopital=new Hopital();
           model.addAttribute("hopital ",hopital);
           return "Hopital/add.html";
       }

    @PostMapping(value="/save")
    public  String addHopi(Hopital hopital, Model model){
    hopital.setNumero(hopitalRepository.count()+1);
    hopitalRepository.save(hopital);
    System.out.println("+++++++++++++ " + hopital.getDepartements().size());
    List<Hopital> hopitals = hopitalRepository.findAll();

    /*System.out.println("voici la taille du tab" +hopitals.size());
        for (int i = 1; i <= hopitals.size(); i++){
            hopitalRepository.updateHopital(i, (int) hopitals.get(i).getId());
        }*/

        model.addAttribute("hopitals",     hopitalRepository.findAll());
        return  "redirect:/hopitals";
    }
    @GetMapping(value="/edithopital/{id}")
    public String showUpdateForm(@PathVariable("id") long id, Model model) {
        Hopital hopital = hopitalRepository.findById(id).get();
        model.addAttribute("hopital",hopital);
        model.addAttribute("depart",departementRepository.findAll());
        return "Hopital/editHopital";

    }
    @GetMapping(value="/deleteHopital/{id}")
    public String deleteHopital(@PathVariable("id") long id, Model model) {
        hopitalRepository.deleteById(id);
        Long i = 0l;
        List<Hopital> hopitals = hopitalRepository
                .findAll()
                .stream()
                .sorted(Comparator.comparingLong(Hopital::getId))
                .collect(Collectors.toList());
        for(Hopital h: hopitals) {
            h.setNumero(++i);
            hopitalRepository.save(h);
        }
        // hopitalRepository.saveAll(hopitals);
        return  "redirect:/hopitals";
    }
}
