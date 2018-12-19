import java.util.ArrayList;

class Sommet{
	public int valeur;
	public ArrayList<Arc> voisins = new ArrayList<Arc>();

	public Sommet(int _valeur){
		this.valeur = _valeur;
	}

	public boolean addVoisin(Sommet s,int poids){
		Arc a = new Arc(this,s,poids);
		if(!voisins.contains(a)){
			this.voisins.add(a);
			return true;
		}
		return false;
	}

	public String toString() {
		return "" + valeur;
	}
	public boolean equals(Object o){
		if(o == this) return true;
		if(!(o instanceof Sommet)) return false;
		if(this.valeur == ((Sommet) o).valeur) return true;
		return false;
	}
}