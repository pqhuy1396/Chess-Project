package chess.view;

import chess.model.Board;

/**
 * The Class used when the Gui is used.
 */
public class GuiView implements Views{

    /**
     * Empty print Method because the View for Gui is implemented through fxml
     * @param board the board to be displayed
     */
    @Override
    public void print(Board board) {

    }

    /**
     * Empty because Gui View is in fxml
     * @param s
     */
    @Override
   public  void printMessage(String s) {

    }
}
