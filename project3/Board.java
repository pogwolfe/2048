package project3;

public class Board {
    /**
     * Holds the value of each cube on the game_board
     */
    private LL game_board;
    /**
     * Holds the status of our game
     */
    private GameStatus gameStatus;
    //this is a comment
    public Board(){ // default constructor will create a 4x4 game_board of LinkedLists
        game_board = new LL<LL>(4);
    }

    public Board(int size){ // legal board sizes are between 4 and 10
        if(size < 4 || size > 10){
            throw new IllegalArgumentException("Board size must be between 4 and 10");
        } else {
            game_board = new LL<LL>(size);
        }
    }

    public LL getBoard(){
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
            int count = 0;
            while (count != game_board.getSize()) { // traverses through each LinkedList row of Nodes
                game_board.get();
                // finish after .get() method is completed
                count++;
            }
        }
    }

    public void moveVertical(int num){

    }

    public void moveHorizontal(int num){

    }

    public void printBoard(){

    }
}
