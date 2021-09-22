package chess.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
//test
/**
 * Create bishop and move
 */
public class BishopTest {
    Bishop bishopBlack = new Bishop(false);
    Bishop bishopWhite = new Bishop(true);
    Board board = new Board();
    /**
     * Testing move of bishop with cross and wrong move
     */
    @Test
    public void testCheckMove() {
        int[] crossMove = {1,1,3,3};
        int[] wrongMove = {1,5,1,3};
        //cross move
        assertTrue(bishopBlack.checkMove(crossMove));
        assertTrue(bishopWhite.checkMove(crossMove));
        //wrong move
        assertFalse(bishopBlack.checkMove(wrongMove));
        assertFalse(bishopWhite.checkMove(wrongMove));
        //no special move
        assertFalse(bishopWhite.specialMove(wrongMove,board));
    }

    /**
     * Testing color of bishop
     */
    @Test
    public void testColor() {
        assertEquals(bishopBlack.print(),"\u2657");
        assertEquals(bishopWhite.print(),"\u265D");
    }

    /**
     * Test for creating a copy of an existing Bishop
     */
    @Test
    public void testCopyConstructor(){
        Bishop bishop1 = new Bishop(false);
        Bishop bishop2 = bishop1;
        assertEquals(bishop1.isColour(),bishop2.isColour());
        bishop1.setColour(true);
        assertEquals(bishop1.isColour(),bishop2.isColour());
        bishop2=new Bishop(bishop1);
        bishop2.setColour(false);
        assertNotEquals(bishop1.isColour(),bishop2.isColour());
    }

    /**
     * Test for the Getter of type
     */
    @Test
    public void testGetType(){
        Bishop bishop1 = new Bishop(false);
        assertEquals(bishop1.getType(),2);
        bishop1.setType(4);
        assertNotEquals(bishop1.getType(),2);
        assertEquals(bishop1.getType(),4);
    }
}
