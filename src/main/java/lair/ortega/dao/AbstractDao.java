package lair.ortega.dao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class AbstractDao<I extends Serializable, T> {
private final Class<T> persistentClass;
	
	public AbstractDao(){
		this.persistentClass = (Class<T>) ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[1];
	}
	
	@Autowired
	private SessionFactory sessionFactory;
	
	protected Session getSession(){
		return sessionFactory.getCurrentSession();
	}
	
	public T findById(I key){
		return (T) getSession().get(persistentClass, key);
	}
	public T find(Criteria c){
		if(c == null){
			c = this.createEntityCriteria();
		}
		return (T) c.uniqueResult();
	}
	public Integer save(T entity){
		return (Integer) getSession().save(entity);
	}
	public void merge(T entity){
		getSession().merge(entity);
	}
	public void delete(T entity){
		getSession().delete(entity);
	}
	protected Criteria createEntityCriteria(){
		return getSession().createCriteria(persistentClass);
	}

	public List<T> findAll(Criteria c){
		if(c == null){
			c = this.createEntityCriteria();
		}
		return (List<T>)c.list();
	}
}
