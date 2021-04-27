package it.prova.gestioneaule.service;

import java.util.Date;
import java.util.List;

import it.prova.gestioneaule.model.AulaScolastica;



public interface IAulaScolasticaService {
	
	public List<AulaScolastica> listAllAule();

	public AulaScolastica caricaSingoloAulaScolastica(Long id);

	public void aggiorna(AulaScolastica aulaInstance);

	public void inserisciNuovo(AulaScolastica abitanteInstance);

	public void rimuovi(AulaScolastica abitanteInstance);

	public List<AulaScolastica> findByExample(AulaScolastica example);
	
	public AulaScolastica caricaSingolaAulaConStudenti(AulaScolastica aulaInput);
	
	public Long contaPerStudentiMinorenni(Date data);
	
}
