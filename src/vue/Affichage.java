package vue;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import jeu.IANiveau3;
import jeu.Joueur;
import jeu.Plateau;

public class Affichage extends JFrame {
	private JPanel panCentre = new JPanel();
	private Plateau p;
	private JButton button[][] = new JButton[4][4];
	private IANiveau3 ia;
	private Joueur joueur;
	int ligne;
	int colonne;
	int count;
	int temoin;

	public Affichage(Plateau p, Joueur j,Joueur j2) {
		this.p = p;
		ligne = 0;
		colonne = 0;
		ia = (IANiveau3) j;
		joueur = j2;
		this.setTitle("Morgpion");
		this.setSize(700, 600);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.add(panCentre);
//		vueAccueil(panCentre);
		affichageTableau(panCentre);
		this.setVisible(true);
	}

	public void vueAccueil(JPanel panCentre) {
		JPanel panHello = new JPanel();
		panHello.setPreferredSize(new Dimension(700, 30));
		JLabel hello = new JLabel("D�fiez le terrible Morgpion !");
		panHello.add(hello);

		JPanel panCommencer = new JPanel();
		panCommencer.setPreferredSize(new Dimension(700, 30));
		JButton commencer = new JButton("Commencer");
		panCommencer.add(commencer);

		JPanel panCredit = new JPanel();
		panCredit.setPreferredSize(new Dimension(700, 30));

		JButton credit = new JButton("Credit");
		credit.setPreferredSize(new Dimension(100, 20));
		JButton regle = new JButton("R�gles");
		regle.setPreferredSize(new Dimension(100, 20));
		panCredit.add(credit);
		panCredit.add(regle);

		panCentre.add(panHello);
		panCentre.add(panCommencer);
		panCentre.add(panCredit);

		commencer.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				panCentre.removeAll();
				panCentre.repaint();
				affichageTableau(panCentre);
				panCentre.validate();
			}

		});
	}

	void affichageTableau(JPanel panCentre) {
		panCentre.setLayout(new GridLayout(4, 4));
		count = 0;
		
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				button[i][j] = new JButton();
				panCentre.add(button[i][j]);
			}
		}
	}
	
	public void jouerAffichage() {
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				button[i][j].addActionListener(new ActionListener() {
					
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						if (e.getSource() instanceof JButton) {
							for(int i = 0 ; i < 4 ; i++) {
								for(int j = 0; j < 4 ; j++) {
									//button[i][j].setEnabled(true);
									if(e.getSource() == button[i][j]) {
										int l = i;
										int c = j;
										if(count == 0) {
												if( ((l == 1) && (c ==1) ) || ((l == 1) && (c == 2) ) || ((l == 2) || (c ==1) ) || ((l == 2) && (c == 2) )) {
													JOptionPane.showMessageDialog(null, "Vous ne pouvez pas jouer ce coup au 1er tour !", "Erreur", 
														JOptionPane.ERROR_MESSAGE);
													l = 0;
													c = 0;
													System.out.println("cout : " + count);
													}
												else {
													System.out.println("a");
													count++;
													((JButton) e.getSource()).setText("X");
													System.out.println(l + " " + c);
													setLigne(l);
													setColonne(c);
												}
											}
										else {
											if(((JButton) e.getSource()).getText()== "X") {
												JOptionPane.showMessageDialog(null, "Cette case a d�j� �t� jou�e !", "Erreur", 
														JOptionPane.ERROR_MESSAGE);
												
											}
											else {
												System.out.println("b");
												System.out.println(l + " " + c);
												setLigne(l);
												setColonne(c);
												//retourneCoupJoueur(ligne, colonne);
												((JButton) e.getSource()).setText("X");
												
											}
										}
										
									}
								}
							}
							
						}
					}}
				);
			}
		}
	}
	
	static int[] retourneCoupJoueur(int i, int j){
		int[] tab = {i,j};
		return tab;
		
	}
	
	public int getLigne() {
		return ligne;
	}
	public int getColonne() {
		return colonne;
	}
	
	public void setColonne(int j) {
		colonne = j;
	}
	public void setLigne(int i) {
		ligne = i;
	}
	void jouerIATableau(JPanel panCentre) {
		
	}

}