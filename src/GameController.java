


import java.util.Random;

public class GameController { // has association wth Board, GameStatus, Text2048, GUI2048

    private Board board;
    private int WinVal;
    private GameStatus gameStatus;

    public GameController() {
        board  = new Board();
        WinVal = 2048;
        newTile();
        gameStatus = GameStatus.IN_PROGRESS;
    }

    /**
     * @param size the size of the board
     * @param Val  the value needed to win the game
     */
    public GameController(int size, int Val) {
        board = new Board(size);
        WinVal = Val;
        newTile();
        gameStatus = GameStatus.IN_PROGRESS;
    }

    /**
     * will place a new tile in a random empty spot on the board
     */
    public void newTile() {
        // generates coords for new Tile
        Random random = new Random();
        int r = random.nextInt(board.getSize());
        int c = random.nextInt(board.getSize());
        boolean done = false;
        boolean availableTiles = false;
        for (int i = 0; i < board.getSize(); i++) {
            for (int j = 0; j < board.getSize(); j++) {
                if (board.getValue(i, j) == -1) { //checks to see if tile is blank
                    availableTiles = true;
                    break;
                }
            }
        }
        if (availableTiles) {
            while (!done) { // tries to find a valid position to place the new Tile
                if (board.getValue(r, c) == -1) {
                    Tile n = new Tile();
                    board.setTile(r, c, n);
                    done = true;
                } else { // randomizes cords again
                    r = random.nextInt(board.getSize());
                    c = random.nextInt(board.getSize());
                }
            }
        }
    }

    /**
     * @return the status of the game
     */
    public GameStatus getStatus(){
        return gameStatus;
    }

    /**
     * @param gameStatus set the status of the game
     */
    public void setGameStatus(GameStatus gameStatus) {
        this.gameStatus = gameStatus;
    }

    /**
     * @return the board
     */
    public Board getBoard(){
        return board;
    }

    /**
     * resets the game by setting all the cells on the board as null
     */
    public void reset() {
        for (int i = 0; i < board.getSize(); i++) {
            for (int j = 0; j < board.getSize(); j++) {
                board.setTile(i, j, null);
            }
        }
    }

    /**
     * will set the GameStatus.WON if any of the tiles reached the winning value
     */
    public void checkWin() { //loops through board to check if a tile is WinVal and updates game status to WON if so
        for (int i = 0; i < board.getSize(); i++) {
            for (int j = 0; j < board.getSize(); j++) {
                if (board.getValue(i, j) == WinVal) { //checks for a tile == WinVal
                    gameStatus = GameStatus.WON;
                }
            }
        }

    }

    /**
     * will set game status to GameStatus.LOST if all the cells on
     * the board are full and there are no similar neighbors to continue the game
     */
    public void checkLoss() { //checks to see if no more moves can be made and thus the game status is updated to lost
        boolean availableTiles = false;
        boolean canCombine = false;
        for (int i = 0; i < board.getSize(); i++) {
            for (int j = 0; j < board.getSize(); j++) {
                if (board.getValue(i, j) == -1) { //checks to see if tile is blank
                    availableTiles = true;
                    break;
                }
            }
        }
        if (findSimilarNeighborsRecursive(0, 0) == true){ // if tiles can combine
            canCombine = true;
        }
        if (!availableTiles && !canCombine){ // if no available tiles and cannot combine any
            gameStatus = GameStatus.LOST;
        }
    }


   /* public boolean findSimilarNeighbors(int row, int col){ // checks the surrounding tiles for same #
        int val = board.getValue(row, col);

        // create special checks for edge cases
        boolean leftCheck = true;
        boolean rightCheck = true;
        boolean aboveCheck = true;
        boolean belowCheck = true;

        if(row == 0) leftCheck = false;
        if(row == board.getSize() - 1) rightCheck = false;
        if(col == 0) aboveCheck = false;
        if(col == board.getSize() - 1) belowCheck = false;

        if(leftCheck){ // left tile
            if(board.getValue(row - 1, col) == val){
                return true;
            }
        }
        if(rightCheck){ // right tile
            if(board.getValue(row + 1, col) == val){
                return true;
            }
        }
        if(belowCheck){ // below tile
            if(board.getValue(row, col + 1) == val){
                return true;
            }
        }
        if(aboveCheck){ // above tile
            if(board.getValue(row, col - 1) == val){
                return true;
            }
        }
        return false;
   } */

    public boolean findSimilarNeighborsRecursive(int row, int col) {
        //NEW ATTEMPT
        if (row == board.getSize() - 1 && col == board.getSize() - 1) { //base case, if we are at the last possible tile
            return false;
        }
        if (row == board.getSize() - 1) { // if we are in last row, check value to the left, otherwise move back to row 0 and col + 1
            if (board.getValue(row, col) == board.getValue(row, col + 1)) {
                return true;
            }
            return findSimilarNeighborsRecursive(0, col + 1);
        }
        if (col == board.getSize() - 1) { //if we are in last col, only need to check straight down
            if (board.getValue(row, col) == board.getValue(row + 1, col)) {
                return true;
            }
            return findSimilarNeighborsRecursive(row + 1, col);
             /*if (board.getValue(col, row) == board.getValue(col, row+1) || board.getValue(col, row + 1)  == board.getValue(col, row + 2) || board.getValue(col, row + 2) == board.getValue(col, row + 3)   ) {
                 return true;
             }*/
        }
        if (board.getValue(row,col) == board.getValue(row,col + 1) || board.getValue(row,col) == board.getValue(row + 1,col) ) { //if 2 tiles equal each other
            return true;
        }
        return findSimilarNeighborsRecursive(row + 1, col); //return with row + 1
    }

