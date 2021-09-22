package chess.controller;

import chess.model.*;
import chess.view.ConsoleView;
import chess.view.Views;
import org.junit.jupiter.api.Test;

import java.util.Locale;
import java.util.ResourceBundle;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests GameControls
 */
public class GameControlsTest {
    Input control = new ConsoleInput();
    ConsoleInput input = new ConsoleInput();
    HumanPlayer player1 = new HumanPlayer(input);
    HumanPlayer player2 = new HumanPlayer(input);

    Game game= new Game(player1,player2);
    Views view = new ConsoleView();

    int[] tempmove = new int[]{1,1,2,2};

    GameControls gameControls = new GameControls( game, view, control);




    /**
     * Tests move on board
     */
    @Test
    public void executeMove(){
        Board positionBoard = new Board();
        for (int j = 0; j < 8; j++) {
            for (int i = 0; i < 8; i++) {

                positionBoard.getSquare(i,j).removeFigure();
            }
        }
        Pawn pawnWhite = new Pawn(true);
        Pawn pawnBlack = new Pawn(false);

        positionBoard.getSquare(4,7).setFigure(pawnWhite);
        positionBoard.getSquare(4,6).setFigure(pawnBlack);
        int[] pawnWhiteMove = {7,4,6,3};
        gameControls.executeMove(pawnWhiteMove,positionBoard);
        assertEquals(positionBoard.getSquare(3,6).getFigure(),pawnWhite);
    }
    /**
     * Tests move on board
     */
    @Test
    public void gameExecution1(){
       int[] move = new int[]{5, 6, 5, 5};
       Figure[] reMove = gameControls.executeMove(move, game.getBoard());
       gameControls.gameExecution(move,reMove,"f2-f3");

       int[] move1 = new int[]{4, 6, 4, 5};
       Figure[] reMove1 = gameControls.executeMove(move1, game.getBoard());
       gameControls.gameExecution(move1,reMove1,"e7-e5");

       int[] move2 = new int[]{6, 6, 6, 4};
       Figure[] reMove2 = gameControls.executeMove(move2, game.getBoard());
       gameControls.gameExecution(move2,reMove2,"g2-g4");

       int[] move3 = new int[]{3, 0, 7, 4};
       Figure[] reMove3 = gameControls.executeMove(move3, game.getBoard());
       gameControls.gameExecution(move3,reMove3,"d8-h4");
       assertTrue(CheckControl.isCheck(true,gameControls.game.getBoard()));
    }
    /**
     * Tests for method undoMove which gets the figures as input which were moved
     */
    @Test
    public void undoMove(){
        Figure[] movedFigure = new Figure[2];
        Queen queen1 = new Queen(true);
        Pawn pawn1 = new Pawn(false);
        movedFigure[0]= queen1;
        movedFigure[1]= pawn1;
        int[] move = new int[]{1,1,2,2};
        gameControls.undoMove(move,movedFigure);
        gameControls.updateKingCount(gameControls.game.getBoard(),move);
        assertEquals(gameControls.game.getBoard().getSquare(1,1).getFigure(),queen1);
        assertEquals(gameControls.game.getBoard().getSquare(2,2).getFigure(),pawn1);
    }

    /**
     * method to check if printBeatenFigures works
     */
    @Test
    public void printBeatenFiguresTest(){
        gameControls.printBeatenFigures();
        Queen queen1 = new Queen(true);
        gameControls.game.getBoard().getBeatenFigures().add(queen1);
        assertTrue(gameControls.game.getBoard().getBeatenFigures().contains(queen1));
    }
    /**
     * method to tie
     */
    @Test
    public void isTie(){
        gameControls.getGame();
        gameControls.getMaxHistoryIndex();
        assertTrue(gameControls.isRun());
        assertFalse(gameControls.isTie(game.getBoard(),true));
        assertFalse(gameControls.isTie(game.getBoard(),false));
    }

    /**
     * Test for undoTest
     */
    @Test
    public void undoTest(){
        boolean a = gameControls.getGame().isTurn();
        gameControls.setMaxHistoryIndex(1);

        gameControls.validRedoSimple();
        boolean b = gameControls.getGame().isTurn();
        assertTrue(b);
        assertTrue(a);

    }
    /**
     * Test for undoTest
     */
    @Test
    public void redTest(){
        boolean a = gameControls.getGame().isTurn();
        gameControls.setMaxHistoryIndex(1);


            GuiVar.setFirstMove(false);
            assertFalse(GuiVar.firstMove);

        gameControls.validUndoSimple();
        boolean b = gameControls.getGame().isTurn();
        assertTrue(a);

    }

    /**
     * test for setMaxHistoryIndex
     */
    @Test
    public void testSetMaxHistoryIndex(){
        gameControls.setMaxHistoryIndex(1);
        assertEquals(gameControls.getMaxHistoryIndex(),1);
        gameControls.setMaxHistoryIndex(2);
        assertEquals(gameControls.getMaxHistoryIndex(),2);
    }

    /**
     * test for setBoardByHistoryIndex
     */
    @Test
    public void setBoardByHistoryIndexTest(){
        Board board = gameControls.getGame().getBoard();
        Board board1 = new Board();
        gameControls.getGame().getHistory().add(new History(board1, tempmove,"aaaa",true));
        gameControls.setBoardByHistoryAndIndex(1);
        assertFalse(board==gameControls.getGame().getBoard());
    }
}