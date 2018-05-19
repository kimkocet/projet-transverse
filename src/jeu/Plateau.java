package jeu;

import java.util.ArrayList;
import java.util.Scanner;

import vue.Affichage;

public class Plateau implements Cloneable {

	public static final int SIZE = 4;
	int[][] plateau = new int[SIZE][SIZE];

	
	public Plateau() {
		for(int i = 0 ; i < SIZE ; i++)
		{
			for(int j = 0 ; j < SIZE ; j++) {
				plateau[i][j] = 0;
			}
		}
	}
	
	public Plateau(Plateau p) {
		for(int i = 0; i < SIZE; i++) {
			for(int j = 0; j < SIZE; j++) {
				this.plateau[i][j] = p.getPlateau()[i][j];
			}
		}
	}
	
	public int[][] getPlateau() {
		return plateau;
	}

	public void setPlateau(int[][] plateau) {
		for(int i = 0; i < SIZE; i++) {
			for(int j = 0; j < SIZE; j++) {
				this.plateau[i][j] = plateau[i][j];
			}
		}
	}
	
	public void setCase(int l, int c, int valeur) {
		try {
			plateau[l][c] = valeur;
		} catch(IndexOutOfBoundsException e) {
			e.printStackTrace();
		}
	}
	
	public String toString() {
		String s = "";
		for(int i = 0 ; i < SIZE ; i++) {
			s+="[ ";
			for(int j = 0 ; j < SIZE ; j++) {
				s += plateau[i][j];
				s += " ";
			}
			s+="]\n";
		}
		return s;
	}
	
	public void jouer2Joueurs(Joueur j1, Joueur j2) {
		Scanner sc = new Scanner(System.in);
		int l;
		int c;
		
		boolean coup;
		do {
			do {
				do {
					System.out.println("entrer la ligne suivie de la colonne du coup à jouer (joueur 1) : ");
					l = sc.nextInt();
					c = sc.nextInt();
				}while(l < 0 || l > 3 || c < 0 || c > 3);
				coup = j1.jouerCoup(l, c, this);
			} while(!coup);
			System.out.println(this);
			if(!jeuTermine(j1, j2)) {
				do {
					do {
						System.out.println("entrer la ligne suivie de la colonne du coup à jouer (joueur 2) : ");
						l = sc.nextInt();
						c = sc.nextInt();
					} while(l < 0 || l > 3 || c < 0 || c > 3);
					coup = j2.jouerCoup(l, c, this);
				} while(!coup);
				System.out.println(this);
				}
				else {
					break;
				}
		} while(!jeuTermine(j1, j2));
		sc.close();
	}
	
	// cas ou le joueur commence
	public void jouerIANiveau1J(Joueur j1, IA ia) {
		Scanner sc = new Scanner(System.in);
		int l;
		int c;
		
		boolean coup;
		do {
			do {
				do {
					System.out.println("entrer la ligne suivie de la colonne du coup à jouer (joueur 1) : ");
					l = sc.nextInt();
					c = sc.nextInt();
				} while(l < 0 || l > 3 || c < 0 || c > 3);
				coup = j1.jouerCoup(l, c, this);
			} while(!coup);
			System.out.println(this);
			if(!jeuTermine(j1, ia)) {
				ia.jouerCoupIA(this);
				System.out.println(this);
			}
			else {
				break;
			}
		} while(!jeuTermine(j1, ia));
		sc.close();
	}
	



	// cas ou l'IA commence
	public void jouerIANiveau1IA(Joueur j1, IA ia) {
		Scanner sc = new Scanner(System.in);
		int l;
		int c;
		
		boolean coup;
		do {

			ia.jouerCoupIA(this);
			System.out.println(this);
			if(!jeuTermine(j1, ia)) {
				do {
					do {
						System.out.println("entrer la ligne suivie de la colonne du coup à jouer (joueur 1) : ");
						l = sc.nextInt();
						c = sc.nextInt();
					} while(l < 0 || l > 3 || c < 0 || c > 3);
					coup = j1.jouerCoup(l, c, this);
				} while(!coup);
				System.out.println(this);
			}
			else {
				break;
			}
		} while(!jeuTermine(j1, ia));
		sc.close();
	}
	
