package package1;

import jeu.IANiveau3;
import jeu.Joueur;
import jeu.Plateau;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("hello");
	
		Plateau p = new Plateau();
		//Affichage a = new Affichage(p);
		System.out.println(p);
		

		
		Joueur j1 = new Joueur(1);
		
		IANiveau3 ia = new IANiveau3(2);
		
//		j1.jouerCoup(0, 1, p);
//		ia.jouerCoup(0, 0, p);
//		j1.jouerCoup(3, 0, p);
//		ia.jouerCoup(3, 2, p);
		System.out.println(p);
		p.jouerIANiveau3(j1, ia);
		
//		Arbre<Plateau> arbre = new Arbre<Plateau>(p.clone());
//		ArrayList<int[]> coups = j1.coupsPossibles(p);
//		for(int i = 0; i < coups.size(); i++) {
//			Plateau enfant = new Plateau(p);
//			enfant.setCase(coups.get(i)[0], coups.get(i)[1], j1.getNumero());
//			arbre.addEnfant(new Arbre<Plateau>(enfant));
//		}
//		System.out.println(arbre);
		
		//p.jouerIA(j1, ia);
		
		
	
	}

}
