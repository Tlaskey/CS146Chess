
public class Board {
	private Piece[][] board;

	public Board() {
		board = new Piece[8][8];
	}

	public void generateBoard() {
		board[0][0] = new RookPiece(false);
		board[0][1] = new KnightPiece(false);
		board[0][2] = new BishopPiece(false);
		board[0][3] = new QueenPiece(false);
		board[0][4] = new KingPiece(false);
		board[0][5] = new BishopPiece(false);
		board[0][6] = new KnightPiece(false);
		board[0][7] = new RookPiece(false);

		board[7][0] = new RookPiece(true);
		board[7][1] = new KnightPiece(true);
		board[7][2] = new BishopPiece(true);
		board[7][3] = new QueenPiece(true);
		board[7][4] = new KingPiece(true);
		board[7][5] = new BishopPiece(true);
		board[7][6] = new KnightPiece(true);
		board[7][7] = new RookPiece(true);

		for (int i = 0; i < 8; i++) {
			board[1][i] = new PawnPiece(false);
			board[6][i] = new PawnPiece(true);
		}
	}

	public Piece getPiece(int x, int y) {
		return board[x][y];
	}

	public void setPiece(int x, int y, Piece piece) {
		board[x][y] = piece;
	}
	
	public void printBoard(){
		for(int i = 0; i < 8; i++){
			for(int k = 0; k < 8; k++){
				if(board[i][k] == null){
					System.out.print("- ");
				}
				else{
				System.out.print(board[i][k].toString() + " ");
				}
			}
			System.out.println();
		}
	}
}
