package it.prova.gestioneaule.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import it.prova.gestioneaule.model.Studente;
import it.prova.gestioneaule.model.AulaScolastica;

@Component
public class StudenteDaoImpl implements IStudenteDAO {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public List<Studente> list() {
		return entityManager.createQuery("from Studente", Studente.class).getResultList();
	}

	@Override
	public Studente get(Long id) {
		return entityManager.find(Studente.class, id);

	}

	@Override
	public void update(Studente studenteInput) {
		studenteInput = entityManager.merge(studenteInput);
	}

	@Override
	public void insert(Studente studenteInput) {
		entityManager.persist(studenteInput);
	}

	@Override
	public void delete(Studente studenteInput) {
		entityManager.remove(entityManager.merge(studenteInput));
	}

	@Override
	public List<Studente> findByExample(Studente studenteInput) {
		Map<String, Object> paramaterMap = new HashMap<String, Object>();
		List<String> whereClauses = new ArrayList<String>();

		StringBuilder queryBuilder = new StringBuilder("select s from Studente s where s.id=s.id ");

		if (StringUtils.isNotEmpty(studenteInput.getNome())) {
			whereClauses.add(" s.nome  like :nome ");
			paramaterMap.put("nome", "%" + studenteInput.getNome() + "%");
		}
		if (StringUtils.isNotEmpty(studenteInput.getCognome())) {
			whereClauses.add(" s.cognome like :cognome ");
			paramaterMap.put("cognome", "%" + studenteInput.getCognome() + "%");
		}
		if (studenteInput.getDataNascita() != null) {
			whereClauses.add("s.dataNascita >= :dataNascita ");
			paramaterMap.put("dataNascita", studenteInput.getDataNascita());
		}

		queryBuilder.append(!whereClauses.isEmpty() ? " and " : "");
		queryBuilder.append(StringUtils.join(whereClauses, " and "));
		TypedQuery<Studente> typedQuery = entityManager.createQuery(queryBuilder.toString(), Studente.class);

		for (String key : paramaterMap.keySet()) {
			typedQuery.setParameter(key, paramaterMap.get(key));
		}

		return typedQuery.getResultList();
	}

	@Override
	public List<Studente> findAllByAula(AulaScolastica aulaInput) {
		TypedQuery<Studente> query = entityManager.createQuery(
				"select s FROM Studente s JOIN FETCH s.aulaScolastica where s.aulaScolastica =:aulaInput",
				Studente.class);
		return query.setParameter("aulaInput", aulaInput).getResultList();
	}

}
