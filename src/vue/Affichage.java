package vue;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;

import javax.swing.*;

import jeu.IA;
import jeu.IANiveau3;
import jeu.IANiveau1;
import jeu.Joueur;
import jeu.Plateau;

public class Affichage extends JFrame {
	private JPanel panCentre = new JPanel();
	private Plateau p;
	private JButton button[][] = new JButton[4][4];
	private IANiveau1 ia;
	private Joueur joueur;
	int ligne;
	int colonne;
	int count;
	int temoin;
	boolean flag;

	public Affichage(Plateau p, Joueur j,Joueur j2) {
		this.p = p;
		ligne = 0;
		colonne = 0;
		ia = (IANiveau1) j;
		joueur = j2;
		this.setTitle("Morgpion");
		this.setSize(700, 600);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.add(panCentre);
//		vueAccueil(panCentre);
//		affichageTableau(panCentre);
		panCentre.setLayout(new GridLayout(4, 4));
		count = 0;
		
		for (int i = 0; i < 4; i++) {
			for (int k = 0; k < 4; k++) {
				button[i][k] = new JButton();
				button[i][k].addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						if (e.getSource() instanceof JButton) {
							for(int i = 0 ; i < 4 ; i++) {
								for(int j = 0; j < 4 ; j++) {
									if(e.getSource() == button[i][j]) {
										((JButton) e.getSource()).setText("X");
										setLigne(i);
										setColonne(j);
										System.out.println("(" + ligne + " ; " + colonne +")");
										flag = true;
										vueJouerIANiveau1J(j2, ia, p, panCentre);
										
									}
									
								}
							}
						}
						

					}
				}
				);
					
				
				panCentre.add(button[i][k]);
			}
		}
		
		
		this.setVisible(true);
	}

	public void vueAccueil(JPanel panCentre) {
		JPanel panHello = new JPanel();
		panHello.setPreferredSize(new Dimension(700, 30));
		JLabel hello = new JLabel("Défiez le terrible Morgpion !");
		panHello.add(hello);

		JPanel panCommencer = new JPanel();
		panCommencer.setPreferredSize(new Dimension(700, 30));
		JButton commencer = new JButton("Commencer");
		panCommencer.add(commencer);

		JPanel panCredit = new JPanel();
		panCredit.setPreferredSize(new Dimension(700, 30));

		JButton credit = new JButton("Credit");
		credit.setPreferredSize(new Dimension(100, 20));
		JButton regle = new JButton("Règles");
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
	
<<<<<<< HEAD
	
	// cas ou le joueur commence
	public void vueJouerIANiveau1J(Joueur j1, IA ia, Plateau p, JPanel panCentre) {
		int l;
		int c;
		boolean coup;
		do {
			do {
				l = getLigne();
				c = getColonne();
				coup = j1.jouerCoup(l, c, p);
			} while(!coup);
			System.out.println(this);
			if(!p.jeuTermine(j1, ia)) {
				ia.jouerCoupIA(p);
				//recupérer la position du dernier coup joué pour positionner le coup de l'IA dans l'affichage
				//button[ligne du dernier coup][colonne du dernier coup].setText("O")
				int v = ia.getDerniereLigne();
				int w = ia.getDerniereColonne();
				button[v][w].setText("O");
				button[v][w].setEnabled(false);
				
				int delay = 10000; //milliseconds
				  ActionListener taskPerformer = new ActionListener() {
				      public void actionPerformed(ActionEvent evt) {
				          System.out.println(java.util.Calendar.getInstance().getTime().toString());		      
				      }
				  };
				  new Timer(delay, taskPerformer).start();
				
				System.out.println(this);
=======
	public void jouerAffichage() {
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				button[i][j].addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						if (e.getSource() instanceof JButton) {
							for(int i = 0 ; i < 4 ; i++) {
								for(int j = 0; j < 4 ; j++) {
									if(e.getSource() == button[i][j]) {
										int l = i;
										int c = j;
										//System.out.println("l = " + l + " c = " + c);
										boolean coup = joueur.jouerCoup(l, c, p);
										if( !coup ) {
											JOptionPane.showMessageDialog(null, "Vous ne pouvez pas jouer ce coup !", "Erreur", 
												JOptionPane.ERROR_MESSAGE);
											l = 0;
											c = 0;
										}
										else {
											System.out.println("a");
											((JButton) e.getSource()).setText("X");
											System.out.println(l + " " + c);
											setLigne(l);
											setColonne(c);
											joueur.jouerCoup(l, c, p);
										}
									}
								}
							}
							
						}
					}}
				);
>>>>>>> 187f62c7637c6a4e482b533db0a0932f74771f93
			}
			else {
				break;
			}
			
			
		} while(!p.jeuTermine(j1, ia));

	}
	
	public void vueSetCase(int l, int c, String text) {
		button[l][c].setText(text);
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


}