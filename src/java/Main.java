import java.util.ArrayList;
import java.util.Arrays;

public class Main{
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
		s3.addVoisin(s2, -2);
		s2.addVoisin(s4, 9);
		s4.addVoisin(s0, 2);
		s4.addVoisin(s3, 7);

		ArrayList<Sommet> list = new ArrayList<Sommet>();
		list.add(s0);
		list.add(s1);
		list.add(s2);
		list.add(s3);
		list.add(s4);

		Graphe g = new Graphe(5, 9, list,0,1000);

		g.afficherGraphe();

		int[] res = g.bellmanFord(0);
		System.out.println("BELLMANFORD : " + Arrays.toString(res));

		System.out.println("DJIKSTRA : " + Arrays.toString(g.dijkstra(s0)));
	}
}