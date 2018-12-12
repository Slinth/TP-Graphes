import java.util.ArrayList;


class Graphe{
	public ArrayList<Sommets> data;
	public int nbSommets;
	public int nbArcs;

	public Graphe(int _nbSommets,int _nbArcs){
		this.nbSommets = nbSommets;
		this.nbArcs = nbArcs;
		this.construire();
	}
}