

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
        // generates coords for new Tile
        Random random = new Random();
        int r = random.nextInt(board.getSize());
        int c = random.nextInt(board.getSize());
        boolean done = false;

    while(!done) { // tries to find a valid position to place the new Tile
        if (board.get(r).get(c) == null) {
        Tile n = new Tile();
        board.set(r).set(c).n.getTileValue();
        done = true;

        } else { // randomizes coords again
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
                    board.setGameStatus(GameStatus.WON);
                }
            }
        }

    }

    private void checkLoss() { //checks to see if no more moves can be made and thus the game status is updated to lost
        boolean availableTiles = false;
        boolean canCombine = false;
        for (int i = 0; i < board.getSize(); i++) {
            for (int j = 0; j < board.getSize(); j++) {
                if (board.getValue(i, j) == -1) { //checks to see if tile is blank
                    availableTiles = true;
                }
                if (findSimilarNeighbors(i, j)){ // if tiles can combine
                    canCombine = true;
                }
            }
        }
        if (!availableTiles && !canCombine){ // if no available tiles and cannot combine any
            board.setGameStatus(GameStatus.LOST);
        }
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

    public void moveVertical(int num){ // 1 if moving down, -1 if moving right
        if(num == 1){ // if moving down
            for(int row = 0; row < board.getSize() - 1; row++){ // tracks rows
                recurseDown(0, row); // moves a single col
            }

        } else{ // if moving up
            for(int row = 0; row < board.getSize() - 1; row++){ // tracks rows
                recurseUp(board.getSize() - 1, row); // moves a single col
            }
        }
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
            return; // exit recursion
        }

        if (board.getValue(row, col) == board.getValue(row - 1, col)){ // can combine
            board.getTile(row - 1, col).setTileValue(board.getValue(row, col) * 2); // set next val = prev * 2
            board.getTile(row, col).setTileValue(null); // reset prev value
        }

        if (board.getValue(row - 1, col) == -1) { // can shift over
            board.getTile(row - 1, col).setTileValue(board.getValue(row, col)); // set next val = prev
            board.getTile(row, col).setTileValue(null); // reset prev value
        }
        // can do nothing --> call function again
        recurseLeft(col, row - 1);
    }
    public void recurseRight(int col, int row){
        if (row == board.getSize() - 2) { // break case--> if end of row
            return; // exit recursion
        }

        if (board.getValue(row, col) == board.getValue(row + 1, col)){ // can combine
            board.getTile(row + 1, col).setTileValue(board.getValue(row, col) * 2); // set next val = prev * 2
            board.getTile(row, col).setTileValue(null); // reset prev value
        }

        if (board.getValue(row + 1, col) == -1) { // can shift over
            board.getTile(row + 1, col).setTileValue(board.getValue(row, col)); // set next val = prev
            board.getTile(row, col).setTileValue(null); // reset prev value
        }
        // can do nothing --> call function again
        recurseRight(col, row + 1);
        // [4, 2, null, null] case ??
    }
    public void recurseUp(int col, int row){ // starts at bottom
        if (col == 1) { // break case--> if end of col
            return; // exit recursion
        }

        if (board.getValue(row, col) == board.getValue(row, col - 1)){ // can combine
            board.getTile(row, col - 1).setTileValue(board.getValue(row, col) * 2); // set next val = prev * 2
            board.getTile(row, col).setTileValue(null); // reset prev value
        }

        if (board.getValue(row, col - 1) == -1) { // can shift over
            board.getTile(row, col - 1).setTileValue(board.getValue(row, col)); // set next val = prev
            board.getTile(row, col).setTileValue(null); // reset prev value
        }
        // can do nothing --> call function again
        recurseUp(col - 1, row);
        // [4, 2, null, null] case ??
    }
    public void recurseDown(int col, int row){
        if (col == board.getSize() - 2) { // break case--> if end of col
            return; // exit recursion
        }

        if (board.getValue(row, col) == board.getValue(row, col + 1)){ // can combine
            board.getTile(row, col + 1).setTileValue(board.getValue(row, col) * 2); // set next val = prev * 2
            board.getTile(row, col).setTileValue(null); // reset prev value
        }

        if (board.getValue(row, col + 1) == -1) { // can shift over
            board.getTile(row, col + 1).setTileValue(board.getValue(row, col)); // set next val = prev
            board.getTile(row, col).setTileValue(null); // reset prev value
        }
        // can do nothing --> call function again
        recurseDown(col + 1, row);
        // [4, 2, null, null] case ??
    }
}