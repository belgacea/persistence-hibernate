package com.dauphine.dao;

import com.dauphine.domain.Apprenti;
import com.dauphine.domain.Apprentissage;
import com.dauphine.domain.Entreprise;
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
public class EntrepriseDAO extends DAO<Entreprise> {

    private static final Logger logger = Logger.getLogger(DAO.class);

    public EntrepriseDAO(EntityManager em) {
        super(em);
        this.entityManager = em;
    }

    public List<Entreprise> findAll() {
        return entityManager.createQuery("Select e From Entreprise e").getResultList();
    }

    public long countAll() {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Long> query = cb.createQuery(Long.class);
        Root<Entreprise> root = query.from(Entreprise.class);
        query.select(cb.count(root));
        return entityManager.createQuery(query).getSingleResult();
    }

    public Entreprise findLazy(int id) {
        return entityManager.find(Entreprise.class, id);
    }

    public Entreprise findEager(int id) {
        Entreprise entreprise = entityManager.find(Entreprise.class, id);
        ArrayList<Apprentissage> apprentissages = new ArrayList<Apprentissage>();
        for(Apprentissage apprentissage : entreprise.getApprentissages()){
            Apprenti apprenti = apprentissage.getApprenti();
            apprentissages.add(entityManager.find(Apprentissage.class, new Apprentissage.ApprentissageId(entreprise, apprenti)));
        }
        entreprise.setApprentissages(apprentissages);
        return entreprise;
    }

    public void delete(Entreprise entreprise) {
        EntityTransaction transaction = entityManager.getTransaction();
        ApprentissageDAO apprentissageDAO = new ApprentissageDAO(entityManager);
        List<Apprentissage> apprentissages = apprentissageDAO.findByEntrepriseId(entreprise.getId());
        try {
            transaction.begin();
            for (Apprentissage apprentissage : apprentissages) {
                entityManager.remove(apprentissage);
            }
            entityManager.remove(entreprise);
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