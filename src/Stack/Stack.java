package Stack;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

public class Stack extends JFrame {
	private int xCord1;
	private int yCord1;
	private int width1;
	private int xVel;
	
	public Stack() {
		this.xCord1 = 100;
		this.yCord1 = 200;
		this.width1 = 10;
		this.xVel = 10;
		JFrame jf = new JFrame("Stack");
		setSize(1200, 500);
		setResizable(true);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
	}
	
	public void paint(Graphics g) {
		
		g.setColor(Color.black);
		g.fillRect(xCord1 + xVel, yCord1, width1, 50);
		
	}
	
	public void move() {
		this.xCord1 = this.xCord1 + this.xVel;
		repaint();
	}
	
	public int getXCord() {
		return this.xCord1;
	}

	
	
}
