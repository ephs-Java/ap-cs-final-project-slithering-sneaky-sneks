
public class Display {
	
	private int squareValue;
	private int[][] squares;
	
	public Display() {
		
		int[][] squares = new int[5][5];
		
		for(int r = 0; r < squares.length; r++) {
			for(int c = 0; c < squares[0].length; c++) {
				squares[r][c] = 0;
			}
		}
		
		this.squares = squares;
	}
	
	public int[][] getSquares() {
		return this.squares;
	}
	
	public int[][] updateSquares() {
		
	}
}
