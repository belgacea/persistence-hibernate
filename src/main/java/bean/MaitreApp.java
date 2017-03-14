package bean;

import java.util.ArrayList;

public class MaitreApp {

	private String nomMA;
	private String prenomMA;
	private ArrayList<Apprentissage> apprentissage;
	
	public MaitreApp(String nomMA, String prenomMA) {
		super();
		this.nomMA = nomMA;
		this.prenomMA = prenomMA;
		apprentissage = new ArrayList<Apprentissage>();
	}
	public MaitreApp() {
		super();
		apprentissage = new ArrayList<Apprentissage>();
	}
	public MaitreApp(String nomMA, String prenomMA, ArrayList<Apprentissage> apprentissage) {
		super();
		this.nomMA = nomMA;
		this.prenomMA = prenomMA;
		this.apprentissage = apprentissage;
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
	public ArrayList<Apprentissage> getApprentissage() {
		return apprentissage;
	}
	public void setApprentissage(ArrayList<Apprentissage> apprentissage) {
		this.apprentissage = apprentissage;
	}
	public void addApprentissage(Apprentissage Apprentissage){
		if(!this.apprentissage.contains(Apprentissage)){
			this.apprentissage.add(Apprentissage);
		}
	}
	public void removeApprentissage(Apprentissage Apprentissage){
		this.apprentissage.remove(Apprentissage);
	}
	
	public boolean equals(MaitreApp maitreApp){
		return this.apprentissage.equals(maitreApp.getApprentissage());
	}
	@Override
	public String toString() {
		return "MaitreApp [nomMA=" + nomMA + ", prenomMA=" + prenomMA + "]";
	}

}
