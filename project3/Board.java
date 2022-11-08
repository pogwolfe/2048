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
                if(game_board.get(i).get(j).getValue() != -1){
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
          t = game_board.get(col).get(row); // this would be setting the inputted value 't'-
            // -equal to game_board value rather than vice versa?

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

    public void moveVertical(int num){

    }

    public void moveHorizontal(int num){ // 1 if moving right, -1 if moving left

        if(num == 1){ // if moving right
            for(int i = 0; i < game_board.size() - 1; i++){
                for(int j = 0; j < game_board.size() - 2; j++){ // don't need to check last value, can't move
                    if(game_board.getValue(i, j) == game_board.getValue(i, j+1)){ // if equal
                        game_board.get(i).set(j+1, game_board.getValue(i, j) * 2); // sets adjacent value equal to double
                    }
                    if(game_board.getValue(i, j + 1) == -1){
                        game_board.get(i).set(j + 1, game_board.getValue(i, j)); // sets adjacent value equal to previous
                        game_board.get(i).set(j, null); // clears previous value
                    }
                }
            }

        } else{ // if moving left
            for(int i = game_board.size() - 1; i == 0; i--){
                for(int j = game_board.size() - 1; j < 2; j--){ // don't need to check last value, can't move
                    if(game_board.getValue(i, j) == game_board.getValue(i, j - 1)){ // if equal
                        game_board.get(i).set(j-1, game_board.get(i).get(j) * 2); // sets adjacent value equal to double
                    }
                    if(game_board.getValue(i, j - 1) == -1){
                        game_board.get(i).set(j - 1, game_board.getValue(i, j)); // sets adjacent value equal to previous
                        game_board.get(i).set(j, null); // clears previous value
                    }
                }
            }

        }
    }

    public void printBoard(){

    }
}
