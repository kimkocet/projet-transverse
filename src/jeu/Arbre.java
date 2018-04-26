package jeu;

import java.util.LinkedList;


public class Arbre<Plateau> {
	
    private Plateau val;
    private LinkedList<Arbre<Plateau>> enfants;

	public Arbre(Plateau val) {
        this.val = val;
        this.enfants = new LinkedList<Arbre<Plateau>>();
    }
	
	public Arbre() {
        this.enfants = new LinkedList<Arbre<Plateau>>();
    }

    public Arbre(Plateau val, LinkedList<Arbre<Plateau>> enfants) {
        this.val = val;
        this.enfants = enfants;
    }
    
    public void addEnfant(Arbre<Plateau> enfant) {
    	this.enfants.add(enfant);
    }
    
    public Plateau getVal() {
    	return val;
    }
    
	public void setVal(Plateau val) {
    	this.val = val;
    }
    
    public LinkedList<Arbre<Plateau>> getEnfants() {
    	return enfants;
    }
    
    public String toString() {
    	String s = "\n";
    	s += val;
    	if(enfants.size() != 0) {
    		for(int i = 0; i < enfants.size(); i++) {
    			s += enfants.get(i).toString();
    		}
    	}
    	return s;
    }
}
