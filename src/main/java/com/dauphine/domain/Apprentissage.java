package com.dauphine.domain;

import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "apprentissage")
@DynamicUpdate
public class Apprentissage implements Serializable {

    private ApprentissageId apprentissageId;

    private MaitreApp maitreApp;

    public Apprentissage() {
        this.maitreApp = new MaitreApp();
    }

    public Apprentissage(Entreprise entreprise, Apprenti apprenti, MaitreApp maitreApp) {
        super();
        this.apprentissageId = new ApprentissageId(entreprise, apprenti);
        this.maitreApp = maitreApp;
    }

    @EmbeddedId
    public ApprentissageId getId(){
        return apprentissageId;
    }

    public void setId(ApprentissageId id){
        this.apprentissageId = id;
    }

    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "entreprise_id", referencedColumnName = "entreprise_id", nullable = false, insertable = false, updatable = false)
    public Entreprise getEntreprise() {
        return apprentissageId.getEntreprise();
    }

    public void setEntreprise(Entreprise entreprise) {
        this.apprentissageId.setEntreprise(entreprise);
    }

    @OneToOne(optional = false)
    @JoinColumn(name = "apprenti_id", referencedColumnName = "apprenti_id", nullable = false, insertable = false, updatable = false)
    public Apprenti getApprenti() {
        return apprentissageId.getApprenti();
    }

    public void setApprenti(Apprenti apprenti) {
        this.apprentissageId.setApprenti(apprenti);
    }

    @Column(name = "nomMA")
    public String getNomMA() {
        return maitreApp.getNomMA();
    }

    @Column(name = "prenomMA")
    public String getPrenomMA() {
        return maitreApp.getPrenomMA();
    }

    public void setNomMA(String nomMA) {
        maitreApp.setNomMA(nomMA);
    }

    public void setPrenomMA(String prenomMA) {
        maitreApp.setPrenomMA(prenomMA);
    }

    @Transient
    public MaitreApp getMaitreApp() {
        return maitreApp;
    }

    public void setMaitreApp(MaitreApp maitreApp) {
        this.maitreApp = maitreApp;
    }

    @Transient
    public int getApprentiId() {
        return apprentissageId.getApprenti().getId();
    }

    @Transient
    public int getEntrepriseId(){
        return apprentissageId.getEntreprise().getId();
    }

    @Override
    public boolean equals(Object o) {
        return apprentissageId.equals(o);
    }

    @Override
    public int hashCode() {
        return apprentissageId.hashCode();
    }

    @Override
    public String toString() {
        return "Apprentissage [" + apprentissageId.getEntreprise().toString() +
                ", " + apprentissageId.getApprenti().toString() +
                ", " + maitreApp.toString() +
                ']';
    }

    @Embeddable
    public static class ApprentissageId implements Serializable {

        private Entreprise entreprise;

        private Apprenti apprenti;

        /**
         * Default contructor for the composite primary key
         */
        public ApprentissageId(){
            this.entreprise = new Entreprise();
            this.apprenti = new Apprenti();
        }

        public ApprentissageId(Entreprise entreprise, Apprenti apprenti){
            this.entreprise = entreprise;
            this.apprenti = apprenti;
        }

        @Transient
        public Entreprise getEntreprise() {
            return entreprise;
        }

        public void setEntreprise(Entreprise entreprise) {
            this.entreprise = entreprise;
        }

        @Transient
        public Apprenti getApprenti() {
            return apprenti;
        }

        public void setApprenti(Apprenti apprenti) {
            this.apprenti = apprenti;
        }

        @Column(name = "entreprise_id", nullable = false, updatable = false)
        public int getEntrepriseId(){
            return entreprise.getId();
        }

        @Column(name = "apprenti_id", nullable = false, updatable = false)
        public int getApprentiId() {
            return apprenti.getId();
        }

        public void setEntrepriseId(int id) {
            entreprise.setId(id);
        }

        public void setApprentiId(int id) {
            apprenti.setId(id);
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

}
