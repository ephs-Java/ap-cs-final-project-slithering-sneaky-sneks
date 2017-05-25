package Stack;

import javax.swing.JFrame;

public class StackRunner {

	public static void main(String[] args) {
		
		StackGameboard n1 = new StackGameboard();
		JFrame f = new JFrame();
		f.add(n1);
		f.setVisible(true);
		f.setSize(600, 400);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}
}