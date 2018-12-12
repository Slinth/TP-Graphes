public class Main{
	static int INFINI = 99999;

	public static int[] bellmanFord(Graphe G, int couts[], int source) {
		int n = G.nbSommets;
		int distance[] = new int[n];

		// Initalisation de toutes les distances a INT_MAX (+ INF) sauf source (= 0)
		for (int i = 0; i < n; i++) {
			distance[i] = INFINI;
		}
		distance[source] = 0;

		// Determination des distances minimales
		for (Sommet s : G.data) {
			int som = s.valeur;
			for (Arc a : s.voisins) {
				int u = a.sommetSource;
				int v = a.sommetDestination;
				int p = a.poids;
				distance[som] = Math.min(distance[v], p + distance[u]);
			}
		}


		// Test presence circuit absorbant
		for (Sommet s : G.data) {
			int som = s.valeur;
			for (Arc a : s.voisins) {
				int u = a.sommetSource.valeur;
				int v = a.sommetDestination.valeur;
				int p = a.poids;

				if (distance[u] + p < distance[v]) {
					throw new RuntimeException("Presence d'un circuit absorbant");
				}
			}			
		}

		return distance;
	}

	public static void main(String[] args) {
	}
}