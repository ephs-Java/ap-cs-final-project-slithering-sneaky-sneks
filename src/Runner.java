import java.awt.Graphics;
import java.io.FileNotFoundException;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class Runner {
	
	public static void main(String[] args) throws InterruptedException, FileNotFoundException, ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException {
		
		Snake snakeGame = new Snake();
		
		snakeGame.run();
		
	}
	
	

}
