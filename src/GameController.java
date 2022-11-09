

import java.util.Random;

public class GameController { // has association wth Board, GameStatus, Text2048, GUI2048

    private Board board;
    private int WinVal;

    public GameController() {
        board  = new Board();
        WinVal = 2048;
        newTile();
    }

    public GameController(int size, int Val) {
        board = new Board(size);
        WinVal = Val;
        newTile();
    }

    // need getters and setter for our game status and board var

    public void newTile() {
        Random random = new Random();
        int r = random.nextInt(board.getSize());
        int c = random.nextInt(board.getSize());
        boolean done = false;

    while(!done) {
        if (board.get(r).get(c) == null) {
        Tile n = new Tile();
        board.set(r).set(c).n.getTileValue();
        done = true;

        } else {
             r = random.nextInt(board.getSize());
             c = random.nextInt(board.getSize());
        }
        }
    }

    public void reset() {
        for (int i = 0; i < board.getSize(); i++) {
            for (int j = 0; j < board.getSize(); j++) {
                board.getTile(i, j).setTileValue(null);
            }
        }
    }

    private void checkWin() { //loops through board to check if a tile is 2048 and updates game status to WON if so
        for (int i = 0; i < board.getSize(); i++) {
            for (int j = 0; j < board.getSize(); j++) {
                if (board.getValue(i, j) == 2048) { //checks for a tile == 2048
                    game.getStatus() = GameStatus.WON;
                }
            }
        }

    }

    private void checkLoss() { //checks to see if no more moves can be made and thus the game status is updated to lost
        for (int i = 0; i < board.getSize(); i++) {
            for (int j = 0; j < board.getSize(); j++) {
                if (board.getValue(i, j) == null) { //checks to see if tile is blank
                    game.getStatus() = GameStatus.IN_PROGRESS;
                    return;
                }
            }
        }
        game.getStatus() = GameStatus.LOST;
    }

    private boolean findSimilarNeighbors(int row, int col) {
        if (row == board.getSize() - 1 && col == board.getSize() - 1) { //base case, if we are at the last possible tile
            return false;
        }
        if (row == board.getSize() - 1) { //if we are in last row, only need to increment cols
            if (board.getValue(row, col) == board.getValue(row, col + 1)) {
                return true;
            }
            return findSimilarNeighbors(row, col + 1);
        }
        if (col == board.getSize() - 1) { //if we are at last col, only need to increment rows
            if (board.getValue(row, col) == board.getValue(row + 1, col)) {
                return true;
            }
            return findSimilarNeighbors(row + 1, col);
        }
        return findSimilarNeighbors(row + 1, col) || findSimilarNeighbors(row, col+1); //Creates Tree
    }

    public void moveVertical(int num){

    }

    public void moveHorizontal(int num){ // 1 if moving right, -1 if moving left

        if(num == 1){ // if moving right
            for(int col = 0; col < board.getSize() - 1; col++){ // tracks cols
                recurseRight(col, 0); // moves a single row
            }

        } else{ // if moving left
            for(int col = 0; col < board.getSize() - 1; col++){ // tracks cols
                recurseLeft(col, board.getSize() - 1); // moves a single row
            }
        }
    }

    public void recurseLeft(int col, int row){
        if (row == 1) { // break case--> if end of row
            // exit recursion
        }

        if (board.getValue(row, col) == board.getValue(row - 1, col)){ // can combine
            board.getTile(row - 1, col).setTileValue(board.getValue(row, col) * 2); // set next val = prev * 2
            board.getTile(row, col).setTileValue(null); // reset prev value
        }

        if (board.getValue(row - 1, col) == null) { // can shift over
            board.getTile(row - 1, col).setTileValue(board.getValue(row, col)); // set next val = prev
            board.getTile(row, col).setTileValue(null); // reset prev value
        }
        // can do nothing --> call function again
        recurseLeft(col, row - 1);
    }
    public void recurseRight(int col, int row){
        if (row == 1) { // break case--> if end of row
            // exit recursion
        }

        if (board.getValue(row, col) == board.getValue(row + 1, col)){ // can combine
            board.getTile(row + 1, col).setTileValue(board.getValue(row, col) * 2); // set next val = prev * 2
            board.getTile(row, col).setTileValue(null); // reset prev value
        }

        if (board.getValue(row + 1, col) == null) { // can shift over
            board.getTile(row + 1, col).setTileValue(board.getValue(row, col)); // set next val = prev
            board.getTile(row, col).setTileValue(null); // reset prev value
        }
        // can do nothing --> call function again
        recurseLeft(col, row + 1);
        // [4, 2, null, null] case ??
    }
    public void recurseUp(int col, int row){

    }
    public void recurseDown(int col, int row){

    }
}