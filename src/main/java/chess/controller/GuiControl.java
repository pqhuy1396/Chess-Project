package chess.controller;


import chess.model.Board;
import chess.model.Figure;

import javafx.animation.RotateTransition;
import javafx.application.Application;

import javafx.collections.ObservableList;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.util.Pair;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;


import java.net.ServerSocket;
import java.net.Socket;
import java.util.Arrays;
import java.util.Optional;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Class for displaying the game in Gui
 */
public class GuiControl extends Application implements Input {

    static String firstPos;
    static String fullInput;
    static GameControls controls;
    public GridPane board;

    static Locale locale = Locale.getDefault();


    public ListView history;

    public ListView beaten;


    public TitledPane menu;
    Socket clientSocket;
    ServerSocket serverSocket;
    DataInputStream in;
    DataOutputStream out;



    @FXML
    GridPane choiceWB;
    @FXML
    VBox box;

    @FXML
    Tab historyTab;
    @FXML
    Tab beatenTab;
    @FXML
    CheckBox rotate;
    @FXML
    CheckBox checkDisplay;
    @FXML
    CheckBox displayPossible;
    @FXML
    CheckBox otherFigure;
    @FXML
    Button exitButton;
    @FXML
    Button undo;
    @FXML
    Button redo;


    public void setControls(GameControls controls) {
        this.controls = controls;
    }


    /**
     * Launches the Gui
     */
    public static void activateGui() {

        launch();

    }


