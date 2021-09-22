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
        assertEquals(queenBlack.print(),"q");
        assertEquals(queenWhite.print(),"Q");
        Queen queenWhiteWithType = new Queen(queenWhite);
        assertEquals(queenWhiteWithType.print(),"Q");
        assertEquals(queenWhiteWithType.getType(),5);
    };
    /**
     * test move of queen with cross, straight, across and wrong move
     */
    @Test
    public void testCheckMove1() {
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

    }


    /**
     * test move of queen with cross, straight, across and wrong move
     */
    @Test
    public void testCheckMove2() {
        int[] crossMove = {1, 3, 1, 3};
        int[] straightMove = {3, 2, 3, 5};
        int[] wrongMove = {2, 3, 5, 1};
        int[] acrossMove = {1, 2, 3, 2};


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
    /**
     * test for print method
     */
    @Test
    public void testPrint() {

        assertEquals(queenWhite.print(), "Q");
        assertEquals(queenBlack.print(), "q");
    }

    /**
     * test for print1 method
     */
    @Test
    public void testPrint1() {

        assertEquals(queenBlack.print1(), "\u265b");
        assertEquals(queenWhite.print1(), "\u2655");



    }


}