
public class Bishop extends Piece{

	public Bishop(boolean team) {
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
