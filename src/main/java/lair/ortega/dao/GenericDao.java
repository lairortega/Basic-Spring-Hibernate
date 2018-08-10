package lair.ortega.dao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class GenericDao<T, I extends Serializable> implements IGenericDao<T, I> {
	
	private Class<T> entity;
	public GenericDao() {
		this.entity = (Class<T>) ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[0];
	}
	
	@Autowired
	protected SessionFactory sessionFactory;
	
	protected Criteria createCriteria() {
		return this.getSession().createCriteria(this.entity);
	}
	protected Session getSession() {
		return this.sessionFactory.openSession();
	}

	@Override
	public I save(T entity) {
		return (I) this.getSession()
			.save(entity);
	}

	@Override
	public void delete(T entity) {
		this.getSession()
			.delete(entity);
	}

	@Override
	public T update(T entity) {
		return (T)this.getSession()
			.merge(entity);
	}

	@Override
	public List<T> findAll() {
		return (List<T>)this.getSession()
			.createCriteria(this.entity)
			.list();
	}

	@Override
	public T findById(I identifier) {
		return (T)this.getSession()
			.get(this.entity, identifier);
	}

}
