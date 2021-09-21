package com.ussd.app.Ussd.controller;

import com.ussd.app.Ussd.entities.Departement;

import com.ussd.app.Ussd.repository.DepartementRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
public class DepartementController {
    @Autowired
    private DepartementRepository departementRepository;
    @RequestMapping(value="/dep",method = RequestMethod.GET)
    public String listdep(Model model){
        List<Departement>services= (List<Departement>) departementRepository.findAll();
        model.addAttribute("services",services);
        model.addAttribute("service",new Departement());
        return "service/service.html";
    }
    @RequestMapping(value="/service" ,method = RequestMethod.GET)
    public String addservice(Model model){
      Departement departement=new Departement();
        model.addAttribute("departement",departement);
        return "service/addService.html";
    }
    @PostMapping(value="/valeur")
    public String saveDep(Model model,  Departement departement, BindingResult h ){
//        if(h.hasErrors()){
//
//            return "service/addService";
//        }
        departementRepository.save(departement);
        model.addAttribute("services",  departementRepository.findAll());


        return  "redirect:/dep";
    }

    @GetMapping(value="/editservice/{id}")
    public String showUpdateForm(@PathVariable("id") long id, Model model) {
        Departement departement = departementRepository.findById(id).get();
        model.addAttribute("departement", departement);
        return "service/editService";

    }
    @GetMapping(value="/deleteservice/{id}")
    public String deletedep(@PathVariable("id") long id, Model model) {
        departementRepository.deleteById(id);
        return  "redirect:/dep";
    }
}
