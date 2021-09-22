package chess.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
//test

/**
 * Tests for the Game class
 */
public class GameTest {
    /**
     * This tes case doesn't make any sense. It is just here as an example.
     */
    boolean player=true;
    Board board;
    Movement movement;
    chess.controller.ConsoleInput ConsoleInput;
    Game game = new Game();

    /**
     * Test for the switching of turns
     */
    @Test
    public void testTurn(){
        game.updateTurn();
        assertFalse(game.player);
    }

    /**
     * Test for allowing the right positions
     */
    @Test
    public void testAllowPosition(){
        int[] oder1 = {4,7,4,2};
        int[] oder2 = {4,7,4,5};
        int[] oder3 = {5,7,5,4};
        Board positionBoard = new Board();
        for (int j = 0; j < 8; j++) {
            for (int i = 0; i < 8; i++) {

                positionBoard.squares[i][j].removeFigure();
            }
        }
        Rook rook1 = new Rook(true);
        positionBoard.squares[7][4].setFigure(rook1);
        assertFalse(game.allowedPosition(oder3, true,positionBoard));
        assertTrue(game.allowedPosition(oder1, true,positionBoard));
        Rook rook2 = new Rook(false);
        positionBoard.squares[5][4].setFigure(rook2);
        assertTrue(game.allowedPosition(oder2, true,positionBoard));
        positionBoard.squares[5][4].removeFigure();
        Rook rook3 = new Rook(true);
        positionBoard.squares[5][4].setFigure(rook3);
        assertFalse(game.allowedPosition(oder2, true,positionBoard));
    }

    /**
     * Test whether a move is allowed
     */
    @Test
    public void testAllowMove(){
        Board moveBoard = new Board();
        int[] oder1 = {1,1,1,3};
        int[] oder2 = {0,1,1,5};
        assertTrue(game.allowedMove(oder1,moveBoard));
        assertFalse(game.allowedMove(oder2,moveBoard));
    }
    @Test
    public void runGame(){

        //this test runGame need long time to wait
        //game.runGame();
        game.print();
    }

    /**
     * Tests the resets for the EnPassant variables in the pawns
     */
    @Test
    public void resetEnPassant(){
        int[] oder1 = {1,6,3,1};
        game.resetEnPassant(oder1);
    }
    /**
     * Tests a player can "escape" from chess
     */
    @Test
    public void canRelease(){
        assertTrue(game.canRelease(false));
        assertTrue(game.canRelease(true));
    }
    /**
     * Tests move on board
     */
    @Test
    public void executeMove(){
        Board positionBoard = new Board();
        for (int j = 0; j < 8; j++) {
            for (int i = 0; i < 8; i++) {

                positionBoard.squares[i][j].removeFigure();
            }
        }
        Pawn pawnWhite = new Pawn(true);
        Pawn pawnBlack = new Pawn(false);

        positionBoard.squares[4][7].setFigure(pawnWhite);
        positionBoard.squares[3][6].setFigure(pawnBlack);
        int[] pawnWhiteMove = {7,4,6,3};
        game.executeMove(pawnWhiteMove);
    }
    /**
     * Tests move on board
     */
    @Test
    public void gameExecution(){

    }
}
