package chessGame;

public class Piece {
	protected boolean team;
	protected String name;
	protected boolean moved;
	protected int row, column;
	protected boolean direction;
	protected boolean empty;
	protected String[] interceptions;
	protected String[] moves;

	public Piece(boolean t, String n, boolean no, int x, int y, boolean d, boolean e) {
		int total = 32;
		interceptions = new String[total];
		moves = new String[30];

		for (int i = 0; i < moves.length; i++) {
			moves[i] = null;
		}

		for (int i = 0; i < total; i++)// 18 because at any point in the game a
										// total of 18 piece from both sides can
		{ // be intercepting a spot or a piece on the board
			interceptions[i] = null;
		}
		team = t;
		name = n;
		moved = no;
		row = x;
		column = y;
		direction = d;
		empty = e;
	}

	// Another constructor for the blank
	public Piece() {

	}

	// Get interceptions
	public String[] getInterceptions() {
		return interceptions;
	}

	// Setters
	// Setting the move count
	public void setMoveCount(int m) {
		moves = new String[m];
	}

	public void setOneMove(int i, String e) {
		moves[i] = e;
	}

	public void setIfEmpty(boolean e) {
		empty = e;
	}

	public void setRowColumn(int r, int c) {
		row = r;
		column = c;
	}

	public void setDirection(boolean d) {
		direction = d;
	}

	public void setName(String n) {
		name = n;
	}

	public void setTeam(boolean t) {
		team = t;
	}

	public void hasMoved() {
		// Set weather the has been moved already
		moved = false;
	}

	// Getters
	public boolean getTeam() {
		return team;
	}

	public int getRow() {
		return row;
	}

	public int getColumn() {
		return column;
	}

	public String getName() {
		return name;
	}

	public boolean directionUp() {
		return direction;
	}

	public boolean getIfMoved() {
		return moved;
	}

	public boolean getIfEmpty() {
		return empty;
	}

	// Movement
	public void moves(Board b) {
		// Fill each class's own move filling function
	}

	// Get the moves
	public String[] getMove() {
		return moves;
	}

	// Print the move list
	public void printMoves() {
		int i = 0;
		while (moves[i] != null) {
			System.out.println(moves[i]);
			i++;
		}
	}

	// Add to interceptions
	// This checks an empty spot to put in the 'being intercepted "by"'
	// string in the interceptions array
	protected void addToInterceptions(String by, int ro, int col, Board b) {
		for (int i = 0; i < b.getPiece(ro, col).interceptions.length; i++) {
			if (b.getPiece(ro, col).interceptions[i] == null) {
				// System.out.println("Adding to the interception of "+ ro + ""
				// + col);
				b.getPiece(ro, col).interceptions[i] = by;
				break;
			}
		}
	}

	// Of this piece, check all the interceptions
	// And return the ones that are opposite to the boolean t value
	public String[] checkIntercept(Board b, boolean t) {
		int otherTeam = 9;
		String[] catchP = new String[otherTeam];// Because only 9 pieces from
												// each side can attack a piece
		int m = 0;
		// intialize the catch array
		for (int i = 0; i < 9; i++)
			catchP[i] = null;

		for (int i = 0; i < interceptions.length; i++) {
			if (interceptions[i] != null) {
				int r = Character.getNumericValue(interceptions[i].charAt(0));
				int c = Character.getNumericValue(interceptions[i].charAt(1));

				if (b.getPiece(r, c).getTeam() != t && m < otherTeam) {
					// System.out.println(row + "," + column + " is being
					// attacked by the piece at" + r + "," + c);
					catchP[m] = interceptions[i];
					m++;
				}
			}
		}
		return catchP;
	}

	// Clear an interception's array for a piece
	public void clearInter() {
		for (int i = 0; i < interceptions.length; i++) {
			interceptions[i] = null;
		}
	}

	// Clear an moves' array for a piece
	public void clearMoves() {
		for (int i = 0; i < moves.length; i++) {
			moves[i] = null;
		}
	}
	
	//public the interceptions of a specific piece
	
}
