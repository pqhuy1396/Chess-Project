package chess.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
//test
/**
 * Testing the Example class with its arbitrary arithmetical computations
 */
public class QueenTest {
    /**
     * create queen and her move
     */
    Queen queenBlack = new Queen(false);
    Queen queenWhite = new Queen(true);
    Board board = new Board();

    /**
     * test color of move
     */
    @Test
    public void testColor() {
        assertEquals(queenBlack.print(),"\u2655");
        assertEquals(queenWhite.print(),"\u265b");
        Queen queenWhiteWithType = new Queen(queenWhite);
        assertEquals(queenWhiteWithType.print(),"\u265b");
        assertEquals(queenWhiteWithType.getType(),5);
    };
    /**
     * test move of queen with cross, straight, across and wrong move
     */
    @Test
    public void testCheckMove() {
        int[] crossMove = {1,3,1,3};
        int[] straightMove = {3,2,3,5};
        int[] wrongMove = {2,3,5,1};
        int[] acrossMove = {1,2,3,2};
        //Test cross move of queen
        assertTrue(queenBlack.checkMove(crossMove));
        assertTrue(queenWhite.checkMove(crossMove));
        //Test straight move of queen
        assertTrue(queenBlack.checkMove(straightMove));
        assertTrue(queenWhite.checkMove(straightMove));
        //Test wrong move of queen
        assertFalse(queenBlack.checkMove(wrongMove));
        assertFalse(queenWhite.checkMove(wrongMove));
        //Test across move of queen
        assertTrue(queenBlack.checkMove(acrossMove));
        assertTrue(queenWhite.checkMove(acrossMove));
        //no special move
        assertFalse(queenWhite.specialMove(acrossMove,board));
    }

    /**
     * Test for creating a copy of an existing Queen
     */
    @Test
    public void testCopyConstructor(){
        Queen queen1 = new Queen(false);
        Queen queen2 = queen1;
        assertEquals(queen1.isColour(),queen2.isColour());
        queen1.isColour();
        assertEquals(queen1.isColour(),queen2.isColour());
        queen2=new Queen(true);
        queen2.isColour();
        assertNotEquals(queen1.isColour(),queen2.isColour());
    }

    /**
     * Test for Getter for the type variable
     */
    @Test
    public void testGetType(){
        Queen queen1 = new Queen(false);
        assertEquals(queen1.getType(),5);
        queen1.setType(5);
        assertNotEquals(queen1.getType(),2);
        assertEquals(queen1.getType(),5);
    }


}