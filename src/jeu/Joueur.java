package jeu;

import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import vue.Affichage;

public class Joueur implements Cloneable {
	int numero;
	int[] coupPrecedent = new int[2];
	
	public Joueur(int num) {
		this.numero = num;
		coupPrecedent[0] = -2;
		coupPrecedent[1] = -2 ;
	}
	
	public int getNumero() {
		return numero;
	}
	
	public int getDerniereLigne(){
		return coupPrecedent[0];
	}
	
	public int getDerniereColonne(){
		return coupPrecedent[1];
	}
	
	// place un pion sur la ligne 'l' et la colonne 'c'
	public boolean jouerCoup(int l, int c, Plateau plateau) {
		if(coupPossible(l,c, plateau)) {
			plateau.setCase(l, c, this.numero);
			coupPrecedent[0] = l;
			coupPrecedent[1] = c;
			return true;
		}
		else {
			System.out.println("Ce coup ne peut pas être joué !");
			return false;
		}
	}
	
	public ArrayList<int[]> coupsPossibles(Plateau plateau) {
		ArrayList<int[]> resultat = new ArrayList<int[]>();
		for(int i = 0; i < Plateau.SIZE; i++) {
			for(int j = 0; j < Plateau.SIZE; j++) {
				if(coupPossible(i, j, plateau)) {
					resultat.add(new int[] {i, j});
				}
			}
		}
		return resultat;
	}
	
	public boolean coupPossible(int l, int c, Plateau plateau) {
		// si la case est déjà occupée
		if(plateau.getPlateau()[l][c] != 0) {
			//System.out.println("Case (" + l + ";" + c + ") déjà jouée");
			return false;
		}
		// cas du premier coup (ne peut pas être joué sur les 4 cases du milieu)
		if(coupPrecedent[0] == -2 && coupPrecedent[1] == -2) {
			if((l == 1 || l == 2) && (c == 1 || c == 2)) {
				//System.out.println("Vous ne pouvez pas jouer ici au premier tour !");
				return false;
			}
		}
		// comparaison au coup précédent (on ne peut pas jouer dans les cases adjacentes)
		if(l == coupPrecedent[0]-1 && c == coupPrecedent[1]-1 ||
			l == coupPrecedent[0]-1 && c == coupPrecedent[1] ||
			l == coupPrecedent[0]-1 && c == coupPrecedent[1]+1 ||
			l == coupPrecedent[0] && c == coupPrecedent[1]-1 ||
			l == coupPrecedent[0] && c == coupPrecedent[1]+1 ||
			l == coupPrecedent[0]+1 && c == coupPrecedent[1]-1 ||
			l == coupPrecedent[0]+1 && c == coupPrecedent[1] ||
			l == coupPrecedent[0]+1 && c == coupPrecedent[1]+1) {
			//System.out.println("Vous ne pouvez pas jouer aux cases adjacentes");
			return false;
		}
		return true;
	}
	
	public Joueur clone() {
	    Joueur j = new Joueur(numero);
//		for(int i = 0 ; i < SIZE ; i++ ) {
//			for(int j = 0; j < SIZE; j++) {
//				copie[i][j] = p.getPlateau()[i][j];
//			}
//		}

		try {
			j = (Joueur) super.clone();
			j.coupPrecedent = this.coupPrecedent.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
	    // on renvoie le clone
	    return j;
	}
	
	public String toString() {
		return "Joueur " + this.numero + "\nCoup précédent : " + this.coupPrecedent[0] + " " + this.coupPrecedent[1];
	}
}
