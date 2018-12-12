#include <stdio.h>
#include <stdlib.h>

typedef struct maillon{
	int indice;
	struct maillon *suivant;
} MAILLON;

typedef struct {
	int n;	//Nb sommets
	int m; 	//Nb arcs
	MAILLON **G;
} GRAPHE;

void initGraphe(int n,int m,GRAPHE* G){
		int cpt = 0,sommetSource,sommetDestination;
		while(cpt < m)	{
			sommetSource =  rand()%n;
			sommetDestination = rand()%n;

			cpt ++;
		}
}
int main(int argc, char const *argv[])
{
	/* code */
	return 0;
}