import java.util.ArrayList;

class Arc{
	public Sommet sommetSource;
	public Sommet sommetDestination;
	public int poids;

	public Arc (Sommet _sommetSource,Sommet _sommetDestination,int _poids){
		this.sommetSource = _sommetSource;
		this.sommetDestination = _sommetDestination;
		this.poids = _poids;
	}
}