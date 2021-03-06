import java.util.ArrayList;
import java.util.*;
/**
 * Classe Graphe permettant de créer des graphes
 */
class Graphe{
	/**
	 * Liste contenant les sommets du graphe
	 */
	public ArrayList<Sommet> data = new ArrayList<Sommet>();
	
	/**
	 * Nombre de sommets dans le graphe
	 */
	public int nbSommets;

	/**
	 * Nombre d'arcs dans le graphe
	 */
	public int nbArcs;

	/**
	 * Valeur du poids Maximal d'un arc
	 */
	public int poidsMax;

	/**
	 * Valeur du poids Minial d'un arc
	 */
	public int poidsMin;

	/**
	 * Constante correspondant à infini
	 */
	public static int INFINI = 99999;


	/**
	 * Constructeur principal d'un graphe. Construit un graphe aléatoire à _nbSommets et _nbArcs
	 * @param  _nbSommets Nombre de sommets
	 * @param  _nbArcs    Nombre d'arcs
	 */
	public Graphe(int _nbSommets, int _nbArcs) {
		this.nbSommets = _nbSommets;
		this.nbArcs = _nbArcs;
		this.poidsMin = 0;
		this.poidsMax = 100;
	}

	/**
	 * Constructeur personnalisé d'un graphe. Construit un graphe aléatoire à _nbSommets et _nbArcs en prennant en compte des préférences de poids pour les arcs
	 * @param  _nbSommets Nombre de sommets
	 * @param  _nbArcs    Nombre d'arcs
	 * @param _poidsMin	  Poids minimal pour les arcs
	 * @param _poidsMax	  Poids Maxiaml pour les arcs
	 */
	public Graphe(int _nbSommets, int _nbArcs, int _poidsMin, int _poidsMax){
		this.nbSommets = _nbSommets;
		this.nbArcs = _nbArcs;
		this.poidsMin = _poidsMin;
		this.poidsMax = _poidsMax;
	}

	/**
	 * Constructeur personnalisé d'un graphe. Construit un graphe aléatoire à _nbSommets et _nbArcs en prennant en compte des préférences de poids pour les arcs
	 * @param  _nbSommets Nombre de sommets
	 * @param  _nbArcs    Nombre d'arcs
	 * @param sommets 	  Liste des sommets de du graphe
	 * @param _poidsMin	  Poids minimal pour les arcs
	 * @param _poidsMax	  Poids Maxiaml pour les arcs
	 */
	public Graphe(int _nbSommets, int _nbArcs, ArrayList<Sommet> sommets, int _poidsMin, int _poidsMax) {
		this.nbSommets = _nbSommets;
		this.nbArcs = _nbArcs;
		this.data = sommets;
		this.poidsMin = _poidsMin;
		this.poidsMax = _poidsMax;
	}

	/**
	 * Initialisation d'un graphe aléatoirement
	 */
	public void init() {

		//Ajout de nbSommets au graphe
		for(int i = 0 ; i < nbSommets ; i++){
			data.add(new Sommet(i));
		}

		int cptArcs = 0;

		//Création des arcs en fonction du nombre max
		while(cptArcs < this.nbArcs){
			//Récupération d'un sommet source aléatoirement
			int indSommetSource = (int)(Math.random() * this.nbSommets );
			//Récupération d'un sommet destination aléatoirement
			int indSommetDestination = (int)(Math.random() * this.nbSommets );
			//Génération du poids de l'arc aléatoirement entre deux bornes
			int poids = poidsMin + (int)(Math.random() * ((poidsMax - poidsMin) + 1));;
			
			//Création de l'arc
			if(indSommetDestination != indSommetSource){
				Sommet sSource = data.get(indSommetSource);
				Sommet sDesti = data.get(indSommetDestination);
				if(sSource.addVoisin(sDesti,poids)){
					//System.out.println("Arc entre " + indSommetSource + " et " + indSommetDestination + " de poids " + poids );
					cptArcs ++ ;
				}
			}
		}
	}

	/**
	 * Affiche le graphe
	 */
	public void afficherGraphe() {
		for (Sommet s : this.data) {
			System.out.print(s.valeur + " | ");
			for (Arc a : s.voisins) {
				System.out.print(a.toString() + " ");
			}
			System.out.println();
		}
	}

	/**
	 * Applique l'algorithme de Bellman Ford sur le graphe
	 * @param  source Sommet source
	 * @return        Tableau contenant les valeurs des pcc du sommet source vers tout sommet du graphe
	 */
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
					if((distance[v] == distance[u]) && (distance[v] == INFINI)) {
						distance[v] = INFINI;
					} else {
						distance[v] = Math.min(distance[v], p + distance[u]);
					}
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

