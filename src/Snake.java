//hello

import java.util.Random;

public class Snake {
	
	private int snakeLength;
	
	private boolean isLeft;
	private boolean isRight;
	private boolean isAbove;
	private boolean isBelow;
	private boolean isFacingNorth;
	private boolean isFacingSouth;
	private boolean isFacingEast;
	private boolean isFacingWest;
	
	Display gameboard = new Display();
	Square head = new Square();
	Square tail = new Square();
	Square second = new Square(3, 2);
	
	public Snake() {
		this.snakeLength = 1;
		this.isLeft = false;
		this.isAbove = false;
		this.isBelow = false;
		this.isRight = false;
		this.isFacingNorth = false;
		this.isFacingEast = true;
		this.isFacingSouth = false;
		this.isFacingWest = false;
		
	}
	
	/*
	 * Returns true if part of the tail is to the left of the head
	 * Returns false otherwise
	 */
	public void updateIsLeft() {
		if(head.getPosition()[1] == 0) {
			this.isLeft = false;
		} else {
			if(gameboard.getSquares()[head.getPosition()[0]][head.getPosition()[1] - 1] == 1) {
				this.isLeft = true;
			} else {
				this.isLeft = false;
			}
		}
	}
	
	/*
	 * Returns true if part of the tail is directly to the right of the head
	 * Returns false otherwise
	 */
	public void udpateIsRight() {
		if(head.getPosition()[1] == gameboard.getSquares()[0].length - 1) {
			this.isRight = false;
		} else {
			if(gameboard.getSquares()[head.getPosition()[0]][head.getPosition()[1] + 1] == 1) {
				this.isRight = true;
			} else {
				this.isRight = false;
			}
		}
	}
	
	public void updateIsAbove() {
		if(head.getPosition()[0] == 0) {
			this.isAbove = false;
		} else {
			if(gameboard.getSquares()[head.getPosition()[0] + 1][head.getPosition()[1]] == 1) {
				this.isAbove = true;
			} else {
				this.isAbove = false;
			}
		}
	}
	
	public void updateIsBelow() {
		if(head.getPosition()[0] == gameboard.getSquares().length - 1) {
			this.isBelow = false;
		} else {
			if(gameboard.getSquares()[head.getPosition()[0] - 1][head.getPosition()[1]] == 1) {
				this.isBelow = true;
			} else {
				this.isBelow = false;
			}
		}
	}
	
	/*
	 * Returns either 0, 1, or 2 to represent the path the snake will take
	 */
	public int choosePath() {
		
		Random random = new Random();
		int path = random.nextInt(3);
		
		if (this.isFacingNorth){
			
		} else if (this.isFacingEast){
			
		}
		
		return path;
	}
	
	/* 
	 * Moves each element of the snake depending on the path that is randomly
	 * selected
	 */
	public void moveSnake(int path) {
		path = choosePath();
		
		if(path == 0) {
			//move forward
		} else if(path == 1) {
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
	public void updateIsFacingNorth() {
		
		if(head.getPosition()[0] == gameboard.getSquares().length - 1) {
			this.isFacingNorth = false;
		} else {
			if(head.getPosition()[0] - 1 == second.getPosition()[0]) {
				this.isFacingNorth = true;
			} else {
				this.isFacingNorth = false;
			}
		}
		
	}
	
	/*
	 * Returns true if the head is facing south
	 * Returns false otherwise
	 */
	public void updateIsFacingSouth() {
		if(head.getPosition()[0] == 0) {
			this.isFacingSouth = false;
		} else {
			if(head.getPosition()[0] + 1 == second.getPosition()[0]) {
				this.isFacingSouth = true;
			} else {
				this.isFacingSouth = false;
			}
		}
	}
	
	/*
	 * Returns true if the head is facing east
	 * Returns false otherwise
	 */
	public void updateIsFacingEast() {
		if(head.getPosition()[1] == 0) {
			this.isFacingEast = false;
		} else {
			if(head.getPosition()[1] - 1 == second.getPosition()[1]) {
				this.isFacingEast = true;
			} else {
				this.isFacingEast = false;
			}
		}
	}
	
	/*
	 * Returns true is the head is facing west
	 * Returns false otherwise
	 */
	public void updateIsFacingWest() {
		if(head.getPosition()[1] == gameboard.getSquares()[0].length - 1) {
			this.isFacingWest = false;
		} else {
			if(head.getPosition()[1] + 1 == second.getPosition()[1]) {
				this.isFacingWest = true;
			} else {
				this.isFacingWest = false;
			}
		}
	}
	
}
