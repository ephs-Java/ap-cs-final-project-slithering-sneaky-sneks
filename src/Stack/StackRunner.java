package Stack;

public class StackRunner {

	public static void main(String[] args) {
		Stack stack = new Stack();
		while(stack.getXCord() < 800) {
			stack.move();
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println(stack.getXCord());
		}
	}

}