	/**
	 * Applique l'algorithme de Dijkstra sur le graphe
	 * @param  source Sommet source
	 * @return        Tableau contenant les valeurs des pcc du sommet source vers tout sommet du graphe
	 */
	public int[] dijkstra(Sommet s){
		int n = this.data.size();
		int d[] = new int[n];
		ArrayList<Sommet> m = new ArrayList<Sommet>();


		//Initialisation du tableau à +infini
		for(int i = 1 ; i < this.nbSommets ; i++) d[i] = INFINI;

		d[s.valeur] = 0;
		m.add(s);

		while(m.size() != this.nbSommets){
			//Récupération du sommet appratenant à m ayant un d[x] minimal 
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

	/**
	 * Applique l'algorithme de Dijkstra sur le graphe avec tableau des coûts
	 * @param  source Sommet source
	 * @param  couts  Matrice des coûts
	 * @return        Tableau contenant les valeurs des pcc du sommet source vers tout sommet du graphe
	 */
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

	/**
	 * Algorithme de Dijkstra utilisant une file de priorité en tas binaire
	 * @param  s Sommet de départ
	 * @return   Pour chaque sommets x , la plus courte distance de s à x sous forme de tableau
	 */
	public int[] dijkstra_filePrio(Sommet s){
		//Déclaration d'une file de priorité de taille maximale "nbSommets"
		TasBinaire file = new TasBinaire(this.nbSommets);
		//Déclaration d'une matrice des distances de taille "nbSommets"
		int[] distances = new int[this.nbSommets];
		//Insertion du sommet source dans la file
		file.insererSommet(s);
		//Déclaration de la liste des sommets visités
		ArrayList<Sommet> visite = new ArrayList<Sommet>();

		//Initialisation des distances à +infini
		for(int i = 0 ; i < this.nbSommets ; i++) distances[i] = INFINI;

		distances[s.valeur] = 0;

		//Récupération du noeud en tête de file
		Sommet n = file.remove();

		while(n != null){
			//Marque n comme visité
			visite.add(n);
			System.out.println(n.valeur + " marqué comme visité");
			//Récupération des voisins de n
			ArrayList<Arc> voisins = n.voisins;

			for (Arc a : voisins) {

				Sommet tmp = a.sommetDestination;
				int poids = a .poids;

				//Si le sommet n'a pas déjà été visité
				if(!visite.contains(tmp)){
					//On insert le sommet dans la file sans le marqué comme visité, son état est "dans la queue"
					System.out.println("SOMMET " + tmp.valeur + " entre en file");
					file.insererSommet(tmp);
				}


				if(distances[tmp.valeur] < distances[n.valeur] + poids){
					distances[tmp.valeur] = distances[n.valeur] + poids;
					System.out.println(tmp);
					file.bubbleUp(file.getIndex(tmp));
				}
			}

			n = file.remove();
		}
		return distances;
	}		

	/**
	 * Retourne la matrice des coûts d'un graphe
	 * @return Matrice a deux dimensions correspondant a la matrices des coûts du graphe
	 */
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
		int distance[][] = new int[this.nbSommets][this.nbSommets];

		//Création du graphe G1 = G où on a ajouté un sommet s et des arcs entre s et tout sommet de G
		ArrayList<Sommet> dataG1 = new ArrayList<Sommet>();
		dataG1.addAll(this.data);

		Sommet s = new Sommet(this.nbSommets);
		for ( Sommet tmp : dataG1) {
			s.addVoisin(tmp,0);
		}

		dataG1.add(s);

		Graphe G1 = new Graphe(this.nbSommets+1, this.nbArcs+this.nbSommets,dataG1, this.poidsMin, this.poidsMax);
		int h[] = G1.bellmanFord(s.valeur);

		int c[][] = this.getTabCouts();
		int c2[][] = new int[this.nbSommets][this.nbSommets];

		for (Sommet som : this.data ) {
			for (Arc a : som.voisins ) {
				int x = a.sommetSource.valeur;
				int y = a.sommetDestination.valeur;
				c2[x][y] = c[x][y] - h[x] + h[y];
			}
		}

		for (Sommet x : this.data ) {
			int d[] = this.dijkstra(x,c2);
			
			for (Sommet y : this.data ) {
				distance[x.valeur][y.valeur] = d[y.valeur] + h[y.valeur] - h [x.valeur];
			}
		}
		return distance;
		
	}



}