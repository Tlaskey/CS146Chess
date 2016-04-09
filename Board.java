import java.util.ArrayList;
import java.util.Scanner;

public class Board {
	private Piece[][] board;
	private Scanner sc;
	private boolean turn;
	private King whiteKing;
	private King blackKing;

	public Board() {
		board = new Piece[8][8];
		sc = new Scanner(System.in);
		turn = true;
		whiteKing = null;
		blackKing = null;
	}

	/**
	 * A method used for handling common-turn actions, primarily
	 * movement/capturing. Also currently handles console-level input.
	 * 
	 * @return whether or not the game has been lost on this turn
	 */
	public boolean turn() {

		boolean validTurn = true;
		// This loop is for making sure SOMETHING happens before the player's
		// turn ends (the player must make a legal move)
		while (validTurn) {
			System.out.println("Please choose a piece");
			String comm = sc.nextLine();
			if (comm.equalsIgnoreCase("quit"))
				return true;
			// This if statement guards against empty string input, which would
			// otherwise throw a string index OoB in the first try/catch
			if (comm.equals(""))
				comm = " ";
			// The initial state of these values forces a later exception catch
			// if the user doesn't provide valid input
			Integer x = -1;
			Integer y = -1;
			try {
				x = Integer.parseInt(comm.substring(0, 1));
				y = Integer.parseInt(comm.substring(2));
			} catch (NumberFormatException ex) {
				// Nothing needs to be done here, it'll be handled later
			}
			Piece p = null;
			try {
				p = board[x][y];
			} catch (ArrayIndexOutOfBoundsException ex) {
				System.out.println("That is not valid input.");
			}
			if (p == null)
				System.out.println("Please try again."); // either there was a
															// OoB exception or
															// a number format
															// exception.
			else if (p.isBlank()) {
				System.out.println("There is no piece there."); // space
																// selected has
																// no piece
			} else if (p.getTeam() != turn) {
				System.out.println("That piece is not yours."); // trying to
																// choose a
																// piece you
																// don't control
			} else {
				boolean validMoved = false; // at this point, the chosen piece
											// is assumed to be a valid piece in
											// the board
				ArrayList<String> moves = p.moves();
				if (moves.isEmpty())
					System.out.println("This piece cannot move."); // this piece
																	// cannot
																	// move, and
																	// therefore
																	// a new
																	// piece
																	// must be
																	// selected
				while (!validMoved && !moves.isEmpty()) {
					System.out.println("Where would you like to move this piece to?");
					String comm2 = sc.nextLine();
					Integer x2 = 0;
					Integer y2 = 0;
					if (comm2.equalsIgnoreCase("check"))
						System.out.println(moves);
					else {
						try {
							x2 = Integer.parseInt(comm2.substring(0, 1));
							y2 = Integer.parseInt(comm2.substring(2));
						} catch (NumberFormatException ex) {
							System.out.println("That was not valid input");
						}
					}
					if (moves.contains(comm2)) {
						if (board[x2][y2] instanceof King)
							return true;
						board[x2][y2] = p; // this code block includes
											// "capturing", which is effectively
											// just removes the reference to the
											// "captured" piece
						p.setLoc(y2, x2);
						board[x][y] = new BlankSpace();
						validTurn = false;
						validMoved = true;
					}

					else {
						System.out.println("That is not a valid move.");
					}
				}
			}
		}
		updateBoardStatus();
		turn = !turn;
		return false;
	}

	/**
	 * Prints a representation of the board.
	 */
	public void print() {
		for (Piece[] parr : board) {
			for (Piece p : parr) {
				System.out.print(p.toString() + " ");
			}
			System.out.println();
		}
	}

