package com.dauphine.dao;

import com.dauphine.domain.Apprentissage;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Transactional
public class ApprentissageDAO extends DAO<Apprentissage> {

    //@PersistenceContext
	private EntityManager entityManager;

	public ApprentissageDAO(EntityManager em) {
		super(em);
	}

//	public int create(Apprentissage apprentissage) {
//        entityManager.persist(apprentissage);
//        return 0;
//
////		try {
////			this.connect.createStatement()
////					.executeUpdate("INSERT INTO Apprentissage" + " VALUES(" + apprentissage.getApprenti().getId() + ","
////							+ apprentissage.getEntreprise().getId() + ",'" + apprentissage.getMaitreApp().getNomMA()
////							+ "','" + apprentissage.getMaitreApp().getPrenomMA() + "')");
////		} catch (SQLException e) {
////			e.printStackTrace();
////		}
////		return 0;
//	}
//
//	public int delete(Apprentissage apprentissage) {
//        entityManager.remove(apprentissage);
//        return 0;
//
////		int status = -1;
////		try {
////			status = this.connect.createStatement().executeUpdate(
////					"DELETE FROM Apprentissage" + " WHERE apprentis_id = " + apprentissage.getApprenti().getId()
////							+ "AND entreprise_id = " + apprentissage.getEntreprise().getId(),
////					Statement.SUCCESS_NO_INFO);
////		} catch (SQLException e) {
////			e.printStackTrace();
////		}
////		return status;
//	}
//
//	public int update(Apprentissage apprentissage) {
//        entityManager.merge(apprentissage);
//		return 0;
//
////		int status = -1;
////		try {
////			status = this.connect.createStatement()
////					.executeUpdate(
////							"UPDATE Apprentissage SET nomMA='" + apprentissage.getMaitreApp().getNomMA()
////									+ ", prenomMA= '" + apprentissage.getMaitreApp().getPrenomMA()
////									+ "' WHERE apprentis_id=" + apprentissage.getApprenti().getId()
////									+ "AND entreprise_id = " + apprentissage.getEntreprise().getId(),
////							Statement.SUCCESS_NO_INFO);
////		} catch (SQLException e) {
////			e.printStackTrace();
////		}
////		return status;
//	}
//
//	public Apprentissage findLazy(int entreprise_id, int apprentis_id) {
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
////			if (result.first())
////				apprentissage = new Apprentissage(entrepriseDAO.findLazy(entreprise_id), apprentiDAO.find(apprentis_id),
////						new MaitreApp(result.getString("nomMA"), result.getString("prenomMA")));
////		} catch (SQLException e) {
////			e.printStackTrace();
////		}
////		return apprentissage;
//	}
//
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
