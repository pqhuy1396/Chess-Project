package chess.model;

import chess.controller.ConsoleInput;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * class to check methods of ai player
 */
public class AiPlayerTest {
    boolean color = true;
    ConsoleInput input = new ConsoleInput();

    AiPlayer Ai = new AiPlayer();
    HumanPlayer player1 = new HumanPlayer(input);
    Game game = new Game(player1,Ai);

    /**
     * method to convert X in inr ro char
     */
    @Test
    public void convertXtoChar() {
        assertEquals(Ai.convertXtoChar(0),'a');
        assertEquals(Ai.convertXtoChar(2),'c');
        assertEquals(Ai.convertXtoChar(3),'d');
        assertEquals(Ai.convertXtoChar(4),'e');
        assertEquals(Ai.convertXtoChar(8),'x');

    }

    /**
     * method to convert Y in inr ro char
     */
    @Test
    public void convertYtoChar() {
        assertEquals(Ai.convertYtoChar(0),'8');
        assertEquals(Ai.convertYtoChar(3),'5');
        assertEquals(Ai.convertYtoChar(5),'3');
        assertEquals(Ai.convertYtoChar(7),'1');
        assertEquals(Ai.convertYtoChar(8),'x');
    }

    /**
     * method to check the colour of a ai
     */
    @Test
    public void aiMove() {
        Ai.getNextMove(game);
        Ai.calcBestMove(game);
        Ai.setColour(color);
        assertTrue(Ai.isColour());

    }
    /**
     *
     */


    /**
     * method to check the value function
     */
    @Test
    public void minmaxTest(){
        int[] move = Ai.minmax(new Board(),0,-10000,10000,true);
        assertEquals(move[4],0);

    }

    /**
     * test for the calculation of the board
     */
    @Test
    public void testCalcCheckValue(){
        Board board1 = new Board();
        assertEquals(Ai.calcCheckValue(board1,true),0);
        assertEquals(Ai.calcCheckValue(board1,false),0);
        board1.getSquare(1,1).removeFigure();
        assertEquals(Ai.calcCheckValue(board1,true),10);
        assertEquals(Ai.calcCheckValue(board1,false),-10);
    }

}