	/**
	 * Places pieces in the current board for the initial game state.
	 */
	public void genBoard() {
		for (int i = 1; i < 7; i += 5) {
			for (int j = 0; j < board[i].length; j++) {
				board[i][j] = new Pawn(i == 6, i, j);
			}
		}
		for (int i = 2; i <= 5; i++) {
			for (int j = 0; j < board[i].length; j++) {
				board[i][j] = new BlankSpace();
			}
		}
		board[0][0] = new Rook(false, 0, 0);
		board[0][1] = new Knight(false, 0, 1);
		board[0][2] = new Bishop(false, 0, 2);
		board[0][3] = new Queen(false, 0, 3);
		board[0][4] = new King(false, 0, 4);
		board[0][5] = new Bishop(false, 0, 5);
		board[0][6] = new Knight(false, 0, 6);
		board[0][7] = new Rook(false, 0, 7);

		board[7][0] = new Rook(true, 7, 0);
		board[7][1] = new Knight(true, 7, 1);
		board[7][2] = new Bishop(true, 7, 2);
		board[7][4] = new Queen(true, 7, 4);
		board[7][3] = new King(true, 7, 3);
		board[7][5] = new Bishop(true, 7, 5);
		board[7][6] = new Knight(true, 7, 6);
		board[7][7] = new Rook(true, 7, 7);

		whiteKing = ((King) board[7][3]);
		blackKing = ((King) board[0][4]);

		updateBoardStatus();
	}

	/**
	 * Returns a piece within this board at the given location as specified as
	 * two integers.
	 * 
	 * @param x
	 *            the column to retrieve the piece from.
	 * @param y
	 *            the row to retrieve the piece from.
	 * @return The piece at x,y, or null if the given coordinate is out of
	 *         bounds or the board is null.
	 */
	public Piece getPiece(int x, int y) {
		if (board == null)
			return null;
		try {
			return board[x][y];
		} catch (ArrayIndexOutOfBoundsException ex) {
			return null;
		}
	}
	public void genBlankBoard()
	{
		for(int i = 0; i < board.length; i++){
			for(int j = 0; j < board[i].length; j++){
				board[i][j] = new BlankSpace(j, i);
			}
		}
	}

	/**
	 * Overloaded method allowing one to get a Piece using a string.
	 * 
	 * @param loc
	 *            a string representation of a coordinate in the format y,x
	 * @return the piece contained at x,y, or null if the given coordinate is
	 *         out of bounds or the board is null.
	 */
	public Piece getPiece(String loc) {
		int x = Integer.parseInt(loc.substring(0, 1));
		int y = Integer.parseInt(loc.substring(2));
		return getPiece(y, x);
	}
	private String intToLoc(int y, int x)
	{
		return y + "," + x;
	}
	public boolean obscured(String pieceLoc, String checkLoc)
	{
		Piece p = getPiece(pieceLoc);
		int pX = p.getX();
		int pY = p.getY();
		Piece q = getPiece(checkLoc);
		int qX = q.getX();
		int qY = q.getY();
		if(pX == qX){
			int startY = Math.min(pY, qY);
			for(int i = startY + 1; startY < 8; startY++){
				Piece temp = getPiece(pX , i);
				if(temp == q) return false;
				if(!temp.isBlank()) return true;
			}
		}else if(pY == qY){
			int startX = Math.min(qX, pX);
			for(int i = startX; startX < 8; startX++){
				Piece temp = getPiece(i , pY);
				if(temp == q) return false;
				if(!temp.isBlank()) return true;
			}
		}
		else if(Math.abs(pY - qY) == pX){
			int modifier = 1;
			if(pX > qX) modifier = -1;
			int startX = pX;
			int startY = pY;
			while(startX >= 0 && startX < 8 && startY < 8){
				Piece r = getPiece(startX, startY);
				if(r == q) return false;
				if(!r.isBlank()) return true;
				startX += modifier;
				startY++;
			}
		}
		else if(Math.abs(pY - qY) == qX){
			int modifier = 1;
			if(pX > qX) modifier = -1;
			int startX = pX;
			int startY = pY;
			while(startX >= 0 && startX < 8 && startY < 8){
				Piece r = getPiece(startX, startY);
				if(r == q) return false;
				if(!r.isBlank()) return true;
				startX += modifier;
				startY--;
			}
		}
		return false;
	}
	public void addPiece(int x, int y, Piece p){
		board[x][y] = p;
	}
	public void removePiece(int x, int y)
	
	{
		board[x][y] = new BlankSpace();
	}

	private void updateBoardStatus() {

	}
}