
public class PawnPiece extends Piece{

	public PawnPiece(boolean team) {
		super(team);
	}
	//Can only capture a piece if moves diagonal 1 to land on piece.
	//First move can move two spaces.
	//If pawn reaches other teams first row, it can be replaced with a captured piece.
	public void move(){
		
	}
	public String toString(){
		if(getTeam()){
			return "P";
		}
		return "p";
	}
}
