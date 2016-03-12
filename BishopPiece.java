
public class BishopPiece extends Piece{

	public BishopPiece(boolean team) {
		super(team);
	}
	//Can move as many spaces as it wants diagonally.
	public void move(){
		
	}
	
	public String toString(){
		if(getTeam()){
			return "B";
		}
		return "b";
	}
}
