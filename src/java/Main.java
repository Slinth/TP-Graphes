import java.util.ArrayList;
import java.util.Arrays;

public class Main{
	static int INFINI = 99999;

	public static int[] bellmanFord(Graphe G, int source) {
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
				int u = a.sommetSource.valeur;
				int v = a.sommetDestination.valeur;
				int p = a.poids;
				distance[som] = Math.min(distance[v], p + distance[u]);
			}
		}


		// Test presence circuit absorbant
		for (Sommet s : G.data) {
			for (Arc a : s.voisins) {
				int u = a.sommetSource.valeur;
				int v = a.sommetDestination.valeur;
				int p = a.poids;

				if (distance[u] + p < distance[v]) {
					//throw new RuntimeException("Presence d'un circuit absorbant");
					System.out.println("PRESENCE D'UN CIRCUIT ABSORBANT");
				}
			}			
		}

		return distance;
	}

	public static void main(String[] args) {
		Sommet s0 = new Sommet(0);
		Sommet s1 = new Sommet(1);
		Sommet s2 = new Sommet(2);
		Sommet s3 = new Sommet(3);
		Sommet s4 = new Sommet(4);

		s0.addVoisin(s1, 6);
		s0.addVoisin(s2, 7);
		s1.addVoisin(s2, 8);
		s1.addVoisin(s4, -4);
		s1.addVoisin(s3, 5);
		s3.addVoisin(s1, -2);
		s2.addVoisin(s3, -3);
		s2.addVoisin(s4, 9);
		s4.addVoisin(s0, 2);
		s4.addVoisin(s3, 7);

		ArrayList<Sommet> list = new ArrayList<Sommet>();
		list.add(s0);
		list.add(s1);
		list.add(s2);
		list.add(s3);
		list.add(s4);

		Graphe g = new Graphe(5, 10, list);

		int[] res = bellmanFord(g, 0);
		System.out.println(Arrays.toString(res));
	}
}