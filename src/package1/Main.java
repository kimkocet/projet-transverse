package package1;

import jeu.IANiveau3;
import jeu.Joueur;
import jeu.Plateau;
import vue.Affichage;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Joueur j1 = new Joueur(1);
	
		Plateau p = new Plateau();

		System.out.println(p);
		
		IANiveau3 ia = new IANiveau3(2);
		p.jouerIANiveau3(j1, ia);

	}

}
