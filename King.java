import java.util.ArrayList;

public class King extends Piece {
	public King(boolean b, int i, int j)
	{
		super(b, i, j);
	}
	@Override
	public String toString()
	{
		if(getTeam()) return "K";
		return "k";
	}
	@Override
	public ArrayList<String> moves()
	{
		ArrayList<String> moves = new ArrayList<String>();
		int x = this.getX();
		int y = this.getY();
		for(int i = x - 1; i <= x + 1; i++)
		{
			for(int j = y - 1; j <= y + 1; j++)
			{
				if(j >= 0 && i >= 0) moves.add(j + "," + i);
			}
		}
		adjust(moves);
		return moves;
	}
	private void adjust(ArrayList<String> rawMoves)
	{
		ArrayList<String> invalidMoves = new ArrayList<String>();
		invalidMoves.add(this.getLoc());
		for(int i = 0; i < rawMoves.size(); i++)
		{
			Piece p = GameHandler.b.getPiece(rawMoves.get(i));
			if(p == null)
			{
				invalidMoves.add(rawMoves.get(i));
			}
			else if(p.isBlank())
			{
				if(!((BlankSpace)p).kingCanMoveHere(this.getTeam())) invalidMoves.add(rawMoves.get(i));		
			}
			else if(p.getTeam() == this.getTeam()){
				invalidMoves.add(rawMoves.get(i));		
			}
		}
		rawMoves.removeAll(invalidMoves);
	}
}