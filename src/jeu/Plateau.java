package jeu;

public class Plateau {

	private static final int SIZE = 4;
	int[][] plateau = new int[SIZE][SIZE];

	
	public Plateau() {
		for(int i = 0 ; i < SIZE ; i++)
		{
			for(int j = 0 ; j < SIZE ; j++) {
				plateau[i][j] = 0;
			}
		}
	}
	
	public int[][] getPlateau() {
		return plateau;
	}

	public void setPlateau(int[][] plateau) {
		for(int i = 0; i < SIZE; i++) {
			for(int j = 0; j < SIZE; j++) {
				this.plateau[i][j] = plateau[i][j];
			}
		}
	}
	
	public void setCase(int l, int c, int valeur) {
		try {
			plateau[l][c] = valeur;
		} catch(IndexOutOfBoundsException e) {
			e.printStackTrace();
		}
	}
	
	public String toString() {
		String s = "";
		for(int i = 0 ; i < SIZE ; i++) {
			s+="[ ";
			for(int j = 0 ; j < SIZE ; j++) {
				s += plateau[i][j];
				s += " ";
			}
			s+="]\n";
		}
		return s;
	}
}
