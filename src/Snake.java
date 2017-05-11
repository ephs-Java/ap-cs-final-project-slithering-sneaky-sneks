import java.util.Random;

public class Snake {
	
	private int snakeLength;
	
	int[][] gameboard = new int[5][5];
	private boolean isWest;
	private boolean isEast;
	private boolean isNorth;
	private boolean isSouth;
	private boolean isFacingNorth;
	private boolean isFacingSouth;
	private boolean isFacingEast;
	private boolean isFacingWest;
	private boolean isComplete;
	private boolean isStuck;
	
	
	
	public Snake() {
		this.snakeLength = 1;
		this.isWest = true;
		this.isNorth = false;
		this.isSouth = false;
		this.isEast = false;
		this.isFacingNorth = false;
		this.isFacingEast = true;
		this.isFacingSouth = false;
		this.isFacingWest = false;
		this.isComplete = false;
		this.isStuck = false;
		this.gameboard[2][2] = 1;
		this.gameboard[2][1] = 2;
		
	}
	
	/* * * * * * * * * * * * * * * * * * *
	 *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ *
	 *									 *
	 *			UPDATE METHODS			 *
	 *									 *
	 *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ *
	 * * * * * * * * * * * * * * * * * * */

	/*
	 * Updates all methods
	 */
	public void updateAll(){
		udpateIsEast();
		updateIsNorth();
		updateIsSouth();
		updateIsWest();
		updateIsFacingEast();
		updateIsFacingNorth();
		updateIsFacingSouth();
		updateIsFacingWest();
//		updateIsStuck();
//		updateIsComplete();
	}
	
	
	/*
	 * Returns true if the head is facing north
	 * Returns false otherwise
	 */
	public void updateIsFacingNorth() {
		
		Square head = new Square();
		Square second = new Square();
		
		for(int r = 0; r < gameboard.length; r++) {
			for(int c = 0; c < gameboard[0].length; c++) {
				if(gameboard[r][c] == 1) {
					head.getPosition()[0] = r;
					head.getPosition()[1] = c;
				}
				
				if(gameboard[r][c] == 2) {
					second.getPosition()[0] = r;
					second.getPosition()[1] = c;
				}
			}
		}
		
		if(head.getPosition()[0] == gameboard.length - 1) {
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
		
		Square head = new Square();
		Square second = new Square();
		
		for(int r = 0; r < gameboard.length; r++) {
			for(int c = 0; c < gameboard[0].length; c++) {
				if(gameboard[r][c] == 1) {
					head.getPosition()[0] = r;
					head.getPosition()[1] = c;
				}
				
				if(gameboard[r][c] == 2) {
					second.getPosition()[0] = r;
					second.getPosition()[1] = c;
				}
			}
		}
		
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
		
		Square head = new Square();
		Square second = new Square();
		
		for(int r = 0; r < gameboard.length; r++) {
			for(int c = 0; c < gameboard[0].length; c++) {
				if(gameboard[r][c] == 1) {
					head.getPosition()[0] = r;
					head.getPosition()[1] = c;
				}
				
				if(gameboard[r][c] == 2) {
					second.getPosition()[0] = r;
					second.getPosition()[1] = c;
				}
			}
		}
		
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
		
		Square head = new Square();
		Square second = new Square();
		
		for(int r = 0; r < gameboard.length; r++) {
			for(int c = 0; c < gameboard[0].length; c++) {
				if(gameboard[r][c] == 1) {
					head.getPosition()[0] = r;
					head.getPosition()[1] = c;
				}
				
				if(gameboard[r][c] == 2) {
					second.getPosition()[0] = r;
					second.getPosition()[1] = c;
				}
			}
		}
		
		if(head.getPosition()[1] == gameboard[0].length - 1) {
			this.isFacingWest = false;
		} else {
			if(head.getPosition()[1] + 1 == second.getPosition()[1]) {
				this.isFacingWest = true;
			} else {
				this.isFacingWest = false;
			}
		}
	}
	/*
	 * Returns true if part of the body is to the west of the head object
	 * Returns false otherwise
	 */
	public void updateIsWest() {
		
		Square head = new Square();
		
		for(int r = 0; r < gameboard.length; r++) {
			for(int c = 0; c < gameboard[0].length; c++) {
				if(gameboard[r][c] == 1) {
					head.getPosition()[0] = r;
					head.getPosition()[1] = c;
				}
			}
		}
		
		
		
		if(head.getPosition()[1] == 0) {
			this.isWest = false;
		} else {
			if(gameboard[head.getPosition()[0]][head.getPosition()[1] - 1] > 0) {
				this.isWest = true;
			} else {
				this.isWest = false;
			}
		}
		
		
	}
	
	/*
	 * Returns true if part of the body is to the east of the head object
	 * Returns false otherwise
	 */
	public void udpateIsEast() {
		
		Square head = new Square();
		
		for(int r = 0; r < gameboard.length; r++) {
			for(int c = 0; c < gameboard[0].length; c++) {
				if(gameboard[r][c] == 1) {
					head.getPosition()[0] = r;
					head.getPosition()[1] = c;
				}
			}
		}
		
		if(head.getPosition()[1] == gameboard[0].length - 1) {
			this.isEast = false;
		} else {
			if(gameboard[head.getPosition()[0]][head.getPosition()[1] + 1] > 0) {
				this.isEast = true;
			} else {
				this.isEast = false;
			}
		}
		
	}
	
	/*
	 * Returns true if part of the body is to the north of the head object
	 * Returns false otherwise
	 */
	public void updateIsNorth() {
		
		Square head = new Square();
		
		for(int r = 0; r < gameboard.length; r++) {
			for(int c = 0; c < gameboard[0].length; c++) {
				if(gameboard[r][c] == 1) {
					head.getPosition()[0] = r;
					head.getPosition()[1] = c;
				}
			}
		}
		
		if(head.getPosition()[0] == 0) {
			this.isNorth = false;
		} else {
			if(gameboard[head.getPosition()[0] + 1][head.getPosition()[1]] > 0) {
				this.isNorth = true;
			} else {
				this.isNorth = false;
			}
		}
		
	}
	
	/*
	 * Returns true if part of the body is to the south of the head object
	 * Returns false otherwise
	 */
	public void updateIsSouth() {
		
		Square head = new Square();
		
		for(int r = 0; r < gameboard.length; r++) {
			for(int c = 0; c < gameboard[0].length; c++) {
				if(gameboard[r][c] == 1) {
					head.getPosition()[0] = r;
					head.getPosition()[1] = c;
				}
			}
		}
		
		if(head.getPosition()[0] == gameboard.length - 1) {
			this.isSouth = false;
		} else {
			if(gameboard[head.getPosition()[0] - 1][head.getPosition()[1]] > 0) {
				this.isSouth = true;
			} else {
				this.isSouth = false;
			}
		}
		
	}
	
	/*
	 * Updates isComplete to reflect whether the snake has completely filled the game board
	 */
	public void updateIsComplete() {
		for(int r = 0; r < gameboard.length; r++) {
			for(int c = 0; c < gameboard[0].length; c++) {
				if(gameboard[r][c] < 1) {
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
		if(this.isNorth && this.isSouth && this.isWest && this.isEast) {
			this.isStuck = true;
		}
	}
	
	/* * * * * * * * * * * * * * * * * * *
	 *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ *
	 *									 *
	 *				LOGIC				 *
	 *									 *
	 *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ *
	 * * * * * * * * * * * * * * * * * * */

	
	/*
	 * Returns either 0, 1, 2, or 3 to represent the path the snake will take
	 * Returns -1 if there is 
	 * 0 = north
	 * 1 = east
	 * 2 = south
	 * 3 = west
	 */
	public int choosePath() {

		Random random = new Random();
		int path = random.nextInt(4);

		// head is facing north
		if (this.isFacingNorth) {
			
			// can go any direction
			if (!(this.isWest && this.isNorth && this.isEast)) {
				while(path == 2) {
					path = random.nextInt(4);
				}
				return path;
				
				// can only go east
			} else if (this.isWest && this.isNorth) {
				return 1;
				
				// can only go west
			} else if (this.isNorth && this.isEast) {
				return 3;

				// can only go north
			} else if (this.isWest && this.isEast) {
				return 0;

				// can only go north or east 
			} else if (this.isWest) {
				while(path == 3 || path == 2) {
					path = random.nextInt(4);
				}

				return path;

				// can only go north or west
			} else if (this.isEast) {
				while(path == 1 || path == 2) {
					path = random.nextInt(4);
				}

				return path;

				// can only go east or west
			} else if (this.isNorth) {
				while(path == 0 || path == 2) {
					path = random.nextInt(4);
				}

				return path;
			}

		// head is facing south
		} else if (this.isFacingSouth){
			
			// can go any direction
			if (!(this.isWest && this.isSouth && this.isEast)) {
				while(path == 0) {
					path = random.nextInt(4);
				}
				return path;
				
				// can only go east
			} else if(this.isWest && this.isSouth) {
				return 1;
				
				// can only go west
			} else if(this.isSouth && this.isEast) {
				return 3;
				
				// can only go south
			} else if(this.isWest && this.isEast) {
				return 2;
				
				// can only go south or east
			} else if(this.isWest) {
				while(path == 0 || path == 3) {
					path = random.nextInt(4);
				}
				return path;
				
				// can only go west or south
			} else if(this.isEast) {
				while(path == 0 || path == 1) {
					path = random.nextInt(4);
				}
				return path;
				
				// can only go east or west
			} else if(this.isSouth) {
				while(path == 0 || path == 2) {
					path = random.nextInt(4);
				}
				return path;

			} 

		// head is facing east
		} else if (this.isFacingEast) {
			
			// can go any direction
			if (!(this.isNorth && this.isSouth && this.isEast)) {
				while(path == 3) {
					path = random.nextInt(4);
				}
				return path;
				
				// can only go east
			} else if(this.isNorth && this.isSouth) {
				return 1;
				
				// can only go south
			} else if(this.isNorth && this.isEast) {
				return 2;
				
				// can only go north
			} else if(this.isEast && this.isSouth) {
				return 0;
				
				// can only go north or south
			} else if(this.isEast) {
				while(path == 3 || path == 1) {
					path = random.nextInt(4);
				}
				return path;
				
				// can only go east or south
			} else if(this.isNorth) {
				while(path == 3 || path == 0) {
					path = random.nextInt(4);
				}
				return path;

				// can only go north or east
			} else if(this.isSouth) {
				while(path == 3 || path == 2) {
					path = random.nextInt(4);
				}
				return path;

			}

		// head is facing west
		} else if (this.isFacingWest) {

			// can go any direction
			if (!(this.isWest && this.isSouth && this.isNorth)) {
				while(path == 1) {
					path = random.nextInt(4);
				}
				return path;
				
				// can only go west
			} else if(this.isNorth && this.isSouth) {
				return 3;
				
				// can only go south
			} else if(this.isNorth && this.isWest) {
				return 2;
				
				// can only go north
			} else if(this.isWest && this.isSouth) {
				return 0;

				// can only go north or south
			} else if(this.isWest) {
				while(path == 1 || path == 3) {
					path = random.nextInt(4);
				}
				return path;

				// can only go west or south
			} else if(this.isNorth) {
				while(path == 1 || path == 0) {
					path = random.nextInt(4);
				}
				return path;

				// can only go west or north
			} else if(this.isSouth) {
				while(path == 1 || path == 2) {
					path = random.nextInt(4);
				}
				return path;

			} 
			
		}
		return -1;

	}
	
	/* * * * * * * * * * * * * * * * * * *
	 *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ *
	 *									 *
	 *			MODIFY SNAKE			 *
	 *									 *
	 *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ *
	 * * * * * * * * * * * * * * * * * * */


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
	
	
	/* * * * * * * * * * * * * * * * * * *
	 *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ *
	 *									 *
	 *			 GET METHODS			 *
	 *									 *
	 *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ *
	 * * * * * * * * * * * * * * * * * * */
	
	public boolean getIsComplete() {
		return this.isComplete;
	}
	
	public boolean getIsStuck() {
		return this.isStuck;
	}
	
}
