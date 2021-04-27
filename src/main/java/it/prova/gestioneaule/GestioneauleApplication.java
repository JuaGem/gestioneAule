package it.prova.gestioneaule;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GestioneauleApplication implements CommandLineRunner {

	@Autowired
	private BatteriaDiTest batteriaDiTest;

	public static void main(String[] args) {
		SpringApplication.run(GestioneauleApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		String casoDaTestare = BatteriaDiTest.CARICA_AULA_EAGER;

		System.out.println("################ START   #################");
		System.out.println("################ eseguo il test " + casoDaTestare + "  #################");

		batteriaDiTest.eseguiBatteriaDiTest(casoDaTestare);

		System.out.println("################ FINE   #################");

	}

}
