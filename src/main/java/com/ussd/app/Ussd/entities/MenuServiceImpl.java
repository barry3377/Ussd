package com.ussd.app.Ussd.entities;


import com.ussd.app.Ussd.repository.DepartementRepository;
import com.ussd.app.Ussd.repository.HeureRepository;
import com.ussd.app.Ussd.repository.HopitalRepository;
import com.ussd.app.Ussd.repository.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;

@Service
public class MenuServiceImpl implements MenuService {
    @Autowired
    HopitalRepository hopitalRepository;
    @Autowired
    DepartementRepository departementRepository;
    @Autowired
    HeureRepository heureRepository;
    public String getMenu(String level, String input) {
        switch (level) {
            case "1*1":
                return this.getPrincipal();
            case "hopitaux":
                return this.getHopital();
            case "services":
                return this.getService(input);
            case "jours":
                return this.getJours(input);
            case "heures":
                return this.getHeure(input);
            case "codeSecret":
                return this.getCodeSecret(input);
            case "telephoneProche":
                return  this.getTelephone();
            case "prolongerRDV":
                return  this.getSecondMenu();
            case "confirmation":
                return  this.getConfirmation();

        }

        return "bonjour";
    }

    @Override
    public String getTelephone() {
        return "CON Entrer le numero de telephone";
    }

    @Override
    public String getPrincipal() {
        String menu = "CON Prise de Rendez vous\n";
        menu += "1.pour vous \n";
        menu += "2.pour une autre personne \n";
        menu += "3.prolonger vos rendez vous\n";
        menu += "4.sortir\n";
        return menu;
    }

    @Override
    public String  getHopital(){
        String menu = "CON Listes des Hopitaux\n";

        List<Hopital> hopitals = hopitalRepository.findAll(Sort.by(Sort.Order.asc("numero")));


        for( Hopital value : hopitals) {
            menu +=value.getNumero()+". "+ value.getNom_hopital()+"\n";
        }
//        for (int i= 0; i < hopitals.size(); i++) {
//            menu += (i+1) + ". " + hopitals.get(i).getNom_hopital() + "\n";
//        }
        return menu;
    }

    @Override
    public String getService(String  input) {
        String menu = "CON choisisez un service\n";
        String id = "0";
        int l = input.split("\\*").length;

        if(input.split("\\*").length == 2) {
            id = input.split("\\*")[1];
        }else if(input.split("\\*").length == 3) {
            id = input.split("\\*")[2];
        }
        long count = Integer.parseInt(id);

        try {

            Hopital hopital = hopitalRepository.findByNumero(count);
            List<Departement> departements = new ArrayList<>(hopital.getDepartements());

            for (int i=0; i < departements.size(); i++){
                System.out.println( "size " +  departements.size());
                menu += (i + 1)  +". " + departements.get(i).getNom_service()+"\n";
            }

            return menu;

        } catch (Exception e){
//
//          id = input.split("\\*")[2];
//          count = Integer.parseInt(id);
//            Hopital hopital = hopitalRepository.findByNumero(count);
//            Set<Departement> departements = hopital.getDepartements();
//
//            for(Departement value : departements) {
//                menu +=value.getId()+". "+ value.getNom_service()+"\n";
//            }
//            return menu;

            return "END Eureur de saisie "+count+" "+l;
        }


    }

    @Override
    public String getJours(String input) {

       Long hopital = Long.parseLong(input.split("\\*")[1]);
        int ordre = Integer.parseInt(input.split("\\*")[2]);

        Hopital hop = hopitalRepository.findByNumero(hopital);

        int size = hop.getDepartements().size();

        if (ordre > size || ordre == 0){
            return "END Erreur de saisie";
        }

        String menu = "CON choisisez  le jour dont vous etes  dispo\n";

        menu += "1.Lundi \n";
        menu += "2.Mardi\n";
        menu += "3.Mercredi\n";
        menu += "4.Jeudi \n";
        menu += "5.Vendredi\n";
        menu += "6.Samedi\n";
        menu += "7.Dimanche\n";
        return menu;


    }

    @Override
    public String getHeure(String input) {
        String menu = "CON Les heures disponibles\n";

        Integer jour = Integer.parseInt(input.split("\\*")[3]);
        if(jour > 7) {
            //return "END Erreur de saisie";
            menu += "Jour: "+jour+"\n";
        }

        List<Heure> heures = heureRepository.findAll(Sort.by(Sort.Order.asc("numero")));

        for( Heure value : heures) {
            menu +=value.getNumero()+". "+ value.getInterval_heur()+"\n";
        }
        return menu;

    }

    @Override
    public String getSecondMenu(){
        String menu = "CON Prolonger vos Rendez vous\n";
        menu += "1.pour vous \n";
        menu += "2.pour une autre personne \n";

        return menu;
    }

    @Override
    public String getCodeSecret(String input) {
        Long ordre = Long.parseLong(input.split("\\*")[4]);
    //    Heure heures = heureRepository.findByNumero(ordre);
        List<Heure> heure = heureRepository.findAll();
        int size = heure.size();
        if (ordre > size || ordre == 0){
            return "Erreur de saisie";
        }
        return "CON Entrer votre code secret";
    }
    @Override
    public String getConfirmation() {
        String menu = "CON  Confirmer votre paiement\n";
        menu += "1.Confirmer \n";
        menu += "2.Annuler \n";
        return menu ;
    }
}
