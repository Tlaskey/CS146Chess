import java.util.ArrayList;

public class Queen extends Piece 
{
	public Queen(boolean b, int i, int j)
	{
		super(b, i, j);
	}
	@Override
	public String toString()
	{
		if(getTeam()) return "Q";
		return "q";
	}
	@Override
	public ArrayList<String> moves()
	{
		ArrayList<String> returnVal = new ArrayList<String>();
		for(int i = 0; i < 8; i++){
			returnVal.add(i + "," + this.getX());
			returnVal.add(this.getY() + "," + i);
		}
		int x1 = this.getX();
		int y1 = this.getY();
		int min = Math.min(x1, y1);
		int startX = x1 - min;
		int startY = y1 - min;
		while(startX < 8 && startY < 8){
			returnVal.add(startY + "," + startX);
			startX++;
			startY++;
		}
		min = Math.min(x1, 7 - y1);
		startX = x1 - min;
		startY = y1 + min;
		while(startX < 8 && startY > -1){
			returnVal.add(startY + "," + startX);
			startX++;
			startY--;
		}
		adjust(returnVal);
		return returnVal;
	}
	private void adjust(ArrayList<String> rawMoves)
	{
		rawMoves.remove(this.getLoc());
		for(int i = rawMoves.size(); i > -1; i--){
			Piece p = GameHandler.b.getPiece(rawMoves.get(i));
			if(p.getTeam() == this.getTeam()) rawMoves.remove(i);
		}
	}
}