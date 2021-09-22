package chess.model;
/**
 * Square Model Class that represents a Square
 */
public class Square {
    @SuppressWarnings("UnusedPrivateField")
    //This Variable is used in the constuctors, and will be important later
    private boolean colour;
    private Figure figure;
    private boolean empty;

    /**
     * Creates a new Square Instance with one of the two Colours and a Figure.
     * @param colour One of the two Colours in Boolean Form. White: True ; Black: False
     * @param figure Any desired Figure that stands on this Square
     */
    public Square(boolean colour, Figure figure){
        this.colour=colour;
        this.figure=figure;
        this.empty = false;
    }

    /**
     * Constructs a copy of a square
     * @param square the square to be copied
     */
    public Square(Square square){
        this.colour = square.colour;
        this.figure = square.figure;
        this.empty = square.empty;
    }
    /**
     * Creates a empty Square Instance with Colour White
     *
     */
    public Square(){
        this.empty=true;
        this.figure=null;
        this.colour=true;
    }


    /**
     * Returns the Symbol of the Figure on the Square or blank for an empty Square
     * @return Returns the specific String Code for the Figure Symbol or blank for an empty Square
     */
    public String print(){
        if (!empty) {
            return figure.print();
        }else {
            return "  ";
        }
    }

    /**
     * Checks if the Square is empty
     * @return Returns true for an empty Square
     */
    public boolean isEmpty(){
        return empty;
    }

    /**
     * Checks the Figure on the Square
     * @return Returns the Figure on the Square
     */
    public Figure getFigure(){
        return figure;
    }

    /**
     * Removes the Figure on the Square
     */
    public void removeFigure(){

        this.figure= null;
        this.empty = true;
    }

    /**
     * Sets a new Figure on the Square
     * @param figure Any desired Figure that shall stand on this Square
     */
    public void setFigure(Figure figure){
        this.figure=figure;
        if(figure == null) {
            this.empty = true;
        } else {
            this.empty = false;
        }

    }

    /**
     * Returns the Colour of the Figure on the Square
     * @return Returns the Colour. White: True ; Black: False
     */
    public boolean isFigureColour(){
            return this.figure.isColour();

    }

    /**
     * Checks if the Move of the Figure on the Square is valid
     * @param order The complete Move expressed in an Array Form
     * @return Returns true when the move is valid
     */
    public boolean allowedFigureMove(int[] order){
        return figure.checkMove(order);
    }

    /**
     * Checks the move in threatlist
     * @param tempOrder temporary order
     * @param tempType temporary type
     * @param board the current board
     * @return the
     */
    public boolean threatMove(int[] tempOrder, int tempType,Board board) {
       boolean res = board.squares[tempOrder[1]][tempOrder[0]].allowedFigureMove(tempOrder);
        board.squares[tempOrder[1]][tempOrder[0]].getFigure().setType(tempType);
        return  res;
    }
}
