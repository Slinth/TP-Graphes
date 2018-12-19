class Arc{
	public Sommet sommetSource;
	public Sommet sommetDestination;
	public int poids;

	public Arc (Sommet _sommetSource,Sommet _sommetDestination,int _poids){
		this.sommetSource = _sommetSource;
		this.sommetDestination = _sommetDestination;
		this.poids = _poids;
	}

	public String toString() {
		return "[ " + sommetSource + ", " + sommetDestination + " | " + poids + " ]";
	}

	public boolean equals(Object o){
		if(o == this) return true;
		if(!(o instanceof Arc)) return false;
		if(((this.sommetSource == ((Arc) o).sommetSource))&&((this.sommetDestination == ((Arc) o).sommetDestination))) return true;
		return false;
	}
}