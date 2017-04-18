package com.dauphine.dao;

import com.dauphine.domain.Apprenti;
import com.dauphine.domain.Apprentissage;
import com.dauphine.domain.Entreprise;
import com.dauphine.domain.MaitreApp;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Transactional
public class ApprentissageDAO extends DAO<Apprentissage> {

	public ApprentissageDAO(EntityManager em) {
		super(em);
		this.entityManager = em;
	}

    public List<Apprentissage> findAll() {
        return entityManager.createQuery("Select ap From Apprentissage ap").getResultList();
    }

	public Apprentissage findLazy(int entreprise_id, int apprentis_id) {
        Apprenti apprenti = entityManager.find(Apprenti.class, apprentis_id);
        Entreprise entreprise = entityManager.find(Entreprise.class, entreprise_id);
        return entityManager.find(Apprentissage.class, new Apprentissage.ApprentissageId(entreprise, apprenti));
	}

    public List<Apprentissage> findByEntrepriseId(int id) {
        TypedQuery<Apprentissage> q = entityManager.createQuery("select ap from Apprentissage ap where entreprise.id =:id", Apprentissage.class);
        q.setParameter("id", id);
        return q.getResultList();
    }

	public Apprentissage findByApprentiId(int id) {
        TypedQuery<Apprentissage> q = entityManager.createQuery("select ap from Apprentissage ap where apprenti.id =:id", Apprentissage.class);
        q.setParameter("id", id);
        return q.getSingleResult();
    }

	public long countAll() {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Long> query = cb.createQuery(Long.class);
		Root<Apprentissage> root = query.from(Apprentissage.class);
		query.select(cb.count(root));
		return entityManager.createQuery(query).getSingleResult();
	}

	public long countByEntrepriseId(int id) {
        TypedQuery<Long> q = entityManager.createQuery("select count(*) from Apprentissage where entreprise.id =:id", Long.class);
        q.setParameter("id", id);
        return q.getSingleResult();
    }

    public ArrayList<Apprentissage> findByMA(MaitreApp maitreApp) {
	    Query q = entityManager.createQuery("Select ap From Apprentissage ap Where nomMA =:nom And prenomMA =:prenom");
        q.setParameter("nom", maitreApp.getNomMA());
        q.setParameter("prenom", maitreApp.getPrenomMA());
        return (ArrayList<Apprentissage>) q.getResultList();
	}

	public Apprentissage findEager(int entreprise_id, int apprentis_id) {
        Apprenti apprenti = entityManager.find(Apprenti.class, apprentis_id);
        EntrepriseDAO entrepriseDAO = new EntrepriseDAO(entityManager);
        Entreprise entreprise = entrepriseDAO.findEager(entreprise_id);
        Apprentissage apprentissage = entityManager.find(Apprentissage.class, new Apprentissage.ApprentissageId(entreprise, apprenti));
        ArrayList<Apprentissage> apprentissages = findByMA(apprentissage.getMaitreApp());
        apprentissage.getMaitreApp().setApprentissages(apprentissages);
        return apprentissage;
	}

}
