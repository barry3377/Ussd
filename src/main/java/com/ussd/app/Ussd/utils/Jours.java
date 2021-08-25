package com.ussd.app.Ussd.utils;

import java.util.HashMap;
import java.util.Map;

public class Jours {
    public static String jours(int in) {
        Map<Integer, String> jour=new HashMap<Integer, String>();
        jour.put(1, "Lundi");
        jour.put(2, "Mardi");
        jour.put(3, "Mercredi");
        jour.put(4, "Jeudi");
        jour.put(5, "Vendredi");
        jour.put(6, "Samedi");
        jour.put(7, "Dimanche");

        return jour.get(in);
    }

}
