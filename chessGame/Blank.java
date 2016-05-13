package chessGame;

public class Blank extends Piece{
	
	//Try to make this the actual constructor
	public Blank(int x, int y){
		//(boolean t, String n, boolean no, int x, int y, boolean d, boolean e)
		//blank(team, null, true, i, m, true, false)
		super(true, null, false, x, y, true, true);
	}
}
