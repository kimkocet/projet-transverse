package vue;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

import jeu.IA;
import jeu.IANiveau1;
import jeu.IANiveau3;
import jeu.Joueur;
import jeu.Plateau;

public class Affichage extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel panCentre = new JPanel();
	private Plateau p;
	private JButton button[][] = new JButton[4][4];
	
	JButton commencer = new JButton("Commencer");
	private IA ia;
	private Joueur joueur;
	int ligne;
	int colonne;
	int niveau;
	int commence;

	public Affichage(Plateau p) {
		this.p = p;
		ligne = 0;
		colonne = 0;
		joueur = new Joueur(1);
		this.setTitle("Morgpion");
		this.setSize(700, 600);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.add(panCentre);
		vueAccueil(panCentre);
		this.setVisible(true);
	}

	public void vueAccueil(JPanel panCentre) {
		JPanel panHello = new JPanel();
		panHello.setBackground(Color.white);
		panHello.setPreferredSize(new Dimension(700,40));
		JLabel hello = new JLabel("Défiez le terrible Morgpion !");
		panHello.add(hello);		
		
		JComboBox<String> combo = new JComboBox<String>();
		JLabel level = new JLabel("Veuillez choisir un niveau : ");
		JPanel panniveau = new JPanel();
		panniveau.setPreferredSize(new Dimension(700, 40));
		panniveau.setBackground(Color.white);
		combo.setPreferredSize(new Dimension(100, 20));
		combo.addItem("1");
		combo.addItem("2");
		combo.addItem("3");
		
		commencer.setEnabled(false);
		
		JButton valider = new JButton("	OK	");
		valider.addActionListener(new ActionListener()
				{

					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						String typeSelect = (String)combo.getSelectedItem();
						niveau = Integer.parseInt(typeSelect);
					}
			
				});
		panniveau.add(level);
		panniveau.add(combo);
		panniveau.add(valider);
		
		JComboBox<String> combo2 = new JComboBox<String>();
		JLabel start = new JLabel("Voulez-vous commencer ? : ");
		JPanel panStart = new JPanel();
		panStart.setPreferredSize(new Dimension(700, 40));
		combo2.setPreferredSize(new Dimension(100, 20));
		combo2.addItem("Oui");
		combo2.addItem("Non");
		JButton valider2 = new JButton(" 	OK	");
		valider2.addActionListener(new ActionListener()
		{
			// selection de qui commence (1 = le joueur commence, 0 = l'IA commence)
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String typeSelect = (String)combo2.getSelectedItem();
				if(typeSelect == "Oui") {
					commence = 1;
					commencer.setEnabled(true);
				}
				else {
					commence = 0;
					commencer.setEnabled(false);
				}
			}
	
		});
		panStart.add(start);
		panStart.add(combo2);
		panStart.add(valider2);
		

		JPanel panCommencer = new JPanel();
		panCommencer.setPreferredSize(new Dimension(700, 30));
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
		panCentre.add(panniveau);
		panCentre.add(panStart);
		panCentre.add(panCommencer);
		panCentre.add(panCredit);
		
		// selection du niveau
		switch(niveau) {
			case 1:
				ia = new IANiveau1(2);
				break;
			case 2:
				ia = new IANiveau3(2);
				break;
			case 3:
				ia = new IANiveau3(2);
				break;
		}
		
		System.out.println(niveau);
		System.out.println(commence);

		commencer.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println(niveau);
				System.out.println(commence);
				panCentre.removeAll();
				panCentre.repaint();
				affichageTableau(panCentre);
				panCentre.validate();
			}

		});
	}

	void affichageTableau(JPanel panCentre) {
		panCentre.setLayout(new GridLayout(4, 4));
		for (int i = 0; i < 4; i++) {
			for (int k = 0; k < 4; k++) {
				button[i][k] = new JButton();
				button[i][k].setFont(new Font("Arial", Font.PLAIN, 60));
				button[i][k].setBackground(Color.WHITE);
				button[i][k].addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						if (e.getSource() instanceof JButton) {
							for(int i = 0 ; i < 4 ; i++) {
								for(int j = 0; j < 4 ; j++) {
									if(e.getSource() == button[i][j]) {
										if(!joueur.coupPossible(i, j, p)) {
											JOptionPane.showMessageDialog(null, "Ce coup ne peut pas être joué !", "Erreur", 
													JOptionPane.ERROR_MESSAGE);
										}
										else if(!p.jeuTermine(joueur, ia)) {
											((JButton) e.getSource()).setText("X");
											((JButton) e.getSource()).setEnabled(false);
											panCentre.validate();
											setLigne(i);
											setColonne(j);
											System.out.println("(" + ligne + " ; " + colonne +")");
											joueur.jouerCoup(i, j, p);
											System.out.println(this);
											if(!p.jeuTermine(joueur, ia)) {
												if(niveau == 1) {
													ia.jouerCoupIA(p);
												}
												else if(niveau == 2){
													ia.jouerCoupIANiveau3(p, joueur, 5);
												}
												else {
													ia.jouerCoupIANiveau3(p, joueur, 16);
												}
												//recupérer la position du dernier coup joué pour positionner le coup de l'IA dans l'affichage
												//button[ligne du dernier coup][colonne du dernier coup].setText("O")
												int v = ia.getDerniereLigne();
												int w = ia.getDerniereColonne();
												button[v][w].setText("O");
												button[v][w].setEnabled(false);
												panCentre.validate();
												if(p.aGagne(joueur)) {
													JOptionPane.showMessageDialog(null, "Vous avez gagné !", "Erreur", 
															JOptionPane.INFORMATION_MESSAGE);
												}
												else if(p.aGagne(ia)) {
													JOptionPane.showMessageDialog(null, "Vous avez perdu !", "Erreur", 
															JOptionPane.INFORMATION_MESSAGE);
												}
											}
											else if(p.aGagne(joueur)) {
												JOptionPane.showMessageDialog(null, "Vous avez gagné !", "Erreur", 
														JOptionPane.INFORMATION_MESSAGE);
											}
											else if(p.aGagne(ia)) {
												JOptionPane.showMessageDialog(null, "Vous avez perdu !", "Erreur", 
														JOptionPane.INFORMATION_MESSAGE);
											}
										}
										else if(p.aGagne(joueur)) {
											JOptionPane.showMessageDialog(null, "Vous avez gagné !", "Erreur", 
													JOptionPane.INFORMATION_MESSAGE);
										}
										else if(p.aGagne(ia)) {
											JOptionPane.showMessageDialog(null, "Vous avez perdu !", "Erreur", 
													JOptionPane.INFORMATION_MESSAGE);
										}
									}
								}
							}
						}
					}
				});
				panCentre.add(button[i][k]);
			}
		}
		if(commence == 0) {
			if(niveau == 1) {
				ia.jouerCoupIA(p);
			}
			else if(niveau == 2) {
				ia.jouerCoupIANiveau3(p, joueur, 5);
			}
			else {
				ia.jouerCoupIANiveau3(p, joueur, 16);
			}
			commence++;
			
			//recupérer la position du dernier coup joué pour positionner le coup de l'IA dans l'affichage
			//button[ligne du dernier coup][colonne du dernier coup].setText("O")
			int v = ia.getDerniereLigne();
			int w = ia.getDerniereColonne();
			button[v][w].setText("O");
			button[v][w].setEnabled(false);
		}
	}
	
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