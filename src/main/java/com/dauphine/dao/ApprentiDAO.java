package com.dauphine.dao;

import com.dauphine.domain.Apprenti;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

@Transactional
public class ApprentiDAO extends DAO<Apprenti> {

	private EntityManager entityManager;

	public ApprentiDAO(EntityManager em) {
		super(em);
		this.entityManager = em;
	}

	public Apprenti find(int id) {
        return entityManager.find(Apprenti.class, id);
	}

}