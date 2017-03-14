package dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public abstract class DAOGeneric<T> {

    @PersistenceContext
    protected EntityManager entityManager;

	public DAOGeneric() {
	}

	/**
	 * Méthode de création
	 */
	public abstract int create(T obj);

	/**
	 * Méthode de suppression
	 */
	public abstract int delete(T obj);

	/**
	 * Méthode de mise à jour
	 */

	public abstract int update(T obj);

    /**
	 * Méthode de recherche
	 *//*

	public abstract T find(int id);
	*/

}
