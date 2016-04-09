
public class BlankSpace extends Piece {
	private boolean isCheckedByWhite;
	private boolean isCheckedByBlack;
	private boolean spaceColor;
	public BlankSpace(int y, int x){
		super(false, y, x, true);
	}
	public BlankSpace(boolean cbw, boolean cbb, boolean spc)
	{
		super(spc,0,0,true);
		isCheckedByWhite = cbw;
		isCheckedByBlack = cbb;
	}
	public BlankSpace()
	{
		super(true,0,0,true);
		isCheckedByWhite = false;
		isCheckedByBlack = false;
	}
	@Override
	public String toString()
	{
		return "-";
	}
	public boolean getSpaceColor()
	{
		return spaceColor;
	}
	public void setSpaceColor(boolean spc)
	{
		spaceColor = spc;
	}
	public boolean getCBW()
	{
		return isCheckedByWhite;
	}
	public boolean getCBB()
	{
		return isCheckedByBlack;
	}
	public void setCBW(boolean cbw)
	{
		isCheckedByWhite = cbw;
	}
	public void setCBB(boolean cbb)
	{
		isCheckedByBlack = cbb;
	}
	public boolean kingCanMoveHere(boolean team)
	{
		if(team) return isCheckedByBlack;
		return isCheckedByWhite;
		
	}
}