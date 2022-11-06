package project3;

public class Board {
    /**
     * Holds the value of each cube on the game_board
     */
    private LL<LL<Tile>> game_board;
    /**
     * Holds the status of our game
     */
    private GameStatus gameStatus;
    //this is a comment
    public Board(){ // default constructor will create a 4x4 game_board of LinkedLists
        game_board = new LL<LL<Tile>>(4); // creates a LinkedList of LinkedLists
    }

    public Board(int size){ // legal board sizes are between 4 and 10
        if(size < 4 || size > 10){
            throw new IllegalArgumentException("Board size must be between 4 and 10");
        } else {
            game_board = new LL<LL<Tile>>(size);
        }
    }

    public LL<LL<Tile>> getBoard(){
        return game_board;
    }

    public GameStatus getStatus(){
        return gameStatus;
    }

    public void newTile(){

    }
    // returns true if our game_board has available cells
    public boolean hasEmpty(){
        for(int i = 0; i < game_board.getSize(); i++) { // traverses through the game_board columns of LinkedLists
            for(int j = 0; j < game_board.getSize(); j++) { // traverses through each LinkedList row of Nodes
                game_board.get(i).get(j);
                // finish after .get() method is completed
            }
        }
    }

    public Tile getTile(int row, int col){
        if (row < 4 || row > 10 && col < 4 || col > 10){

            throw new IllegalArgumentException("either row or Cols was an invalid input");
        }
        else{
            return game_board.get(row).get(col);

        }

    }

    public void setTile(int row, int col, Tile t){

    }

    public int getValue(int row, int col){

    }

    public void moveVertical(int num){

    }

    public void moveHorizontal(int num){

    }

    public void printBoard(){

    }
}
