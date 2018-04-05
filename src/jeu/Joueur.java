package jeu;

public class Joueur {
	int numero;
	int[] coupPrecedent = new int[2];
	
	public Joueur(int num) {
		this.numero = num;
		coupPrecedent[0] = -2;
		coupPrecedent[1] = -2 ;
	}
	
	// place un pion sur la ligne 'l' et la colonne 'c'
	public void jouerCoup(int l, int c, Plateau plateau) {
		if(coupPossible(l,c, plateau)) {
			int[][] p = new int[4][4];
			p[l][c] = numero;
			plateau.setPlateau(p);
			coupPrecedent[0] = l;
			coupPrecedent[1] = c;
		}
		else {
			System.out.println("Ce coup ne peut pas être joué ! Veuillez choisir une autre case");
		}
	}
	
	public boolean coupPossible(int l, int c, Plateau plateau) {
		if(plateau.getPlateau()[l][c] != 0) return false;
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
