package chess.controller;

import javafx.scene.control.ButtonBar;

/**
 * Class that holds necessary Variables for the Gui
 */
public class GuiVar extends GuiControl {
    static boolean rotation = false;
    static boolean freeFigure = true;
    static boolean displayCheck = false;
    static boolean possibleMovesDisplay = true;
    static boolean pawnCheck = false;
    static ButtonBar buttonBar;
    static String ascString;
    static boolean GameAi = false;
    static boolean playerChoice = true;
    static boolean firstMove = true;
    static int depth =1;


    /**
     * Getter for the Depth
     * @return The current Depth
     */
    public static int getDepth(){
        return depth;
    }

    /**
     * Setter for the Depth
     * @param newDepth The new Depth
     */
    public static void setDepth(int newDepth){
        depth = newDepth;
    }

    /**
     * Getter for Rotation
     * @return The current Value
     */
    public static boolean isRotation() {
        return rotation;
    }

    /**
     * Setter for Rotation
     * @param rotation The new Value
     */
    public static void setRotation(boolean rotation) {
        GuiVar.rotation = rotation;
    }

    /**
     * Getter for FreeFigure
     * @return The current Value
     */
    public static boolean isFreeFigure() {
        return freeFigure;
    }

    /**
     * Setter for FreeFigure
     * @param freeFigure The new Value
     */
    public static void setFreeFigure(boolean freeFigure) {
        GuiVar.freeFigure = freeFigure;
    }

    /**
     * Getter for DisplayCheck
     * @return The current Value
     */
    public static boolean isDisplayCheck() {
        return displayCheck;
    }

    /**
     * Setter for DisplayCheck
     * @param displayCheck The new Value
     */
    public static void setDisplayCheck(boolean displayCheck) {
        GuiVar.displayCheck = displayCheck;
    }

    /**
     * Getter for PossibleMovesDisplay
     * @return The current Value
     */
    public static boolean isPossibleMovesDisplay() {
        return possibleMovesDisplay;
    }

    /**
     * Setter for PossibleMovesDisplay
     * @param possibleMovesDisplay The new Value
     */
    public static void setPossibleMovesDisplay(boolean possibleMovesDisplay) {
        GuiVar.possibleMovesDisplay = possibleMovesDisplay;
    }

    /**
     * Getter for PawnCheck
     * @return The current Value
     */
    public static boolean isPawnCheck() {
        return pawnCheck;
    }

    /**
     * Setter for PawnCheck
     * @param pawnCheck The new Value
     */
    public static void setPawnCheck(boolean pawnCheck) {
        GuiVar.pawnCheck = pawnCheck;
    }

    /**
     * Getter for ButtonBar
     * @return The current Value
     */
    public static ButtonBar getButtonBar() {
        return buttonBar;
    }

    /**
     * Setter for ButtonBar
     * @param buttonBar The new Value
     */
    public static void setButtonBar(ButtonBar buttonBar) {
        GuiVar.buttonBar = buttonBar;
    }

    /**
     * Getter for AscString
     * @return The current Value
     */
    public static String getAscString() {
        return ascString;
    }

    /**
     * Setter for AscString
     * @param ascString The new Value
     */
    public static void setAscString(String ascString) {
        GuiVar.ascString = ascString;
    }

    /**
     * Getter for GameAi
     * @return The current Value
     */
    public static boolean isGameAi() {
        return GameAi;
    }

    /**
     * Setter for GameAi
     * @param gameAi The new Value
     */
    public static void setGameAi(boolean gameAi) {
        GameAi = gameAi;
    }

    /**
     * Getter for PlayerChoice
     * @return The current Value
     */
    public static boolean isPlayerChoice() {
        return playerChoice;
    }

    /**
     * Setter for PlayerChoice
     * @param playerChoice The  new Value
     */
    public static void setPlayerChoice(boolean playerChoice) {
        GuiVar.playerChoice = playerChoice;
    }

    /**
     * Getter for FirstMove
     * @return The current Value
     */
    public static boolean isFirstMove() {
        return firstMove;
    }

    /**
     * Setter for FirstMove
     * @param firstMove The new Value
     */
    public static void setFirstMove(boolean firstMove) {
        GuiVar.firstMove = firstMove;
    }
}
