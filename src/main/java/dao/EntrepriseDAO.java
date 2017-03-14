package dao;

import bean.Apprentissage;
import bean.Entreprise;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;

public class EntrepriseDAO extends DAOGeneric<Entreprise> {

    @PersistenceContext(unitName = "TODO", type = PersistenceContextType.EXTENDED)
    private EntityManager entityManager;

    public EntrepriseDAO() {
    }

    public int create(Entreprise entreprise) {
        entityManager.persist(entreprise);
        return 0;

//		int entrepriseId = -1;
//		try {
//			Statement s = this.connect.createStatement();
//			s.executeUpdate("INSERT INTO Entreprise" + " VALUES(nextval('entreprise_entreprise_id_seq'), '"
//							+ entreprise.getNom() + "','" + entreprise.getAdresse() + "')",
//					Statement.RETURN_GENERATED_KEYS);
//			ResultSet result = s.executeQuery("Select last_value FROM entreprise_entreprise_id_seq");
//			if(result.next()){
//				entrepriseId = result.getInt(1);
//				entreprise.setId(entrepriseId);
//			} else throw new SQLException("No serial created");
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		return entrepriseId;
    }

    public int delete(Entreprise entreprise) {
        entityManager.remove(entreprise);
        return 0;

//		int status = -1;
//		try {
//			status = this.connect.createStatement().executeUpdate(
//					"DELETE FROM Entreprise" + " WHERE entreprise_id = " + entreprise.getId(), Statement.SUCCESS_NO_INFO);
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		return status;
    }

    public int update(Entreprise entreprise) {
        return entityManager.merge(entreprise).getId();

//		int status = -1;
//		try {
//			status = this.connect.createStatement()
//					.executeUpdate(
//							"UPDATE Entreprise SET nom='" + entreprise.getNom() + ", adresse= '"
//									+ entreprise.getAdresse() + "' WHERE entreprise_id=" + entreprise.getId(),
//							Statement.SUCCESS_NO_INFO);
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		return status;
    }

    public Entreprise findLazy(int id) {
        //TODO
        return null;

//        Entreprise entreprise = new Entreprise();
//        try {
//            ResultSet result = this.connect
//                    .createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)
//                    .executeQuery("SELECT * FROM entreprise WHERE entreprise_id = " + id);
//            if (result.first())
//                entreprise = new Entreprise(id, result.getString("nom"), result.getString("adresse"));
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return entreprise;
    }

    public Entreprise findEater(int id) {
        //TODO
        return null;

//        Entreprise entreprise = new Entreprise();
//        try {
//
//            //On aura: nom, adresse de l'entreprise
//            ResultSet result = this.connect
//                    .createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)
//                    .executeQuery("SELECT * FROM entreprise WHERE entreprise_id = " + id);
//
//
//            if (result.first()) {
//                entreprise = new Entreprise(id, result.getString("nom"), result.getString("adresse"));
//
//                //On aura: la liste des apprentissage qui sont dans l'entreprise id
//                ResultSet result1 = this.connect
//                        .createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)
//                        .executeQuery("SELECT * FROM apprentissage WHERE entreprise_id = " + id);
//
//                while (result1.next()) {
//                    int apprenti_id = result.getInt(1);
//                    ApprentissageDAO apprentissageDAO = new ApprentissageDAO(this.connect);
//                    apprentissageDAO.findEater(id, apprenti_id);
//                }
//            }
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return entreprise;
    }

}