package bean;

import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;

@Entity(name = "apprenti")
@DynamicUpdate
public class Apprenti implements Serializable {

    @Id
    @GeneratedValue
	private int id;
    @Column(name = "nom", nullable = false)
	private String nom;
    @Column(name = "premom", nullable = false)
	private String prenom;
	
	public Apprenti() {
		super();
	}
	public Apprenti(int id) {
		super();
		this.id = id;
	}
	public Apprenti(String nom, String prenom) {
		super();
		this.nom = nom;
		this.prenom = prenom;
	}
	public Apprenti(int id, String nom, String prenom) {
		super();
		this.id = id;
		this.nom = nom;
		this.prenom = prenom;
	}
	private Apprentissage apprentissage;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String name) {
		this.nom = name;
	}
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	public Apprentissage getEntreprise() {
		return apprentissage;
	}
	public void setEntreprise(Apprentissage apprentissage) {
		this.apprentissage = apprentissage;
	}
	public boolean equals(Apprenti apprenti) {
		return this.getId() == apprenti.getId();
	}
	@Override
	public String toString() {
		return "Apprenti [id=" + id + ", nom=" + nom + ", prenom=" + prenom + "]";
	}
}
