package com.dauphine.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public abstract class DAO<T> {

    //private Class<T> classe;
	@PersistenceContext
	private EntityManager entityManager;

	public DAO(/*Class classe*/) {
        //this.classe = classe;
    }

	/**
	 * Méthode de création
	 */
	public void create(T obj){
        entityManager.persist(obj);
    }

	/**
	 * Méthode de suppression
	 */
	public void delete(T obj){
        entityManager.remove(obj);
    }

	/**
	 * Méthode de mise à jour
	 */

	public void update(T obj){
        entityManager.merge(obj);
    }

    /**
	 * Méthode de recherche
	 */
	/*public T find(int id){
        return entityManager.find(classe, id);
    }*/


}
