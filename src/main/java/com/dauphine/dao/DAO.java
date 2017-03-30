package com.dauphine.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceContext;
import javax.transaction.Transaction;
import javax.transaction.Transactional;

@Transactional
public abstract class DAO<T> {

	//@PersistenceContext
	private EntityManager entityManager;

	public DAO(EntityManager em) {
        this.entityManager = em;
    }

	/**
	 * Méthode de création
	 */
	public void create(T obj){
        EntityTransaction transaction = entityManager.getTransaction();
	    try {
            transaction.begin();
            entityManager.persist(obj);
            entityManager.flush();
        } catch (RuntimeException e){
            if (transaction != null && transaction.isActive()){
                transaction.rollback();
            }
        } finally {
            if(transaction.isActive()) transaction.commit();
        }
    }

	/**
	 * Méthode de suppression
	 */
	public void delete(T obj){
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.remove(obj);
        } catch (RuntimeException e){
            if (transaction != null && transaction.isActive()){
                transaction.rollback();
            }
        } finally {
            if(transaction.isActive()) transaction.commit();
        }
    }

	/**
	 * Méthode de mise à jour
	 */

	public void update(T obj){
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.merge(obj);
        } catch (RuntimeException e){
            if (transaction != null && transaction.isActive()){
                transaction.rollback();
            }
        } finally {
            if(transaction.isActive()) transaction.commit();
        }
    }

    /**
	 * Méthode de recherche
	 */
	/*public T find(int id){
        return entityManager.find(classe, id);
    }*/


}
