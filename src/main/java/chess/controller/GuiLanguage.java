package chess.controller;

import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Tab;
import javafx.scene.control.TitledPane;


import java.util.ResourceBundle;

/**
 * The Class for the Language in the Gui
 */
public class GuiLanguage extends GuiControl {


    /**
     * The Language Change for the History
     * @param bundle The Ressourcebundle
     * @param historyTab The History Tab
     */
    public static void changeHistoryLanguage(ResourceBundle bundle, Tab historyTab){
        historyTab.setText(bundle.getString("history"));
    }

    /**
     * The Language Change for Beaten
     * @param bundle The Ressourcebundle
     * @param beatenTab The beaten Tab
     */
    public static void changeBeatenLanguage(ResourceBundle bundle, Tab beatenTab){
        beatenTab.setText(bundle.getString("beaten"));
    }

    /**
     * The Language Changer for Settings
     * @param bundle The Ressourcebundle
     * @param menu The Setting Text
     */
    public static void changeSettingsLanguage(ResourceBundle bundle, TitledPane menu){
        menu.setText(bundle.getString("settings"));
    }

    /**
     * The Language Changer for Rotation
     * @param bundle The Ressourcebundle
     * @param rotate The Rotate Option
     */
    public static void changeRotateLanguage(ResourceBundle bundle, CheckBox rotate){
        rotate.setText(bundle.getString("rotate"));
    }

    /**
     * The Language Changer for DisplayPossible
     * @param bundle The Ressourcebundle
     * @param checkDisplay The display Possible option
     */
    public static void changeDisplayPossibleLanguage(ResourceBundle bundle, CheckBox checkDisplay){
        checkDisplay.setText(bundle.getString("display.possible"));
    }

    /**
     * The Language Changer for Other Figure
     * @param bundle The Ressourcebundle
     * @param displayPossible The Other Figure Option
     */
    public static void changeOtherFigureLanguage(ResourceBundle bundle, CheckBox displayPossible){
        displayPossible.setText(bundle.getString("other.figure"));
    }

    /**
     * The Display Check Language Changer
     * @param bundle The Ressourcebundle
     * @param otherFigure The Display Check Option
     */
    public static void changeCheckDisplayLanguage(ResourceBundle bundle, CheckBox otherFigure){
        otherFigure.setText(bundle.getString("check.display"));
    }

    /**
     * The Exit Language Changer
     * @param bundle The Ressourcebundle
     * @param exitButton The Exit button
     */
    public static void changeExitLanguage(ResourceBundle bundle, Button exitButton){
        exitButton.setText(bundle.getString("exit"));
    }

    /**
     * The Undo Language Changer
     * @param bundle The Ressourcebundle
     * @param undo The Undo Button
     */
    public static void changeUndoLanguage(ResourceBundle bundle, Button undo){
        undo.setText(bundle.getString("undo"));
    }

    /**
     * The Redo Language Changer
     * @param bundle The Ressourcebundle
     * @param redo The Redo Button
     */
    public static void changeRedoLanguage(ResourceBundle bundle, Button redo){
        redo.setText(bundle.getString("redo"));
    }


}
