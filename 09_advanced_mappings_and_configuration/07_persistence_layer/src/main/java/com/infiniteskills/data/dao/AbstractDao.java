package com.infiniteskills.data.dao;

import com.infiniteskills.data.dao.interfaces.Dao;
import com.infiniteskills.data.utilities.HibernateUtil;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

public class AbstractDao<T,ID extends Serializable> implements Dao<T,ID> {

	private Class<T> persistentClass;
	private Session session;
	
	@SuppressWarnings("unchecked")
    // Find out the generic type that is going to be our persistence class.
    // This allows interaction with the session.
	public AbstractDao(){
		this.persistentClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
	}
	
	public void setSession(Session session){
		this.session = session;
	}
	
	protected Session getSession(){
		if(this.session == null){
			this.session = HibernateUtil.getSessionFactory().getCurrentSession();
		}
		return this.session;
	}
	
	public Class<T> getPersistentClass(){
		return persistentClass;
	}

	@SuppressWarnings("unchecked")
	protected List<T> findByCriteria(Criterion ... criterion){
		Criteria crit = this.getSession().createCriteria(this.getPersistentClass());

		for(Criterion c: criterion){
			crit.add(c);
		}
		return (List<T>) crit.list();

	}

	@SuppressWarnings("unchecked")
    // Grab the session, invoke the load method, and we are passing in the persistence class
    // and an id that is supplied as a parameter to this method.
	public T findById(ID id) {
		return (T) getSession().load(this.getPersistentClass(), id);
	}

	public List<T> findAll() {
		return this.findByCriteria();
	}

	public T save(T entity) {
		this.getSession().saveOrUpdate(entity);
		return entity;
	}

	public void delete(T entity) {
		this.getSession().delete(entity);
	}

	public void flush() {
		this.getSession().flush();
	}

	public void clear() {
		this.getSession().clear();
	}

}
