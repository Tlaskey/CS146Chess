import java.util.ArrayList;

public class Pawn extends Piece {
	boolean firstMove;

	public Pawn(boolean b, int i, int j) {
		super(b, i, j);
		firstMove = true;
	}

	@Override
	public String toString() {
		if (getTeam()) {
			return "P";
		}
		return "p";
	}

	@Override
	public ArrayList<String> moves() {
		ArrayList<String> returnVal = new ArrayList<String>();
		int startX = this.getX();
		int startY = this.getY();
		boolean team = this.getTeam();
		if (team && firstMove) {
			returnVal.add((startY - 2) + "," + startX);
			firstMove = false;
		}
		if (team)
			returnVal.add((startY - 1) + "," + startX);

		if (!team && firstMove) {
			firstMove = false;			
			int newY = startY + 2;
			returnVal.add(newY + "," + startX);
		}
		if (!team) {
			int otherNewY = startY + 1;
			returnVal.add(otherNewY + "," + startX);
		}
		adjust(returnVal);
		return returnVal;
	}
	private void adjust(ArrayList<String> rawMoves)
	{
		boolean team = this.getTeam();
		if(team)
		{
			Piece p = GameHandler.b.getPiece(this.getY()-1, this.getX());
			if(!p.isBlank())
				rawMoves.removeAll(rawMoves);
		}
		else if(!team)
		{
			Piece p = GameHandler.b.getPiece(this.getY()+1, this.getX());
			if(!p.isBlank())
				rawMoves.removeAll(rawMoves);
		}
		if(team)
		{
			int x1 = this.getX() - 1;
			int y1 = this.getY() - 1;
			Piece cap1 = GameHandler.b.getPiece(y1, x1);
			if(cap1 != null && !cap1.isBlank()) rawMoves.add(y1 + "," + x1);
			int x2 = this.getX() + 1;
			Piece cap2 = GameHandler.b.getPiece(y1, x2);
			if(!cap2.isBlank() && cap2 != null) rawMoves.add(y1 + "," + x2);
		}
		if(!team)
		{
			int x1 = this.getX() - 1;
			int y1 = this.getY() + 1;
			Piece cap1 = GameHandler.b.getPiece(y1, x1);
			if(cap1 != null && !cap1.isBlank()) rawMoves.add(y1 + "," + x1);
			int x2 = this.getX() + 1;
			Piece cap2 = GameHandler.b.getPiece(y1, x2);
			if(!cap2.isBlank() && cap2 != null) rawMoves.add(y1 + "," + x2);
		}
	}
}