package lair.ortega.dao;

import java.io.Serializable;
import java.util.List;

public interface IGenericDao<T, I extends Serializable>{
	public I save(T entity);
	public void delete(T entity);
	public T update(T entity);
	public T findById(I identifier);
	public List<T> findAll();
}
