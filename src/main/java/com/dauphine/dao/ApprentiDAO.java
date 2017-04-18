package com.dauphine.dao;

import com.dauphine.domain.Apprenti;
import com.dauphine.domain.Apprentissage;
import org.apache.log4j.Logger;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Transactional
public class ApprentiDAO extends DAO<Apprenti> {

    private static final Logger logger = Logger.getLogger(DAO.class);

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

    public void delete(Apprenti apprenti) {
        EntityTransaction transaction = entityManager.getTransaction();
        ApprentissageDAO apprentissageDAO = new ApprentissageDAO(entityManager);
        Apprentissage apprentissage = apprentissageDAO.findByApprentiId(apprenti.getId());
        try {
            transaction.begin();
            entityManager.remove(apprentissage);
            entityManager.remove(apprenti);
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