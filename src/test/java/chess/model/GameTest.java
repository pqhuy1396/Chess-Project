package chess.model;


import chess.controller.ConsoleInput;
import chess.controller.Scanner;
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
    ConsoleInput input = new ConsoleInput();
    HumanPlayer player1 = new HumanPlayer(input);
    HumanPlayer player2 = new HumanPlayer(input);
    Game game = new Game(player1,player2);

    /**
     * Test for the switching of turns
     */
    @Test
    public void testTurn(){
        game.getPlayer1();
        game.getPlayer2();
        assertTrue(game.player);
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
        assertFalse(Scanner.allowedPosition(oder3, true,positionBoard));
        assertTrue(Scanner.allowedPosition(oder1, true,positionBoard));
        Rook rook2 = new Rook(false);
        positionBoard.squares[5][4].setFigure(rook2);
        assertTrue(Scanner.allowedPosition(oder2, true,positionBoard));
        positionBoard.squares[5][4].removeFigure();
        Rook rook3 = new Rook(true);
        positionBoard.squares[5][4].setFigure(rook3);
        assertFalse(Scanner.allowedPosition(oder2, true,positionBoard));
    }

    /**
     * Test whether a move is allowed
     */
    @Test
    public void testAllowedMove(){
        Board moveBoard = new Board();
        int[] oder1 = {1,1,1,3};
        int[] oder2 = {0,1,1,5};
        assertTrue(game.allowedMove(oder1,moveBoard));
        assertFalse(game.allowedMove(oder2,moveBoard));
    }



    /**
     * Tests the resets for the EnPassant variables in the pawns 20!
     */
    @Test
    public void resetEnPassant(){

        Board board = new Board();
        board.getSquare(3,1).setFigure(new Pawn(true));
        board.getSquare(3,1).getFigure().setType(20);
        int[] oder1 = {0,0,1,1};
        game.resetEnPassant(oder1);
        assertFalse(board.getSquare(3,1).isEmpty());
        assertEquals( board.getSquare(3,1).getFigure().getType(),20);
    }

    /**
     * Tests whether getBoard works
     */
    @Test
    public void getBoardTest(){
        ConsoleInput input = new ConsoleInput();
        HumanPlayer player1 = new HumanPlayer(input);
        HumanPlayer player2 = new HumanPlayer(input);
        Game game = new Game(player1,player2);
        assertFalse(game.getBoard().getSquare(0,0).isEmpty());
        assertTrue(game.getBoard().getSquare(4,4).isEmpty());
    }



    /**
     * Tests whether isTurn works
     */
    @Test
    public void isTurnTest(){
        assertTrue(game.isTurn());
        game.updateTurn();
        assertFalse(game.isTurn());
    }

}
