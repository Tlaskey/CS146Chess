import java.util.Scanner;

public class game {

	private Board b;
	private Scanner in;

	// Constructor
	public game(boolean col) {
		b = new Board(col);
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
		Piece p = null;
		int row = 0, col = 0;
		String m = null;
		int run = 0;
		
		while (run < 10) {
			// Select a piece
			p = selectPiece(b);

			p.printMoves();
			// Call the move to function
			String place = moveTo();
			// Get the row and the column from the returned variable "place"
			row = Character.getNumericValue(place.charAt(0));
			col = Character.getNumericValue(place.charAt(1));
			// Make the move
			b.makeMove(row, col, b, p);
			// Show the board
			b.showBoard();
			run++;
		}

		in.close();
	}

	// Get in put
	public String moveTo() {
		String m = null;
		String r = null, c = null;
		int r1 = 0, c1 = 0;

		do {
			System.out.println("Input row and column you want to move the current piece too.");
			System.out.print("Row: ");
			r1 = in.nextInt();
			System.out.print("Column: ");
			c1 = in.nextInt();
		} while (!(r1 >= 0) || !(r1 <= 7) || !(c1 >= 0) || !(c1 <= 7));

		m = Integer.toString(r1) + Integer.toString(c1);

		return m;
	}

	// Select a piece
	public Piece selectPiece(Board b) {
		b.showBoard();

		Piece p = null;
		int row = 0;
		int col = 0;

		do {
			System.out.println("Rows and columns are number from 0 to 7, both.");
			System.out.print("Row: ");
			row = in.nextInt();
			System.out.print("Column: ");
			col = in.nextInt();
		} while (!(row >= 0) || !(row <= 7) || !(col >= 0) || !(col <= 7));

		p = b.getPiece(row, col);

		return p;
	}
}
