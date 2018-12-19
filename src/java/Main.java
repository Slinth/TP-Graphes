import java.util.ArrayList;
import java.util.Arrays;

public class Main{

	public static void main(String[] args) {
		// Sommet s0 = new Sommet(0);
		// Sommet s1 = new Sommet(1);
		// Sommet s2 = new Sommet(2);
		// Sommet s3 = new Sommet(3);
		// Sommet s4 = new Sommet(4);

		// s0.addVoisin(s1, 6);
		// s0.addVoisin(s2, 7);
		// s1.addVoisin(s2, 8);
		// s1.addVoisin(s4, -4);
		// s1.addVoisin(s3, 5);
		// s3.addVoisin(s2, -2);
		// s2.addVoisin(s4, 9);
		// s4.addVoisin(s0, 2);
		// s4.addVoisin(s3, 7);

		// ArrayList<Sommet> list = new ArrayList<Sommet>();
		// list.add(s0);
		// list.add(s1);
		// list.add(s2);
		// list.add(s3);
		// list.add(s4);

		// Graphe g = new Graphe(5, 9, list,0,1000);

		Graphe g = new Graphe(5, 9,1,1000);
		g.init();
		g.afficherGraphe();
		Sommet s0 = g.data.get(0);

		int[] res = g.bellmanFord(0);
		System.out.println("BELLMANFORD : ");
		afficherDistance(res);

		System.out.println("DJIKSTRA : ");
		afficherDistance(g.dijkstra(s0));

		// System.out.println("DJIKSTRA TAS BINAIRE : " + Arrays.toString(g.dijkstra_filePrio(s0)));
		
		System.out.println("JOHNSON : ");
		int[][] distance = g.johnson();
		for(int i = 0 ; i < g.nbSommets ; i++){
			for(int j = 0 ; j < g.nbSommets ; j++){
				System.out.print(distance[i][j] + " | ");
			}
			System.out.println();
		}


	}

	public static void afficherDistance(int tab[]){
		int INFINI = Integer.MAX_VALUE;
		System.out.println("MAX VALUE " + INFINI);
		for(int i = 0 ; i < tab.length ; i++){
			if(tab[i]==INFINI) System.out.print(" INF");
			else System.out.print(" " + tab[i]);
		}
		System.out.println();
	}
}