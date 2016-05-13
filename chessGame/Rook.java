package chessGame;

public class Rook extends Piece {

	private int limit;

	public Rook(boolean t, String n, boolean m, int x, int y, boolean d, boolean e) {
		super(t, n, m, x, y, d, e);
		limit = 15;

	}

	// move
	public void moves(Board b) {
		int r = row;
		int c = column;
		String m;

		int i = 0;

		// RIght
		while (c < 7 && (b.getPiece(r, c + 1).getName() == null)) {
			c++;
			m = Integer.toString(r) + Integer.toString(c);// Move
			String by = Integer.toString(row) + Integer.toString(column);
			addToInterceptions(by, r, c, b);// interceptions
			moves[i] = m;
		}
		if (c < 7 && b.getPiece(r, c + 1).getName() != null) {
			c++;
			m = Integer.toString(r) + Integer.toString(c) + " This move kills the " + b.getPiece(r, c).getName()
					+ " of the opponent";
			String by = Integer.toString(row) + Integer.toString(column);
			addToInterceptions(by, r, c, b);// interceptions
			if (team != b.getPiece(r, c).getTeam()) {
				moves[i] = m;
				i++;
			}
		}

		r = row;
		c = column;
		// Left
		while (c > 0 && (b.getPiece(r, c - 1).getName() == null)) {
			c--;
			m = Integer.toString(r) + Integer.toString(c);// Move
			String by = Integer.toString(row) + Integer.toString(column);
			addToInterceptions(by, r, c, b);// interceptions
			moves[i] = m;
			i++;
		}
		if (c > 0 && b.getPiece(r, c - 1).getName() != null) {
			c--;
			m = Integer.toString(r) + Integer.toString(c) + " This move kills the " + b.getPiece(r, c).getName()
					+ " of the opponent";
			String by = Integer.toString(row) + Integer.toString(column);
			addToInterceptions(by, r, c, b);// interceptions
			if (team != b.getPiece(r, c).getTeam()) {
				moves[i] = m;
				i++;
			}
		}

		r = row;
		c = column;
		// Down
		while (r < 7 && b.getPiece(r + 1, c).getName() == null) {
			r++;
			m = Integer.toString(r) + Integer.toString(c);// Move
			String by = Integer.toString(row) + Integer.toString(column);
			addToInterceptions(by, r, c, b);// interceptions
			moves[i] = m;
			i++;
		}
		if (r < 7 && b.getPiece(r + 1, c).getName() != null) {
			r++;
			m = Integer.toString(r) + Integer.toString(c);// Move
			m = Integer.toString(r) + Integer.toString(c) + " This move kills the " + b.getPiece(r, c).getName()
					+ " of the opponent";
			String by = Integer.toString(row) + Integer.toString(column);
			addToInterceptions(by, r, c, b);// interceptions
			if (team != b.getPiece(r, c).getTeam()) {
				moves[i] = m;
				i++;
			}
		}

		r = row;
		c = column;
		// Up
		while (r > 0 && b.getPiece(r - 1, c).getName() == null) {
			r--;
			m = Integer.toString(r) + Integer.toString(c);// Move
			String by = Integer.toString(row) + Integer.toString(column);
			addToInterceptions(by, r, c, b);// interceptions
			moves[i] = m;
			i++;
		}
		if (r > 0 && b.getPiece(r - 1, c).getName() != null) {
			r--;
			m = Integer.toString(r) + Integer.toString(c) + " This move kills the " + b.getPiece(r, c).getName()
					+ " of the opponent";
			String by = Integer.toString(row) + Integer.toString(column);
			addToInterceptions(by, r, c, b);// interceptions
			if (team != b.getPiece(r, c).getTeam()) {
				moves[i] = m;
				i++;
			}
		}
	}
}
