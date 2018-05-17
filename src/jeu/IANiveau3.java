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
				//System.out.println(copieIA);
				//System.out.println("i = " + coupsPossibles.get(i)[0]);
				//System.out.println("j = " + coupsPossibles.get(i)[1]);
				copieIA.jouerCoup(coupsPossibles.get(i)[0], coupsPossibles.get(i)[1], copiePlateau);
				//System.out.println(copiePlateau);
				int val = joueurMinElagage(copiePlateau, j1.clone(), copieIA, profondeur, -10^5, 10^5);
				//System.out.println(val);
				if(val > max) {
					max = val;
					meilleurCoup[0] = coupsPossibles.get(i)[0];
					meilleurCoup[1] = coupsPossibles.get(i)[1];
				}
			}
			System.out.println("max = " + max);
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
				Plateau copiePlateau = plateau.clone();
				Joueur copieIA = ia.clone();
				//System.out.println("ia : " + coupsPossibles.get(i)[0] + " " + coupsPossibles.get(i)[1]);
				//System.out.println(copieIA);
				copieIA.jouerCoup(coupsPossibles.get(i)[0], coupsPossibles.get(i)[1], copiePlateau);
				//System.out.println(profondeur);
				//System.out.println(copiePlateau);
				resultat = max(resultat, joueurMin(copiePlateau, j1, copieIA, profondeur-1));

			}
			System.out.println(profondeur + " MAX resultat = " + resultat);
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
				Plateau copiePlateau = plateau.clone();
				Joueur copieJ1 = j1.clone();
				//System.out.println("j1 : " + coupsPossibles.get(i)[0] + " " + coupsPossibles.get(i)[1]);
				//System.out.println(copieJ1);
				copieJ1.jouerCoup(coupsPossibles.get(i)[0], coupsPossibles.get(i)[1], copiePlateau);
				//System.out.println(profondeur);
				//System.out.println(copiePlateau);
				resultat = min(resultat, joueurMax(copiePlateau, copieJ1, ia, profondeur-1));
			}
			//System.out.println(profondeur + " min resultat = " + resultat );
			return resultat;
		}
		
		int joueurMaxElagage(Plateau plateau, Joueur j1, Joueur ia, int profondeur, int alpha, int beta) {
			if(plateau.aGagne(j1) || plateau.aGagne(ia)) {
				return h(plateau, j1, ia);
			}
			if(profondeur == 0) {
				return h(plateau, j1, ia);
			}
			int resultat = alpha;
			ArrayList<int[]> coupsPossibles = ia.coupsPossibles(plateau);
			for(int i = 0; i < coupsPossibles.size(); i++) {
				Plateau copiePlateau = plateau.clone();
				Joueur copieIA = ia.clone();
				//System.out.println("ia : " + coupsPossibles.get(i)[0] + " " + coupsPossibles.get(i)[1]);
				//System.out.println(copieIA);
				copieIA.jouerCoup(coupsPossibles.get(i)[0], coupsPossibles.get(i)[1], copiePlateau);
				//System.out.println(profondeur);
				//System.out.println(copiePlateau);
				resultat = max(resultat, joueurMinElagage(copiePlateau, j1, copieIA, profondeur-1, resultat, beta));
				if(resultat >= beta) return resultat;

			}
			//System.out.println(profondeur + " MAX resultat = " + resultat);
			return resultat;
		}
		
		int joueurMinElagage(Plateau plateau, Joueur j1, Joueur ia, int profondeur, int alpha, int beta) {
			if(plateau.aGagne(j1) || plateau.aGagne(ia)) {
				return h(plateau, j1, ia);
			}
			if(profondeur == 0) {
				return h(plateau, j1, ia);
			}
			int resultat = beta;
			ArrayList<int[]> coupsPossibles = j1.coupsPossibles(plateau);
			for(int i = 0; i < coupsPossibles.size(); i++) {
				Plateau copiePlateau = plateau.clone();
				Joueur copieJ1 = j1.clone();
				//System.out.println("j1 : " + coupsPossibles.get(i)[0] + " " + coupsPossibles.get(i)[1]);
				//System.out.println(copieJ1);
				copieJ1.jouerCoup(coupsPossibles.get(i)[0], coupsPossibles.get(i)[1], copiePlateau);
				//System.out.println(profondeur);
				//System.out.println(copiePlateau);
				resultat = min(resultat, joueurMaxElagage(copiePlateau, copieJ1, ia, profondeur-1, alpha, resultat));
				if(alpha >= resultat) return resultat;
			}
			//System.out.println(profondeur + " min resultat = " + resultat );
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
		public void jouerCoupIA(Plateau plateau) {
			// TODO Auto-generated method stub
			
		}
}
