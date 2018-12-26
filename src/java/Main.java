import java.util.ArrayList;
import java.util.Arrays;

public class Main{

	public static void main(String[] args) {
		Sommet s0 = new Sommet(0);
		Sommet s1 = new Sommet(1);
		Sommet s2 = new Sommet(2);
		Sommet s3 = new Sommet(3);
		Sommet s4 = new Sommet(4);

		s0.addVoisin(s3, 960);
		s0.addVoisin(s2, 51);
		s1.addVoisin(s4,302);
		s2.addVoisin(s1,-302);
		s2.addVoisin(s3,454);
		s3.addVoisin(s4,-246);
		s4.addVoisin(s1,-281);

		
		ArrayList<Sommet> list = new ArrayList<Sommet>();
		list.add(s0);
		list.add(s1);
		list.add(s2);
		list.add(s3);
		list.add(s4);

		Graphe g = new Graphe(5, 9, list,-500,1000);

		// Graphe g = new Graphe(5, 7,-500,1000);
		// g.init();

		g.afficherGraphe();

		System.out.println("SOMMET DEPART "+ s0);

		System.out.println();

		int[] res = g.bellmanFord(0);
		System.out.println("BELLMANFORD : ");
		afficherDistance(res);

		System.out.println();

		System.out.println("DJIKSTRA : ");
		afficherDistance(g.dijkstra(s0));

		// System.out.println("DJIKSTRA TAS BINAIRE : " + Arrays.toString(g.dijkstra_filePrio(s0)));
		
		System.out.println();

		System.out.println("JOHNSON : ");

		int distJohnson[][] = g.johnson();

		for ( int[] array : distJohnson ) {
			afficherDistance(array);
		}


	}

	public static void afficherDistance(int tab[]){
		int INFINI = Integer.MAX_VALUE;
		for(int i = 0 ; i < tab.length ; i++){
			if(tab[i]==INFINI) System.out.print(" INF");
			else System.out.print(" " + tab[i]);
		}
		System.out.println();
	}

	public static void afficherDistance(int tab[][]){
		int INFINI = Integer.MAX_VALUE;
		for(int i = 0 ; i < tab.length ; i++){
			for(int j = 0 ; j < tab.length ; j++ ){
				if(tab[i][j]==INFINI) System.out.print(" INF");
				else System.out.print(" " + tab[i][j]);
			}
					System.out.println();

		}
	}

}