package com.ussd.app.Ussd.entities;



import ch.qos.logback.core.net.SyslogOutputStream;

import com.ussd.app.Ussd.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class MenuServiceImpl implements MenuService {
    @Autowired
    HopitalRepository hopitalRepository;
    @Autowired
    DepartementRepository departementRepository;
    @Autowired
    HeureRepository heureRepository;

    @Autowired
    UserTransactionRepository userTransactionRepository;

    @Autowired
    JoursRepository jourRepository;

    @Autowired
    RendezVousRepository rendezVousRepository;

    @Autowired
    TravailRepository travailRepository;
    public String getMenu(String level, String input, String telephone) {
        switch (level) {
            case "1*1":
                return this.getPrincipal();
            case "hopitaux":
                return this.getHopital(input);
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
                return  this.getSecondMenu(input);
            case "confirmation":
                return  this.getConfirmation();
//            case "rendezVous":
//                return  this.getRendezVours(input, telephone);
//

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
    public String getService(String  input) {
        String menu = "CON choisisez un service\n";

        List<Travail> travails = travailRepository.getGroup();



        if (travails.size() > 0) {
            for (int i=0; i < travails.size(); i++){
                menu += travails.get(i).getDepartement().getId()+". " + travails.get(i).getDepartement().getNom_service()+"\n";
            }
        }


        return menu;

//        String id = "0";
//        int l = input.split("\\*").length;
//
//        if(input.split("\\*").length == 2) {
//            id = input.split("\\*")[1];
//        }else if(input.split("\\*").length == 3) {
//            id = input.split("\\*")[2];
//        }
//
//        long count = Integer.parseInt(id);
//
//        try {
//
//            Hopital hopital = hopitalRepository.findByNumero(count);
//            List<Travail> travails = travailRepository.getByHopital(hopital.getId());
//            //List<Departement> departements = new ArrayList<>(hopital.getDepartements());
//
//            for (int i=0; i < travails.size(); i++){
//                menu += travails.get(i).getDepartement().getId()+". " + travails.get(i).getDepartement().getNom_service()+"\n";
//            }
//
//            return menu;
//
//        } catch (Exception e){
////
////          id = input.split("\\*")[2];
////          count = Integer.parseInt(id);
////            Hopital hopital = hopitalRepository.findByNumero(count);
////            Set<Departement> departements = hopital.getDepartements();
////
////            for(Departement value : departements) {
////                menu +=value.getId()+". "+ value.getNom_service()+"\n";
////            }
////            return menu;
//
//            return "END Eureur de saisie";
//        }


    }

    @Override
    public String  getHopital(String input){
        String menu = "CON Listes des Hopitaux\n";


        String id_hopital = "0";
        String id_service = "0";

        if(input.split("\\*").length == 2) {
            id_service = input.split("\\*")[1];
        }else if(input.split("\\*").length == 4) {
            id_service = input.split("\\*")[3];
        }

        List<Travail> travails = travailRepository.getByService(Integer.parseInt(id_service));


        for (int i=0; i < travails.size(); i++){
            menu += travails.get(i).getHopital().getNumero()+". " + travails.get(i).getHopital().getNom_hopital()+"\n";
        }

        return menu;
    }


    @Override
    public String getJours(String input) {

        String menu = "CON choisisez  le jour dont vous etes  dispo\n";

        List<Jour> jours = jourRepository.findAll();

        for (int i=0; i < jours.size(); i++){
            if (i > 4) continue;
            menu += jours.get(i).getId() +". " + jours.get(i).getNom_jour()+"\n";
        }
        return menu;

        //Integer userinput = Integer.parseInt(input.split("\\*")[0]);

//        String id_hopital = "0";
//        String id_service = "0";
//
//        if(input.split("\\*").length == 3) {
//            id_hopital = input.split("\\*")[1];
//            id_service = input.split("\\*")[2];
//        }else if(input.split("\\*").length == 4) {
//            id_hopital = input.split("\\*")[2];
//            id_service = input.split("\\*")[3];
//        }
//
//        String menu = "CON choisisez  le jour dont vous etes  dispo\n";
//
//        try {
//            Hopital hopital = hopitalRepository.findByNumero(Long.parseLong(id_hopital));
//            //Optional<Departement> departement = departementRepository.findById(Long.parseLong(id_service));
//            List<Travail> travails = travailRepository.getByHopitalAndService(hopital.getId(), Long.parseLong(id_service));
//
//            for (int i=0; i < travails.size(); i++){
//                menu += travails.get(i).getJours().getId() +". " + travails.get(i).getJours().getNom_jour()+"\n";
//            }
//            return menu;
//        } catch (Exception e) {
//            return "END Eureur de saisie";
//        }

        //            Long hopital = null;
        //            int ordre = Integer.parseInt(input.split("\\*")[2]);
        //
        //            if(input.split("\\*").length == 3){
        //                hopital = Long.parseLong(input.split("\\*")[1]);
        //            }else if(input.split("\\*").length == 4){
        //                hopital = Long.parseLong(input.split("\\*")[2]);
        //            }
        //
        //
        //            Hopital hop = hopitalRepository.findByNumero(hopital);
        //
        //            int size = hop.getDepartements().size();
        //
        //            if (ordre > size || ordre == 0){
        //                return "END Erreur de saisie";
        //            }



//        String menu = "CON choisisez  le jour dont vous etes  dispo\n";
//
//        menu += "1.Lundi \n";
//        menu += "2.Mardi\n";
//        menu += "3.Mercredi\n";
//        menu += "4.Jeudi \n";
//        menu += "5.Vendredi\n";
//        menu += "6.Samedi\n";
//        menu += "7.Dimanche\n";
//
//        return menu;


    }

    @Override
    public String getHeure(String input) {
        String menu = "CON Les heures disponibles\n";

        String id_hopital = "0";
        String id_service = "0";
        String id_jour = "0";

        if(input.split("\\*").length == 4) {
            id_hopital = input.split("\\*")[2];
            id_service = input.split("\\*")[1];
            id_jour = input.split("\\*")[3];
        }else if(input.split("\\*").length == 5) {
            id_hopital = input.split("\\*")[2];
            id_service = input.split("\\*")[3];
            id_jour = input.split("\\*")[4];
        }

        try {
            Hopital hopital = hopitalRepository.findByNumero(Long.parseLong(id_hopital));
            List<Travail> travails = travailRepository.getByHopitalServiceAndJour(hopital.getId(), Long.parseLong(id_service));

            for (int i=0; i < travails.size(); i++){
                menu += travails.get(i).getHeures().getId() +". " + travails.get(i).getHeures().getInterval_heur()+"\n";
            }
            return menu;
        } catch (Exception e) {
            return "END Eureur de saisie "+id_jour;
        }


        //        Integer jour = Integer.parseInt(input.split("\\*")[3]);
        //        if(jour > 7) {
        //            //return "END Erreur de saisie";
        //            menu += "Jour: "+jour+"\n";
        //        }

//            List<Heure> heures = heureRepository.findAll(Sort.by(Sort.Order.asc("numero")));
//
//            for (Heure value : heures) {
//                menu += value.getNumero() + ". " + value.getInterval_heur() + "\n";
//            }
//
//        return menu;

    }


    @Override
    public String getSecondMenu(String input){
        String menu = "CON Prolonger vos Rendez vous \n";
        menu += "1.pour vous \n";
        menu += "2.pour une autre personne \n";

        return menu;
    }

    @Override
    public String getCodeSecret(String input) {
        Long ordre = Long.parseLong(input.split("\\*")[4]);
    //    Heure heures = heureRepository.findByNumero(ordre);
       /* List<Heure> heure = heureRepository.findAll();
        int size = heure.size();
        if (ordre > size || ordre == 0){
            return "Erreur de saisie";
        }*/
        return "CON Entrer votre code secret";
    }
    @Override
    public String getConfirmation() {
        String menu = "CON  Confirmer votre paiement\n";
        menu += "1.Confirmer \n";
        menu += "2.Annuler \n";
        return menu ;
    }

//    public String getRendezVours(String input, String telephone) {
//
//        String ticket = "435353";
//
//        int id_hopital = 0;
//        Long id_service = 0L;
//        int id_jour = 0;
//        int id_heure = 0;
//
//        if(input.split("\\*").length == 7) {
//            id_hopital = Integer.parseInt(input.split("\\*")[2]);
//            id_service = Long.parseLong(input.split("\\*")[1]);
//            id_jour = Integer.parseInt(input.split("\\*")[3]);
//            id_heure = Integer.parseInt(input.split("\\*")[4]);
//        }else if(input.split("\\*").length == 5) {
////            id_hopital = input.split("\\*")[2];
////            id_service = input.split("\\*")[3];
////            id_jour = input.split("\\*")[4];
////            id_jour = input.split("\\*")[3];
//        }
//
//        //User
//        UserTransaction usert = new UserTransaction();
//        usert.setMsisdn(telephone);
//        UserTransaction user = userTransactionRepository.save(usert);
//
//        //Departement
//        Departement dep = departementRepository.getById(id_service);
//
//        //Hopital
//        Hopital hopital = hopitalRepository.findByNumero((long) id_hopital);
//
//        //Heure
//        Heure heure = heureRepository.findByNumero((long) id_heure);
//
//        //Jour
//        Jour jour = jourRepository.getById((long) id_jour);
//
//        RendezVous rendezVous = new RendezVous();
//        rendezVous.setHopital(hopital);
//        rendezVous.setHeures(heure);
//        rendezVous.setDepartement(dep);
//        rendezVous.setJours(jour);
//        rendezVous.setTicket(ticket);
//        rendezVous.setUserTransaction(user);
//
//        rendezVous =  rendezVousRepository.save(rendezVous);
//
//        if (rendezVous != null) {
//            OrangeSMS sms = new OrangeSMS();
//            String token = sms.getToken();
////            telephone = "+224"+telephone;
////           System.out.println(telephone);
//         String message =
//                    "Votre rendez-vous  a bien ete enregistre, votre numero d'enregistrement est "+ticket;
//            boolean b = sms.sendMessage(telephone, message, token);
//            System.out.println("Status: "+telephone);
//            System.out.println("Status: "+token);
//
//            return "END Votre rendez-vous  a bien ete enregistre, vous recevrer un sms de confirmation";
//        }else {
//            return "END Une erreur inconnu s'est produit";
//        }
//    }

}
