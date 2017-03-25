package com.dauphine.dao;

import com.dauphine.domain.Apprenti;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class ApprentiDAO extends DAO<Apprenti> {

	@PersistenceContext
	private EntityManager entityManager;

	public ApprentiDAO() {
	    //super(Apprenti.class);
	}
//
//	public int create(Apprenti apprenti) {
//		entityManager.persist(apprenti);
//        return 0;
//
////		int apprentiId = -1;
////		try {
////			Statement s = this.connect.createStatement();
////			s.executeUpdate("INSERT INTO Apprentis" + " VALUES(nextval('apprentis_apprentis_id_seq'), '"
////							+ apprenti.getNom() + "','" + apprenti.getPrenom() + "')");
////			ResultSet result = s.executeQuery("Select last_value FROM apprentis_apprentis_id_seq");
////			if(result.next()){
////				apprentiId = result.getInt(1);
////				apprenti.setId(apprentiId);
////			} else throw new SQLException("No serial created");
////		} catch (SQLException e) {
////			e.printStackTrace();
////		}
////		return apprentiId;
//	}
//
//	public int delete(Apprenti apprenti) {
//		entityManager.remove(apprenti);
//		return 0;
//
////		int status = -1;
////		try {
////			status = this.connect.createStatement().executeUpdate(
////					"DELETE FROM Apprentis" + " WHERE apprentis_id = " + apprenti.getId(), Statement.SUCCESS_NO_INFO);
////		} catch (SQLException e) {
////			e.printStackTrace();
////		}
////		return status;
//	}
//
//	public int update(Apprenti apprenti) {
//		return entityManager.merge(apprenti).getId();
//
////		int status = -1;
////		try {
////			status = this.connect.createStatement()
////					.executeUpdate(
////							"UPDATE Apprentis SET nom='" + apprenti.getNom() + ", prenom= '"
////									+ apprenti.getPrenom() + "' WHERE apprentis_id=" + apprenti.getId(),
////							Statement.SUCCESS_NO_INFO);
////		} catch (SQLException e) {
////			e.printStackTrace();
////		}
////		return status;
//	}

	public Apprenti find(int id) {
        return entityManager.find(Apprenti.class, id);

////		Apprenti apprenti = new Apprenti();
////		try {
////			ResultSet result = this.connect
////					.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)
////					.executeQuery("SELECT * FROM apprentis WHERE apprentis_id = " + id);
////			if (result.first())
////				apprenti = new Apprenti(id, result.getString("nom"), result.getString("prenom"));
////		} catch (SQLException e) {
////			e.printStackTrace();
////		}
////		return apprenti;
	}

}