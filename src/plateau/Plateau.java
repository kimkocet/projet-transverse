package plateau;

public class Plateau {
	int[][] plateau = new int[4][4];
	
	public Plateau() {
		for(int i = 0 ; i < 4 ; i++)
		{
			for(int j = 0 ; j < 4 ; j++) {
				plateau[i][j] = 0;
			}
		}
	}
	
	public String toString() {
		String s = "";
		for(int i = 0 ; i < 4 ; i++) {
			s+="[ ";
			for(int j = 0 ; j < 4 ; j++) {
				s+="0 ";
			}
			s+="]\n";
		}
		return s;
	}
}
