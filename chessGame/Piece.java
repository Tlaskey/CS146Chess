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
		int total = 18;
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
	public Piece(Piece p, Board b){
		this.team = p.team;
		this.name = p.name;
		this.moved = p.moved;
		this.row = p.row;
		this.column= p.column;
		this.direction = p.direction;
		this.empty = p.empty;
		this.interceptions = p.interceptions;
		this.moves = new String[30];
		this.moves(b);	
		
	}

	// Another constructor for the blank
	public Piece() {

	}

	// Setters
	// Setting the move count
	public void setMoveCount(int m) {
		moves = new String[m];
	}
	public Piece setName(String str){
		this.name = str;
		return this;
	}

	public void setOneMove(int i, String e) {
		moves[i] = e;
	}

	public void setRowColumn(int r, int c) {
		row = r;
		column = c;
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

	// Delete previous intersections
	public void toDeleteIntersect() {
		// Since the place of the piece is updated
		// therefore the moveset has to be updated as well, and
		// thus also the intersections' array
		for (int i = 0; i < moves.length; i++) {
			// First Delete the array
			String m = moves[i];
			deleteFromIntersect(row, column, m);
		}

	}

	// Update the interception after making the move
	public void deleteFromIntersect(int row, int col, String del) {
		int m = 0;
		while (m < interceptions.length) {
			if (interceptions[m] == del)
				interceptions[m] = null;
			m++;
		}

	}

	// Add to interceptions
	// This checks an empty spot to put in the 'being intercepted "by"'
	// string in the interceptions array
	protected void addToInterceptions(String by, int r, int c, Board b) {
		for (int i = 0; i < b.getPiece(r, c).interceptions.length; i++) {
			if (b.getPiece(r, c).interceptions[i] == null) {
				b.getPiece(r, c).interceptions[i] = by;
				break;
			}
		}
	}

	// Check if the interceptions have an interception
	// of the opposing team
	public String[] checkIntercept(Board b, boolean t) {
		// Catch all the opposting team intercept in an array
		String[] catchP = new String[9];
		int m = 0;
		// intialize the catch array
		for (int i = 0; i < 9; i++)
			catchP[i] = null;

		for (int i = 0; i < interceptions.length; i++) {
			if (interceptions[i] != null) {
				int r = Character.getNumericValue(interceptions[i].charAt(0));
				int c = Character.getNumericValue(interceptions[i].charAt(1));

				if (b.getPiece(r, c).getTeam() != t) {
					System.out.println("The piece at " + b.getPiece(r, c).getName());
					catchP[m] = interceptions[i];
					m++;
				}
			}
		}

		return catchP;
	}

	// Clear all the old moves
	public void clearMoves() {
		for (int i = 0; i < moves.length; i++)
			moves[i] = null;
	}
}
