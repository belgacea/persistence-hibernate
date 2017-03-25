package com.dauphine.domain;

import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "entreprise")
@DynamicUpdate
public class Entreprise {

    @Id
    @GeneratedValue
    private int id;

    @Column(name = "nom", nullable = false)
    private String nom;

    @Column(name = "adresse")
    private String adresse;

    @OneToMany
    private ArrayList<Apprentissage> apprentissages;

    public Entreprise() {
        apprentissages = new ArrayList<Apprentissage>();
    }

    public Entreprise(int id) {
        super();
        this.id = id;
        apprentissages = new ArrayList<Apprentissage>();
    }

    public Entreprise(int id, String nom, String adresse) {
        this.id = id;
        this.nom = nom;
        this.adresse = adresse;
        apprentissages = new ArrayList<Apprentissage>();
    }

    public Entreprise(String nom, String adresse) {
        super();
        this.nom = nom;
        this.adresse = adresse;
        apprentissages = new ArrayList<Apprentissage>();
    }

    public void addApprentissage(Apprentissage apprentissage) {
        if (!this.apprentissages.contains(apprentissage))
            this.apprentissages.add(apprentissage);
    }

    public void removeApprentissage(Apprentissage apprentissage) {
        this.apprentissages.remove(apprentissage);
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

    public List<Apprentissage> getApprentissages() {
        return apprentissages;
    }

    public void setApprentissages(List<Apprentissage> apprentissages) {
        this.apprentissages = (ArrayList<Apprentissage>) apprentissages;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Entreprise that = (Entreprise) o;

        return getId() == that.getId();
    }

    @Override
    public int hashCode() {
        return getId();
    }

    @Override
    public String toString() {
        return "Entreprise [id=" + id + ", nom=" + nom + ", adresse=" + adresse + "]";
    }


}
