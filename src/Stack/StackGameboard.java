package Stack;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

import javax.swing.JPanel;
import javax.swing.Timer;

public class StackGameboard extends JPanel implements ActionListener, KeyListener {
	
	Timer t = new Timer(5, this);
	double x = 0, y = 0, velX = 2, velY = 2;
	
	public StackGameboard(){
		t.start();
		addKeyListener(this);
		setFocusable(true);
		setFocusTraversalKeysEnabled(true);
		
		
	}
	
	public void paintComponent(Graphics g){
		
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		Rectangle2D circle = new Rectangle2D.Double(x, y, 40, 40);
		g2d.fill(circle);
		t.start();
		
	}
	
	public void actionPerformed(ActionEvent e){
		if (x < 0 || x > 560){
			velX = -velX;
		}
		
		x += velX;
		repaint();
	}
	
	public void space(){
		velX = 0;
	}
	
	public void keyPressed(KeyEvent e){
		
		int user = e.getKeyCode();
		if (user == KeyEvent.VK_SPACE){
			space();
		}
		
	}
	
	public void keyTyped(KeyEvent e){}
	public void keyReleased(KeyEvent e){}

}
