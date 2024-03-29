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
import java.util.Iterator;
import java.util.List;

import static java.lang.System.exit;

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
        logger.info("Trying case n°" + s);
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
                case 3:
                    case3();
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
        //Init
        int[] id1 = init1();
        int[] id2 = init2();
        int[] id3 = test1();

        Apprenti apprenti1 = tryApprenti(id1[0]);
        Entreprise entreprise1 = tryEntrepriseLazy(id1[1]);
        Apprentissage apprentissage1 = tryApprentissageLazy(id1);

        Apprenti apprenti2 = tryApprenti(id2[0]);
        Entreprise entreprise2 = tryEntrepriseEager(id2[1]);
        Apprentissage apprentissage2 = tryApprentissageLazy(id2);

        Apprenti apprenti3 = tryApprenti(id3[0]);
        Apprenti apprenti4 = tryApprenti(id3[1]);
        Apprenti apprenti5 = tryApprenti(id3[2]);
        Apprentissage apprentissage3 = tryApprentissageEager(new int[] {id3[2], id2[1]});
    }

    private static void case2() {
        //Tests
        Apprenti apprenti1 = tryApprenti(1);
        Entreprise entreprise1 = tryEntrepriseLazy(1);
        Apprentissage apprentissage1 = tryApprentissageLazy(new int[] {1,1});
        logger.debug("Apprenti test1 = " + apprenti1.equals(apprentissage1.getApprenti()));
        logger.debug("Entreprise test1 = " + entreprise1.equals(apprentissage1.getEntreprise()));
        logger.debug("Apprentissage test1 = " + apprentissage1.equals(apprenti1.getApprentissage()));

        Apprenti apprenti2 = tryApprenti(2);
        Entreprise entreprise2 = tryEntrepriseLazy(2);
        Apprentissage apprentissage2 = tryApprentissageLazy(new int[] {2,2});
        logger.debug("Apprenti test2 = " + apprenti2.equals(apprentissage2.getApprenti()));
        logger.debug("Entreprise test2 = " + entreprise2.equals(apprentissage2.getEntreprise()));
        logger.debug("Apprentissage test2 = " + apprentissage2.equals(apprenti2.getApprentissage()));

        Apprenti apprenti3 = tryApprenti(3);
        logger.debug("Entreprise test3 = " + entreprise1.equals(apprenti3.getApprentissage().getEntreprise()));
        Apprenti apprenti4 = tryApprenti(4);
        logger.debug("Entreprise test4 = " + entreprise1.equals(apprenti4.getApprentissage().getEntreprise()));

        int[] id4 = test2();

        apprenti3 = tryApprenti(id4[0]);
        apprenti4 = tryApprenti(id4[1]); //This must fail
    }

    private static void case3(){
        //Summary
        tryCount();
        tryAllApprenti();
        tryAllEntreprise();
        tryAllApprentissage();
    }

    private static int[] init1() {
        Apprenti belgacem = new Apprenti("Belgacem", "Adam");
        apprentiDAO.create(belgacem);

        Entreprise bdf = new Entreprise("Banque de France", "Paris");
        entrepriseDAO.create(bdf);

        MaitreApp manouvrier = new MaitreApp("Manouvrier", "Maude");
        Apprentissage appr1 = new Apprentissage(bdf, belgacem, manouvrier);
        apprentissageDAO.create(appr1);

        return new int[] {belgacem.getId(), bdf.getId()};
    }

    private static int[] init2() {
        Apprenti madkour = new Apprenti("Madkour", "Chafiq");
        apprentiDAO.create(madkour);

        Entreprise ca = new Entreprise("Crédit agricole", "Paris");
        entrepriseDAO.create(ca);

        MaitreApp mahjoub = new MaitreApp("Mahjoub", "Ridha");
        Apprentissage appr0 = new Apprentissage(ca, madkour, mahjoub);
        apprentissageDAO.create(appr0);

        return new int[] {madkour.getId(), ca.getId()};
    }

    private static int[] test1() {
        Entreprise bdf = entrepriseDAO.findLazy(1);
        Apprenti jean = new Apprenti("Darmery", "Jean");
        apprentiDAO.create(jean);

        MaitreApp emma = new MaitreApp("Carena", "Emma");
        Apprentissage appr1 = new Apprentissage(bdf, jean, emma);
        apprentissageDAO.create(appr1);

        Apprenti john = new Apprenti("Doeuf", "John");
        apprentiDAO.create(john);

        MaitreApp terry = new MaitreApp("Golo", "Terry");
        Apprentissage appr2 = new Apprentissage(bdf, john, terry);
        apprentissageDAO.create(appr2);

        Entreprise ca = entrepriseDAO.findEager(2);
        Apprenti anna = new Apprenti("Liz", "Anna");
        apprentiDAO.create(anna);

        Apprentissage appr3 = new Apprentissage(ca, anna, emma);
        apprentissageDAO.create(appr3);

        return new int[] {jean.getId(), john.getId(), anna.getId()};
    }

    private static int[] test2() {
        Apprenti jean = apprentiDAO.find(3);
        jean.setNom("D'Armery");
        apprentiDAO.update(jean);

        Apprenti john = apprentiDAO.find(4);
        apprentiDAO.delete(john);

        return new int[] {jean.getId(), john.getId()};
    }

    private static void tryCount() {
        logger.debug("Apprenti =" + apprentiDAO.countAll());
        logger.debug("Entreprise =" + entrepriseDAO.countAll());
        logger.debug("Apprentissage =" + apprentissageDAO.countAll());
    }

    private static void tryAllApprenti(){
        List<Apprenti> apprentis = apprentiDAO.findAll();
        for(Iterator<Apprenti> i = apprentis.iterator(); i.hasNext();) {
            logger.debug(i.next().toString());
        }
    }

    private static void tryAllEntreprise(){
        List<Entreprise> entreprises = entrepriseDAO.findAll();
        for(Iterator<Entreprise> i = entreprises.iterator(); i.hasNext();) {
            logger.debug(i.next().toString());
        }
    }

    private static void tryAllApprentissage(){
        List<Apprentissage> apprentissages = apprentissageDAO.findAll();
        for(Iterator<Apprentissage> i = apprentissages.iterator(); i.hasNext();) {
            logger.debug(i.next().toString());
        }
    }

    private static Apprenti tryApprenti(int id) {
        Apprenti test = apprentiDAO.find(id);
        logger.debug(test.toString());
        return test;
    }

    private static Entreprise tryEntrepriseLazy(int id) {
        Entreprise test = entrepriseDAO.findLazy(id);
        logger.debug(test.toString());
        logger.info("[Count Apprentissage] Java = " + test.countApprentissages());
        logger.info("[Count Apprentissage] API Criteria = " + apprentissageDAO.countByEntrepriseId(test.getId()));
        return test;
    }

    private static Apprentissage tryApprentissageLazy(int[] ids) {
        Apprentissage test = apprentissageDAO.findLazy(ids[1], ids[0]);
        logger.debug(test.toString());
        return test;
    }

    private static Entreprise tryEntrepriseEager(int id) {
        Entreprise test = entrepriseDAO.findEager(id);
        logger.debug(test.toString());
        return test;
    }

    private static Apprentissage tryApprentissageEager(int[] ids) {
        Apprentissage test = apprentissageDAO.findEager(ids[1], ids[0]);
        logger.debug(test.toString());
        return test;
    }

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
