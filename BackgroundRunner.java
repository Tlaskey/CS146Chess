
public class BackgroundRunner {
	private boolean checkMate;
	public static void main(String[] args){
		BackgroundRunner runner = new BackgroundRunner();
		runner.run();
	}
	public BackgroundRunner(){
		setCheckMate(false);
	}
	
	public void run(){
		Board board = new Board();
		board.generateBoard();
		board.printBoard();
	}
	public boolean getCheckMate() {
		return checkMate;
	}
	public void setCheckMate(boolean checkMate) {
		this.checkMate = checkMate;
	}
}
