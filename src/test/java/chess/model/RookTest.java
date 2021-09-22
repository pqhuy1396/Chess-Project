package chess.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
//test
/**
 * Testing the Example class with its arbitrary arithmetical computations
 */
public class RookTest {
    /**
     * create rook and his move
     */
    Rook rookBlack = new Rook(false);
    Rook rookWhite = new Rook(true);
    Board board = new Board();
    /**
     * test color of rook
     */
    @Test
    public void testColor() {
        assertEquals(rookBlack.print(),"r");
        assertEquals(rookWhite.print(),"\u265C");
    };
    /**
     * test move of rook with straight, across and wrong move
     */
    @Test
    public void testCheckMove() {
        int[] straightMove = {1,5,1,5};
        int[] acrossMove = {3,5,6,5};
        int[] wrongMove = {1,3,2,7};
        //straight move
        assertTrue(rookBlack.checkMove(straightMove));
        assertTrue(rookWhite.checkMove(straightMove));
        //across move
        assertTrue(rookBlack.checkMove(acrossMove));
        assertTrue(rookWhite.checkMove(acrossMove));
        //wrong move
        assertFalse(rookBlack.checkMove(wrongMove));
        assertFalse(rookWhite.checkMove(wrongMove));
        //no special move
        assertFalse(rookWhite.specialMove(wrongMove,board));
    }


    /**
     * Test for creating a copy of an existing Rook
     */
    @Test
    public void testCopyConstructor(){
        Rook rook1 = new Rook(false);
        Rook rook2 = rook1;
        assertEquals(rook1.isColour(),rook2.isColour());
        rook1.isColour();
        assertEquals(rook1.isColour(),rook2.isColour());
        rook2=new Rook(true);
        rook2.isColour();
        assertNotEquals(rook1.isColour(),rook2.isColour());
    }

    /**
     * Test for Getter for the type variable
     */
    @Test
    public void testGetType(){
        Rook rook1 = new Rook(false);
        assertEquals(rook1.getType(),10);
        rook1.setType(10);
        assertNotEquals(rook1.getType(),2);
        assertEquals(rook1.getType(),10);
    }

}