	// cas ou le joueur commence
	public void jouerIANiveau2J(Joueur j1, IANiveau3 ia) {
		Scanner sc = new Scanner(System.in);
		int l;
		int c;
		int profondeur = 16;
		boolean coup;
		do {
			do {
				
					System.out.println("entrer la ligne suivie de la colonne du coup à jouer (joueur 1) : ");
					l = sc.nextInt();
					c = sc.nextInt();
					coup = j1.jouerCoup(l, c, this);
			} while(!coup);
			System.out.println(this);
			if(!jeuTermine(j1, ia)) {
				ia.jouerCoupIANiveau3(this, j1, profondeur);
				System.out.println(this);
				if(!jeuTermine(j1, ia)) {
					do {
						do {
							System.out.println("entrer la ligne suivie de la colonne du coup à jouer (joueur 1) : ");
							l = sc.nextInt();
							c = sc.nextInt();
						} while(l < 0 || l > 3 || c < 0 || c > 3);
						coup = j1.jouerCoup(l, c, this);
					} while(!coup);
					coup = j1.jouerCoup(l, c, this);
					System.out.println(this);
					if(!jeuTermine(j1, ia)) {
						ia.jouerCoupIA(this);
						System.out.println(this);
					}
					else {
						break;
					}
				}
				else {
					break;
				}
			}
			else {
				break;
			}
			profondeur -= 2;
		} while(!jeuTermine(j1, ia));
		sc.close();
	}
	
	// cas ou l'IA commence
	public void jouerIANiveau2IA(Joueur j1, IANiveau3 ia) {
		Scanner sc = new Scanner(System.in);
		int l;
		int c;
		int profondeur = 16;
		boolean coup;
		do {
			ia.jouerCoupIANiveau3(this, j1, profondeur);
			System.out.println(this);
			if(!jeuTermine(j1, ia)) {
				do {
					do {
						System.out.println("entrer la ligne suivie de la colonne du coup à jouer (joueur 1) : ");
						l = sc.nextInt();
						c = sc.nextInt();
					} while(l < 0 || l > 3 || c < 0 || c > 3);
					coup = j1.jouerCoup(l, c, this);
				} while(!coup);
				coup = j1.jouerCoup(l, c, this);
				System.out.println(this);
				if(!jeuTermine(j1, ia)) {
					ia.jouerCoupIA(this);
					System.out.println(this);
					if(!jeuTermine(j1, ia)) {
						do {
							do {
								System.out.println("entrer la ligne suivie de la colonne du coup à jouer (joueur 1) : ");
								l = sc.nextInt();
								c = sc.nextInt();
							} while(l < 0 || l > 3 || c < 0 || c > 3);
							coup = j1.jouerCoup(l, c, this);
						} while(!coup);
						System.out.println(this);
					}
					else {
						break;
					}
				}
				else {
					break;
				}
			}
			else {
				break;
			}
			profondeur -= 2;
		} while(!jeuTermine(j1, ia));
		sc.close();
	}
	
	// cas ou le joueur commence
	public void jouerIANiveau3J(Joueur j1, IANiveau3 ia) {
		Scanner sc = new Scanner(System.in);
		int l;
		int c;
		int profondeur = 16;
		boolean coup;
		do {
			do {
				do {
					System.out.println("entrer la ligne suivie de la colonne du coup à jouer (joueur 1) : ");
					l = sc.nextInt();
					c = sc.nextInt();
				} while(l < 0 || l > 3 || c < 0 || c > 3);
				coup = j1.jouerCoup(l, c, this);
			} while(!coup);
			System.out.println(this);
			if(!jeuTermine(j1, ia)) {
				ia.jouerCoupIANiveau3(this, j1, profondeur);
				System.out.println(this);
			}
			else {
				break;
			}
			profondeur--;
		} while(!jeuTermine(j1, ia));
		sc.close();
	}
	
	// cas ou l'IA commence
	public void jouerIANiveau3IA(Joueur j1, IANiveau3 ia) {
		Scanner sc = new Scanner(System.in);
		int l;
		int c;
		int profondeur = 16;
		boolean coup;
		do {
			ia.jouerCoupIANiveau3(this, j1, profondeur);
			System.out.println(this);
			if(!jeuTermine(j1, ia)) {
				do {
					do {
						System.out.println("entrer la ligne suivie de la colonne du coup à jouer (joueur 1) : ");
						l = sc.nextInt();
						c = sc.nextInt();
					} while(l < 0 || l > 3 || c < 0 || c > 3);
					coup = j1.jouerCoup(l, c, this);
				} while(!coup);
				System.out.println(this);
			}
			else {
				break;
			}
			profondeur--;
		} while(!jeuTermine(j1, ia));
		sc.close();
	}
	
