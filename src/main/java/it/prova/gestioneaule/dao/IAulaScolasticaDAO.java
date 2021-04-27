package it.prova.gestioneaule.dao;

import java.util.Date;

import it.prova.gestioneaule.model.AulaScolastica;

public interface IAulaScolasticaDAO extends IBaseDAO<AulaScolastica>{
	
	public AulaScolastica findEagerFetch(AulaScolastica aulaInput);
	
	public Long countByStudentiMinorenni(Date data);

}
