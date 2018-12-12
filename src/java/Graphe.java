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

	public void init(){
		for(int i = 0 ; i < nbSommets ; i++){
			data.add(new Sommet(i));
		}

		int cptArcs = 0;

		while(cptArcs < this.nbArcs){
			int indSommetSource = (int)(Math.random() * this.nbSommets );
			int indSommetDestination = (int)(Math.random() * this.nbSommets );
			int poids = poidsMin + (int)(Math.random() * ((poidsMax - poidsMin) + 1));;
			System.out.println("Arc entre " + indSommetSource + " et " + indSommetDestination + " de poids " + poids );
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
}