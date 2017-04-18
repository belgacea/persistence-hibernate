package com.dauphine.domain;

import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "apprenti")
@DynamicUpdate
public class Apprenti implements Serializable {

	private int id;

	private String nom;

	private String prenom;

    private Apprentissage apprentissage;
	
	public Apprenti() {}

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

	@Id
    @Column(name = "apprenti_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

    @Column(name = "nom", nullable = false)
	public String getNom() {
		return nom;
	}

	public void setNom(String name) {
		this.nom = name;
	}

    @Column(name = "premom", nullable = false)
	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

    @OneToOne(mappedBy = "apprenti", cascade = CascadeType.ALL, orphanRemoval = true)
	public Apprentissage getApprentissage() {
		return apprentissage;
	}

	public void setApprentissage(Apprentissage apprentissage) {
		this.apprentissage = apprentissage;
	}

	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Apprenti apprenti = (Apprenti) o;
        return getId() == apprenti.getId();
    }

    @Override
    public int hashCode() {
        return getId();
    }

    @Override
	public String toString() {
		return "Apprenti [id=" + id + ", nom=" + nom + ", prenom=" + prenom + "]";
	}
}
