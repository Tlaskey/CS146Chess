package chessGame;

import java.util.Scanner;

public class Chess {
	public static void main(String []args)
	{
		//You are working with make a move method in piece
		
		boolean color = false;
		Scanner i = new Scanner(System.in);
		
		System.out.println("What color do you wanna be, press w for white and b for black?");
		String col = i.nextLine();
		
		if(col == "w")
			color = true;
		else if(col == "b")
			color = false;
		
		Game g = new Game(color);
		
		g.runTheGame();
		i.close();
	}	
}

