import java.util.Arrays;

class TasBinaire {
    public Sommet[] data;
    public int taille;

    public TasBinaire(int _taille) {
        this.taille = _taille;
        this.data = new Sommet[_taille];
    }

    public TasBinaire(Sommet _data[], int _taille) {
        this.data = _data;
        this.taille = _taille;
    }

    public int getParentIndex(int i) {
        return i / 2;
    }

    public Sommet getParent(int i) {
        return data[(i/2)];
    }

    public void insererSommet(Sommet s) {
        // Agrandit tableau si necessaire
        if (this.taille >= data.length - 1) {
            data = this.resize();
        }        
        
        // Place element en bas du tas
        taille++;
        int index = taille;
        data[index] = s;
        
        bubbleUp();
    }
    
    /**
     * Renvoie TRUE si le tas est vide, FALSE sinon
     */
    public boolean isEmpty() {
        return (taille == 0);
    }

    /**
     * Renvoie (mais ne retire pas) l'element minimum du tas
     */
    public Sommet peek() {
        if (this.isEmpty()) {
            throw new IllegalStateException();
        }
        
        return data[1];
    }

    /**
     * Retire et renvoie l'element minimum du tas
     */
    public Sommet remove() {
    	Sommet res = peek();
    	
        // supprimer derniere feuille / decremente
    	data[1] = data[taille];
    	data[taille] = null;
    	taille--;
    	
    	bubbleDown();
    	
    	return res;
    }

    public String toString() {
        return Arrays.toString(data);
    }

    /**
     * Place l'element qui est a la racine du tas a la bonne place
     * pour maintenir l'ordre de la file de priorite
     */
    public void bubbleDown() {
        int index = 1;
        
        // bubble down
        while (aFilsGauche(index)) {
            // Recuperation de l'index du plus petit fils
            int smallerChild = index * 2;
            
            // Si un plus petit fils existe, echange des places
            if (aFilsDroit(index)
                && !(data[index * 2].equals(data[index *2 + 1]))) {
                smallerChild = index * 2 + 1;
            } 
            
            if (!(data[index].equals(data[smallerChild]))) {
                swap(index, smallerChild);
            } else {
                // sinon sortie du IF
                break;
            }

            index = smallerChild;
        }        
    }

    /**
     * Place un nouvel element (i.e. l'element a l'index taille) 
     * a la bonne place pour maintenir la file de priorité
     */
    public void bubbleUp() {
        int index = this.taille;
        
        while (aParent(index)
                && (!(getParent(index).equals(data[index])))) {
            // Parent/Enfant pas a la bonne place -> Echange
            swap(index, getParentIndex(index));
            index = getParentIndex(index);
        }        
    }
    
    /**
     * Renvoie TRUE si l'element a l'indice i a un parent dans le tas, FALSE sinon
     */
    public boolean aParent(int i) {
        return i > 1;
    }    
    
    /**
     * Renvoie TRUE si l'element a l'indice i a un fils gauche dans le tas, FALSE sinon
     */
    public boolean aFilsGauche(int i) {
        return ((i * 2) <= taille);
    }
    
    /**
     * Renvoie TRUE si l'element a l'indice i a un fils droit dans le tas, FALSE sinon
     */
    public boolean aFilsDroit(int i) {
        return ((i * 2 + 1) <= taille);
    }
    
    /**
     * Renvoie une copie du tableau avec une taille 2 fois plus grande
     */
    public Sommet[] resize() {
        return Arrays.copyOf(data, data.length * 2);
    }
    
    /**
     * Echange l'element d'indice index1 et l'element d'indice index2 dans le tas
     */
    public void swap(int index1, int index2) {
        Sommet tmp = data[index1];
        data[index1] = data[index2];
        data[index2] = tmp;        
    }

    public int getIndex(Sommet s) {
        int i = 0;
        int res = 0;
        boolean trouve = false;
        while (i < this.taille && !trouve) {
            if (this.data[i].equals(s)) {
                res = i;
                trouve = true;
            }
        }
        return res;
    }


}