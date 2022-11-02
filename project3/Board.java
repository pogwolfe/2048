package project3;

public class Board {
    /**
     * Holds the value of each cube on the game_board
     */
    private int[][] game_board; // replace nums with text enums for color, etc?
    /**
     * Holds the status of our game
     */
    private GameStatus gameStatus;

    public Board(){

    }

    public int[][] getBoard(){
        return game_board;
    }

    public GameStatus getStatus(){
        return gameStatus;
    }

    public void newTile(){

    }
    // returns true if our game_board has available cells
    public boolean hasEmpty(){
        for(int l = 0; l < game_board.length; l++){
            for(int w = 0; w < game_board.length; w++){
                if( game_board[l][w] == 0){ // if the cell is empty
                    return true;
                }
            }
        }
        return false;
    }

    public void moveVertical(int num){

    }

    public void moveHorizontal(int num){

    }

    public void printBoard(){

    }
}
