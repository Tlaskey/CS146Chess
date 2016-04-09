
public class GameHandler {
	public static Board b;
	public static void main(String[] args)
	{		
		b = new Board();
		b.genBoard();
		b.print();
		boolean gameIsOver = false;
		while(!gameIsOver)
		{
			gameIsOver = b.turn();
			b.print();
		}
	}		
}