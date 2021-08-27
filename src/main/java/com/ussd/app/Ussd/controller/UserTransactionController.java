package com.ussd.app.Ussd.controller;

import com.ussd.app.Ussd.repository.*;
import com.ussd.app.Ussd.utils.Jours;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/sante/ussd")
public class UserTransactionController {
    @Autowired
    private UserTransactionRepository userTransactionRepository;
    @Autowired
    private HopitalRepository hopitalRepository;

    @Autowired
    private MenuRepository menuRepository;

    @Autowired
    TransactionRepository transactionRepository;
    @Autowired
    private MenuService menuService;



    @PostMapping

    public ResponseEntity<String> processUssd(@RequestParam(name = "sessionId") String sessionId,
                                              @RequestParam(name = "phoneNumber") String msisdn,
                                              @RequestParam(name = "text") String input) {
        if (input.isEmpty()) { // Nouvelle demande
            return getMenu("1*1", input);
        }else if(( input.equals("2")||
                (input.matches("^3\\*2")))){
            return getMenu("telephoneProche", input);
        }
        else if(input.matches("3") ){
            return getMenu("prolongerRDV", input);
        }
        else if((input.equals("1") || input.matches("^2\\*6[0-9]{8}"))){
            return getMenu("hopitaux", input);
        }else if((input.matches("^1\\*[0-9]+") || input.matches("^2\\*6[0-9]{8}\\*[0-9]+"))){
            return getMenu("services", input);
        }else if(input.matches("^1\\*[0-9]+\\*[0-9]+")
                || input.matches("^2\\*6[0-9]{8}\\*[0-9]+\\*[0-9]+")||
                input.matches("^3\\*1")
                        || input.matches("^3\\*2\\*6[0-9]{8}") ){
            return getMenu("jours", input);
        }else if((input.matches("^1\\*[0-9]+\\*[0-9]+\\*[1-7]")
                || input.matches("^2\\*6[0-9]{8}\\*[0-9]+\\*[0-9]+\\*[1-7]")||
                (input.matches("^3\\*1\\*[1-7]+") ||
                        (input.matches("^3\\*2\\*6[0-9]{8}\\*[0-9]+"))))){
            return getMenu("heures", input);
        }else if(( input.matches("^1\\*[0-9]+\\*[0-9]+\\*[1-7]\\*[0-9]+")
                ||input.matches("^2\\*6[0-9]{8}\\*[0-9]+\\*[0-9]+\\*[1-7]\\*[0-9]+")  )||
                (input.matches("^3\\*1\\*[1-7]+\\*[0-9]+") )){
            return getMenu("codeSecret", input);
        }
        else if(( input.matches("^1\\*[0-9]+\\*[0-9]+\\*[1-7]\\*[0-9]+\\*[0-9]{4,5}")
            ||input.matches("^2\\*6[0-9]{8}\\*[0-9]+\\*[0-9]+\\*[1-7]\\*[0-9]+\\*[0-9]{4,5}"))) {
            return getMenu("confirmation", input);

        }



    	return ResponseEntity.ok()
                .header("FreeFlow", "FB")
				.body("Mauvaise entree ou \nmenu en cours de developpement !!!");
}



    private ResponseEntity<String> getMenu(String level, String input) {
        // Recuperation du men


        return ResponseEntity.ok()
               // .header("FreeFlow", menu.getFreeFlow())
                .body(menuService.getMenu(level, input));
    }

}

