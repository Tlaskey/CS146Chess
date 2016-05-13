package chessGame;

public class King extends Piece {

	private int limit;

	public King(boolean t, String n, boolean m, int x, int y, boolean d, boolean e) {
		super(t, n, m, x, y, d, e);
		limit = 8;
	}

	// move
	public void moves(Board b) {
		int r = row;
		int c = column;
		String m = null;
		int i = 0;

		// Up 1, left 1
		if (r - 1 >= 0 && c - 1 >= 0 && b.getPiece(r - 1, c - 1).getName() == null) {
			String[] catchP = b.getPiece(r - 1, c - 1).checkIntercept(b, team);

			if (catchP[0] == null) {
				m = Integer.toString(r - 1) + Integer.toString(c - 1);
				moves[i] = m;
				i++;
				String by = Integer.toString(row) + Integer.toString(column);
				addToInterceptions(by, r - 1, c - 1, b);// interceptions
			}
		}
		if (r - 1 >= 0 && c - 1 >= 0 && b.getPiece(r - 1, c - 1).getName() != null) {
			String[] catchP = b.getPiece(r - 1, c - 1).checkIntercept(b, team);

			if (catchP[0] == null) {
				m = Integer.toString(r - 1) + Integer.toString(c - 1) + " This move kills the "
						+ b.getPiece(r - 1, c - 1).getName() + " of the opponent";
				if (team != b.getPiece(r-1, c-1).getTeam()) {
					moves[i] = m;
					i++;
				}
				String by = Integer.toString(row) + Integer.toString(column);
				addToInterceptions(by, r - 1, c - 1, b);// interceptions
			}
		}

		// Up 1, right 1
		if (r - 1 >= 0 && c + 1 <= 7 && b.getPiece(r - 1, c + 1).getName() == null) {
			String[] catchP = b.getPiece(r - 1, c + 1).checkIntercept(b, team);

			if (catchP[0] == null) {
				m = Integer.toString(r - 1) + Integer.toString(c + 1);
				moves[i] = m;
				i++;
				String by = Integer.toString(row) + Integer.toString(column);
				addToInterceptions(by, r - 1, c + 1, b);// interceptions
			}
		}
		if (r - 1 >= 0 && c + 1 <= 7 && b.getPiece(r - 1, c + 1).getName() != null) {
			String[] catchP = b.getPiece(r - 1, c + 1).checkIntercept(b, team);

			if (catchP[0] == null) {
				m = Integer.toString(r - 1) + Integer.toString(c + 1) + " This move kills the "
						+ b.getPiece(r - 1, c + 1).getName() + " of the opponent";
				if (team != b.getPiece(r-1, c+1).getTeam()) {
					moves[i] = m;
					i++;
				}
				String by = Integer.toString(row) + Integer.toString(column);
				addToInterceptions(by, r - 1, c + 1, b);// interceptions
			}
		}

		// left 1, down 1
		if (c - 1 >= 0 && r + 1 <= 7 && b.getPiece(r + 1, c - 1).getName() == null) {
			String[] catchP = b.getPiece(r + 1, c - 1).checkIntercept(b, team);

			if (catchP[0] == null) {
				m = Integer.toString(r + 1) + Integer.toString(c - 1);
				moves[i] = m;
				i++;
				String by = Integer.toString(row) + Integer.toString(column);
				addToInterceptions(by, r + 1, c - 1, b);// interceptions
			}
		}
		if (c - 1 >= 0 && r + 1 <= 7 && b.getPiece(r + 1, c - 1).getName() != null) {
			String[] catchP = b.getPiece(r + 1, c - 1).checkIntercept(b, team);

			if (catchP[0] == null) {
				m = Integer.toString(r + 1) + Integer.toString(c - 1) + " This move kills the "
						+ b.getPiece(r + 1, c - 1).getName() + " of the opponent";
				if (team != b.getPiece(r+1, c-1).getTeam()) {
					moves[i] = m;
					i++;
				}
				String by = Integer.toString(row) + Integer.toString(column);
				addToInterceptions(by, r + 1, c - 1, b);// interceptions
			}
		}

		// up 1
		if (r - 1 >= 0 && b.getPiece(r - 1, c).getName() == null) {
			String[] catchP = b.getPiece(r - 1, c).checkIntercept(b, team);

			if (catchP[0] == null) {
				m = Integer.toString(r - 1) + Integer.toString(c);
				moves[i] = m;
				i++;
				String by = Integer.toString(row) + Integer.toString(column);
				addToInterceptions(by, r - 1, c, b);// interceptions
			}
		}
		if (r - 1 >= 0 && b.getPiece(r - 1, c).getName() != null) {
			String[] catchP = b.getPiece(r - 1, c).checkIntercept(b, team);

			if (catchP[0] == null) {
				m = Integer.toString(r - 1) + Integer.toString(c);
				if (team != b.getPiece(r - 1, c).getTeam())
					m = m + " This move kills the " + b.getPiece(r - 1, c).getName() + " of the opponent";
				if (team != b.getPiece(r-1, c).getTeam()) {
					moves[i] = m;
					i++;
				}
				String by = Integer.toString(row) + Integer.toString(column);
				addToInterceptions(by, r - 1, c, b);// interceptions
			}
		}

		// down 1
		if (r + 1 <= 7 && b.getPiece(r + 1, c).getName() == null) {
			String[] catchP = b.getPiece(r + 1, c).checkIntercept(b, team);

			if (catchP[0] == null) {
				System.out.println("King's down method" + r+1 + "," + c);
				m = Integer.toString(r + 1) + Integer.toString(c);
				moves[i] = m;
				i++;
				String by = Integer.toString(row) + Integer.toString(column);
				addToInterceptions(by, r + 1, c, b);// interceptions
			}
		}
		if (r + 1 <= 7 && b.getPiece(r + 1, c).getName() != null) {
			String[] catchP = b.getPiece(r + 1, c).checkIntercept(b, team);

			if (catchP[0] == null) {
				m = Integer.toString(r + 1) + Integer.toString(c);
				if (b.getPiece(r, c).getTeam() != b.getPiece(r + 1, c).getTeam())
					m = m + " This move kills the " + b.getPiece(r + 1, c).getName() + " of the opponent";
				if (team != b.getPiece(r+1, c).getTeam()) {
					moves[i] = m;
					i++;
				}
				String by = Integer.toString(row) + Integer.toString(column);
				addToInterceptions(by, r + 1, c, b);// interceptions
			}
		}

		// left 1
		if (c - 1 >= 0 && b.getPiece(r, c - 1).getName() == null) {
			String[] catchP = b.getPiece(r, c - 1).checkIntercept(b, team);

			if (catchP[0] == null) {
				m = Integer.toString(r) + Integer.toString(c - 1);
				moves[i] = m;
				i++;
				String by = Integer.toString(row) + Integer.toString(column);
				addToInterceptions(by, r, c - 1, b);// interceptions
			}
		}
		if (c - 1 >= 0 && b.getPiece(r, c - 1).getName() != null) {
			String[] catchP = b.getPiece(r, c - 1).checkIntercept(b, team);

			if (catchP[0] == null) {
				m = Integer.toString(r) + Integer.toString(c + 1);
				if (team != b.getPiece(r, c - 1).getTeam())
					m = m + " This move kills the " + b.getPiece(r, c - 1).getName() + " of the opponent";
				if (team != b.getPiece(r, c-1).getTeam()) {
					moves[i] = m;
					i++;
				}
				String by = Integer.toString(row) + Integer.toString(column);
				addToInterceptions(by, r, c - 1, b);// interceptions
			}
		}

		// Right 1
		if (c + 1 <= 7 && b.getPiece(r, c + 1).getName() == null) {
			String[] catchP = b.getPiece(r, c + 1).checkIntercept(b, team);

			if (catchP[0] == null) {
				m = Integer.toString(r) + Integer.toString(c + 1);
				moves[i] = m;
				i++;
				String by = Integer.toString(row) + Integer.toString(column);
				addToInterceptions(by, r, c + 1, b);// interceptions
			}
		}
		if (c + 1 <= 7 && b.getPiece(r, c + 1).getName() != null) {
			String[] catchP = b.getPiece(r, c + 1).checkIntercept(b, team);

			if (catchP[0] == null) {
				m = Integer.toString(r) + Integer.toString(c + 1);
				if (team != b.getPiece(r, c + 1).getTeam())
					m = m + " This move kills the " + b.getPiece(r, c + 1).getName() + " of the opponent";
				if (team != b.getPiece(r, c+1).getTeam()) {
					moves[i] = m;
					i++;
				}
				String by = Integer.toString(row) + Integer.toString(column);
				addToInterceptions(by, r, c + 1, b);// interceptions
			}
		}

		// Right 1, down 1
		if (c + 1 <= 7 && r + 1 <= 7 && b.getPiece(r + 1, c + 1).getName() == null) {
			String[] catchP = b.getPiece(r + 1, c + 1).checkIntercept(b, team);

			if (catchP[0] == null) {
				m = Integer.toString(r + 1) + Integer.toString(c + 1);
				moves[i] = m;
				i++;
				String by = Integer.toString(row) + Integer.toString(column);
				addToInterceptions(by, r + 1, c + 1, b);// interceptions
			}
		}
		if (c + 1 <= 7 && r + 1 <= 7 && b.getPiece(r + 1, c + 1).getName() != null) {
			String[] catchP = b.getPiece(r + 1, c + 1).checkIntercept(b, team);

			if (catchP[0] == null) {
				m = Integer.toString(r + 1) + Integer.toString(c + 1);
				if (b.getPiece(r, c).getTeam() != b.getPiece(r + 1, c + 1).getTeam())
					m = m + " This move kills the " + b.getPiece(r + 1, c + 1).getName() + " of the opponent";
				if (team != b.getPiece(r+1, c+1).getTeam()) {
					moves[i] = m;
					i++;
				}
				String by = Integer.toString(row) + Integer.toString(column);
				addToInterceptions(by, r + 1, c + 1, b);// interceptions
			}
		}
	}
}
