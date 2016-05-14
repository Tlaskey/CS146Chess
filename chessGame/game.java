package chessGame;

import java.util.Scanner;
import java.util.Random;

public class Game {

	private Board b;
	private Scanner in;
	private boolean comp;
	private boolean pl;

	// Constructor
	public Game(boolean player) {
		comp = !player;
		pl = player;
		b = new Board(pl);
		in = new Scanner(System.in);
		// set each piece's moves
		for (int r = 0; r < 8; r++) {
			for (int c = 0; c < 8; c++) {
				if (b.getPiece(r, c).getName() != null)
					b.getPiece(r, c).moves(b);
			}
		}
	}

	// public run the game
	public void runTheGame() {
		b.showBoard();
//		ChessGUI c = new ChessGUI(b);
		b.showBoardInter();
		Piece p = null;
		int row = 0, col = 0;
		String m = null;
		int run = 0;
		String place = null;

		while (run < 10) {

			// Select a piece
			do{
				System.out.println("Please pick a piece from your team.");
				p = selectPiece(b);
			}while(p.getTeam() != pl);
			
			String[] moves = p.getMove();
			p.printMoves();

			if(p.direction)
			{
				System.out.println("This " + p.getName() + " direction is up.");
			}
			if (moves[0] == null)
				System.out.println("This " + p.getName() + " has no moves currently.");
			else {
				// Call the move to function
				place = moveTo(moves);
				// Get the row and the column from the returned variable "place"
				row = Character.getNumericValue(place.charAt(0));
				col = Character.getNumericValue(place.charAt(1));
				// Make the move
				b.makeMove(row, col, p);
				
				//Now the computer's turn
				AI(b);
				// Show the board
				b.showBoard();
				b.showBoardInter();
			}
			run++;
		}

		in.close();
	}

	// Select a piece
	public Piece selectPiece(Board b) {

		Piece p = null;
		int row = 0;
		int col = 0;

		do {
			System.out.println("Pick a piece rows and columns are number from 0 to 7, both.");
			System.out.print("Row: ");
			row = in.nextInt();
			System.out.print("Column: ");
			col = in.nextInt();
		} while (!(row >= 0) || !(row <= 7) || !(col >= 0) || !(col <= 7));

		p = b.getPiece(row, col);

		return p;
	}

	// Get input on where to move the piece
	public String moveTo(String[] moves) {
		String m = null;
		String r = null, c = null;
		int r1 = 0, c1 = 0;

		if (moves[0] != null)
			do {
				System.out.println("Choose a move from the moves listed above.");
				System.out.print("Row: ");
				r1 = in.nextInt();
				System.out.print("Column: ");
				c1 = in.nextInt();
			} while (!(r1 >= 0) || !(r1 <= 7) || !(c1 >= 0) || !(c1 <= 7) || !isAMove(r1, c1, moves));

		// Call the interceptions method first
		m = Integer.toString(r1) + Integer.toString(c1);

		return m;
	}

	// Check if the player is choosing a move from the list
	public boolean isAMove(int r1, int c1, String[] moves) {
		String m;
		String temp = null;
		m = Integer.toString(r1) + Integer.toString(c1);

		for (int i = 0; i < moves.length; i++) {
			if (moves[i] != null)
				temp = Character.toString(moves[i].charAt(0)) + Character.toString(moves[i].charAt(1));
			if (temp != null && m.equals(temp))
				return true;
		}

		System.out.println("Please select a move from the gives set of moves.");
		return false;
	}
	//Checks if this move will capture an enemy piece.
	public boolean isAMoveCaptures(int r1, int c1){
		Piece p = b.getPiece(r1, c1);
		if(p.getName() != null){
			return true;
		}
		return false;
	}
	public void checkInterceptions() {
		int m = 0;
		while (m < 10) {
			System.out.println("Input the row and column on the spot you want to" 
					+ " check the interceptions for.");
			System.out.print("Row: ");
			int r = in.nextInt();
			System.out.print("Column: ");
			int c = in.nextInt();
			String[] inter = b.getPiece(r, c).getInterceptions();

			for (int i = 0; i < inter.length; i++) {
				if (inter[i] != null)
					System.out.println("For the piece at " + r + c + ", " + inter[i]);
			}
			m++;
		}

	}
	
	//AI
	public void AI(Board b){
		Piece p = null;
		//Lets start with random difficulty
		//this way comp just pick pieces randomly
		p = pickRandomPiece(b);
	}
	
	//random picking
	public Piece pickRandomPiece(Board b){
		Piece p = null;
		String[] moves;
		String[] movablePieces = new String[16];
		int ind = 0;
		for(int i = 0; i < 8; i++)
		{
			for(int m = 0; m < 8; m++)
			{ 
				moves = b.getPiece(i, m).moves;
				if(moves[0] != null && b.getPiece(i, m).getTeam() == comp)//if the piece is movable
				{
					movablePieces[ind] = Integer.toString(b.getPiece(i, m).getRow()) 
							+ Integer.toString(b.getPiece(i, m).getColumn());
					ind++;
				}
			}
		}
		//Select a random array
		Random rand = new Random();
		
		int random = 0;
		int minimum = 0;
		int maximum = 15;
		do{
			random = rand.nextInt(maximum - minimum + 1) + minimum;
		}while(movablePieces[random] == null);
		
		System.out.println("The computer is moving the piece at " + movablePieces[random]);
		
		//Select a move from this piece's moves
		int r = Character.getNumericValue(movablePieces[random].charAt(0));
		int c = Character.getNumericValue(movablePieces[random].charAt(1));
		
		p = b.getPiece(r, c);
		moves = b.getPiece(r, c).getMove();
		minimum = 0;
		maximum = 29;
		
		for(String move: moves){
			if(move != null){
				r = Character.getNumericValue(move.charAt(0)); 
				c = Character.getNumericValue(move.charAt(1)); 
				if(isAMoveCaptures(r, c)){
					b.makeMove(r, c, p);
					return p;
				}
			}
		}
		
		do{
			random = rand.nextInt(maximum - minimum + 1) + minimum;
		}while(moves[random] == null);
		
		r = Character.getNumericValue(moves[random].charAt(0)); 
		c = Character.getNumericValue(moves[random].charAt(1)); 
		
		b.makeMove(r, c, p);
		return p;
	}
}
