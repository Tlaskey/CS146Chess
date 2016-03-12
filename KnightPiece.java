
public class KnightPiece extends Piece{

	public KnightPiece(boolean team) {
		super(team);
	}
	//Can move in patterns of L shapes. 
	//(two moves up either horizonal or vertical, then 1 move vertical or horizontal)
	public void move(){
		
	}
	
	public String toString(){
		if(getTeam()){
			return "I";
		}
		return "i";
	}
}
