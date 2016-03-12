
public class QueenPiece extends Piece{

	public QueenPiece(boolean team) {
		super(team);
	}
	//Can move as many spaces it wants in any direction.
	public void move(){
		
	}
	
	public String toString(){
		if(getTeam()){
			return "Q";
		}
		return "q";
	}
}
