import java.util.ArrayList;

class Sommet{
	public int valeur;
	public ArrayList<Arc> voisins = new ArrayList<Arc>();

	public Sommet(int _valeur){
		this.valeur = _valeur;
	}

	public void addVoisin(Sommet s,int poids){
		this.voisins.add(new Arc(this,s,poids));
	}

	public String toString() {
		return "" + valeur;
	}

}