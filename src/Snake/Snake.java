package Snake;
import java.util.Random;
import java.util.Scanner;

import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

public class Snake extends JFrame implements KeyListener, MouseListener, Runnable {

	/* * * * * * * * * * * * * * * * * * *
	 *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ *
	 *									 *
	 *		  Instance Variables		 *
	 *									 *
	 *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ *
	 * * * * * * * * * * * * * * * * * * */

	private int snakeLength;

	private int[][] gameboard = new int[60][60];
	
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

	private int spaceHolder;
	private int spaceHolderRow;
	private int spaceHolderCol;
	private int numberOfMoves;
	private int longestSnakeLength;

	private int path;
	private boolean gameIsStarted;
	
	private boolean isPlayAgainPresed;
	private boolean isPlayAgainClicked;
	private boolean isMainMenuPressed;
	private boolean isMainMenuClicked;
	private boolean isGameOver;
	
	private boolean canMove;
	private int speed;
	private int headColor;
	private int bodyColor;
	private int fruitColor;
	private int backgroundColor;


	/* * * * * * * * * * * * * * * * * * *
	 *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ *
	 *									 *
	 *			Constructor(s)			 *
	 *									 *
	 *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ *
	 * * * * * * * * * * * * * * * * * * */

	public Snake() throws FileNotFoundException {

		Scanner input = new Scanner(new File("High Score"));
		String l = "";
		while(input.hasNext()) {
			l = input.next();
	
		}
		
		this.longestSnakeLength = Integer.valueOf(l);
		initialize();
		setTitle("Snake");
		
		setSize(850, 1000);
		setVisible(true);
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		addKeyListener(this);
		addMouseListener(this);
		
		this.headColor = 0;
		this.bodyColor = 1;
		this.fruitColor = 2;
		this.backgroundColor = 8;
		
		System.setProperty("apple.laf.useScreenMenuBar", "true");
		System.setProperty("com.apple.mrj.application.apple.menu.about.name", "Name");
		JMenuBar menuBar = new JMenuBar();
		
		//Creates options menu
		JMenu options = new JMenu("Options");
		menuBar.add(options);
		
		
		
		JMenu color = new JMenu("Color Scheme");
		
		JCheckBoxMenuItem classic = new JCheckBoxMenuItem("Classic");
		JCheckBoxMenuItem by = new JCheckBoxMenuItem("Blue and Yellow");
		JCheckBoxMenuItem ob = new JCheckBoxMenuItem("Orange and Blue");
		JCheckBoxMenuItem cloud = new JCheckBoxMenuItem("Cloud");
		
		color.add(classic);
		color.add(by);
		color.add(ob);
		color.add(cloud);
		
		
		classic.setSelected(true);
		
		 classic.addActionListener(new ActionListener() {
		        

				public void actionPerformed(ActionEvent actionEvent) {
					chooseColor("classic");
					classic.setSelected(true);
					by.setSelected(false);
					ob.setSelected(false);
					cloud.setSelected(false);
		        }
				
		    });
		 
		 by.addActionListener(new ActionListener() {
		        

				public void actionPerformed(ActionEvent actionEvent) {
					chooseColor("by");
					classic.setSelected(false);
					by.setSelected(true);
					ob.setSelected(false);
					cloud.setSelected(false);
		        }
				
		    });
		 
		 ob.addActionListener(new ActionListener() {
		        

				public void actionPerformed(ActionEvent actionEvent) {
					chooseColor("ob");
					classic.setSelected(false);
					by.setSelected(false);
					ob.setSelected(true);
					cloud.setSelected(false);
		        }
				
		    });
		 
		 cloud.addActionListener(new ActionListener() {
		        

				public void actionPerformed(ActionEvent actionEvent) {
					chooseColor("cloud");
					classic.setSelected(false);
					by.setSelected(false);
					ob.setSelected(false);
					cloud.setSelected(true);
		        }
				
		    });
		
		
		//Creates submenu speed within options menu
				JMenu speed = new JMenu("Speed");
				
		//Creates items fast, medium, slow within submenu
		JCheckBoxMenuItem fast = new JCheckBoxMenuItem("Fast");
		JCheckBoxMenuItem medium = new JCheckBoxMenuItem("Medium");
		JCheckBoxMenuItem slow = new JCheckBoxMenuItem("Slow");
		
		speed.add(fast);
		speed.add(medium);
		speed.add(slow);
		options.add(speed);
		options.add(color);
		
		setJMenuBar(menuBar);
		this.speed = 40;
		
		medium.setSelected(true);
		
		 fast.addActionListener(new ActionListener() {
		        

				public void actionPerformed(ActionEvent actionEvent) {
					chooseSpeed(20);
					fast.setSelected(true);
					medium.setSelected(false);
					slow.setSelected(false);
		        }
				
		    });
	
		 medium.addActionListener(new ActionListener() {
		     

				public void actionPerformed(ActionEvent actionEvent) {
					chooseSpeed(40);
					fast.setSelected(false);
					medium.setSelected(true);
					slow.setSelected(false);
		        }
		    });
		 
		 slow.addActionListener(new ActionListener() {
		   

				public void actionPerformed(ActionEvent actionEvent) {
					chooseSpeed(60);
					fast.setSelected(false);
					medium.setSelected(false);
					slow.setSelected(true);
		        }
		    });
	
		

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
	
	public void fileWriter() throws IOException {
		FileWriter fw = new FileWriter("High Score");
		
		fw.write("" + this.longestSnakeLength);
		
		fw.close();
	}
	
	public void updateAll(){
		udpateIsEast();
		updateIsNorth();
		updateIsSouth();
		updateIsWest();
		updateIsFacingEast();
		updateIsFacingNorth();
		updateIsFacingSouth();
		updateIsFacingWest();
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

		this.gameboard[30][30] = 1;
		this.gameboard[30][29] = 2;
		this.spaceHolder = 0;
		this.numberOfMoves = 0;
		this.gameIsStarted = false;
		
		this.isPlayAgainPresed = false;
		this.isPlayAgainClicked = false;
		this.isMainMenuPressed = false;
		this.isMainMenuClicked = false;
		this.isGameOver = false;
		this.canMove = true;
		
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
		int zeroCounter = 0;
		for(int r = 0; r < gameboard.length; r++) {
			for(int c = 0; c < gameboard[0].length; c++) {
				if(gameboard[r][c] < 1) {
					zeroCounter++;
				}
			}
		}

		if(zeroCounter == 0) {
			this.isComplete = true;
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
	public void run() {
		
		while(this.gameIsStarted == false) {
			repaint();
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		while(this.isComplete == false) {
			updateAll();
			while(canMove()) {
				moveSnake();
				updateAll();
				
				//choosePath is embedded in moveSnake
				

				if(this.numberOfFruit == 0 || (this.numberOfFruit <= 2 && this.numberOfMoves % 100 == 0)){
					createFruit();
				}
				
				
	
				//updates longestSnakeLength
				if(this.snakeLength > this.longestSnakeLength) {
					this.longestSnakeLength = this.snakeLength;
				}
	
				//Pause, then make next move
				try {
					Thread.sleep(this.speed);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				repaint();
			}
			
			try {
				fileWriter();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			if(this.isGameOver) {
				try {
					Thread.sleep(1);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if(this.isPlayAgainClicked) {
					initialize();
					repaint();
					run();
					
					
				}
			}
			
			if(this.isMainMenuClicked) {
				//go to main menu
			}
			
			this.isGameOver = true;

		} 
		
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


	/* * * * * * * * * * * * * * * * * * *
	 *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ *
	 *									 *
	 *			 	OTHER				 *
	 *									 *
	 *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ *
	 * * * * * * * * * * * * * * * * * * */


	public void paint(Graphics g) {
		int x = 50;
		int y = 50;
		
		Color color = new Color(45, 250, 30);
		
		for(int r = 0; r < this.gameboard.length; r++) {
			for(int c = 0; c < this.gameboard[0].length; c++) {
				if(this.gameboard[r][c] == 1) {
					
					//Choose head color
					if(this.headColor == 0) {
						g.setColor(Color.red);
					} else if(this.headColor == 3) {
						g.setColor(Color.BLUE);
					} else if(this.headColor == 4) {
						g.setColor(Color.ORANGE);
					} else if(this.headColor == 6) {
						g.setColor(new Color(255, 179, 209));
					}
					
					g.fillRect(x, y, 10, 10);
					g.setColor(Color.black);
					g.drawRect(x, y, 10, 10);
					x = x + 10;
				} else if (this.gameboard[r][c] == -1){
					
					//Chooses fruit color
					if(this.fruitColor == 2) {
						g.setColor(Color.yellow);
					} else if(this.fruitColor == 0) {
						g.setColor(Color.RED);
					}
					
					g.fillRect(x, y, 10, 10);
					g.setColor(Color.black);
					g.drawRect(x, y, 10, 10);
					x = x + 10;
				} else if(this.gameboard[r][c] > 0) {
					
					//Chooses body color
					if(this.bodyColor == 1) {
						g.setColor(color);
					} else if(this.bodyColor == 2) {
						g.setColor(Color.YELLOW);
					} else if(this.bodyColor == 3) {
						g.setColor(Color.BLUE);
					} else if(this.bodyColor == 7) {
						g.setColor(new Color(179, 209, 255));
					}
					
					g.fillRect(x, y, 10, 10);
					g.setColor(Color.black);
					g.drawRect(x, y, 10, 10);
					x = x + 10;
				} else {
					if(this.backgroundColor == 5) {
						g.setColor(Color.white);
					} else {
						g.setColor(Color.BLACK);
					}
			
					g.fillRect(x, y, 10, 10);
					if(this.backgroundColor == 5) {
						g.setColor(Color.WHITE);
					} else {
						g.setColor(Color.black);
					}
					g.drawRect(x, y, 10, 10);
					x = x + 10;
				}


			}
			x = 50;
			y = y + 10;
		}
		
		g.setColor(getBackground());
		g.fillRect(680, 250, 500, 100);
		g.setColor(Color.BLACK);
		Font font = new Font("Courier", Font.BOLD,20);
		g.setFont(font);
		if(this.snakeLength == 2) {
			g.drawString("Score: 0", 700, 300);
		} else {
			g.drawString("Score: " + (this.snakeLength - 2), 700, 300);
		}
		g.drawString("High score: " + (this.longestSnakeLength - 2), 670, 350);
		
		if(canMove() == false) {
			g.setColor(color);
			g.fillRect(150, 250, 400, 200);
			
			g.setColor(Color.YELLOW);
			g.fillRect(175, 375, 150, 50);
			g.fillRect(370, 375, 150, 50);
			
			g.setColor(Color.RED);
			g.setFont(new Font("Courier", Font.BOLD,40));
			g.drawString("GAME OVER", 240, 300);
			
			g.setFont(new Font("Courier", Font.BOLD,20));
			g.drawString("Play Again", 190, 407);
			g.drawString("Main Menu", 390, 407);
			
			if(this.isPlayAgainPresed) {
				
				g.setColor(Color.BLUE);
				g.fillRect(175, 375, 150, 50);
				g.fillRect(370, 375, 150, 50);
				
				g.setColor(Color.YELLOW);
				
				
				g.setFont(new Font("Courier", Font.BOLD,20));
				g.drawString("Play Again", 190, 407);
				
				
			}
		}
		

	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyPressed(KeyEvent e) {
		if(!this.isGameOver) {
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
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		Point point = e.getPoint();
		
		if(point.x > 175 && point.x < 325 && point.y > 375 && point.y < 425) {
			this.isPlayAgainClicked = true;
		
		}
		
		if(point.x > 370 && point.x < 520 && point.y > 375 && point.y < 425) {
			this.isMainMenuClicked = true;
		}
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		Point point = e.getPoint();
		
		if(point.x > 175 && point.x < 325 && point.y > 375 && point.y < 425) {
			this.isPlayAgainPresed = true;
			
		}
		
		if(point.x > 370 && point.x < 520 && point.y > 375 && point.y < 425) {
			this.isMainMenuPressed = true;
		}
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		
			this.isPlayAgainPresed = false;
	
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	public void chooseSpeed(int speed) {
		this.speed = speed;
	}
	
	//0 = red, 1 = green, 2 = yellow, 3 = blue, 4 = orange, 5 = white, 6 = pink, 7 = light blue, 8 = black
	public void chooseColor(String color) {
		if(color == "classic") {
			this.headColor = 0;
			this.bodyColor = 1;
			this.fruitColor = 2;
			this.backgroundColor = 8;
		} else if(color == "by") {
			this.headColor = 3;
			this.bodyColor = 2;
			this.fruitColor = 0;
			this.backgroundColor = 8;
		} else if(color == "ob") {
			this.headColor = 4;
			this.bodyColor = 3;
			this.fruitColor = 2;
			this.backgroundColor = 8;
		} else if(color == "cloud") {
			this.headColor = 6;
			this.bodyColor = 7;
			this.fruitColor = 2;
			this.backgroundColor = 5;
		}
		
	}
	
}







