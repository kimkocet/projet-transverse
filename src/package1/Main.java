package package1;

import java.util.Random;
import java.util.Scanner;

import jeu.IA;
import jeu.IANiveau1;
import jeu.IANiveau3;
import jeu.Joueur;
import jeu.Plateau;
import vue.Affichage;

public class Main {
<<<<<<< HEAD
	
=======
>>>>>>> ef1df9c9bdd4664d7976fb379991a0950c90be75
	public static void menu() {
		System.out.println("Choix du niveau (1, 2 ou 3) : ");
		Scanner sc = new Scanner(System.in);
		int niveau;
		do {
			niveau = sc.nextInt();
		} while(niveau < 1 || niveau > 3);
		Plateau p = new Plateau();
		System.out.println(p);
<<<<<<< HEAD
=======
		
		IANiveau3 ia = new IANiveau3(2);
>>>>>>> ef1df9c9bdd4664d7976fb379991a0950c90be75
		Joueur j1 = new Joueur(1);
		IA ia;
		switch(niveau) {
			case 1: {
				System.out.println("Souhaitez-vous commencer ?\n1. Oui\n2. Non\n3. Je ne sais pas");
				int choix;
				do {
					choix = sc.nextInt();
				} while(niveau < 1 || niveau > 3);
				System.out.println(p);
				ia = new IANiveau1(2);
				switch(choix) {
					case 1:
						p.jouerIANiveau1J(j1, ia);
						break;
					case 2:
						p.jouerIANiveau1IA(j1, ia);
						break;
					case 3:
						Random random = new Random();
						int rand = random.nextInt(2);
						if(rand == 0) {
							p.jouerIANiveau1J(j1, ia);
						}
						else if(rand == 1) {
							p.jouerIANiveau1IA(j1, ia);
						}
						break;
				}
				break;
			}
			case 2:
				System.out.println("Souhaitez-vous commencer ?\n1. Oui\n2. Non\n3. Je ne sais pas");
				int choix2;
				do {
					choix2 = sc.nextInt();
				} while(niveau < 1 || niveau > 3);
				System.out.println(p);
				ia = new IANiveau3(2);
				switch(choix2) {
					case 1:
						p.jouerIANiveau2J(j1, (IANiveau3)ia);
						break;
					case 2:
						p.jouerIANiveau2IA(j1, (IANiveau3)ia);
						break;
					case 3:
						Random random = new Random();
						int rand = random.nextInt(2);
						if(rand == 0) {
							p.jouerIANiveau2J(j1, (IANiveau3)ia);
						}
						else if(rand == 1) {
							p.jouerIANiveau2IA(j1, (IANiveau3)ia);
						}
						break;
				}
				break;
			case 3:
				System.out.println("Souhaitez-vous commencer ?\n1. Oui\n2. Non\n3. Je ne sais pas");
				int choix3;
				do {
					choix3 = sc.nextInt();
				} while(niveau < 1 || niveau > 3);
				System.out.println(p);
				ia = new IANiveau3(2);
				switch(choix3) {
					case 1:
						p.jouerIANiveau3J(j1, (IANiveau3)ia);
						break;
					case 2:
						p.jouerIANiveau3IA(j1, (IANiveau3)ia);
						break;
					case 3:
						Random random = new Random();
						int rand = random.nextInt(2);
						if(rand == 0) {
							p.jouerIANiveau3J(j1, (IANiveau3)ia);
						}
						else if(rand == 1) {
							p.jouerIANiveau3IA(j1, (IANiveau3)ia);
						}
						break;
				}
				break;
		}
		
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

<<<<<<< HEAD
		//menu();
		Plateau p = new Plateau();
		IANiveau3 ia = new IANiveau3(2);
		Joueur j1 = new Joueur(1);
		Affichage a = new Affichage(p, ia, j1);
=======
		menu();
	
	
>>>>>>> ef1df9c9bdd4664d7976fb379991a0950c90be75
	}

}
