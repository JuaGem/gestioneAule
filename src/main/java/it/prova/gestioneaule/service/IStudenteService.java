package it.prova.gestioneaule.service;

import java.util.List;

import it.prova.gestioneaule.model.*;

public interface IStudenteService {
	
	public List<Studente> listAllAbitanti();

	public Studente caricaSingoloStudente(Long id);

	public void aggiorna(Studente studenteInstance);

	public void inserisciNuovo(Studente studenteInstance);

	public void rimuovi(Studente studenteInstance);

	public List<Studente> findByExample(Studente example);
	
	public List<Studente> cercaStudentiDataAula(AulaScolastica input);

}
