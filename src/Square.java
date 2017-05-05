
public class Square {
	private int row;
	private int column;
	
	public Square() {
		this.row = 2;
		this.column = 2;
	}
	
	public Square(int r, int c) {
		this.row = r;
		this.column = c;
	}
	
	/*
	 * Returns the position of the square as an array, with the first
	 * spot in the array being its row and the second spot being its 
	 * column
	 */
	public int[] getPosition() {
		int[] position = new int[2];
		
		position[0] = this.row;
		position[1] = this.column;
		
		return position;
	}

}
