import java.util.ArrayList;

public class Piece {	
	private boolean team;
	private int x;
	private int y;
	private final boolean isBlankSpace;	
	public Piece(){ 
		isBlankSpace = false;
		team = false;
		x = 0;
		y = 0;
	}
	public Piece(boolean color, int y, int x)
	{
		isBlankSpace = false;
		team = color;
		this.x = x;
		this.y = y;
	}
	public Piece(boolean color, int y, int x, boolean blank)
	{
		isBlankSpace = blank;
		team = color;
		this.x = x;
		this.y = y;
	}
	public boolean isBlank(){
		return isBlankSpace;
	}
	public boolean getTeam()
	{		
		return team;
	}
	public int getX()
	{
		return x;
	}
	public int getY()
	{
		return y;
	}
	public void setLoc(int x, int y)
	{
		this.x = x;
		this.y = y;
	}
	public String getLoc()
	{
		return y + "," + x;
	}
	public void setX(int x)
	{
		this.x = x;
	}
	public void setY(int y)
	{
		this.y = y;
	}
	public ArrayList<String> moves()
	{
		String temp = "0,0";
		ArrayList<String> temp2 = new ArrayList<>();
		temp2.add(temp);
		return temp2;
	}
}