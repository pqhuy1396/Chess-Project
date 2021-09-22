package chess.controller;

import chess.model.Figure;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.*;
import javafx.scene.effect.InnerShadow;
import javafx.scene.layout.GridPane;



/**
 * Class for the MatchInformations
 */
public class GuiMatchInfo extends GuiControl{

    Alert alert = new Alert(Alert.AlertType.INFORMATION);
    static ObservableList histList = FXCollections.observableArrayList();
    static int HistInt = 0;
    ChoiceDialog<String> dialog = new ChoiceDialog<>();
    static ObservableList beatenList = FXCollections.observableArrayList();
    /**
     * Getter for histList
     * @return The current histList
     */
    public static ObservableList getHistList() {
        return histList;
    }

    /**
     * Getter for HistInt
     * @return The current Value
     */
    public static int getHistInt() {
        return HistInt;
    }

    /**
     * Setter for HistInt
     * @param histInt The new Value
     */
    public static void setHistInt(int histInt) {
        HistInt = histInt;
    }

    /**
     * Method to Update the History of the game (moves and checks and checkmate)
     * @param s the actual executed move
     * @param history the history
     */
    void updateHistory(String s , ListView history) {
        // List<String> choices = createHistoryList();
      //  dialog = new ChoiceDialog<>("00(0)", choices);
        dialog.setTitle("Choice Dialog");
        dialog.setHeaderText("Look, a Choice Dialog");
        dialog.setContentText("Choose your letter:");

        //Optional<String> result = dialog.showAndWait();
        if(s != "") {
            histList.clear();
            {
                //List<String> choices = new ArrayList<>();
                int i = 1 ;
                histList.add("start");
                while (i <= controls.getGame().getHistory().size()-1){



                    histList.add(controls.getGame().getHistory().get(i).getMoveAsString()+"("+i+")");
                    i++;
                }



                history.setItems(histList);
        }

        }


        if (GuiVar.isDisplayCheck() && CheckControl.isCheck(super.controls.getGame().isTurn(), super.controls.getGame().getBoard())) {
            alert.setTitle("Schach");
            alert.setHeaderText(null);
            alert.setContentText("Schach!");
            alert.showAndWait();


        } else if (GuiVar.isDisplayCheck() && super.controls.stopNext) {
            alert.setTitle("Schachmatt");
            alert.setHeaderText(null);
            alert.setContentText("Schachmatt!");
            alert.showAndWait();


            //history.contentTextProperty().setValue(history.getContentText() + " CHECKMATE");


        }
    }



    /**
     * Method to update the list of beatenFigures

     */
    void updateBeatenFigures(ListView beaten) {

        beatenList.clear();

            beatenList.addAll(controls.getGame().getHistory().get(controls.getMaxHistoryIndex()).getBoard().getBeatenHistory());



                beaten.setItems(beatenList);
    }



    /**
     * Method for the white turn
     * @param pre the actual beaten figure
     * @param beaten the list of beaten figures
     */
    void updateBeatenFiguresWhite(Figure pre, TextArea beaten) {
        switch (pre.getType()) {
            case 5:
                beaten.textProperty().setValue(beaten.getText() +" " + "\u2655");
                break;
            case 0:
            case 20:
                beaten.textProperty().setValue(beaten.getText() +" " + "\u2659");
                break;
            case 1:
            case 10:
                beaten.textProperty().setValue(beaten.getText() +" " + "\u2656");
                break;
            case 2:
                beaten.textProperty().setValue(beaten.getText() +" " + "\u2657");
                break;
            case 3:
                beaten.textProperty().setValue(beaten.getText() +" " + "\u2658");

        }
    }

    /**
     * Method for the black turn
     * @param pre the actual beaten figure
     * @param beaten the list of beaten figures
     */
    void updateBeatenFiguresBlack(Figure pre,TextArea beaten) {
        switch (pre.getType()) {
            case 5:
                beaten.textProperty().setValue(beaten.getText() +" " + "\u265B");
                break;
            case 0:
            case 20:
                beaten.textProperty().setValue(beaten.getText() +" " + "\u265F");
                break;
            case 1:
            case 10:
                beaten.textProperty().setValue(beaten.getText() +" " + "\u265C");
                break;
            case 2:
                beaten.textProperty().setValue(beaten.getText() +" " + "\u265D");
                break;
            case 3:
                beaten.textProperty().setValue(beaten.getText() +" " + "\u265E");
                break;

        }
    }

    /**
     * Method that displays the possible moves of the selected Figure
     * @param firstPos The figures position
     */
    void showPossibleMoves(String firstPos, GridPane board) {
        int[] pos = new int[2];
        pos[0] = InputHandler.convertX(firstPos.charAt(0));
        pos[1] = InputHandler.convertY(firstPos.charAt(1));
        if(GuiVar.isPossibleMovesDisplay()) {
            boolean[][] possibleMoves = CheckControl.generateReachableSpaces(controls.getGame().getBoard(), pos[0],pos[1]);
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    if (possibleMoves[j][i]) {
                        getNodeByRowColumnIndex(j, i, board).setEffect(new InnerShadow());
                    }
                }
            }
        }
    }

    /**
     * Resets the marked fields
     * @param board grid pane of board
     */
    public void resetMarkings(GridPane board){
        for (int i = 0;i < 8 ; i++) {
            for(int j = 0 ; j < 8 ; j++) {
                getNodeByRowColumnIndex(j, i, board).setEffect(null);
            }
        }
    }

}
