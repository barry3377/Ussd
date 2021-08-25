package com.ussd.app.Ussd;

import com.ussd.app.Ussd.entities.Heure;
import com.ussd.app.Ussd.repository.HeureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
@SpringBootApplication
public class UssdApplication implements CommandLineRunner {
@Autowired
private HeureRepository heureRepository;
	public static void main(String[] args) {
		SpringApplication.run(UssdApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		heureRepository.findAll().forEach(heure -> {
			System.out.println(heure.toString());
		});

	}
}