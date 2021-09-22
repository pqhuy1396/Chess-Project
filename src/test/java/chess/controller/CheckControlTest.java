package chess.controller;

import chess.model.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * class to test methods if class CheckControl
 */
public class CheckControlTest {

    ConsoleInput input = new ConsoleInput();
    HumanPlayer player1 = new HumanPlayer(input);
    HumanPlayer player2 = new HumanPlayer(input);
    int[] tempmove = new int[]{1,1,2,2};
    Game game= new Game(player1,player2);




    /**
     * Tests whether generate threatenedSpaces works
     */
    @Test
    public void checkThreatTest1(){
        Board moveBoard = new Board();
        boolean[][] threads;
        threads = CheckControl.generateThreatenedSpaces(moveBoard,true);
        assertTrue(threads[5][0]);
        assertFalse(threads[4][0]);

    }
    /**
     * Tests whether generate threatenedSpaces works
     */
    @Test
    public void checkThreatTest2(){
        Board moveBoard = new Board();
        boolean[][] threads;
        threads =CheckControl.generateThreatenedSpaces(moveBoard,false);
        assertTrue(threads[2][0]);
        assertFalse(threads[3][0]);

    }
    /**
     * Tests whether getPlayerKingPositionTest works
     */
    @Test
    public void getPlayerKingPositionTest(){
        int[] kingpos= new int[2];
        kingpos[0]=4;
        kingpos[1]=7;
        assertArrayEquals(CheckControl.getPlayerKingPosition(true,game.getBoard()),kingpos);

    }
    /**
     * Tests a player can "escape" from chess
     */
    @Test
    public void canReleaseTest(){
        assertTrue(CheckControl.canRelease(true,game.getBoard()));
        assertTrue(CheckControl.canRelease(false,game.getBoard()));
    }

    /**
     * Test for the isCheck Method
     */
    @Test
    public void isCheckTest(){
        assertFalse(CheckControl.isCheck(true,game.getBoard()));
        Board testBoard = new Board(game.getBoard());
        testBoard.setSquare(6,4,new Queen(false));
        assertTrue(CheckControl.isCheck(true, testBoard));
    }
    /**
     * Test for the checkReach method.
     * Check if a pawn can move 2 fields and check if a pawn can not move 3 fields
     */
    @Test
    public void checkReachTest(){
        int[] move1 = new int[]{1,1,1,3};
        int[] move2 = new int[]{1,1,1,4};
        assertTrue(CheckControl.checkReach(move1, game.getBoard()));
        assertFalse(CheckControl.checkReach(move2, game.getBoard()));

    }
    /**
     * Test for generateReachableSpaces
     */
    @Test
    public void generateReachableSpacesTest(){
        Board board = new Board();
        boolean[][] a=CheckControl.generateReachableSpaces(board,1,1);
        boolean c = a[2][1];
        assertTrue(c);
    }

}
