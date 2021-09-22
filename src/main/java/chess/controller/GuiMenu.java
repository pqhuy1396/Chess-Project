package chess.controller;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.util.Pair;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;

/**
 * The Gui Class for the Menu
 */
public class GuiMenu extends GuiControl {
    @FXML
    AnchorPane base;
    static AnchorPane baseC;
    @FXML
    private Button findGameBtn;
    @FXML
    private Button hvhBtn;
    @FXML
    private Button hvaBtn;
    @FXML
    private Button hostGameBtn;
    @FXML
    Label title;
    @FXML
    Button blackButton;
    @FXML
    Button whiteButton;




    @FXML
    void hvhGame() throws IOException {
        baseC = base;
        GameSetup.guiSetupHuman(this);
        VBox box = FXMLLoader.load(getClass().getResource("/Chessboard(1).fxml"), ResourceBundle.getBundle("/language", locale));
        base.getChildren().setAll(box);
    }

    @FXML
    void hvaGame() {
        choiceWB.disableProperty().set(false);
        choiceWB.visibleProperty().set(true);
    }

    private void playDecision(){
        List<String> choices = new ArrayList<>();
        choices.add("0");
        choices.add("1");
        choices.add("2");
        choices.add("3");
        choices.add("4");

        ChoiceDialog<String> dialog = new ChoiceDialog<>("0", choices);
        dialog.setTitle("");
        dialog.setHeaderText("Ai depth");
        dialog.setContentText("avoid 4 and 3");
        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()){
            String answer =result.get();
            int depth = Integer.parseInt(answer);
            GuiVar.setDepth(depth);
        }
        GuiVar.setGameAi(true);
    }
    @FXML
    void playWhite() throws IOException {
        playDecision();
        GuiVar.setPlayerChoice(true);
        baseC = base;
        GameSetup.guiSetupAi(this, true);
        VBox box = FXMLLoader.load(getClass().getResource("/Chessboard(1).fxml"), ResourceBundle.getBundle("/language", locale));
        base.getChildren().setAll(box);
    }

    @FXML
    void playBlack() throws IOException {
        playDecision();
        GuiVar.setPlayerChoice(false);
        baseC = base;
        GameSetup.guiSetupAi(this, false);
        VBox box = FXMLLoader.load(getClass().getResource("/Chessboard(1).fxml"), ResourceBundle.getBundle("/language", locale));
        base.getChildren().setAll(box);
    }
    @FXML
    void hvhGameOnline(boolean canControlPlayer, DataInputStream in, DataOutputStream out) throws IOException {
        baseC = base;
        GameSetup.guiSetupHumanOnline(this, canControlPlayer, in, out);
        VBox box = FXMLLoader.load(getClass().getResource("/Chessboard(1).fxml"));
        base.getChildren().setAll(box);
        board = (GridPane) ((AnchorPane) ((SplitPane) box.getChildren().get(0)).getItems().get(0)).getChildren().get(0);
        history = (ListView) (((AnchorPane) (((Tab) ((TabPane) ((AnchorPane) ((SplitPane) box.getChildren().get(0)).getItems().get(1)).getChildren().get(1)).getTabs().get(1)).getContent())).getChildren().get(0));
        beaten = (ListView) (((AnchorPane)(((ScrollPane) ((Tab) ((TabPane) ((AnchorPane) ((SplitPane) box.getChildren().get(0)).getItems().get(1)).getChildren().get(1)).getTabs().get(0)).getContent()).getContent())).getChildren().get(0));
    }

    @FXML
    void hostGame() {
        String port = null;
        try {
            port = showInputHostGame();
            serverSocket = new ServerSocket(Integer.parseInt(port));
            showAlertWithoutHeaderText("Hosted a new game on port: " + port);

            findGameBtn.setDisable(true);
            hvhBtn.setDisable(true);
            hvaBtn.setDisable(true);
            hostGameBtn.setText("Stop host (port: " + port + ")");
            hostGameBtn.setOnAction(actionEvent -> stopHost());

            new Thread(() -> {
                Socket connectionSocket = null;
                try {
                    connectionSocket = serverSocket.accept();
                    in = new DataInputStream(connectionSocket.getInputStream());
                    out = new DataOutputStream(connectionSocket.getOutputStream());
                    Platform.runLater(() -> {
                        try {
                            hvhGameOnline(true, in, out);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    });

                    while (true) {
                        System.out.println("Listening move: " + in);
                        String move = in.readUTF();
                        System.out.println("RECEIVED MOVE: " + move);
                        Platform.runLater(() -> {
                            if(move.equals("undo")) {

                                    doUndoSimple();

                            } else if(move.equals("redo")) {

                                    doRedoSimple();

                            } else if(move.startsWith("redoHistory")) {
                                int index = Integer.parseInt(move.substring(12).trim());
                                System.out.println("Undo history index: " + index);
                                doUndoHistory(index);
                            } else {
                                buttonPressedWithInput(move);
                            }
                        });
                    }
                } catch (IOException ignored) {
                }
            }).start();

        } catch (Exception e) {
            showAlertWithoutHeaderText("Cannot host game on port: " + port + ". Please try a different port(port only runs with number sequence)");
        }
    }

    @FXML
    void findGame() {
        try {
            Pair<String, String> inputs = showInputFindGame();
            clientSocket = new Socket(inputs.getKey(), Integer.parseInt(inputs.getValue()));
            in = new DataInputStream(clientSocket.getInputStream());
            out = new DataOutputStream(clientSocket.getOutputStream());

            hvhGameOnline(false, in, out);

            new Thread(() -> {
                try {
                    while (true) {
                        System.out.println("Listening move: " + in);
                        String move = in.readUTF();
                        System.out.println("RECEIVED MOVE: " + move);
                        Platform.runLater(() -> {
                            if(move.equals("undo")) {
                                    doUndoSimple();
                            } else if(move.equals("redo")) {
                                    doRedoSimple();
                            } else if(move.startsWith("redoHistory")) {
                                int index = Integer.parseInt(move.substring(12).trim());
                                System.out.println("Undo history index: " + index);
                                doUndoHistory(index);
                            } else {
                                buttonPressedWithInput(move);
                            }
                        });
                    }
                } catch (IOException e) {
                    System.exit(0);
                }
            }).start();

        } catch (IOException e) {
            showAlertWithoutHeaderText("Cannot find any game");
        }
    }

    private void networkGame(){

    }
    private void showAlertWithoutHeaderText(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Alert");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
    void stopHost() {
        if (serverSocket != null && serverSocket.isBound()) {
            try {
                serverSocket.close();
                findGameBtn.setDisable(false);
                hvhBtn.setDisable(false);
                hvaBtn.setDisable(false);
                hostGameBtn.setText("Host game");
                hostGameBtn.setOnAction(actionEvent -> hostGame());
            } catch (IOException e) {
                showAlertWithoutHeaderText(e.getMessage());
            }
        }
    }


    /**
     * Gette for BaseC
     * @return The current Value
     */
    public static AnchorPane getBaseC() {
        return baseC;
    }



    /**
     * The option to change Language
     */
    public void changeLanguage(){
        if(locale.getLanguage() == "eng_uk"){
            locale = new Locale("deu_de");
        }else{
            locale = new Locale("eng_uk");
        }
        ResourceBundle bundle = ResourceBundle.getBundle("/language", locale);

        hvaBtn.setText(bundle.getString("human.v.ai"));
        hvhBtn.setText(bundle.getString("human.v.human"));
        title.setText(bundle.getString("title"));
        hostGameBtn.setText(bundle.getString("HostGameBtn"));
        findGameBtn.setText(bundle.getString("FindGameBtn"));
        blackButton.setText(bundle.getString("blackBtn"));
        whiteButton.setText(bundle.getString("whiteBtn"));
    }

}
