package vue;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
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
	private JPanel panCredit = new JPanel();
	private JPanel panRegle = new JPanel();
	private Plateau p;
	private JButton button[][] = new JButton[4][4];

	JButton commencer = new JButton("Commencer");
	private IA ia1 = new IANiveau1(2);
	private IA ia2 = new IANiveau3(2);
	private IA ia3 = new IANiveau3(2);
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
	
	public void affichageCredit() {
		JFrame frameCredit = new JFrame();
		frameCredit.setTitle("Les crédits !");
		frameCredit.setSize(700, 600);
		frameCredit.setLocation(50, 300);
		frameCredit.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frameCredit.add(panCredit);
		frameCredit.setVisible(true);
		
		JLabel wael = new JLabel("Ali Baba et les 40 voleurs : Wael Hage !");
		JLabel kim = new JLabel("Kim la survivante : Kim Kocet !");
		JLabel elsa = new JLabel("ProCode2.0 : Elsa Charpentier !");
		JLabel morgan = new JLabel("I'm Batman, je vis la nuit : Morgan Wolf !");
		JLabel anthony = new JLabel(" 'Insérer anecdotes' : Anthony Leguay !");
		
		frameCredit.setLayout(new GridBagLayout());
		frameCredit.setBackground(Color.white);
		GridBagConstraints position = new GridBagConstraints();
		position.ipadx = 0;
		position.ipady = 0;
		position.weightx = 1;
		position.weighty = 1;
		position.gridwidth = 1;
		position.gridheight = 1;

		position.gridx = 0;
		position.gridy = 1;
		frameCredit.add(wael, position);
		
		position.gridx = 0;
		position.gridy = 2;
		frameCredit.add(kim, position);
		
		position.gridx = 0;
		position.gridy = 3;
		frameCredit.add(elsa, position);
		
		position.gridx = 0;
		position.gridy = 4;
		frameCredit.add(morgan, position);
		
		position.gridx = 0;
		position.gridy = 5;
		frameCredit.add(anthony, position);
	}
	
	public void afficherRegles() {
		JFrame frameRegle = new JFrame();
		frameRegle.setTitle("Les regles !");
		frameRegle.setSize(700, 600);
		frameRegle.setLocation(1150, 300);
		frameRegle.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frameRegle.add(panRegle);
		frameRegle.setVisible(true);
		
		JLabel regle1 = new JLabel("- Les 4 cases centrales ne peuvent être jouées au premier tour !");
		JLabel regle2 = new JLabel("- Il est interdit de jouer sur les cases adjacentes au precedant coup !");
		JLabel regle3 = new JLabel("- Il est formellement interdit de s'en prendre à l'IA si vous ne pouvez pas gagner !");
		
		frameRegle.setLayout(new GridBagLayout());
		frameRegle.setBackground(Color.white);
		GridBagConstraints position = new GridBagConstraints();
		position.ipadx = 0;
		position.ipady = 0;
		position.weightx = 1;
		position.weighty = 1;
		position.gridwidth = 1;
		position.gridheight = 1;
		
		position.gridx = 0;
		position.gridy = 1;
		frameRegle.add(regle1, position);
		
		position.gridx = 0;
		position.gridy = 2;
		frameRegle.add(regle2, position);
		
		position.gridx = 0;
		position.gridy = 3;
		frameRegle.add(regle3, position);
	}

	public void vueAccueil(JPanel panCentre) {
		JPanel panHello = new JPanel();
		panHello.setBackground(Color.white);
		panHello.setPreferredSize(new Dimension(700, 40));
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

		JButton valider = new JButton("	OK	");
		JButton valider2 = new JButton(" 	OK	");
		valider2.setEnabled(false);
		commencer.setEnabled(false);

		valider.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String typeSelect = (String) combo.getSelectedItem();
				niveau = Integer.parseInt(typeSelect);
				valider2.setEnabled(true);
			}

		});
		panniveau.add(level);
		panniveau.add(combo);
		panniveau.add(valider);

		JComboBox<String> combo2 = new JComboBox<String>();
		JLabel start = new JLabel("Voulez-vous jouer en premier ? : ");
		JPanel panStart = new JPanel();
		panStart.setPreferredSize(new Dimension(700, 40));
		combo2.setPreferredSize(new Dimension(100, 20));
		combo2.addItem("Oui");
		combo2.addItem("Non");
		valider2.addActionListener(new ActionListener() {
			// selection de qui commence (1 = le joueur commence, 0 = l'IA commence)
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String typeSelect = (String) combo2.getSelectedItem();
				commencer.setEnabled(true);
				if (typeSelect == "Oui") {
					commence = 1;
				} else {
					commence = 0;
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
		
		credit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				affichageCredit();
			}
		});
		
		JButton regle = new JButton("Règles");
		regle.setPreferredSize(new Dimension(100, 20));
		
		regle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				afficherRegles();
			}
		});
		
		panCredit.add(credit);
		panCredit.add(regle);

		panCentre.add(panHello);
		panCentre.add(panniveau);
		panCentre.add(panStart);
		panCentre.add(panCommencer);
		panCentre.add(panCredit);
		
	
		
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
							for (int i = 0; i < 4; i++) {
								for (int j = 0; j < 4; j++) {
									if (e.getSource() == button[i][j]) {
										if (!joueur.coupPossible(i, j, p)) {
											JOptionPane.showMessageDialog(null, "Ce coup ne peut pas être joué !",
													"Erreur", JOptionPane.ERROR_MESSAGE);
										} else if (niveau == 1) {
											if (!p.jeuTermine(joueur, ia1)) {
												((JButton) e.getSource()).setText("X");
												((JButton) e.getSource()).setEnabled(false);
												panCentre.validate();
												setLigne(i);
												setColonne(j);
												System.out.println("(" + ligne + " ; " + colonne + ")");
												joueur.jouerCoup(i, j, p);
												System.out.println(this);
												if (!p.jeuTermine(joueur, ia1)) {
													ia1.jouerCoupIA(p);
													// recupérer la position du dernier coup joué pour positionner le
													// coup de l'IA dans l'affichage
													// button[ligne du dernier coup][colonne du dernier
													// coup].setText("O")
													int v = ia1.getDerniereLigne();
													int w = ia1.getDerniereColonne();
													button[v][w].setText("O");
													button[v][w].setEnabled(false);
													panCentre.validate();
													if (p.aGagne(joueur)) {
														JOptionPane.showMessageDialog(null, "Vous avez gagné !",
																"Erreur", JOptionPane.INFORMATION_MESSAGE);
													} else if (p.aGagne(ia1)) {
														JOptionPane.showMessageDialog(null, "Vous avez perdu !",
																"Erreur", JOptionPane.INFORMATION_MESSAGE);
													}
												} else if (p.aGagne(joueur)) {
													JOptionPane.showMessageDialog(null, "Vous avez gagné !", "Erreur",
															JOptionPane.INFORMATION_MESSAGE);
												} else if (p.aGagne(ia1)) {
													JOptionPane.showMessageDialog(null, "Vous avez perdu !", "Erreur",
															JOptionPane.INFORMATION_MESSAGE);
												}
											} else if (p.aGagne(joueur)) {
												JOptionPane.showMessageDialog(null, "Vous avez gagné !", "Erreur",
														JOptionPane.INFORMATION_MESSAGE);
											} else if (p.aGagne(ia1)) {
												JOptionPane.showMessageDialog(null, "Vous avez perdu !", "Erreur",
														JOptionPane.INFORMATION_MESSAGE);
											}
										} else if (niveau == 2) {
											if (!p.jeuTermine(joueur, ia2)) {
												((JButton) e.getSource()).setText("X");
												((JButton) e.getSource()).setEnabled(false);
												panCentre.validate();
												setLigne(i);
												setColonne(j);
												System.out.println("(" + ligne + " ; " + colonne + ")");
												joueur.jouerCoup(i, j, p);
												System.out.println(this);
												if (!p.jeuTermine(joueur, ia2)) {
													ia2.jouerCoupIANiveau3(p, joueur, 5);
													// recupérer la position du dernier coup joué pour positionner le
													// coup de l'IA dans l'affichage
													// button[ligne du dernier coup][colonne du dernier
													// coup].setText("O")
													int v = ia2.getDerniereLigne();
													int w = ia2.getDerniereColonne();
													button[v][w].setText("O");
													button[v][w].setEnabled(false);
													panCentre.validate();
													if (p.aGagne(joueur)) {
														JOptionPane.showMessageDialog(null, "Vous avez gagné !",
																"Erreur", JOptionPane.INFORMATION_MESSAGE);
													} else if (p.aGagne(ia2)) {
														JOptionPane.showMessageDialog(null, "Vous avez perdu !",
																"Erreur", JOptionPane.INFORMATION_MESSAGE);
													}
												} else if (p.aGagne(joueur)) {
													JOptionPane.showMessageDialog(null, "Vous avez gagné !", "Erreur",
															JOptionPane.INFORMATION_MESSAGE);
												} else if (p.aGagne(ia2)) {
													JOptionPane.showMessageDialog(null, "Vous avez perdu !", "Erreur",
															JOptionPane.INFORMATION_MESSAGE);
												}
											} else if (p.aGagne(joueur)) {
												JOptionPane.showMessageDialog(null, "Vous avez gagné !", "Erreur",
														JOptionPane.INFORMATION_MESSAGE);
											} else if (p.aGagne(ia2)) {
												JOptionPane.showMessageDialog(null, "Vous avez perdu !", "Erreur",
														JOptionPane.INFORMATION_MESSAGE);
											}
										} else if (niveau == 3) {
											if (!p.jeuTermine(joueur, ia3)) {
												((JButton) e.getSource()).setText("X");
												((JButton) e.getSource()).setEnabled(false);
												panCentre.validate();
												setLigne(i);
												setColonne(j);
												System.out.println("(" + ligne + " ; " + colonne + ")");
												joueur.jouerCoup(i, j, p);
												System.out.println(this);
												if (!p.jeuTermine(joueur, ia3)) {

													ia3.jouerCoupIANiveau3(p, joueur, 16);

													// recupérer la position du dernier coup joué pour positionner le
													// coup de l'IA dans l'affichage
													// button[ligne du dernier coup][colonne du dernier
													// coup].setText("O")
													int v = ia3.getDerniereLigne();
													int w = ia3.getDerniereColonne();
													button[v][w].setText("O");
													button[v][w].setEnabled(false);
													panCentre.validate();
													if (p.aGagne(joueur)) {
														JOptionPane.showMessageDialog(null, "Vous avez gagné !",
																"Erreur", JOptionPane.INFORMATION_MESSAGE);
													} else if (p.aGagne(ia3)) {
														JOptionPane.showMessageDialog(null, "Vous avez perdu !",
																"Erreur", JOptionPane.INFORMATION_MESSAGE);
													}
												} else if (p.aGagne(joueur)) {
													JOptionPane.showMessageDialog(null, "Vous avez gagné !", "Erreur",
															JOptionPane.INFORMATION_MESSAGE);
												} else if (p.aGagne(ia3)) {
													JOptionPane.showMessageDialog(null, "Vous avez perdu !", "Erreur",
															JOptionPane.INFORMATION_MESSAGE);
												}
											} else if (p.aGagne(joueur)) {
												JOptionPane.showMessageDialog(null, "Vous avez gagné !", "Erreur",
														JOptionPane.INFORMATION_MESSAGE);
											} else if (p.aGagne(ia3)) {
												JOptionPane.showMessageDialog(null, "Vous avez perdu !", "Erreur",
														JOptionPane.INFORMATION_MESSAGE);
											}
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
		// recupérer la position du dernier coup joué pour positionner le coup de l'IA
		// dans l'affichage
		// button[ligne du dernier coup][colonne du dernier coup].setText("O")

		if (commence == 0) {
			if (niveau == 1) {
				ia1.jouerCoupIA(p);
				int v = ia1.getDerniereLigne();
				int w = ia1.getDerniereColonne();
				button[v][w].setText("O");
				button[v][w].setEnabled(false);
			} else if (niveau == 2) {
				ia2.jouerCoupIANiveau3(p, joueur, 5);
				int v = ia2.getDerniereLigne();
				int w = ia2.getDerniereColonne();
				button[v][w].setText("O");
				button[v][w].setEnabled(false);
			} else {
				ia3.jouerCoupIANiveau3(p, joueur, 16);
				int v = ia3.getDerniereLigne();
				int w = ia3.getDerniereColonne();
				button[v][w].setText("O");
				button[v][w].setEnabled(false);
			}
			commence++;
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
			} while (!coup);
			System.out.println(this);
			if (!p.jeuTermine(j1, ia)) {
				ia.jouerCoupIA(p);
				// recupérer la position du dernier coup joué pour positionner le coup de l'IA
				// dans l'affichage
				// button[ligne du dernier coup][colonne du dernier coup].setText("O")
				int v = ia.getDerniereLigne();
				int w = ia.getDerniereColonne();
				button[v][w].setText("O");
				button[v][w].setEnabled(false);

				int delay = 10000; // milliseconds
				ActionListener taskPerformer = new ActionListener() {
					public void actionPerformed(ActionEvent evt) {
						System.out.println(java.util.Calendar.getInstance().getTime().toString());
					}
				};
				new Timer(delay, taskPerformer).start();

				System.out.println(this);
			} else {
				break;
			}

		} while (!p.jeuTermine(j1, ia));

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