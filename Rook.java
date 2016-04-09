
public class Rook extends Piece{

	public Rook(boolean team) {
		super(team);
	}
	//Can move as many spaces as it wants horizontally or vertically.
	public void move(){
		
	}
	public String toString(){
		if(getTeam()){
			return "R";
		}
		return "r";
	}
}
