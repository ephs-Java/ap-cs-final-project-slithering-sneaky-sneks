
public class Runner {
	
	Snake game = new Snake();

	public static void main(String[] args) throws InterruptedException {
		Runner runner = new Runner();
		runner.continueOrGameOver();
		
		 
	}
	
	
	
	public Runner() {
		
	}
	
	/* 
	 * Returns true if the snake is not stuck and can continue to move
	 * Returns true if the snake is complete
	 * Returns false otherwise
	 */
	public void continueOrGameOver() throws InterruptedException {
		
		while(!(game.getIsComplete() && game.getIsStuck())) {
			game.updateAll();
			
			game.moveSnake(game.choosePath());
			System.out.println(game.choosePath());
			
			//Pause for one second, then make next move
			Thread.sleep(1000);
			
		} 
		
		//game over message
		
	}
	

}
