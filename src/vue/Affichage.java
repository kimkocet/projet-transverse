package vue;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import jeu.Plateau;

public class Affichage extends JFrame {
	private JPanel panCentre = new JPanel();
	private Plateau p;
	private JButton button[][] = new JButton[4][4];

	public Affichage(Plateau p) {
		this.p = p;
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
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				button[i][j] = new JButton();
				button[i][j].addActionListener(new ActionListener() {

					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						if (e.getSource() instanceof JButton) {
							((JButton) e.getSource()).setText("X");
							((JButton) e.getSource()).setEnabled(false);
	//						p.setCase(i, j, 1);
						}
					}
				});

				panCentre.add(button[i][j]);
			}
		}
	}

}
