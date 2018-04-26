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
		
		j1.jouerCoup(2, 0, p);
		ia.jouerCoup(1, 0, p);
		j1.jouerCoup(2, 2, p);
		
		ia.construireArbre(p, j1, 1);
		
		System.out.println(ia.getArbre());
		
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
