package package1;

import jeu.IANiveau1;
import jeu.Joueur;
import jeu.Plateau;
import vue.Affichage;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("hello");
	
		Plateau p = new Plateau();
		Affichage a = new Affichage(p);
		System.out.println(p);
		
		Joueur j1 = new Joueur(1);
		
//		Scanner sc = new Scanner(System.in);
//		int l;
//		int c;
//		do {
//			System.out.println("entrer la ligne suivie de la colonne du coup � jouer : ");
//			l = sc.nextInt();
//			c = sc.nextInt();
//		}while(l < 0 || l > 3 || c < 0 || c > 3);
//		
//		j1.jouerCoup(l, c, p);
//		System.out.println(p);
//		Affichage a = new Affichage();
		
		IANiveau1 ia = new IANiveau1(2);
		p.jouerIA(j1, ia);
	
	}

}
