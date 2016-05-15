package chessGame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import javax.swing.*;
import javax.swing.border.*;
import java.net.URL;
import javax.imageio.ImageIO;

public class ChessGUI {

	private Board board;
	private JFrame f;
	private final JPanel gui = new JPanel(new BorderLayout(3, 3));
	private JButton[][] chessBoardSquares = new JButton[8][8];
	private Image[][] chessPieceImages = new Image[2][6];
	private JPanel chessBoard;
	private final JLabel message = new JLabel("Chess Champ is ready to play!");
	private static final String COLS = "ABCDEFGH";
	public static final int QUEEN = 0, KING = 1, ROOK = 2, KNIGHT = 3, BISHOP = 4, PAWN = 5;
	public static final int[] STARTING_ROW = { ROOK, KNIGHT, BISHOP, KING, QUEEN, BISHOP, KNIGHT, ROOK };
	public static final int BLACK = 0, WHITE = 1;

	ChessGUI(Board b) {
		board = b;
		f = new JFrame();
		initializeGui();
	}

	@SuppressWarnings("serial")
	public final void initializeGui() {
		// create the images for the chess pieces
		createImages();

		f.setSize(900, 900);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// set up the main GUI
		gui.setBorder(new EmptyBorder(5, 5, 5, 5));
		JToolBar tools = new JToolBar();
		tools.setFloatable(false);
		gui.add(tools, BorderLayout.PAGE_START);
		Action newGameAction = new AbstractAction("New") {

			@Override
			public void actionPerformed(ActionEvent e) {
				setupNewGame();
			}
		};
		tools.add(newGameAction);
		tools.add(new JButton("Save")); // TODO - add functionality!
		tools.add(new JButton("Restore")); // TODO - add functionality!
		tools.addSeparator();
		tools.add(new JButton("Resign")); // TODO - add functionality!
		tools.addSeparator();
		tools.add(message);

		gui.add(new JLabel("?"), BorderLayout.LINE_START);

		chessBoard = new JPanel(new GridLayout(0, 9)) {

			/**
			 * Override the preferred size to return the largest it can, in a
			 * square shape. Must (must, must) be added to a GridBagLayout as
			 * the only component (it uses the parent as a guide to size) with
			 * no GridBagConstaint (so it is centered).
			 */
			@Override
			public final Dimension getPreferredSize() {
				Dimension d = super.getPreferredSize();
				Dimension prefSize = null;
				Component c = getParent();
				if (c == null) {
					prefSize = new Dimension((int) d.getWidth(), (int) d.getHeight());
				} else if (c != null && c.getWidth() > d.getWidth() && c.getHeight() > d.getHeight()) {
					prefSize = c.getSize();
				} else {
					prefSize = d;
				}
				int w = (int) prefSize.getWidth();
				int h = (int) prefSize.getHeight();
				// the smaller of the two sizes
				int s = (w > h ? h : w);
				return new Dimension(s, s);
			}
		};
		chessBoard.setBorder(new CompoundBorder(new EmptyBorder(8, 8, 8, 8), new LineBorder(Color.BLACK)));
		// Set the BG to be ochre
		Color ochre = new Color(204, 119, 34);
		chessBoard.setBackground(ochre);
		JPanel boardConstrain = new JPanel(new GridBagLayout());
		boardConstrain.setBackground(ochre);
		boardConstrain.add(chessBoard);
		gui.add(boardConstrain);

		// create the chess board squares
		Insets buttonMargin = new Insets(0, 0, 0, 0);
		// action for moving pieces
		for (int ii = 0; ii < chessBoardSquares.length; ii++) {
			for (int jj = 0; jj < chessBoardSquares[ii].length; jj++) {
				JButton b = new JButton();
				b.addActionListener(new pieceAction(ii, jj));
				b.setMargin(buttonMargin);
				// our chess pieces are 64x64 px in size, so we'll
				// 'fill this in' using a transparent icon..
				ImageIcon icon = new ImageIcon(new BufferedImage(64, 64, BufferedImage.TYPE_INT_ARGB));
				b.setIcon(icon);
				if ((jj % 2 == 1 && ii % 2 == 1)
						// ) {
						|| (jj % 2 == 0 && ii % 2 == 0)) {
					b.setBackground(Color.WHITE);
				} else {
					b.setBackground(Color.BLACK);
				}
				chessBoardSquares[ii][jj] = b;
			}

			f.add(gui);
			f.setVisible(true);
		}

		/*
		 * fill the chess board
		 */
		chessBoard.add(new JLabel(""));
		// fill the top row
		for (int ii = 0; ii < 8; ii++) {
			chessBoard.add(new JLabel(COLS.substring(ii, ii + 1), SwingConstants.CENTER));
		}
		// fill the black non-pawn piece row
		for (int ii = 0; ii < 8; ii++) {
			for (int jj = 0; jj < 8; jj++) {
				switch (jj) {
				case 0:
					chessBoard.add(new JLabel("" + (9 - (ii + 1)), SwingConstants.CENTER));
				default:
					chessBoard.add(chessBoardSquares[jj][ii]);
				}
			}
		}
	}

