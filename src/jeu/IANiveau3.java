package jeu;

import java.util.ArrayList;
import java.util.Random;
import java.util.Vector;

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
			// tableau des meilleurs coups possibles (qui permettent de gagner la partie)
			Vector<int[]> meilleursCoups = new Vector<int[]>(0);
			// pour chaque coup possible
			for(int i = 0; i < coupsPossibles.size() ; i++) {
				// on copie le plateau
				Plateau copiePlateau = plateau.clone();
				// on copie l'IA
				Joueur copieIA = this.clone();
				// on simule le coup possible avec la copie de l'IA
				copieIA.jouerCoup(coupsPossibles.get(i)[0], coupsPossibles.get(i)[1], copiePlateau);
				// on recupere la valeur de joueurMin
				int val = joueurMinElagage(copiePlateau, j1.clone(), copieIA, profondeur, -10^5, 10^5);
				// si cette valeur est superieur au max calculé précedemment
				if(val > max) {
					// on met à jour la valeur de max
					max = val;
					// on récupère le coup joué (qui est pour l'instant le meilleur coup)
					meilleurCoup[0] = coupsPossibles.get(i)[0];
					meilleurCoup[1] = coupsPossibles.get(i)[1];
					// on réinitialise les meilleursCoups
					meilleursCoups.clear();
					// on ajoute le meilleurCoup au tableau des meilleurs coups
					meilleursCoups.add(meilleurCoup);
				}
				// sinon si la valeur est égale au max
				else if(val == max) {
					// le coup simulé fait partie des meilleurs coups
					meilleurCoup[0] = coupsPossibles.get(i)[0];
					meilleurCoup[1] = coupsPossibles.get(i)[1];
					meilleursCoups.add(meilleurCoup);
				}
			}
			System.out.println("max = " + max);
			
			// on sélectionne un coup aléatoirement parmi les meilleurs coups
			Random random = new Random();
			int indice = random.nextInt(meilleursCoups.size());
			int l = meilleursCoups.elementAt(indice)[0];
			int c = meilleursCoups.elementAt(indice)[1];
			
			// on joue le coup
			this.jouerCoup(l, c, plateau);
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
				copieIA.jouerCoup(coupsPossibles.get(i)[0], coupsPossibles.get(i)[1], copiePlateau);
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
				copieJ1.jouerCoup(coupsPossibles.get(i)[0], coupsPossibles.get(i)[1], copiePlateau);
				resultat = min(resultat, joueurMax(copiePlateau, copieJ1, ia, profondeur-1));
			}
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
				copieIA.jouerCoup(coupsPossibles.get(i)[0], coupsPossibles.get(i)[1], copiePlateau);
				resultat = max(resultat, joueurMinElagage(copiePlateau, j1, copieIA, profondeur-1, resultat, beta));
				if(resultat >= beta) return resultat;

			}
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
				copieJ1.jouerCoup(coupsPossibles.get(i)[0], coupsPossibles.get(i)[1], copiePlateau);
				resultat = min(resultat, joueurMaxElagage(copiePlateau, copieJ1, ia, profondeur-1, alpha, resultat));
				if(alpha >= resultat) return resultat;
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
		public void jouerCoupIA(Plateau plateau) {
			// TODO Auto-generated method stub
			
			// joue un coup aléatoirement parmi les coups possibles
			ArrayList<int[]> coups = coupsPossibles(plateau);
			Random random = new Random();
			int indice = random.nextInt(coups.size());
			int l = coups.get(indice)[0];
			int c = coups.get(indice)[1];
			jouerCoup(l, c, plateau);
			
		}
}
