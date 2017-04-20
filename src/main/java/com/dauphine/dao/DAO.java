package com.dauphine.dao;

import org.apache.log4j.Logger;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.transaction.Transactional;

@Transactional
public abstract class DAO<T> {

    private static final Logger logger = Logger.getLogger(DAO.class);
    protected static final String RB = "ROLLBACK";
	protected EntityManager entityManager;

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
	        logger.error(e);
            if (transaction.isActive()){
                transaction.rollback();
                logger.info(RB);
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
            logger.error(e);
            if (transaction.isActive()){
                transaction.rollback();
                logger.info(RB);
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
            logger.error(e);
            if (transaction.isActive()){
                transaction.rollback();
                logger.info(RB);
            }
        } finally {
            if(transaction.isActive()) transaction.commit();
        }
    }

}
