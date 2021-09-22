package chess.controller;

import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TitledPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;


/**
 * Gui Class for the ButtonBar
 */
public class GuiButtonBar extends GuiControl{

    static ListView historyAsc;
    static ListView beatenAsc;
    static TitledPane menuAsc;
    static MouseEvent pawnAsc;
    static GridPane boardAsc;



    @FXML
    void queenAsc()  {
        boardAsc.getChildren().remove(GuiVar.getButtonBar());
        updateVar();
        buttonPressed(pawnAsc);

    }

    @FXML
    void rookAsc() {
        GuiVar.setAscString("R");
        GuiVar.setPawnCheck(true);
        boardAsc.getChildren().remove(GuiVar.getButtonBar());
        updateVar();
        buttonPressed(pawnAsc);

    }

    @FXML
    void bishopAsc() {
        GuiVar.setAscString("B");
        GuiVar.setPawnCheck(true);
        boardAsc.getChildren().remove(GuiVar.getButtonBar());
        updateVar();
        buttonPressed(pawnAsc);

    }

    @FXML
    void knightAsc() {

        GuiVar.setAscString("N");
        GuiVar.setPawnCheck(true);
        boardAsc.getChildren().remove(GuiVar.getButtonBar());
        updateVar();
        buttonPressed(pawnAsc);
    }

    /**
     * Setter for HistoryAsc
     * @param historyAsc The new value
     */
    public static void setHistoryAsc(ListView historyAsc) {
        GuiButtonBar.historyAsc = historyAsc;
    }

    /**
     * Setter for BeatenAsc
     * @param beatenAsc The new value
     */
    public static void setBeatenAsc(ListView beatenAsc) {
        GuiButtonBar.beatenAsc = beatenAsc;
    }

    /**
     * Setter for MenuAsc
     * @param menuAsc The new value
     */
    public static void setMenuAsc(TitledPane menuAsc) {
        GuiButtonBar.menuAsc = menuAsc;
    }

    /**
     * Setter for PawnAsc
     * @param pawnAsc The new value
     */
    public static void setPawnAsc(MouseEvent pawnAsc) {
        GuiButtonBar.pawnAsc = pawnAsc;
    }

    /**
     * Setter for BoardAsc
     * @param boardAsc The new value
     */
    public static void setBoardAsc(GridPane boardAsc) {
        GuiButtonBar.boardAsc = boardAsc;
    }

    void updateVar() {
        super.beaten = beatenAsc;
        super.history = historyAsc;
        super.board = boardAsc;
        super.menu = menuAsc;
    }


}
