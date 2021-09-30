package com.ussd.app.Ussd.entities;



import ch.qos.logback.core.net.SyslogOutputStream;

import com.ussd.app.Ussd.OrangeSMS.OrangeSMS;
import com.ussd.app.Ussd.repository.*;
import com.ussd.app.Ussd.utils.HTravail;
import com.ussd.app.Ussd.utils.ITravail;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

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
    OrangeSMS orangeSMS;

    @Autowired
    TravailRepository travailRepository;
    public String getMenu(String level, String input, String telephone)  {
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
            case "verification":
                return  this.getVerification(input);
            case "ticket":
                return  this.checkTicket(input);
            case "confirmation":
                return  this.getConfirmation();
           // case "validation":
              //  return  this.prolongementSuccess(input,telephone);
               case "rendezVous":
             return  this.getRendezVours(input, telephone);


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
        List<Departement> departements= departementRepository.findAll();

//        System.out.println("Size : " + travails.size());


        if (departements.size() > 0) {
            for( Departement departement: departements){
                Long depart_id = departement.getId();
                departement = departementRepository.findById(depart_id).get();
                menu += departement.getId() +". " + departement.getNom_service()+"\n";
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
        }else if(input.split("\\*").length == 3) {
            id_service = input.split("\\*")[2];
        }

        Departement departement = departementRepository.findById(Long.parseLong(id_service)).get();
//      List<Hopital>hopitals =hopitalRepository.getByService(Integer.parseInt(id_service));
        List<Hopital>hopitals =hopitalRepository.findByDepartements(departement);


            for( Hopital hopital: hopitals){
                Long hopital_id = hopital.getId();
                hopital= hopitalRepository.findById(hopital_id).get();
                menu += hopital.getId() +". " + hopital.getNom_hopital()+"\n";
            }

        return menu;
    }


    @Override
    public String getJours(String input) {

//
//        List<Jour> jours = jourRepository.findAll();
//
//        for (int i=0; i < jours.size(); i++){
//            if (i > 4) continue;
//            menu += jours.get(i).getId() +". " + jours.get(i).getNom_jour()+"\n";
//        }

      return  " CON saisisez votre date et excepter les samedis et les dimanches \n";

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


        if(input.split("\\*").length == 4) {
            id_hopital = input.split("\\*")[2];
            id_service = input.split("\\*")[1];

        }else if(input.split("\\*").length == 5) {
            id_hopital = input.split("\\*")[2];
            id_service = input.split("\\*")[3];

        }
//
//        try {
//            Hopital hopital = hopitalRepository.findByNumero(Long.parseLong(id_hopital));
//            List<Travail> travails = travailRepository.getByHopitalServiceAndJour(hopital.getId(), Long.parseLong(id_service));
//
//            for (int i=0; i < travails.size(); i++){
//                menu += travails.get(i).getHeures().getId() +". " + travails.get(i).getHeures().getInterval_heur()+"\n";
//            }
//            return menu;
//        } catch (Exception e) {
//            return "END Eureur de saisie "+id_jour;
//        }


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
        List<Heure> heures= heureRepository.findAll();
        if (heures.size() > 0) {
            for( Heure heure: heures){
                Long depart_id = heure.getId();
                heure= heureRepository.findById(depart_id).get();
                menu += heure.getId() +". " + heure.getInterval_heur()+"\n";
            }
        }

        return menu;

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
      //  Long ordre = Long.parseLong(input.split("\\*")[4]);
    //    Heure heures = heureRepository.findByNumero(ordre);
       /* List<Heure> heure = heureRepository.findAll();
        int size = heure.size();
        if (ordre > size || ordre == 0){
            return "Erreur de saisie";
        }*/
        return "CON Entrer votre code secret slp";
    }
    @Override
    public String getConfirmation() {
        String menu = "CON  Confirmer votre paiement\n";
        menu += "1.Confirmer \n";
        menu += "2.Annuler \n";
        return menu ;
    }

    @SneakyThrows
    public String getRendezVours(String input, String telephone) {


        long x = 1234L;
        long y = 2345L;
        Random r = new Random();
        long  ticket  = x+((long)(r.nextDouble()*(y-x)));
        int id_hopital = 0;
        Long id_service = 0L;
        int id_heure = 0;
        String date ="";
        String num="";

        if(input.split("\\*").length == 7) {
            id_service = Long.parseLong(input.split("\\*")[1]);
            id_hopital = Integer.parseInt(input.split("\\*")[2]);

            date= input.split("\\*")[3];
            id_heure = Integer.parseInt(input.split("\\*")[4]);

            date = input.split("\\*")[3];

        }else if(input.split("\\*").length == 8) {
            id_hopital = Integer.parseInt(input.split("\\*")[2]);
            id_service = Long.parseLong(input.split("\\*")[3]);
            date = input.split("\\*")[4];
         //   id_jour = Integer.parseInt(input.split("\\*")[4]);
            id_heure = Integer.parseInt(input.split("\\*")[3]);
            num=(input.split("\\*")[1]);
            UserTransaction usert = new UserTransaction();
            usert.setMsisdn("+224"+num);
            UserTransaction user = userTransactionRepository.save(usert);

            //Departement
            Departement dep = departementRepository.getById(id_service);

            //Hopital
            Hopital hopital = hopitalRepository.findById((long) id_hopital).get();

            //Heure
            Heure heure = heureRepository.findByNumero((long) id_heure);

            Date date1=new SimpleDateFormat("dd/MM/yyyy").parse(date);

            RendezVous rendezVous = new RendezVous();
            rendezVous.setDepartement(dep);
            rendezVous.setHopital(hopital);
            rendezVous.setDate(date1);
            rendezVous.setHeures(heure);

            rendezVous.setTicket(ticket);
            rendezVous.setUserTransaction(user);

            rendezVous =  rendezVousRepository.save(rendezVous);
            String message = "Votre rendez-vous  a bien ete enregistre, votre numero d'enregistrement est "+ticket;
            boolean b = orangeSMS.sendMessage("+224"+num, message);
            return "END Votre rendez-vous pour une autre personne à ete enregistrer, vous recevrer un sms de confirmation"+"Status: "+telephone;

        }
      else  if (input.split("\\*").length == 5) {
            System.out.println("vous etes super");
            date = input.split("\\*")[3];
            id_heure = Integer.parseInt(input.split("\\*")[4]);
            Long numero = Long.parseUnsignedLong(input.split("\\*")[2]);
            Date date1 = new SimpleDateFormat("dd/MM/yyyy").parse(date);
            System.out.println("Ticket : " + numero);
            RendezVous rendezVous=rendezVousRepository.findByTicket(numero);
            Heure heure = heureRepository.findByNumero((long) id_heure);
          //  return "END "+"Status: "+telephone +date1 +"bb"+heure+rendezVous.getTicket();

            rendezVous.setDate(date1);
            rendezVous.setHeures(heure);
            rendezVousRepository.save(rendezVous);
            String message = "Votre RendezVous a été prolonger avec success  pour la date suivante"+rendezVous.getDate();
            boolean b = orangeSMS.sendMessage(telephone, message);
            return "END Votre rendez-vous  a bien été prolonger, vous recevrer un sms de confirmation"+"Status: "+telephone;


        }


        //User
        UserTransaction usert = new UserTransaction();
        usert.setMsisdn(telephone);
        UserTransaction user = userTransactionRepository.save(usert);

        //Departement
        Departement dep = departementRepository.getById(id_service);

        //Hopital
        Hopital hopital = hopitalRepository.findById((long) id_hopital).get();

        //Heure
        Heure heure = heureRepository.findByNumero((long) id_heure);

        //Jour
        //Jour jour = jourRepository.getById((long) id_jour);

//        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy ");
//        Date date1 = new Date();
//        String date2= dateFormat.format(date);

       // DateTimeFormatter format = DateTimeFormatter.ofP  attern("dd/mm/yyyy", Locale.FRENCH);
       // LocalDate date1 = Date.parse(date, format);
        Date date1=new SimpleDateFormat("dd/MM/yyyy").parse(date);

        RendezVous rendezVous = new RendezVous();
        rendezVous.setDepartement(dep);
        rendezVous.setHopital(hopital);
        rendezVous.setDate(date1);
        rendezVous.setHeures(heure);

        rendezVous.setTicket(ticket);
        rendezVous.setUserTransaction(user);

        rendezVous =  rendezVousRepository.save(rendezVous);

        if (rendezVous != null) {
//            telephone = "+224"+telephone;
//           System.out.println(telephone);
         String message = "Votre rendez-vous  a bien ete enregistre, votre numero d'enregistrement est "+ticket;
            boolean b = orangeSMS.sendMessage(telephone, message);
            System.out.println("Status: "+telephone);

            return "END Votre rendez-vous  a bien ete enregistre, vous recevrer un sms de confirmation"+"Status: "+telephone;
        }else {
            return "END Une erreur inconnu s'est produit";
        }
    }

    @Override
    public String getVerification(String input) {
         if((input.split("\\*").length == 3)){

             return "CON  entrer le numero de son tichet precedent";
         }
else{
        return "CON  entrer le numero de votre tichet precedent";}
    }

    @Override
    public String checkTicket(String input) {

        List< RendezVous> rendezVous=rendezVousRepository.findAll();

        long numero = Long.parseLong(input.split("\\*")[2]);

        boolean etat = false;
        for(RendezVous rendezVous1:rendezVous){
            if(rendezVous1.getTicket() == numero) {
                etat =true;
                break;
            }
        }

        if(etat == true) {
            return "CON Entrer la nouvlle date";
        }
        return "END Numero de ticket invalide";
    }


//    public String prolongementSuccess(String input,String telephone) throws ParseException {
//        int id_heure = 0;
//        String date = "";
//
//
//        return null;
//    }
}
