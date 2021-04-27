package it.prova.gestioneaule;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.prova.gestioneaule.service.IAulaScolasticaService;
import it.prova.gestioneaule.service.IStudenteService;
import it.prova.gestioneaule.model.*;

@Service
public class BatteriaDiTest {

	@Autowired
	IAulaScolasticaService aulaService;

	@Autowired
	IStudenteService studenteService;

	public static final String INSERISCI_NUOVA_AULASCOLASTICA = "INSERISCI_NUOVA_AULASCOLASTICA";
	public static final String INSERISCI_STUDENTE_DATA_UN_AULA = "INSERISCI_STUDENTE_DATA_UN_AULA";
	public static final String CERCA_STUDENTE_DATO_ID_AULA = "CERCA_STUDENTE_DATO_ID_AULA";
	public static final String RIMUOVI_AULA = "RIMUOVI_AULA";
	public static final String RIMUOVI_STUDENTE = "RIMUOVI_STUDENTE";
	public static final String ELENCA_TUTTE_LE_AULE = "ELENCA_TUTTE_LE_AULE";
	public static final String UPDATE_STUDENTE_SET_DATA = "UPDATE_STUDENTE_SET_DATA";
	public static final String CARICA_AULA_EAGER = "CARICA_AULA_EAGER";
	public static final String COUNT_AULE_BY_MINORENNI = "COUNT_AULE_BY_MINORENNI";
	public static final String FIND_BY_EXAMPLE_BY_COGNOME = "FIND_BY_EXAMPLE_BY_COGNOME";
	public static final String FIND_BY_EXAMPLE_BY_COGNOME_AND_ETA = "FIND_BY_EXAMPLE_BY_COGNOME_AND_ETA";

	public void eseguiBatteriaDiTest(String casoDaTestare) {
		try {
			switch (casoDaTestare) {
			case INSERISCI_NUOVA_AULASCOLASTICA:

				AulaScolastica nuovaAula = new AulaScolastica("A11", "ITALIANO", 5);

				aulaService.inserisciNuovo(nuovaAula);
				System.out.println("Aula appena inserita: " + nuovaAula);
				break;

			case INSERISCI_STUDENTE_DATA_UN_AULA:

				AulaScolastica aulaAssociataStudente = aulaService.caricaSingoloAulaScolastica(1L);
				Studente nuovoStudente = new Studente("Andrea", "Vecchiato", parseDate("13-11-2001"),
						aulaAssociataStudente);
				// salvo
				studenteService.inserisciNuovo(nuovoStudente);
				break;

			case CERCA_STUDENTE_DATO_ID_AULA:
				// stampo gli abitanti di un determinato municipio
				System.out.println(studenteService.cercaStudentiDataAula(aulaService.caricaSingoloAulaScolastica(1L)));
				break;

			case RIMUOVI_STUDENTE:

				Studente studenteItem = studenteService.caricaSingoloStudente(4L);
				studenteService.rimuovi(studenteItem);
				break;

			case RIMUOVI_AULA:

				AulaScolastica aulaItem = aulaService.caricaSingoloAulaScolastica(1L);
				aulaService.rimuovi(aulaItem);
				break;

			case ELENCA_TUTTE_LE_AULE:
				// elencare i municipi
				System.out.println("Elenco di tutte le aule:");
				for (AulaScolastica aulaCurrente : aulaService.listAllAule())
					System.out.println(aulaCurrente);

				break;

			case FIND_BY_EXAMPLE_BY_COGNOME:
				System.out.println("########### EXAMPLE ########################");

				Studente studenteExample = new Studente();
				studenteExample.setCognome("Gemini");
				System.out.println(studenteService.findByExample(studenteExample));

				break;
			case FIND_BY_EXAMPLE_BY_COGNOME_AND_ETA:
				System.out.println("########### EXAMPLE ########################");

				Studente studente = new Studente();
				studente.setCognome("Gemini");
				studente.setDataNascita(parseDate("29-03-1998"));
				System.out.println(studenteService.findByExample(studente));
				break;

			case UPDATE_STUDENTE_SET_DATA:

				Studente studenteCurrent = studenteService.caricaSingoloStudente(2L);
				if (studenteCurrent != null) {
					studenteCurrent.setDataNascita(parseDate("29-03-1998"));
					studenteService.aggiorna(studenteCurrent);
				}
				break;

			case CARICA_AULA_EAGER:

				AulaScolastica aulaCurrent = aulaService.caricaSingoloAulaScolastica(1L);
				if (aulaCurrent != null) {
					System.out.println(aulaService.caricaSingolaAulaConStudenti(aulaCurrent));
				}
				break;

			case COUNT_AULE_BY_MINORENNI:
				System.out.println("########### COUNT_AULE_BY_MINORENNI ########################");
				System.out.println("ci sono " + aulaService.contaPerStudentiMinorenni(parseDate("31-11-2021"))
						+ " aule con minorenni");
				break;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static Date parseDate(String data) {

		try {
			return new SimpleDateFormat("dd-MM-yyyy").parse(data);
		} catch (ParseException e) {
			return null;
		}

	}

}
