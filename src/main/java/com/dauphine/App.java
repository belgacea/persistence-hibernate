package com.dauphine;

import com.dauphine.dao.ApprentiDAO;
import com.dauphine.dao.ApprentissageDAO;
import com.dauphine.dao.EntrepriseDAO;
import com.dauphine.domain.Apprenti;
import com.dauphine.domain.Apprentissage;
import com.dauphine.domain.Entreprise;
import com.dauphine.domain.MaitreApp;
import org.apache.log4j.Logger;

import javax.persistence.*;

import static java.lang.System.exit;

/**
 * @author belgacea
 * @date 25/03/2017
 */
public class App {

    private static final Logger logger = Logger.getLogger(App.class);

    @PersistenceUnit
    private static final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("PersistancePostgres");
    private static final EntityManager entityManager = entityManagerFactory.createEntityManager();

    private static final EntrepriseDAO entrepriseDAO = new EntrepriseDAO(entityManager);
    private static final ApprentiDAO apprentiDAO = new ApprentiDAO(entityManager);
    private static final ApprentissageDAO apprentissageDAO = new ApprentissageDAO(entityManager);

    public static void main(String[] args) {
        logger.info("Start running...");
        if (args.length <= 0) {
            incorrectUsage();
        }
//        if (args.length == 2 && args[1].equals("clean")) {
//            logger.debug("Cleaning database.");
//            dropDatabase();
//        }
        int s = Integer.parseInt(args[0]);
        logger.info("Trying the case n°" + s);
        try {
            switch (s){
                case 0:
                    break;
                case 1:
                    case1();
                    break;
                case 2:
                    case2();
                    break;
                default:
                    throw new RuntimeException("Case not define");
            }
        } catch (Exception e) {
            logger.error("Something really bad happened : ", e);
        } finally {
            logger.info("Stop running...");
        }
        exit(0);
    }

    private static void case1() {
        int[] id1 = init1();
        tryApprenti(id1[1]);
    }

    private static void case2() {
        int[] id2 = init2();
        tryApprenti(id2[1]);
    }

    private static int[] init1() {
        Entreprise bdf = new Entreprise("Banque de France", "Paris");
        Apprenti belgacem = new Apprenti("Belgacem", "Adam");
        MaitreApp manouvrier = new MaitreApp("Manouvrier", "Maude");
        Apprentissage appr1 = new Apprentissage(bdf, belgacem, manouvrier);

        entrepriseDAO.create(bdf);
        apprentiDAO.create(belgacem);
        apprentissageDAO.create(appr1);

        logger.debug("IDA = "+ belgacem.getId());
        logger.debug("IDE = "+ bdf.getId());

        return new int[] {belgacem.getId(), bdf.getId()};
    }

    private static int[] init2() {
        Entreprise ca = new Entreprise("Crédit agricole", "Paris");
        Apprenti madkour = new Apprenti("Madkour", "Chafiq");
        MaitreApp mahjoub = new MaitreApp("Mahjoub", "Ridha");
        Apprentissage appr0 = new Apprentissage(ca, madkour, mahjoub);

        entrepriseDAO.create(ca);
        apprentiDAO.create(madkour);
        apprentissageDAO.create(appr0);

        logger.debug("IDA = "+ madkour.getId());
        logger.debug("IDE = "+ ca.getId());

        return new int[] {madkour.getId(), ca.getId()};
    }

    private static void tryApprenti(int id) {
        Apprenti test = apprentiDAO.find(id);
        logger.info(test.toString());
    }

//    private static void tryEntreprise(int id) {
//        //TODO
//    }
//
//    private static void tryApprentissage(int[] ids) {
//        //TODO
//    }

    private static void incorrectUsage() {
        logger.error("\nIncorrect number of arguments.\nUsage:\n "+
                "java   \n");
        exit(1);
    }

    private static void dropDatabase() {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            logger.info("Dropping database schema.");
            entityManager.createNativeQuery("DROP SCHEMA public CASCADE").executeUpdate();
            entityManager.createNativeQuery("CREATE SCHEMA public").executeUpdate();
            entityManager.createNativeQuery("GRANT ALL ON SCHEMA public TO postgres").executeUpdate();
        } catch (RuntimeException e){
            logger.error(e);
            if (transaction.isActive()){
                transaction.rollback();
            }
        } finally {
            if(transaction.isActive()) {
                transaction.commit();
            }
            logger.info("Database schema dropped.");
        }
    }
}