	public boolean aGagne(Joueur j1) {
		int compteurJ1 = 0;
		// 1 joueur a gagné (3 cases alignées)
		// 1 ligne avec 3 cases à 1 ou 2
		for(int i = 0; i < SIZE; i++) {
			for(int col = 0; col < 2; col++) {
				for(int j = 0; j < 3; j++) {
					if(plateau[i][col+j] == j1.getNumero()) {
						compteurJ1++;
					}
				}
				if(compteurJ1 == 3) {
					return true;
				}
				compteurJ1 = 0;
			}
		}
		// 1 colonne avec 3 cases à 1 ou 2
		// parcourt de chaque colonne
		for(int j = 0; j < SIZE; j++) {
			// 2 possibilités: début à la première ou deuxième ligne
			for(int ligne = 0; ligne < 2; ligne++) {
				// 3 lignes successives
				for(int i = 0; i < 3; i++) {
					if(plateau[i+ligne][j] == j1.getNumero()) {
						compteurJ1++;
					}
				}
				if(compteurJ1 == 3) {
					return true;
				}
				compteurJ1 = 0;
			}
		}
		// 1 diagonale (haut gauche à bas droite)
		// 2 possibilitées, début à la case [0][0] ou à la case [1][1]
		for(int deb = 0; deb < 2; deb++) {
			for(int i = 0; i < 3; i++) {
				if(plateau[deb+i][deb+i] == j1.getNumero()) {
					compteurJ1++;
				}
			}
			if(compteurJ1 == 3) {
				return true;
			}
			compteurJ1 = 0;
		}
		// 1 diagonale inverse (haut droite à bas gauche)
		for(int deb = 0; deb < 2; deb++) {
			for(int i = 0; i < 3; i++) {
				if(plateau[deb+i][SIZE-(deb+i)-1] == j1.getNumero()) {
					compteurJ1++;
				}
			}
			if(compteurJ1 == 3) {
				return true;
			}
			compteurJ1 = 0;
		}
		// petites diagonales
		for(int i = 1; i < 4; i++) {
			if(plateau[i][i-1] == j1.getNumero()) {
				compteurJ1++;
			}
		}
		if(compteurJ1 == 3) {
			return true;
		}
		compteurJ1 = 0;
		for(int i = 0; i < 3; i++) {
			if(plateau[i][i+1] == j1.getNumero()) {
				compteurJ1++;
			}
		}
		if(compteurJ1 == 3) {
			return true;
		}
		compteurJ1 = 0;
		for(int i = 1; i < 4; i++) {
			if(plateau[i][SIZE-i] == j1.getNumero()) {
				compteurJ1++;
			}
		}
		if(compteurJ1 == 3) {
			return true;
		}
		compteurJ1 = 0;
		for(int i = 0; i < 3; i++) {
			if(plateau[i][SIZE-2-i] == j1.getNumero()) {
				compteurJ1++;
			}
		}
		if(compteurJ1 == 3) {
			return true;
		}
		return false;	
	}
	
