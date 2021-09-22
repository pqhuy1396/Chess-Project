package chess.controller;

/**
 * Class which got methods to check the syntax of a command and methods to covert the commands
 */
public class InputHandler {
    /**
     * Checks the syntactic Validity of the Command
     * @param s The command in String Form
     * @return Returns true if the Command is valid.
     */

    public static boolean validCommand(String s) {
        //check move command

        if ((s.length() == 5 || s.length() == 6)&&s.charAt(2) == '-' ) {
            return validMove(s);
        }else{
            return false;
        }
    }

    /**
     * Checks if the Move Positions are Syntactic correct
     * @param s The Move command
     * @return Returns true when the Positions are syntactic correct.
     */
    public static boolean validMove(String s){
        if(s.length() == 5){
            return validPosition(s.charAt(0), s.charAt(1)) && validPosition(s.charAt(3), s.charAt(4));
        }else {
            return validPosition(s.charAt(0),s.charAt(1))&&validPosition(s.charAt(3),s.charAt(4))&&validTransform(s.charAt(5));
        }
    }

    /**
     * Checks the correct syntactic Position of the x and y Position
     * @param x The x Position char
     * @param y The y Position char
     * @return Returns true when the char Positions are valid
     */
    public static boolean validPosition(char x,char y) {
        return validX(x)&&validY(y);

    }

    /**
     * Checks whether the input is "beaten"
     * @param s The input
     * @return Whether the input is "beaten"
     */

    public static boolean validListRequest(String s){
        String comp = "beaten";
        return s.equals(comp);
    }


    /**
     * Checks the correct Syntax of the x Position
     * @param x The x Position char
     * @return Returns true when the char is valid
     */
    public static boolean validX(char x){
        char[] validCharX = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h'};
        for (char charX : validCharX) {
            if (x == charX) {
                return true;
            }
        }
        return false;

    }

    /**
     * Checks the correct Syntax of the y Position
     * @param y The y Position char
     * @return Returns true when the char is valid
     */
    public static boolean validY(char y) {
        char[] validCharY = {'1', '2', '3', '4', '5', '6', '7', '8'};
        for (char c : validCharY) {

            if (y == c)
                return true;


        }
        return false;
    }

    /**
     * Converts the Command into an Array that the Program can use.
     * @param s The entire syntactic correct Command.
     * @return The Command in Array Form.
     */
    public static int[] convertCommand(String s){
        int[] executable = new int[4];
        executable[0]=convertX(s.charAt(0));
        executable[1]=convertY(s.charAt(1));
        executable[2]=convertX(s.charAt(3));
        executable[3]=convertY(s.charAt(4));
        return executable;


    }

    /**
     * Converts the x-Component of the Command from chars into numbers.
     * @param x The x-Component of the Command.
     * @return The corresponding Number of the Char.
     */
    public static int convertX(char x) {
        int value = 0;
        switch (x) {
            case 'a':
                value= 0;
                break;
            case 'b':
                value = 1;
                break;
            case 'c':
                value = 2;
                break;
            case 'd':
                value = 3;
                break;
            case 'e':
                value = 4;
                break;
            case 'f':
                value = 5;
                break;
            case 'g':
                value = 6;
                break;
            case 'h':
                value = 7;
                break;


        }
        return value;

    }

    /**
     * Converts the Number into an Number the Program can use
     * @param y The y-Component of the Command
     * @return The corresponding Number for the Program
     */
    public static int convertY(char y) {
        int value = 0;
        switch (y) {
            case '1':
                value =  7;
                break;
            case '2':
                value =  6;
                break;
            case '3':
                value =  5;
                break;
            case '4':
                value =  4;
                break;
            case '5':
                value =  3;
                break;
            case '6':
                value =  2;
                break;
            case '7':
                value =  1;
                break;
            case '8':
                value =  0;
                break;


        }
        return value;

    }

    /**
     * Checks whether the letter for transforming the pawn is valid
     * @param t the character for transforming the pawn
     * @return Whether the letter for transforming the pawn is valid
     */
    public static boolean validTransform(char t){
        switch (t) {

            case 'Q':
                return true;
            case '\u0000':
                return true;
            case 'N':
                return true;
            case 'R':
                return true;
            case 'B':
                return true;
            default:
                return false;
        }
    }

    /**
     * Validates the Use of the Undo command
     * @param s The command
     * @return Returns true when the command is valid
     */
    public static boolean validUndoRequest(String s) {
        return s.equals("undo");
    }

    /**
     * Validates the Use of the Redo Command
     * @param s The command
     * @return Returns true when the command is valid
     */
    public static boolean validRedoRequest(String s) {
        return s.equals("redo");
    }
}