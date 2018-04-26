package jeu;

import java.util.ArrayList;

public class IANiveau3 extends IA {

		private Arbre<Plateau> arbre;
		
		public IANiveau3(int num) {
			super(num);
			arbre = new Arbre<Plateau>();
		}
		
		public void construireArbre(Plateau plateau, Joueur j1, int profondeur) {
			if(profondeur > 3) return;
			arbre.setVal(plateau.clone());
			ArrayList<int[]> coupsIA = this.coupsPossibles(plateau);
			for(int i = 0; i < coupsIA.size(); i++) {
				Plateau enfant = new Plateau(plateau);
				this.clone().jouerCoup(coupsIA.get(i)[0], coupsIA.get(i)[1], enfant);
				Arbre<Plateau> arbreEnfant = new Arbre<Plateau>(enfant);
				arbre.addEnfant(arbreEnfant);
				System.out.println(enfant);
				if(enfant.aGagne(this)) return;
					ArrayList<int[]> coupsJoueur = j1.coupsPossibles(enfant);
					for(int j = 0; j < coupsJoueur.size(); j++) {
						Joueur copie = j1.clone();
						Plateau petitEnfant = new Plateau(enfant);
						copie.jouerCoup(coupsJoueur.get(j)[0], coupsJoueur.get(j)[1], petitEnfant);
						Arbre<Plateau> arbrePetitEnfant = new Arbre<Plateau>(petitEnfant);
						arbre.addEnfant(arbrePetitEnfant);
						if(!petitEnfant.aGagne(j1)) {
							construireArbre(petitEnfant, copie, profondeur+1);
						}
					}	
				}
			}

		//}

		public void jouerCoupIANiveau3(Plateau plateau, Joueur j1, int profondeur) {
			ArrayList<int[]> coupsPossibles = this.coupsPossibles(plateau);
			int max = -2;
			int[] meilleurCoup = { -1, -1 };
			for(int i = 0; i < coupsPossibles.size() ; i++) {
				Plateau copiePlateau = plateau.clone();
				Joueur copieIA = this.clone();
				copieIA.jouerCoup(coupsPossibles.get(i)[0], coupsPossibles.get(i)[1], copiePlateau);
				//System.out.println(copiePlateau);
				int val = joueurMax(copiePlateau, j1.clone(), copieIA, profondeur);
//				System.out.println("i = " + coupsPossibles.get(i)[0]);
//				System.out.println("j = " + coupsPossibles.get(i)[1]);
//				System.out.println("val = " + val);
				if(val > max) {
					max = val;
					meilleurCoup[0] = coupsPossibles.get(i)[0];
					meilleurCoup[1] = coupsPossibles.get(i)[1];
				}
			}
			this.jouerCoup(meilleurCoup[0], meilleurCoup[1], plateau);
			// TODO Auto-generated method stub
		}
		
		public Arbre<Plateau> getArbre() {
			return this.arbre;
		}
		
		int joueurMax(Plateau plateau, Joueur j1, Joueur ia, int profondeur) {
			if(plateau.aGagne(j1) || plateau.aGagne(ia)) {
				return h(plateau, j1, ia);
			}
			if(profondeur == 0) {
				return h(plateau, j1, ia);
			}
			int resultat = -10^5;
			ArrayList<int[]> coupsPossibles = ia.coupsPossibles(plateau);
			for(int i = 0; i < coupsPossibles.size(); i++) {
				//Plateau copiePlateau = plateau.clone();
				//Joueur copieIA = this.clone();
				ia.jouerCoup(coupsPossibles.get(i)[0], coupsPossibles.get(i)[1], plateau);
				resultat = max(resultat, joueurMin(plateau, j1, ia, profondeur-1));
			}
			return resultat;
		}
		
		int joueurMin(Plateau plateau, Joueur j1, Joueur ia, int profondeur) {
			if(plateau.aGagne(j1) || plateau.aGagne(ia)) {
				return h(plateau, j1, ia);
			}
			if(profondeur == 0) {
				return h(plateau, j1, ia);
			}
			int resultat = 10^5;
			ArrayList<int[]> coupsPossibles = j1.coupsPossibles(plateau);
			for(int i = 0; i < coupsPossibles.size(); i++) {
				j1.jouerCoup(coupsPossibles.get(i)[0], coupsPossibles.get(i)[1], plateau);
				resultat = min(resultat, joueurMax(plateau, j1, ia, profondeur-1));
			}
			return resultat;
		}
		
		int h(Plateau plateau, Joueur j1, Joueur ia) {
			if(plateau.aGagne(j1)) {
				return -1;
			}
			if(plateau.aGagne(ia)) {
				return 1;
			}
			else return 0;
		}
		
		int max(int i, int j) {
			if(i > j) return i;
			return j;
		}
		
		int min(int i, int j) {
			if(i < j) return i;
			return j;
		}

		@Override
		public void jouerCoupIA(Plateau plateau, Joueur j1) {
			// TODO Auto-generated method stub
			
		}
}
