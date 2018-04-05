package jeu;

public abstract class IA extends Joueur {
	
	IA(int num) {
		super(num);
	}
	
	public abstract void jouerCoupIA(Plateau plateau);
}
