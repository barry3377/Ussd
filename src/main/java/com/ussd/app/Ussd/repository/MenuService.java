package com.ussd.app.Ussd.repository;

import java.text.ParseException;

public interface MenuService {
    String getPrincipal();
    String getHopital(String input);
    String getService(String input);
    String getJours(String input);
    String getHeure(String input);
    String getMenu(String level, String input, String telephone) ;
    String getCodeSecret(String input);
    String getTelephone();
    String getSecondMenu(String input);
    String getConfirmation();
   String getRendezVours(String input, String telephone);
   String getVerification(String input);
    String checkTicket(String input);
//    String prolongementSuccess(String input,String telephone) throws ParseException;
}


