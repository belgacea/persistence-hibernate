package bean;

import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "apprentissage")
@DynamicUpdate
public class Apprentissage {
    //TODO composite key
    private Entreprise entreprise;
    private Apprenti apprenti;
    private MaitreApp maitreApp;

    public Apprentissage() {
        super();
    }

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

    public boolean equals(Apprentissage apprentissage) {
        return (
                (this.entreprise.getId() == apprentissage.getEntreprise().getId()) &&
                        (this.apprenti.getId() == apprentissage.getApprenti().getId())
        );

    }

}
