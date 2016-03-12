
public class KingPiece extends Piece{
	public KingPiece(boolean team) {
		super(team);
	}
	//Can move any direction. Can only move 1 space.
	//Can never move in a direction where he will be in check.
	public void move(){
		
	}
	
	public String toString(){
		if(getTeam()){
			return "K";
		}
		return "k";
	}
}