    @Override
    public void start(Stage stage) {

        try {
            locale = new Locale("eng_uk");
            ResourceBundle bundle = ResourceBundle.getBundle("/language", locale);
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Menu.fxml"),bundle);
            Parent root = (Parent) fxmlLoader.load();
            Scene scene = new Scene(root, 755, 605);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

@FXML
    void buttonLastRowPressed(MouseEvent event) throws IOException {
    String input = event.getSource().toString();
    char inpRes1 = input.charAt(13);
    char inpRes2 = input.charAt(14);
    if(firstPos != null) {
        int[] pos = new int[2];
        pos[0] = InputHandler.convertX(firstPos.charAt(0));
        pos[1] = InputHandler.convertY(firstPos.charAt(1));

        int[] ar = new int[2];
        ar[0] = InputHandler.convertX(inpRes1);
        ar[1] = InputHandler.convertY(inpRes2);
        if (ar[1] == 0 && controls.getGame().getBoard().getSquare(pos[1], pos[0]).getFigure().getType() == 0 &&controls.getGame().getBoard().getSquare(ar[1], ar[0]).isEmpty()){
            ButtonBar buttonBar = FXMLLoader.load(getClass().getResource("/whiteButtonbar.fxml"));
            GuiVar.setButtonBar(buttonBar);
            GuiButtonBar.setBoardAsc(board);
            GuiButtonBar.setHistoryAsc(history);
            GuiButtonBar.setBeatenAsc(beaten);
            GuiButtonBar.setMenuAsc(menu);
            board.add(buttonBar, 0, 0);
            GuiButtonBar.setPawnAsc(event);

        } else {
           buttonPressed(event);
        }
    }  else {
        buttonPressed(event);
    }

    }
    /**
     * pressed button load with input
     */
    void buttonPressedWithInput(String fullInput) {
        pawnChecker();
        System.out.println(fullInput);
        String histInput = fullInput;
        //  System.out.println(fullInput);
        int[] ar = InputHandler.convertCommand(fullInput);
        //  pawnAscension(ar);
        Figure pre;
        if (!controls.getGame().getBoard().getSquare(ar[3], ar[2]).isEmpty()) {

            pre = controls.getGame().getBoard().getSquare(ar[3], ar[2]).getFigure();

        } else {
            pre = null;
        }
        controls.runGame(fullInput,false);
        Figure post = controls.getGame().getBoard().getSquare(ar[3], ar[2]).getFigure();

        if(post != pre) {
            newPrint(controls.getGame().getBoard(),histInput, pre);
            aiGame();
        }
        if(GuiVar.isFreeFigure()) {
            firstPos = null;
            GuiMatchInfo guiMatchInfo = new GuiMatchInfo();
            guiMatchInfo.resetMarkings(board);
        }
    }

    String showInputHostGame() {
        Dialog<String> dialog = new Dialog<>();
        dialog.setTitle("Host Game");
        dialog.setHeaderText("Enter port to host a new game");
        dialog.setResizable(true);
        Label label = new Label("Port: ");
        TextField text = new TextField();
        text.setText("6543");
        GridPane grid = new GridPane();
        grid.add(label, 1, 1);
        grid.add(text, 2, 1);
        grid.setHgap(10);
        grid.setVgap(10);
        dialog.getDialogPane().setContent(grid);
        ButtonType buttonTypeOk = new ButtonType("Okay", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().add(buttonTypeOk);
        dialog.setResultConverter(b -> {
            if (b == buttonTypeOk) {
                return text.getText();
            }
            return null;
        });
        Optional<String> result = dialog.showAndWait();
        return result.orElse(null);
    }

    Pair<String, String> showInputFindGame() {
        Dialog<Pair<String, String>> dialog = new Dialog<>();
        dialog.setTitle("Find Game");
        dialog.setHeaderText("Enter host and port to connect");
        dialog.setResizable(true);
        Label label1 = new Label("Host: ");
        Label label2 = new Label("Port: ");
        TextField text1 = new TextField();
        TextField text2 = new TextField();
        text1.setText("127.0.0.1");
        text2.setText("6543");
        GridPane grid = new GridPane();
        grid.add(label1, 1, 1);
        grid.add(text1, 2, 1);
        grid.add(label2, 1, 2);
        grid.add(text2, 2, 2);
        grid.setHgap(10);
        grid.setVgap(10);
        dialog.getDialogPane().setContent(grid);
        ButtonType buttonTypeOk = new ButtonType("Okay", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().add(buttonTypeOk);
        dialog.setResultConverter(b -> {
            if (b == buttonTypeOk) {
                return new Pair<>(text1.getText(), text2.getText());
            }
            return null;
        });
        Optional<Pair<String, String>> result = dialog.showAndWait();
        return result.orElse(null);
    }



    void doUndoSimple() {
        fullInput = "undo";
        controls.runGame(fullInput, false);
        newPrint(controls.game.getBoard(),"",null);
    }

    @FXML
 void undoSimple() throws IOException {
       controls.sendUndo();
        doUndoSimple();
 }

 void doUndoHistory(int i) {
     controls.setBoardByHistoryAndIndex(i);
     newPrint(controls.game.getBoard(),"",null);
 }

@FXML
 void undoHistory() throws IOException {
    int i = history.getSelectionModel().getSelectedIndex();
    controls.sendUndoHistory(i);
    doUndoHistory(i);
 }

 void doRedoSimple() {
     fullInput = "redo";
     controls.runGame(fullInput, false);
     newPrint(controls.game.getBoard(),"",null);
 }

 @FXML
 void redoSimple() throws IOException {
        controls.sendRedo();
     doRedoSimple();
 }
@FXML
     void buttonPressed(MouseEvent event) {
        if(!GuiVar.isPlayerChoice() && GuiVar.isFirstMove()) {
            board.rotateProperty().set(180);
            aiGame();
            GuiVar.setFirstMove(false);
        }
       String input = event.getSource().toString();
       StackPane stackPane =(StackPane) event.getSource();
      // System.out.println(input);
       char inpRes1 = input.charAt(13);
      // System.out.println(inpRes1);
       char inpRes2 = input.charAt(14);
       String Res =  inpRes1 + Character.toString(inpRes2);
       int[] pos = new int[2];
       pos[0] = InputHandler.convertX(inpRes1);
       pos[1] = InputHandler.convertY(inpRes2);
       boolean[][] falseField = new boolean[8][8];
       for (boolean[] falseArray : falseField) {
           Arrays.fill(falseArray, false);
       }
       if (firstPos == null && !stackPane.getChildren().isEmpty() && stackPane.getChildren().get(0).getClass() == ImageView.class && controls.getGame().getBoard().getSquare(pos[1], pos[0]).getFigure().isColour() == controls.getGame().isTurn()
            && (controls.getGame().isTurn() && controls.getGame().isCanControlFirstPlayer() || !controls.getGame().isTurn() && controls.getGame().isCanControlSecondPlayer())  && !Arrays.deepEquals(CheckControl.generateReachableSpaces(controls.getGame().getBoard(), pos[0], pos[1]), falseField))
       {
          firstPos = Res;
          GuiMatchInfo guiMatchInfo = new GuiMatchInfo();
          guiMatchInfo.showPossibleMoves(firstPos,board);
      } else if(firstPos != null) {
          secondButtonPressed(Res);
      }
    }
    void secondButtonPressed(String in) {
        String Res;
        Res = firstPos + "-" + in;

        fullInput = Res;
        pawnChecker();
        String histInput = fullInput;
        //  System.out.println(fullInput);
        int[] ar = InputHandler.convertCommand(fullInput);
        //  pawnAscension(ar);
        Figure pre;
        if (!controls.getGame().getBoard().getSquare(ar[3],ar[2]).isEmpty())  {

            pre = controls.getGame().getBoard().getSquare(ar[3],ar[2]).getFigure();

        } else {
            pre = null;
        }
        controls.runGame(null, true);
        Figure post = controls.getGame().getBoard().getSquare(ar[3],ar[2]).getFigure();
        if(post != pre) {
            newPrint(controls.getGame().getBoard(),histInput, pre);
            aiGame();
            firstPos = null;
        }



        if(GuiVar.isFreeFigure()) {
            firstPos = null;
            GuiMatchInfo guiMatchInfo = new GuiMatchInfo();
            guiMatchInfo.resetMarkings(board);
        }
    }
    void pawnChecker() {
        if(GuiVar.isPawnCheck()){
            fullInput = fullInput + GuiVar.getAscString();
            GuiVar.setPawnCheck(false);


        }
    }

@FXML
    void aiGame() {
        if(GuiVar.isGameAi()) {

            String move;
            if(GuiVar.isPlayerChoice()) {
                move = controls.getGame().getPlayer2().getNextMove(controls.getGame());
            } else {
                 move = controls.getGame().getPlayer1().getNextMove(controls.getGame());
            }

            String histInput = move;
            int[] ar = InputHandler.convertCommand(move);
            Figure pre = controls.getGame().getBoard().getSquare(ar[3],ar[2]).getFigure();
            controls.runGame(null, true);
            newPrint(controls.getGame().getBoard(), histInput , pre);




        }
    }




    void newPrint(Board modelBoard, String histInput , Figure pre) {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                StackPane firstStack = (StackPane) getNodeByRowColumnIndex(j, i, board);
                ImageView child = (ImageView) firstStack.getChildren().get(0);
                if (!modelBoard.getSquare(j, i).isEmpty()) {
                    child.setImage(new Image(getClass().getResource("/images/" + modelBoard.getSquare(j,i).getFigure().getValue() + modelBoard.getSquare(j,i).getFigure().isColour() + ".png").toExternalForm()));

                }
                else {
                    child.setImage(null);
                }


            }
        }
        GuiMatchInfo guiMatchInfo = new GuiMatchInfo();
        guiMatchInfo.updateHistory(histInput, history);
        guiMatchInfo.resetMarkings(board);

           guiMatchInfo.updateBeatenFigures(beaten);

        if(GuiVar.isRotation()) {
            for(int i = 0 ; i < 8; i++ ) {
                for (int j = 0 ; j< 8; j++) {
                   StackPane stack = (StackPane) getNodeByRowColumnIndex(i,j,board);
                   stack.rotateProperty().set(stack.rotateProperty().get()+180);
                }
            }
            RotateTransition rt = new RotateTransition(Duration.millis(1),board);
            rt.setByAngle(180);
            rt.play();
        }
    }



    @FXML
    void exitGame() throws IOException {
        AnchorPane pane =  FXMLLoader.load(getClass().getResource("/Menu.fxml") ,ResourceBundle.getBundle("/language", locale));
        GuiMenu.getBaseC().getChildren().setAll(pane);
        GuiVar.setGameAi(false);
        GuiVar.setRotation(false);
        GuiVar.setPossibleMovesDisplay(true);
        GuiVar.setFreeFigure(true);
        GuiVar.setDisplayCheck(false);
    }











    /**
     * Displays or hides the settings
     */
    public void menuCall() {
        if(menu.visibleProperty().get()) {
            menu.visibleProperty().set(false);
            menu.disableProperty().set(true);
        } else {
            menu.visibleProperty().set(true);
            menu.disableProperty().set(false);
        }
    }


    /**
     * Toggles whether the board rotates
     */
    public void rotationToggle() {
      GuiVar.setRotation(!GuiVar.isRotation());
    }

    /**
     * Toggles whether one can choose another figure after clicking on one
     */
    public void freeFigureToggle() {
        GuiVar.setFreeFigure(!GuiVar.isFreeFigure());
    }

    /**
     * Toggles whether check is displayed
     */
    public void displayCheckToggle() {
       GuiVar.setDisplayCheck(!GuiVar.isDisplayCheck());
    }

    /**
     * Toggles whether the possible moves are shown
     */
    public void possibleMovesToggle(){
       GuiVar.setPossibleMovesDisplay(!GuiVar.isPossibleMovesDisplay());
    }




    /**
     * Returns the Node with the use of the row and column numbers
     * @param row The row of the figure
     * @param column The column of the figure
     * @param gridPane The gridPane
     * @return The node of the figure
     */
    public Node getNodeByRowColumnIndex (final Integer row, final Integer column, GridPane gridPane) {
        //Node result = null;
        ObservableList<Node> childrens = gridPane.getChildren();

        for (Node node : childrens) {
            if(GridPane.getRowIndex(node) == row && GridPane.getColumnIndex(node) == column) {
                return node;
            }
        }

        return null;
    }

    /**
     * returns the input as a String
     * @return The input in String form
     */
    @Override
    public String listenInput() {
        String input;
        input = fullInput;
        fullInput = null;
        return input;

    }

    /**
     * Changes the Language
     * @throws  IOException check exception changeLanguage
     */
    public void changeLanguage() throws IOException {

        if(locale.getLanguage() == "eng_uk"){
            locale = new Locale("deu_de");
        }else{
            locale = new Locale("eng_uk");
        }
        ResourceBundle bundle = ResourceBundle.getBundle("/language", locale);

        GuiLanguage.changeHistoryLanguage(bundle, historyTab);
        GuiLanguage.changeBeatenLanguage(bundle, beatenTab);
        GuiLanguage.changeSettingsLanguage(bundle, menu);
        GuiLanguage.changeRotateLanguage(bundle, rotate);
        GuiLanguage.changeDisplayPossibleLanguage(bundle, displayPossible);
        GuiLanguage.changeOtherFigureLanguage(bundle, otherFigure);
        GuiLanguage.changeCheckDisplayLanguage(bundle, checkDisplay);
        GuiLanguage.changeExitLanguage(bundle, exitButton);
        GuiLanguage.changeUndoLanguage(bundle, undo);
        GuiLanguage.changeRedoLanguage(bundle, redo);


    }
}