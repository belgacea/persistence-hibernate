package bean;

import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.ArrayList;

@Entity(name = "entreprise")
@DynamicUpdate
public class Entreprise {

    @Id
    @GeneratedValue
    private int id;
    @Column(name = "nom", nullable = false)
    private String nom;
    @Column(name = "adresse")
    private String adresse;
    private ArrayList<Apprentissage> Apprentissage;

    public Entreprise() {
        Apprentissage = new ArrayList<Apprentissage>();
    }

    public Entreprise(int id) {
        super();
        this.id = id;
        Apprentissage = new ArrayList<Apprentissage>();
    }

    public Entreprise(int id, String nom, String adresse) {
        this.id = id;
        this.nom = nom;
        this.adresse = adresse;
        Apprentissage = new ArrayList<Apprentissage>();
    }

    public Entreprise(String nom, String adresse) {
        super();
        this.nom = nom;
        this.adresse = adresse;
        Apprentissage = new ArrayList<Apprentissage>();
    }

    public void addApprentissage(Apprentissage Apprentissage) {
        if (!this.Apprentissage.contains(Apprentissage))
            this.Apprentissage.add(Apprentissage);
    }

    public void removeApprentissage(Apprentissage Apprentissage) {
        this.Apprentissage.remove(Apprentissage);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        if (nom == null) {
            return "";
        }
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public ArrayList<Apprentissage> getApprentissage() {
        return Apprentissage;
    }

    public void setApprentissage(ArrayList<Apprentissage> Apprentissage) {
        this.Apprentissage = Apprentissage;
    }

    public boolean equals(Entreprise entreprise) {
        return this.getId() == entreprise.getId();
    }

    @Override
    public int hashCode() {
        return id;
    }

    @Override
    public String toString() {
        return "Entreprise [id=" + id + ", nom=" + nom + ", adresse=" + adresse + "]";
    }


}
