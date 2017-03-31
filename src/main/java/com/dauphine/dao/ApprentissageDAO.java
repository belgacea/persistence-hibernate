package com.dauphine.dao;

import com.dauphine.domain.Apprenti;
import com.dauphine.domain.Apprentissage;
import com.dauphine.domain.Entreprise;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

@Transactional
public class ApprentissageDAO extends DAO<Apprentissage> {

	private EntityManager entityManager;

	public ApprentissageDAO(EntityManager em) {
		super(em);
		this.entityManager = em;
	}

	public Apprentissage findLazy(int entreprise_id, int apprentis_id) {
	    ApprentiDAO apprentiDAO = new ApprentiDAO(entityManager);
	    EntrepriseDAO entrepriseDAO = new EntrepriseDAO(entityManager);
        Apprenti apprenti = apprentiDAO.find(apprentis_id);
        Entreprise entreprise = entrepriseDAO.findLazy(entreprise_id);
        return entityManager.find(Apprentissage.class, new Apprentissage.ApprentissageId(entreprise, apprenti));
	}

//	public Apprentissage findEater(int entreprise_id, int apprentis_id) {
//        //TODO
//        return null;
//
////		Apprentissage apprentissage = new Apprentissage();
////		ApprentiDAO apprentiDAO = new ApprentiDAO(this.connect);
////		EntrepriseDAO entrepriseDAO = new EntrepriseDAO(this.connect);
////
////		try {
////			ResultSet result = this.connect
////					.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)
////					.executeQuery("SELECT * FROM apprentissage WHERE apprentis_id = " + apprentis_id
////							+ "AND entreprise_id = " + entreprise_id);
////			if (result.first()){
////				String nomMA = result.getString("nomMA");
////				String prenomMA = result.getString("prenomMA");
////				MaitreApp maitreApp = new MaitreApp(nomMA, prenomMA);
////
////				result = this.connect
////						.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)
////						.executeQuery("SELECT * FROM apprentissage WHERE nomMA = '" + nomMA
////								+ "' AND prenomMA = '" + prenomMA + "'");
////
////				while(result.next()){
////					int entr_id = result.getInt(2);
////					int apprent_id = result.getInt(1);
////					apprentissage = new Apprentissage(entrepriseDAO.findLazy(entr_id), apprentiDAO.find(apprent_id), maitreApp);
////					maitreApp.addApprentissage(apprentissage);
////
////				}
////				apprentissage = new Apprentissage(entrepriseDAO.findLazy(entreprise_id), apprentiDAO.find(apprentis_id), maitreApp);
////			}
////		} catch (SQLException e) {
////			e.printStackTrace();
////		}
////		return apprentissage;
//	}

}
