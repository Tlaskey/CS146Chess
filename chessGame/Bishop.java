package chessGame;

public class Bishop extends Piece {

	private int limit;

	public Bishop(boolean t, String n, boolean m, int x, int y, boolean d, boolean e) {
		super(t, n, m, x, y, d, e);
		limit = 13;
	}
	public Bishop(Piece p, Board b){
		super(p, b);
		limit = 13;
	}

	// Pawn's move
	public void moves(Board b) {
		int r = row;
		int c = column;
		String m;
		for (int i = 0; i < limit; i++) {
			moves[i] = null;
		}

		// Now loop to get all the possible moves
		int i = 0;
		// Another while loop, up right diagonal
		while (r > 0 && c < 7 && (b.getPiece(r-1, c + 1).getName() == null)) {
			c++;
			r--;
			m = Integer.toString(r) + Integer.toString(c);// Move
			String by = Integer.toString(row) + Integer.toString(column);
			addToInterceptions(by, r, c, b);// interceptions
			moves[i] = m;
			i++;
		}
		if (r > 0 && c < 7 && b.getPiece(r-1, c + 1).getName() != null && team != b.getPiece(r-1, c + 1).getTeam()) {
			c++;
			r--;
			m = Integer.toString(r) + Integer.toString(c) + " This move kills the " + b.getPiece(r, c).getName()
					+ " of the opponent";
			String by = Integer.toString(row) + Integer.toString(column);
			addToInterceptions(by, r, c, b);// interceptions
			moves[i] = m;
			i++;
		}

		r = row;
		c = column;
		//up, Left
		while (r>0 && c > 0 && (b.getPiece(r-1, c - 1).getName() == null)) {
			c--;
			r--;
			m = Integer.toString(r) + Integer.toString(c);// Move
			String by = Integer.toString(row) + Integer.toString(column);
			addToInterceptions(by, r, c, b);// interceptions
			moves[i] = m;
			i++;
		}
		if (r>0 && c > 0 && b.getPiece(r-1, c - 1).getName() != null && team != b.getPiece(r-1, c - 1).getTeam()) {
			c--;
			r--;
			m = Integer.toString(r) + Integer.toString(c) + " This move kills the " + b.getPiece(r, c).getName()
					+ " of the opponent";
			String by = Integer.toString(row) + Integer.toString(column);
			addToInterceptions(by, r, c, b);// interceptions
			moves[i] = m;
			i++;
		}

		r = row;
		c = column;
		// Down, left
		while (r < 7 && c>0 && b.getPiece(r + 1, c-1).getName() == null) {
			r++;
			c--;
			m = Integer.toString(r) + Integer.toString(c);// Move
			String by = Integer.toString(row) + Integer.toString(column);
			addToInterceptions(by, r, c, b);// interceptions
			moves[i] = m;
			i++;
		}
		if (r < 7 && c > 0 && b.getPiece(r + 1, c-1).getName() != null && team != b.getPiece(r + 1, c-1).getTeam()) {
			r++;
			c--;
			m = Integer.toString(r) + Integer.toString(c);// Move
			m = Integer.toString(r) + Integer.toString(c) + " This move kills the " + b.getPiece(r, c).getName()
					+ " of the opponent";
			String by = Integer.toString(row) + Integer.toString(column);
			addToInterceptions(by, r, c, b);// interceptions
			moves[i] = m;
			i++;
		}

		r = row;
		c = column;
		// down right;
		while (r < 7 && c<7 && (b.getPiece(r + 1, c+1).getName() == null
				|| (b.getPiece(r + 1, c+1).getName() != null && team != b.getPiece(r + 1, c+1).getTeam()))) {
			r++;
			c++;
			m = Integer.toString(r) + Integer.toString(c);// Move
			String by = Integer.toString(row) + Integer.toString(column);
			addToInterceptions(by, r, c, b);// interceptions
			moves[i] = m;
			i++;
		}
		if (r < 7 && c < 7 && b.getPiece(r + 1, c+1).getName() != null && team != b.getPiece(r + 1, c+1).getTeam()) {
			r++;
			c++;
			m = Integer.toString(r) + Integer.toString(c);// Move
			m = Integer.toString(r) + Integer.toString(c) + " This move kills the " + b.getPiece(r, c).getName()
					+ " of the opponent";
			String by = Integer.toString(row) + Integer.toString(column);
			addToInterceptions(by, r, c, b);// interceptions
			moves[i] = m;
			i++;
		}
	}
}
