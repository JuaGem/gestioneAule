package it.prova.gestioneaule.service;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.prova.gestioneaule.dao.IAulaScolasticaDAO;
import it.prova.gestioneaule.model.AulaScolastica;

@Service
public class AulaScolasticaServiceImpl implements IAulaScolasticaService {

	@Autowired
	IAulaScolasticaDAO aulaDAO;

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public List<AulaScolastica> listAllAule() {
		return aulaDAO.list();
	}

	@Override
	public AulaScolastica caricaSingoloAulaScolastica(Long id) {
		return aulaDAO.get(id);
	}

	@Transactional
	public void aggiorna(AulaScolastica aulaInstance) {
		aulaDAO.update(aulaInstance);
	}

	@Transactional
	public void inserisciNuovo(AulaScolastica aulaInstance) {
		aulaDAO.insert(aulaInstance);
	}

	@Transactional
	public void rimuovi(AulaScolastica aulaInstance) {
		aulaInstance = entityManager.merge(aulaInstance);

		if (!aulaInstance.getStudenti().isEmpty())
			throw new RuntimeException("Nell'aula ci sono dei studenti, impossibile rimuoverla");

		aulaDAO.delete(aulaInstance);
	}

	@Override
	public List<AulaScolastica> findByExample(AulaScolastica example) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AulaScolastica caricaSingolaAulaConStudenti(AulaScolastica aulaInstance) {
		return aulaDAO.findEagerFetch(aulaInstance);
	}

	@Override
	public Long contaPerStudentiMinorenni(Date data) {
		return aulaDAO.countByStudentiMinorenni(data);
	}

}
