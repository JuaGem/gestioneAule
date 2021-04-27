package it.prova.gestioneaule.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.prova.gestioneaule.dao.IStudenteDAO;
import it.prova.gestioneaule.model.AulaScolastica;
import it.prova.gestioneaule.model.Studente;

@Service
public class StudenteServiceImpl implements IStudenteService {

	@Autowired
	IStudenteDAO studenteDAO;

	@PersistenceContext
	private EntityManager entityManager;

	@Transactional(readOnly = true)
	public List<Studente> listAllAbitanti() {
		return studenteDAO.list();
	}

	@Transactional(readOnly = true)
	public Studente caricaSingoloStudente(Long id) {
		return studenteDAO.get(id);
	}

	@Transactional
	public void aggiorna(Studente studenteInstance) {
		studenteDAO.update(studenteInstance);
	}

	@Transactional
	public void inserisciNuovo(Studente studenteInstance) {
		AulaScolastica aulaItem = entityManager.merge(studenteInstance.getAulaScolastica());

		if (aulaItem.getCapienza() == aulaItem.getStudenti().size())
			throw new RuntimeException("Aula ha raggiunto la sua capienza massima, impossibile inserire");

		studenteDAO.insert(studenteInstance);
	}

	@Transactional
	public void rimuovi(Studente studenteInstance) {
		studenteDAO.delete(studenteInstance);
	}

	@Transactional(readOnly = true)
	public List<Studente> findByExample(Studente example) {
		return studenteDAO.findByExample(example);
	}

	@Transactional(readOnly = true)
	public List<Studente> cercaStudentiDataAula(AulaScolastica input) {
		return studenteDAO.findAllByAula(input);
	}

}
