package com.ussd.app.Ussd.service;

import com.ussd.app.Ussd.entities.Heure;
import com.ussd.app.Ussd.repository.HeureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class service {
    @Autowired
    HeureRepository heureRepository;
    public void deleteHeure(Long id)
    {
        Optional<Heure> heure =heureRepository .findById(id);

        if(heure.isPresent())
        {
            heureRepository.deleteById(id);
        } else {
          System.out.println("eureur") ;
    }
}}

