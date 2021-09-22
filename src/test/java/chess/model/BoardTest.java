package chess.model;

import chess.view.ConsoleView;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test for the board class
 */
public class BoardTest {
    Board board = new Board();
    Board boardCopy = new Board(board);
    ConsoleView view = new ConsoleView();
    /**
     * Test if PawnAscension does not work on blocked board
     */
    @Test
    public void pawnAscension() {
        board.pawnAscension(board,"a2-a1");
        assertEquals(board.getSquare(7,0).getFigure().getType(),10);
    }

    /**
     * Test the ascension for pawns of colour black to queen
     */
    @Test
    public void pawnAscensionBlack() {

        board.pawnAscensionBlack(board,"a2-a1",0);
        assertEquals(board.getSquare(7,0).getFigure().getType(),5);
        board.pawnAscensionBlack(board,"a2-a1N",0);
        assertEquals(board.getSquare(7,0).getFigure().getType(),3);
        board.pawnAscensionBlack(board,"a2-a1R",0);
        assertEquals(board.getSquare(7,0).getFigure().getType(),10);
        board.pawnAscensionBlack(board,"a2-a1B",0);
        assertEquals(board.getSquare(7,0).getFigure().getType(),2);

    }
    /**
     * Test the ascension for pawns of colour white to queen
     */
    @Test
    public void pawnAscensionWhite() {
        board.pawnAscensionWhite(board,"a7-a8",0);
        assertEquals(board.getSquare(0,0).getFigure().getType(),5);
        board.pawnAscensionWhite(board,"a7-a8R",0);
        assertEquals(board.getSquare(0,0).getFigure().getType(),10);
        board.pawnAscensionWhite(board,"a7-a8N",0);
        assertEquals(board.getSquare(0,0).getFigure().getType(),3);
        board.pawnAscensionWhite(board,"a7-a8B",0);
        assertEquals(board.getSquare(0,0).getFigure().getType(),2);
    }

    /**
     * Tests if the setSquare methods works correctly
     */
    @Test
    public void setSquareTest(){
        board.setSquare(0,0,new Queen(false));
        assertFalse(board.getSquare(0,0).getFigure().isColour());
    }
    /**
     * Tests if the beatenFigure list works correctly
     */
    @Test
    public void beatenFigureTest(){
        Queen queen1 = new Queen(true);
        board.getFigureAmount();
        board.beatenFigures.add(queen1);
        assertTrue(board.getBeatenFigures().contains(queen1));
    }
}
