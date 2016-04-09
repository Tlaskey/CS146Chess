import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class ObsTest {
	Board b;

	@Before
	public void setUp() throws Exception {
		b = new Board();
		b.genBlankBoard();
		b.addPiece(0, 0, new Piece(false, 0, 0));
		b.addPiece(1, 1, new Piece(false, 1, 1));
		b.addPiece(1, 0, new Piece(false, 0, 1));
		b.addPiece(0, 1, new Piece(false, 1, 0));
		b.addPiece(2, 0, new Piece(false, 0, 2));
		b.addPiece(2, 2, new Piece(false, 2, 2));
		b.addPiece(0, 2, new Piece(false, 2, 0));
	}

	@Test
	public void test1() {
		assertTrue(b.obscured("0,0", "2,0"));
		assertTrue(b.obscured("2,0", "0,0"));
	}
	@Test
	public void test2() {
		assertTrue(b.obscured("0,0","0,2"));
		assertTrue(b.obscured("0,2", "0,0"));
	}
	@Test
	public void test3() {
		assertTrue(b.obscured("0,0", "2,2"));
		assertTrue(b.obscured("2,2", "0,0"));
	}
	@Test
	public void test4() {
		assertTrue(b.obscured("2,0", "0,2"));
		assertTrue(b.obscured("0,2", "2,0"));
	}
	@Test
	public void test5(){
		assertFalse(b.obscured("1,0", "7,7"));
		assertFalse(b.obscured("2,0", "4,0"));
		assertFalse(b.obscured("2,2", "4,4"));
		assertFalse(b.obscured("0,2", "0,4"));
	}
}