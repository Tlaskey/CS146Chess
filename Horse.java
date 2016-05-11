public class Horse extends Piece {
	private int limit;

	public Horse(boolean t, String n, boolean m, int x, int y, boolean d, boolean e) {
		super(t, n, m, x, y, d, e);
		limit = 18;

	}

	// move
	public void moves(Board b) {
		int r = row;
		int c = column;
		String m;
		int i = 0;
		
		// Up two, left 1
		if (r > 1 && c >= 1 && b.getPiece(r - 2, c - 1).getName() == null) {
			m = Integer.toString(r - 2) + Integer.toString(c - 1);
			moves[i] = m;
			i++;
			String by = Integer.toString(row) + Integer.toString(column);
			addToInterceptions(by, r - 2, c - 1, b);// interceptions
		}
		if (r > 1 && c >= 1 && b.getPiece(r - 2, c - 1).getName() != null
				&& team != b.getPiece(r - 2, c - 1).getTeam()) {
			m = Integer.toString(r - 2) + Integer.toString(c - 1) + " This move kills the "
					+ b.getPiece(r - 1, c - 1).getName() + " of the opponent";
			moves[i] = m;
			i++;
			String by = Integer.toString(row) + Integer.toString(column);
			addToInterceptions(by, r - 2, c - 1, b);// interceptions
		}

		// Up two, right 1
		if (r - 2 >= 0 && c <= 6 && b.getPiece(r - 2, c + 1).getName() == null) {
			m = Integer.toString(r - 2) + Integer.toString(c + 1);
			moves[i] = m;
			i++;
			String by = Integer.toString(row) + Integer.toString(column);
			addToInterceptions(by, r - 2, c + 1, b);// interceptions
		}
		if (r - 2 >= 0 && c + 1 <= 7 && b.getPiece(r - 2, c + 1).getName() != null
				&& b.getPiece(r - 2, c + 1).getTeam() != team) {
			m = Integer.toString(r - 2) + Integer.toString(c + 1) + " This move kills the "
					+ b.getPiece(r - 2, c + 1).getName() + " of the opponent";
			moves[i] = m;
			i++;
			String by = Integer.toString(row) + Integer.toString(column);
			addToInterceptions(by, r - 2, c + 1, b);// interceptions
		}

		// left two, down 1
		if (c - 2 >= 0 && r + 1 <= 7 && b.getPiece(r + 1, c - 2).getName() == null) {
			m = Integer.toString(r + 1) + Integer.toString(c - 2);
			moves[i] = m;
			i++;
			String by = Integer.toString(row) + Integer.toString(column);
			addToInterceptions(by, r + 1, c - 2, b);// interceptions
		}
		if (c - 2 >= 0 && r + 1 <= 7 && b.getPiece(r + 1, c - 2).getName() != null
				&& b.getPiece(r + 1, c - 2).team != team) {
			m = Integer.toString(r + 1) + Integer.toString(c - 2) + " This move kills the "
					+ b.getPiece(r + 1, c - 2).getName() + " of the opponent";
			moves[i] = m;
			i++;
			String by = Integer.toString(row) + Integer.toString(column);
			addToInterceptions(by, r + 1, c - 2, b);// interceptions
		}

		// left two, up 1
		if (c - 2 >= 0 && r - 1 >= 0 && b.getPiece(r - 1, c - 2).getName() == null) {
			m = Integer.toString(r - 1) + Integer.toString(c - 2);
			moves[i] = m;
			i++;
			String by = Integer.toString(row) + Integer.toString(column);
			addToInterceptions(by, r - 1, c - 2, b);// interceptions
		}
		if (c - 2 >= 0 && r - 1 >= 0 && team != b.getPiece(r - 1, c - 2).getTeam()
				&& b.getPiece(r - 1, c - 2).getName() != null) {
			m = Integer.toString(r - 1) + Integer.toString(c - 2);
			if (b.getPiece(r, c).getTeam() != b.getPiece(r - 1, c - 2).getTeam())
				m = m + " This move kills the " + b.getPiece(r - 1, c - 2).getName() + " of the opponent";
			moves[i] = m;
			i++;
			String by = Integer.toString(row) + Integer.toString(column);
			addToInterceptions(by, r - 1, c - 2, b);// interceptions
		}

		// down two, left 1
		if (r + 2 <= 7 && c - 1 >= 0 && b.getPiece(r + 2, c - 1).getName() == null) {
			m = Integer.toString(r + 2) + Integer.toString(c - 1);
			moves[i] = m;
			i++;
			String by = Integer.toString(row) + Integer.toString(column);
			addToInterceptions(by, r + 2, c - 1, b);// interceptions
		}
		if (r + 2 <= 7 && c - 1 >= 0 && team != b.getPiece(r + 2, c - 1).getTeam()
				&& b.getPiece(r + 2, c - 1).getName() != null) {
			m = Integer.toString(r + 2) + Integer.toString(c - 1);
			if (b.getPiece(r, c).getTeam() != b.getPiece(r + 2, c - 1).getTeam())
				m = m + " This move kills the " + b.getPiece(r + 1, c - 1).getName() + " of the opponent";
			moves[i] = m;
			i++;
			String by = Integer.toString(row) + Integer.toString(column);
			addToInterceptions(by, r + 2, c - 1, b);// interceptions
		}

		// down two, right 1
		if (r + 2 <= 7 && c + 1 <= 7 &&  b.getPiece(r + 2, c + 1).getName() == null) {
			m = Integer.toString(r + 2) + Integer.toString(c + 1);
			moves[i] = m;
			i++;
			String by = Integer.toString(row) + Integer.toString(column);
			addToInterceptions(by, r + 2, c + 1, b);// interceptions
		}
		if (r + 2 <= 7 && c + 1 <= 7 && team != b.getPiece(r + 2, c + 1).getTeam()
				&&  b.getPiece(r + 2, c + 1).getName() != null) {
			m = Integer.toString(r + 2) + Integer.toString(c + 1);
			if (b.getPiece(r, c).getTeam() != b.getPiece(r + 2, c + 1).getTeam())
				m = m + " This move kills the " + b.getPiece(r + 2, c + 1).getName() + " of the opponent";
			moves[i] = m;
			i++;
			String by = Integer.toString(row) + Integer.toString(column);
			addToInterceptions(by, r + 2, c + 1, b);// interceptions
		}

		// Right two, up 1
		if (c + 2 <= 7 && r - 1 >= 0 &&  b.getPiece(r - 1, c + 2).getName() == null) {
			m = Integer.toString(r - 1) + Integer.toString(c + 2);
			moves[i] = m;
			i++;
			String by = Integer.toString(row) + Integer.toString(column);
			addToInterceptions(by, r - 1, c + 2, b);// interceptions
		}
		if (c + 2 <= 7 && r - 1 >= 0 && team != b.getPiece(r - 1, c + 2).getTeam()
				&& b.getPiece(r - 1, c + 2).getName() != null) {
			m = Integer.toString(r - 1) + Integer.toString(c + 2);
			if (b.getPiece(r, c).getTeam() != b.getPiece(r - 1, c + 2).getTeam())
				m = m + " This move kills the " + b.getPiece(r - 1, c + 2).getName() + " of the opponent";
			moves[i] = m;
			i++;
			String by = Integer.toString(row) + Integer.toString(column);
			addToInterceptions(by, r - 1, c + 2, b);// interceptions
		}

		// Right two, down 1
		if (c + 2 <= 7 && r + 1 <= 7 && b.getPiece(r + 1, c + 2).getName() == null) {
			m = Integer.toString(r + 1) + Integer.toString(c + 2);
			moves[i] = m;
			i++;
			String by = Integer.toString(row) + Integer.toString(column);
			addToInterceptions(by, r + 1, c + 2, b);// interceptions
		}
		if (c + 2 <= 7 && r + 1 <=7 && team != b.getPiece(r + 1, c + 2).getTeam()
				&& b.getPiece(r + 1, c + 2).getName() != null) {
			m = Integer.toString(r + 1) + Integer.toString(c + 2)
			+ " This move kills the " + b.getPiece(r + 1, c + 2).getName() + " of the opponent";
			moves[i] = m;
			i++;
			String by = Integer.toString(row) + Integer.toString(column);
			addToInterceptions(by, r + 1, c + 2, b);// interceptions
		}
	}
}
