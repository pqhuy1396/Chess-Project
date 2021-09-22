package chess.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BoardTest {
    Board board = new Board();
    Board boardCopy = new Board(board);
    @Test
    public void print() {
        boardCopy.print();
    }

    @Test
    public void pawnAscension() {
        board.pawnAscension(board,"q");
    }

    @Test
    public void pawnAscensionBlack() {

        board.pawnAscensionBlack(board,"a7-a5q",0);
        board.pawnAscensionBlack(board,"",0);
    }

    @Test
    public void pawnAscensionWhite() {
        board.pawnAscensionWhite(board,"a0-a5Q",0);
        board.pawnAscensionWhite(board,"",0);
    }
}