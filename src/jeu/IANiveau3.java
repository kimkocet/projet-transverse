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

		@Override
		public void jouerCoupIA(Plateau plateau) {
			// TODO Auto-generated method stub
		}
		
		public Arbre<Plateau> getArbre() {
			return this.arbre;
		}
}
