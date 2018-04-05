package package1;

import jeu.IANiveau1;
import jeu.Joueur;
import jeu.Plateau;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("hello");
		
		Plateau p = new Plateau();
		System.out.println(p);
		
		Joueur j1 = new Joueur(1);
		IANiveau1 ia = new IANiveau1(2);
		
		p.jouerIA(j1, ia);
	
	}

}
