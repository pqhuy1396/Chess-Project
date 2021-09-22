package chess.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

/**
 * test for the class History
 */
public class HistoryTest {
    Board board1 = new Board();
    int[] move = new int[]{0,0,0,0};
    String moveString = "aa-aa";
    boolean turn = false;
    History history = new History(board1,move,moveString,turn);

    /**
     * test for the Board getter
     */
    @Test
    public void testGetBoard(){
        assertEquals(board1,history.getBoard());

    }

    /**
     * test for the move getter
     */
    @Test
    public void testGetMove(){
        assertEquals(move,history.getMove());

    }

    /**
     * test for the String getter
     */
    @Test
    public void testGetMoveString(){
        assertEquals(moveString,history.getMoveAsString());

    }

    /**
     * test for the turn getter
     */
    @Test
    public void testGetTurn(){
        assertEquals(turn,history.isTurn());
        assertFalse(history.isTurn());
    }

}
