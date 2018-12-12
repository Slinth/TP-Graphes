import java.util.ArrayList;
import java.util.Arrays;


class Graphe{
	public ArrayList<Sommet> data = new ArrayList();
	public int nbSommets;
	public int nbArcs;
	public static int poidsMax = 100;
	public static int poidsMin = 1;
	public static int INFINI = 99999;

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

	public void afficherGraphe() {
		for (Sommet s : this.data) {
			System.out.print(s.valeur + " | ");
			for (Arc a : s.voisins) {
				System.out.print(a.toString() + " ");
			}
			System.out.println();
		}
	}

	public int[] bellmanFord(int source) {
		int n = this.nbSommets;
		int distance[] = new int[n];

		// Initalisation de toutes les distances a INT_MAX (+ INF) sauf source (= 0)
		for (int i = 0; i < n; i++) {
			distance[i] = INFINI;
		}
		distance[source] = 0;
		System.out.println("Initialisation distances : " + Arrays.toString(distance));

		// Determination des distances minimales
		for (int i = 1; i < n; i++) {
			for (Sommet s : this.data) {
				for (Arc a : s.voisins) {
					int u = a.sommetSource.valeur;
					int v = a.sommetDestination.valeur;
					int p = a.poids;
					distance[v] = Math.min(distance[v], p + distance[u]);
				}
			}
		}
		

		// Test presence circuit absorbant
		for (Sommet s : this.data) {
			for (Arc a : s.voisins) {
				int u = a.sommetSource.valeur;
				int v = a.sommetDestination.valeur;
				int p = a.poids;

				if (distance[u] + p < distance[v]) {
					System.err.println("PRESENCE D'UN CIRCUIT ABSORBANT");
					throw new RuntimeException("Presence d'un circuit absorbant");
				}
			}			
		}

		return distance;
	}


	public int[] dijkstra(Sommet s){
		int n = this.nbSommets;
		int d[] = new int[n];
		ArrayList<Sommet> m = new ArrayList();

		for(int i = 1 ; i < this.nbSommets ; i++) d[i] = INFINI;

		d[s.valeur] = 0;
		m.add(s);

		while(m.size() != this.nbSommets){
			//Sommet appartenant pas a m ayant d(x) minimale
			Sommet sMin = null;
			for (Sommet so : this.data) {
				if(!m.contains(s)){
					if(sMin == null)sMin = s;
					else if(d[sMin.valeur] > d[s.valeur])sMin = s;
				}
			}

			m.add(sMin);

			for (Sommet som : this.data) {
				for (Arc a : som.voisins) {
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