import java.util.LinkedList;

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

    public int getSize(){
        return size;
    }

    public void newTile(){

    }
    // returns true if our game_board has available cells
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

    public Tile getTile(int row, int col){
        if (!check(row,col)){ // if our LinkedList doesn't pass the check()

            throw new IllegalArgumentException("either row or Cols was an invalid input");
        }
        else{
            return game_board.get(col).get(row);
        }

    }

    public void setTile(int row, int col, Tile t){
        if (!check(row,col)){

            throw new IllegalArgumentException("either row or Cols was an invalid input");
        }
        else{
            game_board.get(col).set(row, t);
        }

    }

    public int getValue(int row, int col){
        if (!check(row,col)){

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

    private boolean check(int row, int col){ // returns true if values are within parameters
        if ((row < 4 || row > 10) && (col < 4 || col > 10)){
            return true;
        }
        else{
            return false;
        }

    }
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