    public void moveVertical(int num){ // 1 if moving down, -1 if moving up //TODO: Needs to be fixed
        if(num == 1){ // if moving down
            for (int col = 0; col < board.getSize(); col++) { //checks every col
                recurseDown(col, 0); // moves a single col, starting at top and going down
            }

        } else{ // if moving up
            for (int col = 0; col < board.getSize(); col++) { //checks every col
                recurseUp(col, board.getSize() - 1); // moves a single col,starting at bottom and going up
            }
        }
    }

    public void moveHorizontal(int num){ // 1 if moving right, -1 if moving left //TODO: Needs to be fixed
        if(num == 1){ // if moving right
            for(int row = 0; row < board.getSize(); row++){ // tracks rows, starts at top left, ends at bottom right
                recurseRight(0, row); // moves right across a single row
            }

        } else{ // if moving left
            for(int row = 0; row < board.getSize(); row++){ // tracks rows, starts at top right, ends at bottom left
                recurseLeft(board.getSize()-1, row); // moves left across a single row
            }
        }
    }

    public void recurseLeft(int col, int row){ //TODO: Needs to be fixed
        if (col == 0) { // break case--> if first col in row
            return; // exit recursion
        }

        if (board.getValue(row, col) != -1 && board.getValue(row, col) == board.getValue(row, col - 1)){ // can combine
            board.setTile(row, col - 1, new Tile(board.getValue(row, col) * 2)); // set next val = prev * 2
            board.setTile(row, col, null); //sets prev tile to null
        }

        if (board.getValue(row, col) != -1 && board.getValue(row, col -1 ) == -1) { // can shift over due to blank tile
            board.setTile(row, col - 1, new Tile(board.getValue(row, col))); // set next val = prev
            board.setTile(row, col, null); //set prev tile to null
        }
        // can do nothing --> call function again
        recurseLeft(col -1, row);
    }
    public void recurseRight(int col, int row){ //TODO: Needs to be fixed
        if (col == board.getSize() - 1) { // break case--> if last col in row
            return; // exit recursion
        }

        if (board.getValue(row, col) != -1 && board.getValue(row, col) == board.getValue(row, col + 1)){ // can combine
            board.setTile(row, col + 1, new Tile(board.getValue(row, col) * 2)); // set next val = prev * 2
            board.setTile(row, col, null); //sets prev tile to null
        }

        if (board.getValue(row, col) != -1 && board.getValue(row, col + 1) == -1) { // can shift over due to blank tile
            board.setTile(row, col + 1, new Tile(board.getValue(row, col))); // set next val = prev
            board.setTile(row, col, null);//set prev tile to null
        }
        // can do nothing --> call function again
        recurseRight(col + 1, row);
    }
    public void recurseUp(int col, int row){ // starts at bottom //TODO: Needs to be fixed
        if (row == 0) { // break case--> if end of row
            return; // exit recursion
        }

        if (board.getValue(row, col) != -1 && board.getValue(row, col) == board.getValue(row - 1, col)){ // can combine since tile and next tile above are the same
            board.setTile(row - 1, col, new Tile(board.getValue(row, col) * 2)); // set next val = prev * 2
            board.setTile(row, col, null); //sets prev tile to null
        }

        if (board.getValue(row, col) != -1 && board.getValue(row - 1, col) == -1) { // can shift up due to blank tile above
            board.setTile(row - 1, col, new Tile(board.getValue(row, col))); // set next val = prev
            board.setTile(row, col, null); //sets prev tile to null
        }
        // can do nothing --> call function again
        recurseUp(col, row - 1); //decrement row to move up in same col
    }
    public void recurseDown(int col, int row){ //TODO: Needs to be fixed
        if (row == board.getSize() - 1) { // break case--> if end of row
            return; // exit recursion
        }

        if (board.getValue(row, col) != -1 && board.getValue(row, col) == board.getValue(row + 1, col)){ // can combine since curr tile and next tile down are equal
            board.setTile(row + 1, col, new Tile(board.getValue(row, col) * 2)); // set next val = prev * 2
            board.setTile(row, col, null);//sets prev tile to null
        }

        if (board.getValue(row, col) != -1 && board.getValue(row + 1, col) == -1) { // can shift down due to blank tile
            board.setTile(row + 1, col, new Tile(board.getValue(row, col))); // set next val = prev
            board.setTile(row, col, null);//sets prev tile to null
        }
        // can do nothing --> call function again
        recurseDown(col, row + 1); //moves down a row
    }
}