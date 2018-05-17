package package1;

import jeu.IANiveau3;
import jeu.Joueur;
import jeu.Plateau;
import vue.Affichage;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("hello");
		Joueur j1 = new Joueur(1);
	
		Plateau p = new Plateau();

		//Affichage a = new Affichage(p);
		


		
		IANiveau3 ia = new IANiveau3(2);
		ia.jouerCoup(2, 0, p);
		System.out.println(p);
		p.jouerIANiveau2(j1, ia);
//		System.out.println(p);
//		Affichage a = new Affichage(p,ia, j1);
		//Affichage a = new Affichage(p);
	
	
	}

}
