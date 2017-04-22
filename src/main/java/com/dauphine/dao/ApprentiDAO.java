package com.dauphine.dao;

import com.dauphine.domain.Apprenti;
import org.apache.log4j.Logger;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.util.List;

@Transactional
public class ApprentiDAO extends DAO<Apprenti> {

	public ApprentiDAO(EntityManager em) {
		super(em);
		this.entityManager = em;
	}

    public List<Apprenti> findAll() {
        return entityManager.createQuery("Select a From Apprenti a").getResultList();
    }

	public Apprenti find(int id) {
        return entityManager.find(Apprenti.class, id);
	}

	public long countAll() {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Long> query = cb.createQuery(Long.class);
        Root<Apprenti> root = query.from(Apprenti.class);
        query.select(cb.count(root));
		return entityManager.createQuery(query).getSingleResult();
	}

}