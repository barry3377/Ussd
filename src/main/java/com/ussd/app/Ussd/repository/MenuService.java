package com.ussd.app.Ussd.repository;

public interface MenuService {
    String getPrincipal();
    String getHopital();
    String getService(String input);
    String getJours(String input);
    String getHeure(String input);
    String getMenu(String level, String input);
    String getCodeSecret(String input);
    String getTelephone();
    String getSecondMenu();
    String getConfirmation();
}
