import java.util.ArrayList;
import java.util.Arrays;

public class Main{

	public static void main(String[] args) {
		// Sommet s0 = new Sommet(0);
		// Sommet s1 = new Sommet(1);
		// Sommet s2 = new Sommet(2);
		// Sommet s3 = new Sommet(3);
		// Sommet s4 = new Sommet(4);
		// Sommet s5 = new Sommet(5);
		// Sommet s6 = new Sommet(6);
		// Sommet s7 = new Sommet(7);
		// Sommet s8 = new Sommet(8);
		// Sommet s9 = new Sommet(9);


		// s0.addVoisin(s2, 488);
		// s1.addVoisin(s8,-311);
		// s1.addVoisin(s9,463);
		// s2.addVoisin(s0,668);
		// s2.addVoisin(s6,701);
		// s3.addVoisin(s0,973);
		// s3.addVoisin(s1,428);
		// s3.addVoisin(s5,-253);
		// s3.addVoisin(s8,304);
		// s4.addVoisin(s2,-487);
		// s4.addVoisin(s3,975);
		// s4.addVoisin(s9,34);
		// s5.addVoisin(s6,706);
		// s6.addVoisin(s1,585);
		// s6.addVoisin(s3,115);
		// s6.addVoisin(s9,-422);
		// s7.addVoisin(s2,187);
		// s7.addVoisin(s3,386);
		// s8.addVoisin(s0,-92);
		// s8.addVoisin(s2,110);
		// s8.addVoisin(s7,-118);
		// s9.addVoisin(s1,502);
		// s9.addVoisin(s5,258);
		// s9.addVoisin(s6,424);
		// s9.addVoisin(s7,871);

		
		// ArrayList<Sommet> list = new ArrayList<Sommet>();
		// list.add(s0);
		// list.add(s1);
		// list.add(s2);
		// list.add(s3);
		// list.add(s4);
		// list.add(s5);
		// list.add(s6);
		// list.add(s7);
		// list.add(s8);
		// list.add(s9);
		
		// Graphe g = new Graphe(10, 25, list,-500,1000);
		
		Graphe g = new Graphe(60, 75,0,1000);
		g.init();
		Sommet s0 = g.data.get(0);

		g.afficherGraphe();

		System.out.println("SOMMET DEPART "+ s0);

		System.out.println();
		

		long debut = System.currentTimeMillis(); 

		int[] res = g.bellmanFord(0);

		System.out.println("BELLMANFORD : ");
		afficherDistance(res);
		long tmps = System.currentTimeMillis()-debut;
		System.out.println("Temps d'execution : " + tmps + "ms");

		System.out.println();

		debut = System.currentTimeMillis(); 
		System.out.println("DJIKSTRA : ");
		afficherDistance(g.dijkstra(s0));
		tmps = System.currentTimeMillis()-debut;
		System.out.println("Temps d'execution : " + tmps + "ms");


		//System.out.println("DJIKSTRA TAS BINAIRE : \n " + Arrays.toString(g.dijkstra_filePrio(s0)));
		
		System.out.println();

		System.out.println("JOHNSON : ");
		debut = System.currentTimeMillis(); 
		int distJohnson[][] = g.johnson();
		tmps = System.currentTimeMillis()-debut;
		System.out.println("Temps d'execution : " + tmps + "ms");
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