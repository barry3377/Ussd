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
JoursRepository joursRepository;
	public static void main(String[] args) {
		SpringApplication.run(UssdApplication.class, args);
//		Jour jour=new Jour("Lundi");
//		joursRepository.save(jour)
	}

	@Override
	public void run(String... args) throws Exception {
//		Jour jour=new Jour("Lundi");
//		joursRepository.save(jour);

		heureRepository.findAll().forEach(heure -> {
			System.out.println(heure.toString());
		});

	}
}