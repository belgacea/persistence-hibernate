package com.dauphine.dao;

import com.dauphine.domain.Entreprise;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;

@Transactional
public class EntrepriseDAO extends DAO<Entreprise> {

    private EntityManager entityManager;

    public EntrepriseDAO(EntityManager em) {
        super(em);
        this.entityManager = em;
    }

    public List<Entreprise> findAll() {
        return entityManager.createQuery("Select e From Entreprise e").getResultList();
    }

    public Entreprise findLazy(int id) {
        return entityManager.find(Entreprise.class, id);
    }

//    public Entreprise findEater(int id) {
//        //TODO
//        return null;
//
////        Entreprise entreprise = new Entreprise();
////        try {
////
////            //On aura: nom, adresse de l'entreprise
////            ResultSet result = this.connect
////                    .createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)
////                    .executeQuery("SELECT * FROM entreprise WHERE entreprise_id = " + id);
////
////
////            if (result.first()) {
////                entreprise = new Entreprise(id, result.getString("nom"), result.getString("adresse"));
////
////                //On aura: la liste des apprentissage qui sont dans l'entreprise id
////                ResultSet result1 = this.connect
////                        .createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)
////                        .executeQuery("SELECT * FROM apprentissage WHERE entreprise_id = " + id);
////
////                while (result1.next()) {
////                    int apprenti_id = result.getInt(1);
////                    ApprentissageDAO apprentissageDAO = new ApprentissageDAO(this.connect);
////                    apprentissageDAO.findEater(id, apprenti_id);
////                }
////            }
////
////        } catch (SQLException e) {
////            e.printStackTrace();
////        }
////        return entreprise;
//    }

}