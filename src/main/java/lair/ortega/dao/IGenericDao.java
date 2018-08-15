package lair.ortega.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Criteria;

public interface IGenericDao<I extends Serializable, T> {
	T findById(I id);
	void save(T entity);
	void update(T entity);
	void delete(T entity);
	List<T> findAll(Criteria c);
	T find(Criteria c);
}
