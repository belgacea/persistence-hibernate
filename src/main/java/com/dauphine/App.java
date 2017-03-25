package com.dauphine;

import com.dauphine.dao.ApprentiDAO;
import com.dauphine.dao.ApprentissageDAO;
import com.dauphine.dao.EntrepriseDAO;
import com.dauphine.domain.Apprenti;
import com.dauphine.domain.Apprentissage;
import com.dauphine.domain.Entreprise;
import com.dauphine.domain.MaitreApp;
import org.apache.log4j.Logger;

import static java.lang.System.exit;

/**
 * @author belgacea
 * @date 25/03/2017
 */
public class App {

    private static final Logger logger = Logger.getLogger(App.class);

    private static EntrepriseDAO entrepriseDAO = new EntrepriseDAO();
    private static ApprentiDAO apprentiDAO = new ApprentiDAO();
    private static ApprentissageDAO apprentissageDAO = new ApprentissageDAO();

    public static void main(String[] args) {
        //TODO
        if (args.length != 3) {
            incorrectUsage();
        }
        int s = Integer.parseInt(args[0]);
        try {
            switch (s){
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
            logger.error(e.getMessage());
            logger.error(e.getStackTrace());
        }

    }

    private static void case1() {
        int[] id1 = init1();
        tryApprenti(id1[1]);
    }

    private static void case2() {
        int[] id2 = init2();
        tryApprenti(id2[2]);
    }

    private static int[] init1() {
        Entreprise bdf = new Entreprise("Banque de France", "Paris");
        Apprenti belgacem = new Apprenti("Belgacem", "Adam");
        MaitreApp manouvrier = new MaitreApp("Manouvrier", "Maude");
        Apprentissage appr1 = new Apprentissage(bdf, belgacem, manouvrier);

        entrepriseDAO.create(bdf);
        apprentiDAO.create(belgacem);
        apprentissageDAO.create(appr1);

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

        return new int[] {madkour.getId(), ca.getId()};
    }

    private static void tryApprenti(int id) {
        apprentiDAO.find(id);
    }

    private static void tryEntreprise(int id) {
        //TODO
    }

    private static void tryApprentissage(int[] ids) {
        //TODO
    }

    public static void incorrectUsage() {
        logger.error("\nIncorrect number of arguments.\nUsage:\n "+
                "java   \n");
        exit(1);
    }
}
