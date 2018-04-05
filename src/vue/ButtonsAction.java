package vue;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JButton;


public class ButtonsAction extends AbstractAction {

	private String texte;
	
	public ButtonsAction(String texte) {
		super(texte);
		this.texte = texte;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		
	}

	

}
