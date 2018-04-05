package package1;

import java.util.Scanner;

import jeu.Joueur;
import jeu.Plateau;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("hello");
		
		Plateau p = new Plateau();
		System.out.println(p);
		
		Joueur j1 = new Joueur(1);
		Joueur j2 = new Joueur(2);
		
		Scanner sc = new Scanner(System.in);
		int l;
		int c;
		do {
			System.out.println("entrer la ligne suivie de la colonne du coup à jouer : ");
			l = sc.nextInt();
			c = sc.nextInt();
		}while(l < 0 || l > 3 || c < 0 || c > 3);
		
		j1.jouerCoup(l, c, p);
		System.out.println(p);
		
		sc.close();
	}

}
