package com.ussd.app.Ussd;

import com.ussd.app.Ussd.entities.Heure;
import com.ussd.app.Ussd.entities.Jour;
import com.ussd.app.Ussd.repository.HeureRepository;
import com.ussd.app.Ussd.repository.JoursRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
@SpringBootApplication
public class UssdApplication implements CommandLineRunner {
@Autowired
private HeureRepository heureRepository;
	@Autowired
JoursRepository joursRepository;
	public static void main(String[] args) {
		SpringApplication.run(UssdApplication.class, args);

	}

	@Override
	public void run(String... args) throws Exception {
		Jour jour=new Jour("Lundi");
		joursRepository.save(jour);
		Jour jour0=new Jour("Mardi");
	joursRepository.save(jour0);
		Jour jour1=new Jour("Mercredi");
		joursRepository.save(jour1);
		Jour jour sitory.save(jour2);
		Jour jour3=new Jour("Vendredi");
		joursRepository.save(jour3);
		Jour jour4=new Jour("Samedi");
		joursRepository.save(jour4);
		Jour jour5=new Jour("Dimanche");
		joursRepository.save(jour5);
		heureRepository.findAll().forEach(heure -> {
			System.out.println(heure.toString());
		});

	}
}