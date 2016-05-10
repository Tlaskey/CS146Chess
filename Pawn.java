public class Pawn extends Piece {
	private int limit;

	public Pawn(boolean t, String n, boolean m, int x, int y, boolean d, boolean e) {
		super(t, n, m, x, y, d, e);
		limit = 4;
	}

	// move
	public void moves(Board b) {
		int r = row;
		int c = column;

		String m;
		int up = 1;
		int up2 = 2;
		int down = -1;
		int down2 = -2;
		int c1 = 0;
		int c2 = 0;
		if (direction) {
			c1 = up;
			c2 = up2;
		} else {
			c1 = down;
			c2 = down2;
		}

		int i = 0;
		// Two different options for the pawn
		if (row == 0 || row == 7) {
			// call the promotion function
		} else {
			// Right, diagonal
			if (r > 0 && r < 7 && c < 7 && team != b.getPiece(r - c1, c + 1).getTeam()
					&& b.getPiece(r - c1, c + 1).getName() != null) {
				r = r - c1;
				c = c + 1;
				m = Integer.toString(r) + Integer.toString(c);// Move
				if (team != b.getPiece(r, c).getTeam() && b.getPiece(r, c).getName() != null)
					m = m + " This move kills the " + b.getPiece(r, c).getName() + " of the opponent";
				String by = Integer.toString(row) + Integer.toString(column);
				addToInterceptions(by, r, c, b);// interceptions
				b.getPiece(row, column).moves[i] = m;
				i++;

			}

			r = row;
			c = column;
			// left, diagonal
			if (r > 0 && r < 7 && c > 0 && team != b.getPiece(r - c1, c - 1).getTeam()
					&& b.getPiece(r - c1, c - 1).getName() != null) {
				r = r - c1;
				c--;
				m = Integer.toString(r) + Integer.toString(c);// Move
				if (team != b.getPiece(r, c).getTeam() && b.getPiece(r, c).getName() != null)
					m = m + " This move kills the " + b.getPiece(r, c).getName() + " of the opponent";
				String by = Integer.toString(row) + Integer.toString(column);
				addToInterceptions(by, r, c, b);// interceptions
				b.getPiece(row, column).moves[i] = m;
				i++;

			}

			r = row;
			c = column;
			// up
			if (r > 0 && r < 7 && b.getPiece(r - c1, c).getName() == null) {
				r = r - c1;
				m = Integer.toString(r) + Integer.toString(c);// Move
				String by = Integer.toString(row) + Integer.toString(column);
				addToInterceptions(by, r, c, b);// interceptions
				b.getPiece(row, column).moves[i] = m;
				i++;
			}

			r = row;
			c = column;
			// Up two
			if (moved) {
				if (r > 0 && b.getPiece(r - c2, c).getName() == null) {
					r = r - c2;
					m = Integer.toString(r) + Integer.toString(c);// Move
					String by = Integer.toString(row) + Integer.toString(column);
					addToInterceptions(by, r, c, b);// interceptions
					b.getPiece(row, column).moves[i] = m;
					i++;
				}
			}
		}
	}

	// THis function promotes the pawn
	public void promotion() {

	}
}
