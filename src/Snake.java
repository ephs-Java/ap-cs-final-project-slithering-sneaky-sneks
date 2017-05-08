import java.util.Random;

public class Snake {
	
	private int snakeLength;
	Display gameboard = new Display();
	Square head = new Square();
	Square tail = new Square();
	Square second = new Square(3, 2);
	
	public Snake() {
		this.snakeLength = 1;
	}
	
	/*
	 * Returns true if part of the tail is to the left of the head
	 * Returns false otherwise
	 */
	public boolean isLeft() {
		if(head.getPosition()[1] == 0) {
			return false;
		} else {
			if(gameboard.getSquares()[head.getPosition()[0]][head.getPosition()[1] - 1] == 1) {
				return true;
			} else {
				return false;
			}
		}
	}
	
	/*
	 * Returns true if part of the tail is directly to the right of the head
	 * Returns false otherwise
	 */
	public boolean isRight() {
		if(head.getPosition()[1] == gameboard.getSquares()[0].length - 1) {
			return false;
		} else {
			if(gameboard.getSquares()[head.getPosition()[0]][head.getPosition()[1] + 1] == 1) {
				return true;
			} else {
				return false;
			}
		}
	}
	
	public boolean isAbove() {
		if(head.getPosition()[0] == 0) {
			return false;
		} else {
			if(gameboard.getSquares()[head.getPosition()[0] + 1][head.getPosition()[1]] == 1) {
				return true;
			} else {
				return false;
			}
		}
	}
	
	public boolean isBelow() {
		if(head.getPosition()[0] == gameboard.getSquares().length - 1) {
			return false;
		} else {
			if(gameboard.getSquares()[head.getPosition()[0] - 1][head.getPosition()[1]] == 1) {
				return true;
			} else {
				return false;
			}
		}
	}
	
	/*
	 * Returns either 0, 1, or 2 to represent the path the snake will take
	 */
	public static int choosePath() {
		Random random = new Random();
		int path = random.nextInt(3);
		
		return path;
	}
	
	/* 
	 * Moves each element of the snake depending on the path that is randomly
	 * selected
	 */
	public void moveSnake() {
		if(choosePath() == 0) {
			//move forward
		} else if(choosePath() == 1) {
			//turn left
		} else {
			//turn right
		}
		
	}
	
	public void addToSnake() {
		this.snakeLength++;
		
		//turn the square behind the end of the tail to "1"
		
	}
	
	/* 
	 * Returns true if the snake is not stuck and can continue to move
	 * Returns false otherwise
	 */
	public boolean continueOrGameOver() {
		
	}
	
	/*
	 * Returns true if the head is facing north
	 * Returns false otherwise
	 */
	public boolean isFacingNorth() {
		
		if(head.getPosition()[0] == gameboard.getSquares().length - 1) {
			return false;
		} else {
			if(head.getPosition()[0] - 1 == second.getPosition()[0]) {
				return true;
			} else {
				return false;
			}
		}
		
	}
	
	/*
	 * Returns true if the head is facing south
	 * Returns false otherwise
	 */
	public boolean isFacingSouth() {
		if(head.getPosition()[0] == 0) {
			return false;
		} else {
			if(head.getPosition()[0] + 1 == second.getPosition()[0]) {
				return true;
			} else {
				return false;
			}
		}
	}
	
	/*
	 * Returns true if the head is facing east
	 * Returns false otherwise
	 */
	public boolean isFacingEast() {
		if(head.getPosition()[1] == 0) {
			return false;
		} else {
			if(head.getPosition()[1] - 1 == second.getPosition()[1]) {
				return true;
			} else {
				return false;
			}
		}
	}
	
	/*
	 * Returns true is the head is facing west
	 * Returns false otherwise
	 */
	public boolean isFacingWest() {
		if(head.getPosition()[1] == gameboard.getSquares()[0].length - 1) {
			return false;
		} else {
			if(head.getPosition()[1] + 1 == second.getPosition()[1]) {
				return true;
			} else {
				return false;
			}
		}
	}
	
}
