import java.util.Random;
import java.util.Scanner;

import javax.swing.JFrame;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Snake extends JFrame implements KeyListener {

	/* * * * * * * * * * * * * * * * * * *
	 *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ *
	 *									 *
	 *		  Instance Variables		 *
	 *									 *
	 *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ *
	 * * * * * * * * * * * * * * * * * * */

	private int snakeLength;

	private int[][] gameboard = new int[60][60];

	private int thePath = 1;
	
	public int numberOfFruit;

	private boolean isNorth;
	private boolean isEast;
	private boolean isSouth;
	private boolean isWest;

	private boolean isFacingNorth;
	private boolean isFacingEast;
	private boolean isFacingSouth;
	private boolean isFacingWest;

	private boolean isComplete;
	private boolean isStuck;

	private boolean canMoveNorth;
	private boolean canMoveEast;
	private boolean canMoveSouth;
	private boolean canMoveWest;

	private int spaceHolder;
	private int spaceHolderRow;
	private int spaceHolderCol;
	private int numberOfMoves;
	private int longestSnakeLength;

	private int path;
	private boolean gameIsStarted;


	/* * * * * * * * * * * * * * * * * * *
	 *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ *
	 *									 *
	 *			Constructor(s)			 *
	 *									 *
	 *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ *
	 * * * * * * * * * * * * * * * * * * */

	public Snake() {

		initialize();
		this.longestSnakeLength = 2;
		setTitle("Snake");
		setSize(1000, 1000);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		addKeyListener(this);

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
		updateIsStuck();
		updateIsComplete();
	}

	/*
	 * Starts the gameBoard over again
	 */
	void initialize(){

		for(int r = 0; r < this.gameboard.length; r++) {
			for(int c = 0; c < this.gameboard[0].length; c++) {
				this.gameboard[r][c] = 0;
			}
		}

		this.path = 1;
		this.snakeLength = 2;

		this.numberOfFruit = 0;
		
		this.isNorth = false;
		this.isEast = false;
		this.isSouth = false;
		this.isWest = true;

		this.isFacingNorth = false;
		this.isFacingEast = true;
		this.isFacingSouth = false;
		this.isFacingWest = false;

		this.isComplete = false;
		this.isStuck = false;

		this.gameboard[30][30] = 1;
		this.gameboard[30][29] = 2;
		this.spaceHolder = 0;
		this.numberOfMoves = 0;
		this.gameIsStarted = false;

	}

	/*
	 * Returns true if the head is facing north
	 * Returns false otherwise
	 */
	public void updateIsFacingNorth() {

		int headRow = 0;
		int headCol = 0;
		int secondRow = 0;
		int secondCol = 0;

		for(int r = 0; r < this.gameboard.length; r++) {
			for(int c = 0; c < this.gameboard[0].length; c++) {
				if(this.gameboard[r][c] == 1) {
					headRow = r;
					headCol = c;
				}

				if(this.gameboard[r][c] == 2) {
					secondRow = r;
					secondCol = c;
				}
			}
		}

		if(headRow == gameboard.length - 1) {
			this.isFacingNorth = false;
		} else {
			if(headRow + 1 == secondRow) {
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

		int headRow = 0;
		int headCol = 0;
		int secondRow = 0;
		int secondCol = 0;

		for(int r = 0; r < this.gameboard.length; r++) {
			for(int c = 0; c < this.gameboard[0].length; c++) {
				if(this.gameboard[r][c] == 1) {
					headRow = r;
					headCol = c;
				}

				if(this.gameboard[r][c] == 2) {
					secondRow = r;
					secondCol = c;
				}
			}
		}

		if(headRow == 0) {
			this.isFacingSouth = false;
		} else {
			if(headRow - 1 == secondRow) {
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

		int headRow = 0;
		int headCol = 0;
		int secondRow = 0;
		int secondCol = 0;

		for(int r = 0; r < this.gameboard.length; r++) {
			for(int c = 0; c < this.gameboard[0].length; c++) {
				if(this.gameboard[r][c] == 1) {
					headRow = r;
					headCol = c;
				}

				if(this.gameboard[r][c] == 2) {
					secondRow = r;
					secondCol = c;
				}
			}
		}

		if(headCol == 0) {
			this.isFacingEast = false;
		} else {
			if(headCol - 1 == secondCol) {
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

		int headRow = 0;
		int headCol = 0;
		int secondRow = 0;
		int secondCol = 0;

		for(int r = 0; r < this.gameboard.length; r++) {
			for(int c = 0; c < this.gameboard[0].length; c++) {
				if(this.gameboard[r][c] == 1) {
					headRow = r;
					headCol = c;
				}

				if(this.gameboard[r][c] == 2) {
					secondRow = r;
					secondCol = c;
				}
			}
		}

		if(headCol == gameboard[0].length - 1) {
			this.isFacingWest = false;
		} else {
			if(headCol + 1 == secondCol) {
				this.isFacingWest = true;
			} else {
				this.isFacingWest = false;
			}
		}
	}

	/* ~~~~~~~~~~~~~
	 *  updateIs... 
	 * ~~~~~~~~~~~~~ */

	/*
	 * Returns true if part of the body is to the west of the head
	 * or if the head is in the far west column
	 * Returns false otherwise
	 */
	public void updateIsWest() {

		int headRow = 0;
		int headCol = 0;

		for(int r = 0; r < this.gameboard.length; r++) {
			for(int c = 0; c < this.gameboard[0].length; c++) {
				if(this.gameboard[r][c] == 1) {
					headRow = r;
					headCol = c;
				}
			}
		}

		if(headCol == 0) {
			this.isWest = true;
		} else {
			if(gameboard[headRow][headCol - 1] > 0) {
				this.isWest = true;
			} else {
				this.isWest = false;
			}
		}


	}

	/*
	 * Returns true if part of the body is to the east of the head
	 * or if the head is in the far east column
	 * Returns false otherwise
	 */
	public void udpateIsEast() {

		int headRow = 0;
		int headCol = 0;

		for(int r = 0; r < this.gameboard.length; r++) {
			for(int c = 0; c < this.gameboard[0].length; c++) {
				if(this.gameboard[r][c] == 1) {
					headRow = r;
					headCol = c;
				}
			}
		}

		if(headCol == gameboard[0].length - 1) {
			this.isEast = true;
		} else {
			if(gameboard[headRow][headCol + 1] > 0) {
				this.isEast = true;
			} else {
				this.isEast = false;
			}
		}

	}

	/*
	 * Returns true if part of the body is to the north of the head
	 * or if the head is in the far north row
	 * Returns false otherwise
	 */
	public void updateIsNorth() {

		int headRow = 0;
		int headCol = 0;

		for(int r = 0; r < this.gameboard.length; r++) {
			for(int c = 0; c < this.gameboard[0].length; c++) {
				if(this.gameboard[r][c] == 1) {
					headRow = r;
					headCol = c;
				}
			}
		}

		if(headRow == 0) {
			this.isNorth = true;
		} else {
			if(gameboard[headRow - 1][headCol] > 0) {
				this.isNorth = true;
			} else {
				this.isNorth = false;
			}
		}

	}

	/*
	 * Returns true if part of the body is to the south of the head object
	 * or if the head is in the far south row
	 * Returns false otherwise
	 */
	public void updateIsSouth() {

		int headRow = 0;
		int headCol = 0;

		for(int r = 0; r < this.gameboard.length; r++) {
			for(int c = 0; c < this.gameboard[0].length; c++) {
				if(this.gameboard[r][c] == 1) {
					headRow = r;
					headCol = c;
				}
			}
		}

		if(headRow == gameboard.length - 1) {
			this.isSouth = true;
		} else {
			if(gameboard[headRow + 1][headCol] > 0) {
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

	/*
	 * this method adds a tail in the spot of the previous tail
	 */
	public void addTail(){
		this.snakeLength++;
		this.gameboard[this.spaceHolderRow][this.spaceHolderCol] = this.snakeLength;
	}

	public void createFruit(){
		Random rand = new Random();
		int row = rand.nextInt(gameboard.length);
		int col = rand.nextInt(gameboard[0].length);
		while(gameboard[row][col] != 0){
			row = rand.nextInt(gameboard.length);
			col = rand.nextInt(gameboard[0].length);
		}
		gameboard[row][col] = -1;
		this.numberOfFruit++;
	}
	
	/* * * * * * * * * * * * * * * * * * *
	 *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ *
	 *									 *
	 *				LOGIC				 *
	 *									 *
	 *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ *
	 * * * * * * * * * * * * * * * * * * */


	public boolean canMove() {
		if(this.path == 0) {
			
			if(this.isNorth) {
				return false;
			} else {
				return true;
			}
			
		} else if(this.path == 1) {
			
			if(this.isEast) {
				return false;
			} else {
				return true;
			}
			
		} else if(this.path == 2) {
			
			if(this.isSouth) {
				return false;
			} else {
				return true;
			}
			
		} else {
			
			if(this.isWest) {
				return false;
			} else {
				return true;
			}
			
		}
	}
	
	



	/*
	 * Returns either 0, 1, 2, or 3 to represent the path the snake will take
	 * Returns -1 if there is 
	 * 0 = north
	 * 1 = east
	 * 2 = south
	 * 3 = west
	 */
	public int choosePath() {

		if(this.isFacingNorth) {
			if(this.path == 0) {
				return 0;
			} else if(this.path == 1) {
				return 1;
			} else if(this.path == 2) {
				return 0;
			} else {
				return 3;
			}
		} else if(this.isFacingEast) {
			if(this.path == 0) {
				return 0;
			} else if(this.path == 1) {
				return 1;
			} else if(this.path == 2) {
				return 2;
			} else {
				return 1;
			}
		} else if(this.isFacingSouth) {
			if(this.path == 0) {
				return 2;
			} else if(this.path == 1) {
				return 1;
			} else if(this.path == 2) {
				return 2;
			} else {
				return 3;
			}
		} else {
			if(this.path == 0) {
				return 0;
			} else if(this.path == 1) {
				return 3;
			} else if(this.path == 2) {
				return 2;
			} else {
				return 3;
			}
		}


		//		Random random = new Random();
		//		int path = random.nextInt(4);
		//		
		//		
		//		
		//
		//		// head is facing north
		//		if (this.isFacingNorth) {
		//			
		//			// can go any direction
		//				while(path == 2) {
		//					path = random.nextInt(4);
		//				}
		//				
		//				// can only go east
		//			if (this.isWest && this.isNorth) {
		//				return 1;
		//				
		//				// can only go west
		//			} else if (this.isNorth && this.isEast) {
		//				return 3;
		//
		//				// can only go north
		//			} else if (this.isWest && this.isEast) {
		//				return 0;
		//
		//				// can only go north or east 
		//			} else if (this.isWest) {
		//				while(path == 3 || path == 2) {
		//					path = random.nextInt(4);
		//				}
		//
		//				return path;
		//
		//				// can only go north or west
		//			} else if (this.isEast) {
		//				while(path == 1 || path == 2) {
		//					path = random.nextInt(4);
		//				}
		//
		//				return path;
		//
		//				// can only go east or west
		//			} else if (this.isNorth) {
		//				while(path == 0 || path == 2) {
		//					path = random.nextInt(4);
		//				}
		//
		//				return path;
		//			}
		//			
		//			return path;
		//
		//		// head is facing south
		//		} else if (this.isFacingSouth){
		//			
		//			// can go any direction
		//		
		//				while(path == 0) {
		//					path = random.nextInt(4);
		//				}
		//				
		//				
		//				// can only go east
		//			if(this.isWest && this.isSouth) {
		//				return 1;
		//				
		//				// can only go west
		//			} else if(this.isSouth && this.isEast) {
		//				return 3;
		//				
		//				// can only go south
		//			} else if(this.isWest && this.isEast) {
		//				return 2;
		//				
		//				// can only go south or east
		//			} else if(this.isWest) {
		//				while(path == 0 || path == 3) {
		//					path = random.nextInt(4);
		//				}
		//				return path;
		//				
		//				// can only go west or south
		//			} else if(this.isEast) {
		//				while(path == 0 || path == 1) {
		//					path = random.nextInt(4);
		//				}
		//				return path;
		//				
		//				// can only go east or west
		//			} else if(this.isSouth) {
		//				while(path == 0 || path == 2) {
		//					path = random.nextInt(4);
		//				}
		//				return path;
		//
		//			} 
		//			
		//			return path;
		//
		//		// head is facing east
		//		} else if (this.isFacingEast) {
		//			
		//			// can go any direction
		//			
		//				while(path == 3) {
		//					path = random.nextInt(4);
		//				}
		//				
		//				
		//				// can only go east
		//			if(this.isNorth && this.isSouth) {
		//				return 1;
		//				
		//				// can only go south
		//			} else if(this.isNorth && this.isEast) {
		//				return 2;
		//				
		//				// can only go north
		//			} else if(this.isEast && this.isSouth) {
		//				return 0;
		//				
		//				// can only go north or south
		//			} else if(this.isEast) {
		//				while(path == 3 || path == 1) {
		//					path = random.nextInt(4);
		//				}
		//				return path;
		//				
		//				// can only go east or south
		//			} else if(this.isNorth) {
		//				while(path == 3 || path == 0) {
		//					path = random.nextInt(4);
		//				}
		//				return path;
		//
		//				// can only go north or east
		//			} else if(this.isSouth) {
		//				while(path == 3 || path == 2) {
		//					path = random.nextInt(4);
		//				}
		//				return path;
		//
		//			}
		//			
		//			return path;
		//
		//		// head is facing west
		//		} else if (this.isFacingWest) {
		//
		//			// can go any direction
		//			
		//				while(path == 1) {
		//					path = random.nextInt(4);
		//				}
		//				
		//				
		//				// can only go west
		//			if(this.isNorth && this.isSouth) {
		//				return 3;
		//				
		//				// can only go south
		//			} else if(this.isNorth && this.isWest) {
		//				return 2;
		//				
		//				// can only go north
		//			} else if(this.isWest && this.isSouth) {
		//				return 0;
		//
		//				// can only go north or south
		//			} else if(this.isWest) {
		//				while(path == 1 || path == 3) {
		//					path = random.nextInt(4);
		//				}
		//				return path;
		//
		//				// can only go west or south
		//			} else if(this.isNorth) {
		//				while(path == 1 || path == 0) {
		//					path = random.nextInt(4);
		//				}
		//				return path;
		//
		//				// can only go west or north
		//			} else if(this.isSouth) {
		//				while(path == 1 || path == 2) {
		//					path = random.nextInt(4);
		//				}
		//				return path;
		//
		//			} 
		//			
		//			return path;
		//			
		//		}
		//		return -1;

	}


	/* 
	 * Continues the game if the snake is not stuck and can continue to move
	 * Ends the game and prints you win message if the snake is complete
	 * Ends the game and prints game over message if the snake is stuck
	 */
	public void continueOrGameOver() throws InterruptedException {

		
//		while(this.gameIsStarted == false) {
//			//do nothing
//			Thread.sleep(50);
//		}
		
		while(this.gameIsStarted == false) {
			//do nothing
			Thread.sleep(50);
		}

		while(!(getIsComplete() && getIsStuck())) {
			
			while(canMove()) {
				moveSnake();
				repaint();
				//choosePath is embedded in moveSnake
				
				
//				//adds to the snake every ten moves
//				if(this.numberOfMoves % 10 == 0) {
//					addTail();
//				}

				if(this.numberOfFruit <= 2 && this.numberOfMoves % 100 == 0){
					createFruit();
				}
				
				//
				
				updateAll();
	
				//updates longestSnakeLength
				if(this.snakeLength > this.longestSnakeLength) {
					this.longestSnakeLength = this.snakeLength;
				}
	
				//prints the array
				//			for(int r = 0; r < this.gameboard.length; r++) {
				//				for(int c = 0; c < this.gameboard[0].length; c++) {
				//					if(this.gameboard[r][c] == 1) {
				//						System.out.print("@" + " ");
				//					}
				//					else if(this.gameboard[r][c] > 0) {
				//						System.out.print("+" + " ");
				//					} else {
				//						System.out.print("." + " ");
				//					}
				//				}
				//				
				//				
				//				System.out.println();
				//			}
	
				//			//prints out current and longest snake length
				//			System.out.println("\t\t\t Snake length: " + this.snakeLength);
				//			System.out.println("\t\t\t Longest Snake length: " + this.longestSnakeLength);
				//			System.out.println();
	
				//Pause for one second, then make next move
				Thread.sleep(50);
			
			}

		} 

		//System.out.println();
		//System.out.println("GAME OVER");
		//System.out.println("Final score: " + this.snakeLength);

		/*
		 * Resets the game so it plays continuously 
		 */
		initialize();
		continueOrGameOver();
		
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
	public void moveSnake() {

		int path = choosePath();

		int headRow = 0;
		int headCol = 0;
		int swapCounter = 0;
		int numFruit = this.numberOfFruit;

		//Finds the row and column of the head
		for(int row = 0; row < this.gameboard.length; row++) {
			for(int col = 0; col < this.gameboard[0].length; col++) {
				if(this.gameboard[row][col] == 1) {
					headRow = row;
					headCol = col;
				}
			}
		}

		// sets the holder for where the head needs to move next
		if(this.isFacingNorth) {
			if(path == 0) {
				this.spaceHolderRow = headRow - 1;
				this.spaceHolderCol = headCol;

			} else if(path == 1) {
				this.spaceHolderRow = headRow;
				this.spaceHolderCol = headCol + 1;

			} else if(path == 3) {
				this.spaceHolderRow = headRow;
				this.spaceHolderCol = headCol - 1;
			} 
		} else if(this.isFacingEast) {
			if(path == 0) {
				this.spaceHolderRow = headRow - 1;
				this.spaceHolderCol = headCol;

			} else if(path == 1) {
				this.spaceHolderRow = headRow;
				this.spaceHolderCol = headCol + 1;

			} else if(path == 2) {
				this.spaceHolderRow = headRow + 1;
				this.spaceHolderCol = headCol;
			}
		} else if(this.isFacingSouth){
			if(path == 1) {
				this.spaceHolderRow = headRow;
				this.spaceHolderCol = headCol + 1;

			} else if(path == 2) {
				this.spaceHolderRow = headRow + 1;
				this.spaceHolderCol = headCol;

			} else if(path == 3) {
				this.spaceHolderRow = headRow;
				this.spaceHolderCol = headCol - 1;
			}
		} else {
			if(path == 0) {
				this.spaceHolderRow = headRow - 1;
				this.spaceHolderCol = headCol;

			} else if(path == 2) {
				this.spaceHolderRow = headRow + 1;
				this.spaceHolderCol = headCol;

			} else if(path == 3) {
				this.spaceHolderRow = headRow;
				this.spaceHolderCol = headCol - 1;
			}
		}
		
		if (this.gameboard[this.spaceHolderRow][this.spaceHolderCol] == -1){
			numFruit--;
		}

		for(int i = 1; i <= this.snakeLength; i++) {
			for(int r = 0; r < this.gameboard.length; r++) {
				for(int c = 0; c < this.gameboard[0].length; c++) {
					if(this.gameboard[r][c] == i) {
						if(swapCounter < 1) {
							swap(r, c);
							swapCounter++;
						}

					}
				}
			}
			swapCounter = 0;
		}

		if (numFruit < this.numberOfFruit){
			addTail();
		}
		this.numberOfFruit = numFruit;
		this.numberOfMoves++;

	}

	/*
	 * Swaps the value and index of the snake square with the 
	 * value and index of the spaceHolder
	 */
	public void swap(int row, int col) {



		int rowHolder = row;
		int colHolder = col;
		int holder = this.gameboard[row][col];

		this.gameboard[row][col] = this.spaceHolder;
		this.gameboard[this.spaceHolderRow][this.spaceHolderCol] = holder;



		row = this.spaceHolderRow;
		this.spaceHolderRow = rowHolder;

		col = this.spaceHolderCol;
		this.spaceHolderCol = colHolder;

	}

	/*
	 * turns the square to the south of the tail to (1 + snakeLength) and adds to snake length
	 * If the square to the south of the snake is filled it tries the west, then the north, then the east
	 * If there is no open space to add to the tail, the snake simply continuous without adding to the tail
	 */
	public void addToSnake() {

		int tailRow = 0;
		int tailCol = 0;

		for(int r = 0; r < this.gameboard.length; r++) {
			for(int c = 0; c < this.gameboard[0].length; c++) {
				if(this.gameboard[r][c] == this.snakeLength) {
					tailRow = r;
					tailCol = c;
				}
			}
		}

		//Tries to add the new piece to the south
		if(tailRow != this.gameboard[0].length - 1 && this.gameboard[tailRow + 1][tailCol] < 1) {
			this.gameboard[tailRow + 1][tailCol] = this.snakeLength + 1;
			this.snakeLength++;

			//Tries to add the new piece to the west	
		} else if(tailCol != 0 && this.gameboard[tailRow][tailCol - 1] < 1) {
			this.gameboard[tailRow][tailCol - 1] = this.snakeLength + 1;
			this.snakeLength++;

			//Tries to add the new piece to the north	
		} else if(tailRow != 0 && this.gameboard[tailRow - 1][tailCol] < 1) {
			this.gameboard[tailRow - 1][tailCol] = this.snakeLength + 1;
			this.snakeLength++;

			//Tries to add the new piece to the east	
		} else if(tailCol != this.gameboard[0].length - 1 && this.gameboard[tailRow][tailCol + 1] < 1) {
			this.gameboard[tailRow][tailCol + 1] = this.snakeLength + 1;
			this.snakeLength++;

		} 

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

	/* * * * * * * * * * * * * * * * * * *
	 *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ *
	 *									 *
	 *			 	OTHER				 *
	 *									 *
	 *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ *
	 * * * * * * * * * * * * * * * * * * */

	public void printGame(){

		for(int r = 0; r < gameboard.length; r++){
			for(int c = 0; c < gameboard[r].length; c++){
				System.out.print("[ " + gameboard[r][c] + " ]");
			}
			System.out.println();
		}

		System.out.println();
		System.out.println();

	}

	public void gameOver(){

		Scanner input = new Scanner(System.in);

		System.out.println("game over");

		System.exit(0);
	}

	public void paint(Graphics g) {
		int x = 50;
		int y = 50;

		Color color = new Color(45, 250, 30);

		for(int r = 0; r < this.gameboard.length; r++) {
			for(int c = 0; c < this.gameboard[0].length; c++) {
				if(this.gameboard[r][c] == 1) {
					g.setColor(Color.red);
					g.fillRect(x, y, 10, 10);
					g.setColor(Color.black);
					g.drawRect(x, y, 10, 10);
					x = x + 10;
				} else if (this.gameboard[r][c] == -1){
					g.setColor(Color.yellow);
					g.fillRect(x, y, 10, 10);
					g.setColor(Color.black);
					g.drawRect(x, y, 10, 10);
					x = x + 10;
				} else if(this.gameboard[r][c] > 0) {
					g.setColor(color);
					g.fillRect(x, y, 10, 10);
					g.setColor(Color.black);
					g.drawRect(x, y, 10, 10);
					x = x + 10;
				} else {
					g.setColor(Color.BLACK);
					g.fillRect(x, y, 10, 10);
					g.setColor(Color.black);
					g.drawRect(x, y, 10, 10);
					x = x + 10;
				}


			}
			x = 50;
			y = y + 10;
		}

	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyPressed(KeyEvent e) {

		int keyCode = e.getKeyCode();

		if(keyCode == KeyEvent.VK_UP || keyCode == KeyEvent.VK_LEFT || keyCode == KeyEvent.VK_RIGHT || keyCode == KeyEvent.VK_DOWN) {
			this.gameIsStarted = true;
		}

		if(this.isFacingNorth) {
			if(keyCode == KeyEvent.VK_UP) {
				this.path = 0;
			} else if(keyCode == KeyEvent.VK_LEFT) {
				this.path = 3;
			} else if(keyCode == KeyEvent.VK_RIGHT) {
				this.path = 1;
			} else {
				this.path = 0;
			}
		} else if(this.isFacingEast) {
			if(keyCode == KeyEvent.VK_UP) {
				this.path = 0;
			} else if(keyCode == KeyEvent.VK_LEFT) {
				this.path = 1;
			} else if(keyCode == KeyEvent.VK_RIGHT) {
				this.path = 1;
			} else {
				this.path = 2;
			}
		} else if(this.isFacingSouth) {
			if(keyCode == KeyEvent.VK_UP) {
				this.path = 2;
			} else if(keyCode == KeyEvent.VK_LEFT) {
				this.path = 3;
			} else if(keyCode == KeyEvent.VK_RIGHT) {
				this.path = 1;
			} else {
				this.path = 2;
			}
		} else {
			if(keyCode == KeyEvent.VK_UP) {
				this.path = 0;
			} else if(keyCode == KeyEvent.VK_LEFT) {
				this.path = 3;
			} else if(keyCode == KeyEvent.VK_RIGHT) {
				this.path = 3;
			} else {
				this.path = 2;
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
}







