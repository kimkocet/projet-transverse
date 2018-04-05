package jeu;

public class Joueur {
	int numero;
	int[] coupPrecedent = new int[2];
	
	public Joueur(int num) {
		this.numero = num;
		coupPrecedent[0] = -2;
		coupPrecedent[1] = -2 ;
	}
	
	public int getNumero() {
		return numero;
	}
	
	// place un pion sur la ligne 'l' et la colonne 'c'
	public boolean jouerCoup(int l, int c, Plateau plateau) {
		if(coupPossible(l,c, plateau)) {
			plateau.setCase(l, c, this.numero);
			coupPrecedent[0] = l;
			coupPrecedent[1] = c;
			return true;
		}
		else {
			System.out.println("Ce coup ne peut pas être joué !");
			return false;
		}
	}
	
	public boolean coupPossible(int l, int c, Plateau plateau) {
		// si la case est déjà occupée
		if(plateau.getPlateau()[l][c] != 0) return false;
		// cas du premier coup (ne peut pas être joué sur les 4 cases du milieu)
		if(coupPrecedent[0] == -2 && coupPrecedent[1] == -2) {
			if((l == 1 || l == 2) && (c == 1 || c == 2)) {
				return false;
			}
		}
		// comparaison au coup précédent (on ne peut pas jouer dans les cases adjacentes)
		if(l == coupPrecedent[0]-1 && c == coupPrecedent[1]-1 ||
			l == coupPrecedent[0]-1 && c == coupPrecedent[1] ||
			l == coupPrecedent[0]-1 && c == coupPrecedent[1]+1 ||
			l == coupPrecedent[0] && c == coupPrecedent[1]-1 ||
			l == coupPrecedent[0] && c == coupPrecedent[1]+1 ||
			l == coupPrecedent[0]+1 && c == coupPrecedent[1]-1 ||
			l == coupPrecedent[0]+1 && c == coupPrecedent[1] ||
			l == coupPrecedent[0]+1 && c == coupPrecedent[1]+1) return false;
		return true;
	}
}
