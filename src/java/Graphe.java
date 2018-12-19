import java.util.ArrayList;

class Graphe{
	public ArrayList<Sommet> data = new ArrayList<Sommet>();
	public int nbSommets;
	public int nbArcs;
	public int poidsMax;
	public int poidsMin;
	public static int INFINI = 99999;

	public Graphe(int _nbSommets, int _nbArcs) {
		this.nbSommets = _nbSommets;
		this.nbArcs = _nbArcs;
		this.poidsMin = 0;
		this.poidsMax = 100;
	}

	public Graphe(int _nbSommets, int _nbArcs, int _poidsMin, int _poidsMax){
		this.nbSommets = _nbSommets;
		this.nbArcs = _nbArcs;
		this.poidsMin = _poidsMin;
		this.poidsMax = _poidsMax;
	}

	public Graphe(int _nbSommets, int _nbArcs, ArrayList<Sommet> sommets, int _poidsMin, int _poidsMax) {
		this.nbSommets = _nbSommets;
		this.nbArcs = _nbArcs;
		this.data = sommets;
		this.poidsMin = _poidsMin;
		this.poidsMax = _poidsMax;
	}

	public void init() {
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
		//System.out.println("Initialisation distances : " + Arrays.toString(distance));

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
		int n = this.data.size();
		int d[] = new int[n];
		ArrayList<Sommet> m = new ArrayList<Sommet>();

		for(int i = 1 ; i < this.nbSommets ; i++) d[i] = INFINI;

		d[s.valeur] = 0;
		m.add(s);

		while(m.size() != this.nbSommets){
			//Sommet appartenant pas a m ayant d(x) minimale
			Sommet sMin = null;
			for (Sommet so : this.data) {
				if(!m.contains(so)){
					if(sMin == null)sMin = so;
					else if(d[sMin.valeur] > d[s.valeur])sMin = so;
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

	public int[] dijkstra(Sommet s, int[][] couts){
		int n = this.data.size();
		int d[] = new int[n];
		ArrayList<Sommet> m = new ArrayList<Sommet>();

		for(int i = 1 ; i < this.nbSommets ; i++) d[i] = INFINI;

		d[s.valeur] = 0;
		m.add(s);

		while(m.size() != this.nbSommets){
			//Sommet appartenant pas a m ayant d(x) minimale
			Sommet sMin = null;
			for (Sommet so : this.data) {
				if(!m.contains(so)) {
					if(sMin == null)sMin = so;
					else if(d[sMin.valeur] > d[s.valeur])sMin = so;
				}
			}

			m.add(sMin);

			for (Sommet som : this.data) {
				for (Arc a : som.voisins) {
					int x = a.sommetSource.valeur;
					int y = a.sommetDestination.valeur;
					int p = couts[x][y];
					d[y] = Math.min(d[y], p + d[x]);
				}		
			}
		}

		return d;	
	}

	public int[][] getTabCouts(){
		int[][] c = new int[this.nbSommets][this.nbSommets];

		for ( Sommet s : data ) {
			for ( Arc a : s.voisins ) {
				c[a.sommetSource.valeur][a.sommetDestination.valeur] = a.poids;
			}
		}

		return c;
	}
	/**
	 * Algorithme de Johnson
	 * @return Matrice ou M[i][j] =  coût du plus court chemin de i à j
	 */
	public int[][] johnson(){
		int[][] c = this.getTabCouts();

		int[][] distance = new int[this.nbSommets+1][this.nbSommets+1];

		Sommet q = new Sommet(this.nbSommets);

		for ( Sommet s :this.data ) {
			q.addVoisin(s,0);
		}

		ArrayList<Sommet> data2 = this.data;
		data2.add(q);

		Graphe g2 = new Graphe(data2.size(),this.nbArcs+this.nbSommets,data2,this.poidsMin,this.poidsMax);

		int[] h = g2.bellmanFord(q.valeur-1);

		int[][] c2 = new int[this.nbSommets+1][this.nbSommets+1];

		for (int i = 0 ; i < this.nbSommets ; i++ ) {
			for(int j = 0 ; j <this.nbSommets ; j++ ){
				c2[i][j] = c[i][j] - h[i] + h[j];
			}
		}

		for (Sommet som : this.data ) {
			int[] d = this.dijkstra(som,c2);
			for ( Sommet sommet : this.data) {
				distance[som.valeur][sommet.valeur] = d[sommet.valeur] + h[sommet.valeur] - h[som.valeur];
			}
		}

		return distance;
	}
}