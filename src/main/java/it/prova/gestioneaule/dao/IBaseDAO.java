package it.prova.gestioneaule.dao;

import java.util.List;

public interface IBaseDAO<T> {
	
	public List<T> list();
	
	public T get(Long id);
	
	public void update(T t);
	
	public void insert(T t);
	
	public void delete(T t);
	
	public List<T> findByExample(T t);

}
