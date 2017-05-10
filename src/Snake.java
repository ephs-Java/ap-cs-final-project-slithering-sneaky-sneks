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
	private boolean isComplete;
	private boolean isStuck;
	
	Display gameboard = new Display();
	Square head = new Square();
	Square tail = new Square();
	Square second = new Square(2, 1);
	

	
	public Snake() {
		this.snakeLength = 1;
		this.isLeft = true;
		this.isAbove = false;
		this.isBelow = false;
		this.isRight = false;
		this.isFacingNorth = false;
		this.isFacingEast = true;
		this.isFacingSouth = false;
		this.isFacingWest = false;
		this.isComplete = false;
		this.isStuck = false;
		
	}
	
	/*
	 * Returns true if part of the body is to the left of the head
	 * Returns false otherwise
	 */
	public void updateIsLeft() {
		if(head.getPosition()[1] == 0) {
			this.isLeft = false;
		} else {
			if(gameboard.getSquares()[head.getPosition()[0]][head.getPosition()[1] - 1] > 0) {
				this.isLeft = true;
			} else {
				this.isLeft = false;
			}
		}
	}
	
	/*
	 * Returns true if part of the body is directly to the right of the head
	 * Returns false otherwise
	 */
	public void udpateIsRight() {
		if(head.getPosition()[1] == gameboard.getSquares()[0].length - 1) {
			this.isRight = false;
		} else {
			if(gameboard.getSquares()[head.getPosition()[0]][head.getPosition()[1] + 1] > 0) {
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
			if(gameboard.getSquares()[head.getPosition()[0] + 1][head.getPosition()[1]] > 0) {
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
			if(gameboard.getSquares()[head.getPosition()[0] - 1][head.getPosition()[1]] > 0) {
				this.isBelow = true;
			} else {
				this.isBelow = false;
			}
		}
	}
	
	/*
	 * Returns either 0, 1, 2, or 3 to represent the path the snake will take
	 * 0 = north
	 * 1 = east
	 * 2 = south
	 * 3 = west
	 */
	public int choosePath() {
		
		Random random = new Random();
		int path = random.nextInt(4);
		
		if (this.isFacingNorth) {
			
			if(this.isLeft && this.isAbove) {
				return 1;
				
			} else if(this.isAbove && this.isRight) {
				return 3;
				
			} else if(this.isLeft && this.isRight) {
				return 0;
				
			} else if(this.isLeft) {
				while(path == 3 || path == 2) {
					path = random.nextInt(4);
				}
				
				return path;
				
			} else if(this.isRight) {
				while(path == 1 || path == 2) {
					path = random.nextInt(4);
				}
				
				return path;
				
			} else if(this.isAbove) {
				while(path == 0 || path == 2) {
					path = random.nextInt(4);
				}
				
				return path;
				
			} else {
				while(path == 2) {
					path = random.nextInt(4);
				}
				
				return path;
				
			}
			
		} else if (this.isFacingSouth){
			
			if(this.isLeft && this.isBelow) {
				return 1;
				
			} else if(this.isBelow && this.isRight) {
				return 3;
				
			} else if(this.isLeft && this.isRight) {
				return 2;
				
			} else if(this.isLeft) {
				while(path == 0 || path == 3) {
					path = random.nextInt(4);
				}
				
				return path;
				
			} else if(this.isRight) {
				while(path == 0 || path == 1) {
					path = random.nextInt(4);
				}
				
				return path;
				
			} else if(this.isBelow) {
				while(path == 0 || path == 2) {
					path = random.nextInt(4);
				}
				
				return path;
				
			} else {
				while(path == 0) {
					path = random.nextInt(4);
				}
				
				return path;
				
			}
			
		} else if (this.isFacingEast) {
			
			if(this.isAbove && this.isBelow) {
				return 1;
				
			} else if(this.isAbove && this.isRight) {
				return 2;
				
			} else if(this.isRight && this.isBelow) {
				return 0;
				
			} else if(this.isRight) {
				while(path == 3 || path == 1) {
					path = random.nextInt(4);
				}
				
				return path;
				
			} else if(this.isAbove) {
				while(path == 3 || path == 0) {
					path = random.nextInt(4);
				}
				
				return path;
				
			} else if(this.isBelow) {
				while(path == 3 || path == 2) {
					path = random.nextInt(4);
				}
				
				return path;
				
			} else {
				while(path == 3) {
					path = random.nextInt(4);
				}
				
				return path;
				
			}
			
		} else {
			
			if(this.isAbove && this.isBelow) {
				return 3;
				
			} else if(this.isAbove && this.isLeft) {
				return 2;
				
			} else if(this.isLeft && this.isBelow) {
				return 0;
				
			} else if(this.isLeft) {
				while(path == 1 || path == 3) {
					path = random.nextInt(4);
				}
				
				return path;
				
			} else if(this.isAbove) {
				while(path == 1 || path == 0) {
					path = random.nextInt(4);
				}
				
				return path;
				
			} else if(this.isBelow) {
				while(path == 1 || path == 2) {
					path = random.nextInt(4);
				}
				
				return path;
				
			} else {
				while(path == 1) {
					path = random.nextInt(4);
				}
				
				return path;
			}
		}
		
	}
	
	/* 
	 * Moves each element of the snake depending on the path that is randomly
	 * selected
	 */
	public void moveSnake(int path) {
		path = choosePath();
		
		if(this.isFacingNorth) {
			if(path == 0) {
				//go straight
				
			} else if(path == 1) {
				//turn right
				
			} else if(path == 3) {
				//turn left
			} 
			
		} else if(this.isFacingEast) {
			if(path == 0) {
				//turn left
				
			} else if(path == 1) {
				//go straight
				
			} else if(path == 2) {
				//turn right
			}
			
		} else if(this.isFacingSouth){
			if(path == 1) {
				//turn left
				
			} else if(path == 2) {
				//go straight
				
			} else if(path == 3) {
				//turn right
			}
		} else {
			if(path == 0) {
				//turn right
				
			} else if(path == 2) {
				//turn left
				
			} else if(path == 3) {
				//go straight
			}
		}
		
	}
	
	public void addToSnake() {
		this.snakeLength++;
		
		//turn the square behind the end of the tail to "1"
		
	}
	
	
	/*
	 * Updates isComplete to reflect whether the snake has completely filled the game board
	 */
	public void updateIsComplete() {
		for(int r = 0; r < gameboard.getSquares().length; r++) {
			for(int c = 0; c < gameboard.getSquares()[0].length; c++) {
				if(gameboard.getSquares()[r][c] < 1) {
					this.isComplete = false;
				}
			}
		}
		
		this.isComplete = true;
	}
	
	/*
	 * Updates isStuck to reflect whether the snake can move or is stuck
	 */
	public void updateIsStuck() {
		if(this.isAbove && this.isBelow && this.isLeft && this.isRight) {
			this.isStuck = false;
		}
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
	
	public boolean getIsComplete() {
		return this.isComplete;
	}
	
	public boolean getIsStuck() {
		return this.isStuck;
	}
	
}
