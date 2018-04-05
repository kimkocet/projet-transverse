package jeu;

import java.util.ArrayList;
import java.util.Random;

public class IANiveau1 extends IA {

	public IANiveau1(int num) {
		super(num);
	}
	
	public void jouerCoupIA(Plateau plateau) {
		ArrayList<int[]> coups = coupsPossibles(plateau);
		Random random = new Random();
		int indice = random.nextInt(coups.size());
		int l = coups.get(indice)[0];
		int c = coups.get(indice)[1];
		jouerCoup(l, c, plateau);
	}
	
	public ArrayList<int[]> coupsPossibles(Plateau plateau) {
		ArrayList<int[]> resultat = new ArrayList<int[]>();
		for(int i = 0; i < Plateau.SIZE; i++) {
			for(int j = 0; j < Plateau.SIZE; j++) {
				if(coupPossible(i, j, plateau)) {
					resultat.add(new int[] {i, j});
				}
			}
		}
		return resultat;
	}
}
