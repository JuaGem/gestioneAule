package it.prova.gestioneaule.dao;

import java.util.List;

import it.prova.gestioneaule.model.AulaScolastica;
import it.prova.gestioneaule.model.Studente;

public interface IStudenteDAO extends IBaseDAO<Studente>{
	
	public List<Studente> findAllByAula(AulaScolastica aulaInput);

}