	public final JComponent getGui() {
		return gui;
	}

	private final void createImages() {
		try {
			URL url = new URL("http://i.stack.imgur.com/memI0.png");
			BufferedImage bi = ImageIO.read(url);
			for (int ii = 0; ii < 2; ii++) {
				for (int jj = 0; jj < 6; jj++) {
					chessPieceImages[ii][jj] = bi.getSubimage(jj * 64, ii * 64, 64, 64);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(1);
		}
	}

	/**
	 * Initializes the icons of the initial chess board piece places
	 */
	private final void setupNewGame() {
		message.setText("Make your move!");
		reDrawBoard();
	}

	// Redraw board
	public void reDrawBoard() {
		for (int r = 0; r < 8; r++) {
			for (int c = 0; c < 8; c++) {
				// For kings
				if (board.getPiece(r, c).getName() == "King" && board.getPiece(r, c).getTeam()) {
					chessBoardSquares[r][c].addActionListener(new pieceAction(r,c));
					chessBoardSquares[r][c].setIcon(new ImageIcon(chessPieceImages[WHITE][KING]));
				}
				if (board.getPiece(r, c).getName() == "King" && !board.getPiece(r, c).getTeam()) {
					chessBoardSquares[r][c].addActionListener(new pieceAction(r,c));
					chessBoardSquares[r][c].setIcon(new ImageIcon(chessPieceImages[BLACK][KING]));
				}
				// For queen
				if (board.getPiece(r, c).getName() == "Queen" && board.getPiece(r, c).getTeam()) {
					chessBoardSquares[r][c].addActionListener(new pieceAction(r,c));
					chessBoardSquares[r][c].setIcon(new ImageIcon(chessPieceImages[WHITE][QUEEN]));
				}
				if (board.getPiece(r, c).getName() == "Queen" && !board.getPiece(r, c).getTeam()) {
					chessBoardSquares[r][c].addActionListener(new pieceAction(r,c));
					chessBoardSquares[r][c].setIcon(new ImageIcon(chessPieceImages[BLACK][QUEEN]));
				}
				// Rook
				if (board.getPiece(r, c).getName() == "Rook" && board.getPiece(r, c).getTeam()) {
					chessBoardSquares[r][c].addActionListener(new pieceAction(r,c));
					chessBoardSquares[r][c].setIcon(new ImageIcon(chessPieceImages[WHITE][ROOK]));
				}
				if (board.getPiece(r, c).getName() == "Rook" && !board.getPiece(r, c).getTeam()) {
					chessBoardSquares[r][c].addActionListener(new pieceAction(r,c));
					chessBoardSquares[r][c].setIcon(new ImageIcon(chessPieceImages[BLACK][ROOK]));
				}
				// Bishop
				if (board.getPiece(r, c).getName() == "Bishop" && board.getPiece(r, c).getTeam()) {
					chessBoardSquares[r][c].addActionListener(new pieceAction(r,c));
					chessBoardSquares[r][c].setIcon(new ImageIcon(chessPieceImages[WHITE][BISHOP]));
				}
				if (board.getPiece(r, c).getName() == "Bishop" && !board.getPiece(r, c).getTeam()) {
					chessBoardSquares[r][c].addActionListener(new pieceAction(r,c));
					chessBoardSquares[r][c].setIcon(new ImageIcon(chessPieceImages[BLACK][BISHOP]));
				}
				// Knight
				if (board.getPiece(r, c).getName() == "Knight" && board.getPiece(r, c).getTeam()) {
					chessBoardSquares[r][c].addActionListener(new pieceAction(r,c));
					chessBoardSquares[r][c].setIcon(new ImageIcon(chessPieceImages[WHITE][KNIGHT]));
				}
				if (board.getPiece(r, c).getName() == "Knight" && !board.getPiece(r, c).getTeam()) {
					chessBoardSquares[r][c].addActionListener(new pieceAction(r,c));
					chessBoardSquares[r][c].setIcon(new ImageIcon(chessPieceImages[BLACK][KNIGHT]));
				}
				// Pawn
				if (board.getPiece(r, c).getName() == "Pawn" && board.getPiece(r, c).getTeam()) {
					chessBoardSquares[r][c].addActionListener(new pieceAction(r,c));
					chessBoardSquares[r][c].setIcon(new ImageIcon(chessPieceImages[WHITE][PAWN]));
				}
				if (board.getPiece(r, c).getName() == "Pawn" && !board.getPiece(r, c).getTeam()) {
					chessBoardSquares[r][c].addActionListener(new pieceAction(r,c));
					chessBoardSquares[r][c].setIcon(new ImageIcon(chessPieceImages[BLACK][PAWN]));
				}
				if(board.getPiece(r, c).getName() == null){
					chessBoardSquares[r][c].addActionListener(new pieceAction(r,c));
					chessBoardSquares[r][c].setIcon(null);
				}

				//Also reset the color of the board
				if ((c % 2 == 1 && r % 2 == 1)
						// ) {
						|| (c % 2 == 0 && r % 2 == 0)) {
					chessBoardSquares[r][c].setBackground(Color.WHITE);
				} else {
					chessBoardSquares[r][c].setBackground(Color.BLACK);
				}
			}
		}
	}

	/*********************
	 * Action Listener
	 **************************************/
	class pieceAction implements ActionListener {
		private int row;
		private int col;

		public pieceAction(int r, int c) {
			row = r;
			col = c;
		}

		public void actionPerformed(ActionEvent e) {
			// First reset the color of all the pieces board
			// Because every time by pressing a button you
			// might be changing the colors to blue

			reDrawBoard();
			String[] moves = board.getPiece(row, col).getMove();

			int i = 0;

			while (i < moves.length) {
				if (moves[i] != null) {
					int r = Character.getNumericValue(moves[i].charAt(0));
					int c = Character.getNumericValue(moves[i].charAt(1));
					chessBoardSquares[r][c].setBackground(Color.blue);
					chessBoardSquares[r][c].addActionListener(new moveTo(r, c, board.getPiece(row, col)));	
				}

				i++;
			}
			
			f.repaint();
		}
	}
	
	//Action listener to move the piece
	class moveTo implements ActionListener
	{
		private int moveToRow;
		private int moveToCol;
		private Piece p;
		public moveTo(int r, int c, Piece P){
			moveToRow = r;
			moveToCol = c;
			p = P;
		}
		
		public void actionPerformed(ActionEvent e)
		{
			//Make the move
			System.out.println(moveToRow + "" + moveToCol);
			board.makeMove(moveToRow, moveToCol, p);
			//reDrawBoard();
			board.showBoard();
		}	 
	}
}
