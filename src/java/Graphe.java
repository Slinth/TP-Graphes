import java.util.ArrayList;


class Graphe{
	public ArrayList<Sommet> data = new ArrayList();
	public int nbSommets;
	public int nbArcs;
	public static int poidsMax = 100;
	public static int poidsMin = 1;

	public Graphe(int _nbSommets, int _nbArcs){
		this.nbSommets = _nbSommets;
		this.nbArcs = _nbArcs;
	}

	public Graphe(int _nbSommets, int _nbArcs, ArrayList<Sommet> sommets) {
		this.nbSommets = _nbSommets;
		this.nbArcs = _nbArcs;
		this.data = sommets;
	}

	public void init(){
		for(int i = 0 ; i < nbSommets ; i++){
			data.add(new Sommet(i));
		}

		int cptArcs = 0;

		while(cptArcs < this.nbArcs){
			int indSommetSource = (int)(Math.random() * this.nbSommets );
			int indSommetDestination = (int)(Math.random() * this.nbSommets );
			int poids = poidsMin + (int)(Math.random() * ((poidsMax - poidsMin) + 1));;
			if(indSommetDestination != indSommetSource){
				
				Sommet sSource = data.get(indSommetSource);
				Sommet sDesti = data.get(indSommetDestination);
				Arc a = new Arc (sSource,sDesti,poids);
				sSource.addVoisin(sDesti,poids);
				System.out.println("Arc entre " + indSommetSource + " et " + indSommetDestination + " de poids " + poids );
				cptArcs ++ ;
			}
		}
	}


	public int[] dijkstra(Sommet s){
		int d[] = int[n];
		int inf = 99999;
		ArrayList<Sommet> m = new ArrayList();

		for(int i = 1 ; i < this.nbSommets ; i++) d[i] = inf;

		d[s.valeur] = 0;
		m.add(s);

		while(m.size() != this.nbSommets){
			//Sommet appartenant pas a m ayant d(x) minimale
			Sommet sMin = null;
			for ( Sommet s : this.data ) {
				if(!m.contains(s)){
					if(sMin == null)sMin = s;
					else if(d[sMin.valeur] > d[s.valeur])sMin = s;
				}
			}

			m.add(sMin);

			for (Sommet s : this.data) {
				for (Arc a : s.voisins) {
					int x = a.sommetSource.valeur;
					int y = a.sommetDestination.valeur;
					int p = a.poids;
					d[y] = Math.min(d[y], p + d[x]);
				}		
			}
		}

		return d;	
	}
}