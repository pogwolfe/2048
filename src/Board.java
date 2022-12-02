/**
 * Authors: Connor Wolfe, Parker Kuchulan, Catherine Stacey
 */
public class Board{
    /**
     * Holds the value of each cube on the game_board
     */
    private LinkedList<LinkedList<Tile>> game_board;
    /**
     * Holds the status of our game
     */
    private int size;

    public Board(){ // default constructor will create a 4x4 game_board of LinkedLists
        this(4);
    }

    /**
     * Board() takes in an int, checks if it is between 4 and 10
     * and then makes a board with the parameter
     * @param size the size of the board
     */
    public Board(int size){ // legal board sizes are between 4 and 10
        this.size = size;
        if(size < 4 || size > 10){
            throw new IllegalArgumentException("Board size must be between 4 and 10");
        } else {
            game_board = new LinkedList<>();

            for(int i = 0; i < size; i++){
                LinkedList<Tile> temp = new LinkedList<Tile>();
                for(int j = 0; j < size; j++){
                    temp.add(null); // add Tiles to LinkedList
                }
                game_board.add(temp); // add LinkedList to game_board
            }
        }
    }

    /** our getsize() will return the size of the board
     * @return the size of the board
     */

    public int getSize(){
        return size;
    }

    /** our hasEmpty() will check to see if any of the tiles on the board have a value
     * @return true if our game_board has available cells, false if otherwise
     */
    public boolean hasEmpty(){
        for(int i = 0; i < game_board.size(); i++) { // traverses through the game_board columns of LinkedLists
            for(int j = 0; j < game_board.size(); j++) { // traverses through each LinkedList row of Nodes
                if(this.getValue(i, j) == -1){
                    return true; // returns true if there's an empty space
                }
            }
        }
        return false;
    }

    /** our getTile() will get the value held by the tile at a specfic value
     * @param row col , numbers specifying where on the board we want the tile
     * @return the tile at the row and col specification
     */
    public Tile getTile(int row, int col){
        if (check(row,col)){ // if our LinkedList doesn't pass the check()

            throw new IllegalArgumentException("either row or Cols was an invalid input");
        }
        else{
            return game_board.get(col).get(row);
        }

    }

    /** our setTile() will set the value held by a tile at a specfic location
     * @param row col ,numbers specifying where on the board we want to set the tile
     * @param t the tile being set
     */
    public void setTile(int row, int col, Tile t){
        if (check(row,col)){

            throw new IllegalArgumentException("either row or Cols was an invalid input");
        }
        else{
            game_board.get(col).set(row, t);
        }

    }

    /** our getVlaue() will return the value held by a specfic tile
     * @param row col , numbers specifying where on the board we want to get the value
     * @return the value at the row and col specification
     */
    public int getValue(int row, int col){
        if (check(row,col)){

            throw new IllegalArgumentException("either row or Cols was an invalid input");
        }
        else{
           if (getTile(row,col) == null){
               return -1;
           }
           else {
               return game_board.get(col).get(row).getTileValue();
           }

        }

    }

    /** our check() will see if the inputed values are valid
     * @param row col , values that are being checked to see if they are within the board size
     * @return true if they are NOT within the board size, false if they are
     */
    private boolean check(int row, int col){ // returns true if values are out of the parameters
        if ((row < 0 || row > this.size) || (col < 0 || col > this.size)){
            return true;
        }
        else{
            return false;
        }

    }

    /**
     * will print the board that is currently being played
     */
    public void printBoard(){
        String boardText = "";

        for(int i = 0; i < game_board.size(); i++){ // Every row
            boardText += "\n";
            for(int j = 0; j < game_board.size(); j++){ // Add each value in row to 'boardText'
                boardText += this.getValue(i, j) + "  ";
            }

        }
        System.out.println(boardText);
    }
}
