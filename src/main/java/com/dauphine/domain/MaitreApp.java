package com.dauphine.domain;

import java.util.ArrayList;
import java.util.List;

public class MaitreApp {

	private String nomMA;
	private String prenomMA;
	private ArrayList<Apprentissage> apprentissages;
	
	public MaitreApp(String nomMA, String prenomMA) {
		super();
		this.nomMA = nomMA;
		this.prenomMA = prenomMA;
		this.apprentissages = new ArrayList<Apprentissage>();
	}
	public MaitreApp() {
		super();
		apprentissages = new ArrayList<Apprentissage>();
	}
	public MaitreApp(String nomMA, String prenomMA, List<Apprentissage> apprentissages) {
		super();
		this.nomMA = nomMA;
		this.prenomMA = prenomMA;
		this.apprentissages = (ArrayList<Apprentissage>) apprentissages;
	}

	public String getNomMA() {
		return nomMA;
	}

	public void setNomMA(String nomMA) {
		this.nomMA = nomMA;
	}

	public String getPrenomMA() {
		return prenomMA;
	}

	public void setPrenomMA(String prenomMA) {
		this.prenomMA = prenomMA;
	}

	public List<Apprentissage> getApprentissages() {
		return apprentissages;
	}

	public void setApprentissages(List<Apprentissage> apprentissages) {
		this.apprentissages = (ArrayList<Apprentissage>) apprentissages;
	}

	public void addApprentissage(Apprentissage apprentissage){
		if(!this.apprentissages.contains(apprentissage)){
			this.apprentissages.add(apprentissage);
		}
	}

	public void removeApprentissage(Apprentissage apprentissage){
		this.apprentissages.remove(apprentissage);
	}
	
//	public boolean equals(MaitreApp maitreApp){
//		return this.apprentissages.equals(maitreApp.getApprentissages());
//	}

	@Override
	public String toString() {
		return "MaitreApp [nomMA=" + nomMA + ", prenomMA=" + prenomMA + "]";
	}

}
