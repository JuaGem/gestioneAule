package it.prova.gestioneaule.dao;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Component;

import it.prova.gestioneaule.BatteriaDiTest;
import it.prova.gestioneaule.model.AulaScolastica;

@Component
public class AulaScolasticaDaoImpl implements IAulaScolasticaDAO {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public List<AulaScolastica> list() {
		return entityManager.createQuery("from AulaScolastica", AulaScolastica.class).getResultList();
	}

	@Override
	public AulaScolastica get(Long id) {
		return entityManager.find(AulaScolastica.class, id);
	}

	@Override
	public void update(AulaScolastica aulaInput) {
		aulaInput = entityManager.merge(aulaInput);
	}

	@Override
	public void insert(AulaScolastica aulaInput) {
		entityManager.persist(aulaInput);
	}

	@Override
	public void delete(AulaScolastica aulaInput) {
		entityManager.remove(entityManager.merge(aulaInput));
	}

	@Override
	public List<AulaScolastica> findByExample(AulaScolastica t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AulaScolastica findEagerFetch(AulaScolastica aulaInput) {
		Query q = entityManager.createQuery("SELECT a FROM AulaScolastica a JOIN FETCH a.studenti s WHERE a.id = :id");
		q.setParameter("id", aulaInput.getId());
		return (AulaScolastica) q.getSingleResult();
	}

	@Override
	public Long countByStudentiMinorenni(Date data) {
		Query q = entityManager.createQuery(
				"SELECT count(a) from AulaScolastica a where a in ( select distinct a from AulaScolastica a join a.studenti s where s.dataNascita < ?1 ) ");
		q.setParameter(1, BatteriaDiTest.parseDate("31-08-2021"));
		Object result = q.getSingleResult();
		return (Long) result;
	}

}