	public boolean jeuTermine(Joueur j1, Joueur j2) {
		// tableau rempli
		boolean tableauRempli = true;
		for(int i = 0; i < SIZE; i++) {
			for(int j = 0; j < SIZE; j++) {
				if(plateau[i][j] == 0) tableauRempli = false;
			}
		}
		if(tableauRempli) return true;
		// 1 joueur a gagné (3 cases alignées)
		// 1 ligne avec 3 cases à 1 ou 2
		int compteurJ1 = 0;
		int compteurJ2 = 0;
		for(int i = 0; i < SIZE; i++) {
			for(int col = 0; col < 2; col++) {
				for(int j = 0; j < 3; j++) {
					if(plateau[i][col+j] == j1.getNumero()) {
						compteurJ1++;
					}
					else if(plateau[i][col+j] == j2.getNumero()) {
						compteurJ2++;
					}
				}
				if(compteurJ1 == 3) {
					System.out.println("Le joueur 1 a gagné !");
					return true;
				}
				if(compteurJ2 == 3) {
					System.out.println("Le joueur 2 a gagné !");
					return true;
				}
				compteurJ1 = 0;
				compteurJ2 = 0;
			}
		}
		// 1 colonne avec 3 cases à 1 ou 2
		// parcourt de chaque colonne
		for(int j = 0; j < SIZE; j++) {
			// 2 possibilités: début à la première ou deuxième ligne
			for(int ligne = 0; ligne < 2; ligne++) {
				// 3 lignes successives
				for(int i = 0; i < 3; i++) {
					if(plateau[i+ligne][j] == j1.getNumero()) {
						compteurJ1++;
					}
					else if(plateau[i+ligne][j] == j2.getNumero()) {
						compteurJ2++;
					}
				}
				if(compteurJ1 == 3) {
					System.out.println("Le joueur 1 a gagné !");
					return true;
				}
				if(compteurJ2 == 3) {
					System.out.println("Le joueur 2 a gagné !");
					return true;
				}
				compteurJ1 = 0;
				compteurJ2 = 0;
			}
		}
		// 1 diagonale (haut gauche à bas droite)
		// 2 possibilitées, début à la case [0][0] ou à la case [1][1]
		for(int deb = 0; deb < 2; deb++) {
			for(int i = 0; i < 3; i++) {
				if(plateau[deb+i][deb+i] == j1.getNumero()) {
					compteurJ1++;
				}
				if(plateau[deb+i][deb+i] == j2.getNumero()) {
					compteurJ2++;
				}
			}
			if(compteurJ1 == 3) {
				System.out.println("Le joueur 1 a gagné !");
				return true;
			}
			if(compteurJ2 == 3) {
				System.out.println("Le joueur 2 a gagné !");
				return true;
			}
			compteurJ1 = 0;
			compteurJ2 = 0;
		}
		// 1 diagonale inverse (haut droite à bas gauche)
		for(int deb = 0; deb < 2; deb++) {
			for(int i = 0; i < 3; i++) {
				if(plateau[deb+i][SIZE-(deb+i)-1] == j1.getNumero()) {
					compteurJ1++;
				}
				if(plateau[deb+i][SIZE-(deb+i)-1] == j2.getNumero()) {
					compteurJ2++;
				}
			}
			if(compteurJ1 == 3) {
				System.out.println("Le joueur 1 a gagné !");
				return true;
			}
			if(compteurJ2 == 3) {
				System.out.println("Le joueur 2 a gagné !");
				return true;
			}
			compteurJ1 = 0;
			compteurJ2 = 0;
		}
		// petites diagonales
		for(int i = 1; i < 4; i++) {
			if(plateau[i][i-1] == j1.getNumero()) {
				compteurJ1++;
			}
			if(plateau[i][i-1] == j2.getNumero()) {
				compteurJ2++;
			}
		}
		if(compteurJ1 == 3) {
			System.out.println("Le joueur 1 a gagné !");
			return true;
		}
		if(compteurJ2 == 3) {
			System.out.println("Le joueur 2 a gagné !");
			return true;
		}
		compteurJ1 = 0;
		compteurJ2 = 0;
		for(int i = 0; i < 3; i++) {
			if(plateau[i][i+1] == j1.getNumero()) {
				compteurJ1++;
			}
			if(plateau[i][i+1] == j2.getNumero()) {
				compteurJ2++;
			}
		}
		if(compteurJ1 == 3) {
			System.out.println("Le joueur 1 a gagné !");
			return true;
		}
		if(compteurJ2 == 3) {
			System.out.println("Le joueur 2 a gagné !");
			return true;
		}
		compteurJ1 = 0;
		compteurJ2 = 0;
		for(int i = 1; i < 4; i++) {
			if(plateau[i][SIZE-i] == j1.getNumero()) {
				compteurJ1++;
			}
			if(plateau[i][SIZE-i] == j2.getNumero()) {
				compteurJ2++;
			}
		}
		if(compteurJ1 == 3) {
			System.out.println("Le joueur 1 a gagné !");
			return true;
		}
		if(compteurJ2 == 3) {
			System.out.println("Le joueur 2 a gagné !");
			return true;
		}
		compteurJ1 = 0;
		compteurJ2 = 0;
		for(int i = 0; i < 3; i++) {
			if(plateau[i][SIZE-2-i] == j1.getNumero()) {
				compteurJ1++;
			}
			if(plateau[i][SIZE-2-i] == j2.getNumero()) {
				compteurJ2++;
			}
		}
		if(compteurJ1 == 3) {
			System.out.println("Le joueur 1 a gagné !");
			return true;
		}
		if(compteurJ2 == 3) {
			System.out.println("Le joueur 2 a gagné !");
			return true;
		}
		return false;	
	}
	
	public Plateau clone() {
	    Plateau p = new Plateau();
	    int[][] copie = new int[SIZE][SIZE];
		for(int i = 0 ; i < SIZE ; i++ ) {
			for(int j = 0; j < SIZE; j++) {
				copie[i][j] = this.getPlateau()[i][j];
			}
		}
		p.setPlateau(copie);

//		try {
//			p = (Plateau) super.clone();
//			p.plateau = this.getPlateau.clone();
//		} catch (CloneNotSupportedException e) {
//			e.printStackTrace();
//		}
	    // on renvoie le clone
	    return p;
	}
}
