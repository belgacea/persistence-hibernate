package com.dauphine.domain;

import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@Entity
@Table(name = "apprentissage")
@DynamicUpdate
public class Apprentissage {

    @ManyToOne
    @JoinTable(name = "entreprise")
    private Entreprise entreprise;

    @OneToOne
    @JoinTable(name = "apprenti")
    private Apprenti apprenti;

    private MaitreApp maitreApp;

//    public Apprentissage() {
//        super();
//    }

    public Apprentissage(Entreprise entreprise, Apprenti apprenti, MaitreApp maitreApp) {
        super();
        this.entreprise = entreprise;
        this.apprenti = apprenti;
        this.maitreApp = maitreApp;
    }

    @Id
    public int getApprentiId() {
        return apprenti.getId();
    }

    @Id
    public int getEntrepriseId(){
        return entreprise.getId();
    }

    @Column(name = "nomMA")
    public String getNomMA() {
        return maitreApp.getNomMA();
    }

    @Column(name = "prenomMA")
    public String getPrenomMA() {
        return maitreApp.getPrenomMA();
    }

    public Entreprise getEntreprise() {
        return entreprise;
    }

    public void setEntreprise(Entreprise entreprise) {
        this.entreprise = entreprise;
    }

    public Apprenti getApprenti() {
        return apprenti;
    }

    public void setApprenti(Apprenti apprenti) {
        this.apprenti = apprenti;
    }

    public MaitreApp getMaitreApp() {
        return maitreApp;
    }

    public void setMaitreApp(MaitreApp maitreApp) {
        this.maitreApp = maitreApp;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Apprentissage that = (Apprentissage) o;

        if (!getEntreprise().equals(that.getEntreprise())) return false;
        return getApprenti().equals(that.getApprenti());
    }

    @Override
    public int hashCode() {
        int result = getEntreprise().hashCode();
        result = 31 * result + getApprenti().hashCode();
        return result;
    }
}
