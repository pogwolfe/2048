
/**
 * Authors: Connor Wolfe, Parker Kuchulan, Catherine Stacey
 * course: CIS 163 03
 * Instructor: Professor Woodring
 * date: December 2, 2022
 * description: this class hold many of the game logic methods and has association wth Board, GameStatus, Text2048, GUI2048
 */

import java.util.Random;

public class GameController { //

    private Board board;
    private int WinVal;
    private GameStatus gameStatus;

    /**
     * default constructor that set basic parameter for  the game
     */
    public GameController() {
        board  = new Board();
        WinVal = 2048;
        newTile();
        gameStatus = GameStatus.IN_PROGRESS;
    }

    /** This GameController constructor set different parameters for the game
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
     * our newTile() looks for random spots on the board available and attempts to place a new tile
     * Will not place a new tile if there are no Avialble spots
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

    /**setGameStatus() will set the current gamestatus to the incoming parameter
     * @param gameStatus set the status of the game
     */
    public void setGameStatus(GameStatus gameStatus) {
        this.gameStatus = gameStatus;
    }

    /** getBoard() will return the current board being played
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
        gameStatus = GameStatus.IN_PROGRESS;
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
     * our checkloss() will set game status to GameStatus.LOST if all the cells on
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

    /** findSimilarNeighborsRecursive() will indiviually check each tile on the board to see if there are available combination for the user to make
     * @param row, col specific starting point of the recursive method
     * to start looking through the board for similar neighbors to combine
     * @return true if there is an available combination
     */
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

    /** moveVertical() will call a recursive function based on if the user is attempting to move up or down in the game
     * @param num 1 if moving down, -1 if moving up
     */
    public void moveVertical(int num){
        if(num == 1){ // if moving down
            for (int col = 0; col < board.getSize(); col++) {
                recurseDown(col, board.getSize() - 2);
            }

        } else{ // if moving up
            for (int col = 0; col < board.getSize(); col++) {
                recurseUp(col, 1);
            }
        }
    }

    /** moveHorizontal() will call a recursive function based on if the user is attempting to move left or right in the game
     * @param num 1 if moving right, -1 if moving left
     */
    public void moveHorizontal(int num){
        if(num == 1){ // if moving right
            for(int row = 0; row < board.getSize(); row++){ // tracks rows, starts at top right, ends at bottom left
                recurseRight(board.getSize() - 2, row);
            }

        } else{ // if moving left
            for(int row = 0; row < board.getSize(); row++){ // tracks rows, starts at top left, ends at bottom right
                recurseLeft(1, row);
            }
        }
    }

    /**
     * recurseLeft() will move all numbers as far left as possible and make all possible combinations in the process
     * @param col , row starting point for the recursion
     */
    public void recurseLeft(int col, int row){
        // pass in 1 for col to start from 1 before left side
        if ((board.getValue(row, col) == -1 && col == board.getSize() - 1) || (col == board.getSize() - 1 && board.getValue(row, col - 1) != board.getValue(row, col) && board.getValue(row, col - 1) != -1)){
            // Base: Tile is empty && last Tile || Tile cannot shift && cannot combine && last Tile
            return;
        } else if (board.getValue(row, col) == -1) {
            // Case 1: Tile is empty --> move onto next Tile
            recurseLeft(col + 1, row);

        } else if (board.getValue(row, col - 1) == -1){
            // Case 2: Tile can shift & not empty--> shift, restart
            board.setTile(row, col - 1, new Tile(board.getValue(row, col))); // swap
            board.setTile(row, col, null); // reset
            recurseLeft(1, row); // start over

        } else if(board.getValue(row, col - 1) == board.getValue(row, col)){
            // Case 3: Tile can combine  & cannot shift & not empty--> combine, restart
            board.setTile(row, col - 1, new Tile(board.getValue(row, col) * 2)); // double
            board.setTile(row, col, null); // reset
            recurseLeft(1, row); // start over

        } else {
            // Case 4: Tile cannot shift nor combine
            recurseLeft(col + 1, row);
        }
    }

    /**
     * recurseRight() will move all numbers as far right as possible and make all possible combinations in the process
     * @param col @param row starting point for the recursion
     *
     */
    public void recurseRight(int col, int row){
        // pass in board size - 2 for col to start from 1 before right side
        if ((board.getValue(row, col) == -1 && col == 0) || (col == 0 && board.getValue(row, col + 1) != board.getValue(row, col) && board.getValue(row, col + 1) != -1)){
            // Base: Tile is empty && last Tile || Tile cannot shift && cannot combine && last Tile
            return;
        } else if (board.getValue(row, col) == -1) {
            // Case 1: Tile is empty --> move onto next Tile
            recurseRight(col - 1, row);

        } else if (board.getValue(row, col + 1) == -1){
            // Case 2: Tile can shift --> shift, restart
            board.setTile(row, col + 1, new Tile(board.getValue(row, col))); // swap
            board.setTile(row, col, null); // reset
            recurseRight(board.getSize() - 2, row); // start over

        } else if(board.getValue(row, col + 1) == board.getValue(row, col)){
            // Case 3: Tile can combine --> combine, restart
            board.setTile(row, col + 1, new Tile(board.getValue(row, col) * 2)); // double
            board.setTile(row, col, null); // reset
            recurseRight(board.getSize() - 2, row); // start over

        } else {
            // Case 4: Tile cannot shift nor combine
            recurseRight(col - 1, row);
        }
    }

    /**
     * recurseUp() will move all numbers as far up as possible and make all possible combinations in the process
     * @param col @param row starting point for the recursion
     *
     */
    public void recurseUp(int col, int row){
        // pass in board size - 2 for row to start from 1 before bottom side
        if ((board.getValue(row, col) == -1 && row == board.getSize() - 1) || (row == board.getSize() - 1 && board.getValue(row - 1, col) != board.getValue(row, col) && board.getValue(row - 1, col) != -1)){
            // Base: Tile is empty && last Tile || Last Tile && cannot shift && cannot combine
            return;
        } else if (board.getValue(row, col) == -1) {
            // Case 1: Tile is empty --> move onto next Tile
            recurseUp(col, row + 1);

        } else if (board.getValue(row - 1, col) == -1){
            // Case 2: Tile can shift --> shift, restart
            board.setTile(row - 1, col, new Tile(board.getValue(row, col))); // swap
            board.setTile(row, col, null); // reset
            recurseUp(col, 1); // start over

        } else if(board.getValue(row - 1, col) == board.getValue(row, col)){
            // Case 3: Tile can combine --> combine, restart
            board.setTile(row - 1, col, new Tile(board.getValue(row, col) * 2)); // double
            board.setTile(row, col, null); // reset
            recurseUp(col, 1); // start over

        } else {
            // Case 4: Tile cannot shift nor combine --> move onto next Tile
            recurseUp(col, row + 1);
        }
    }
    /**
     * recurseDown() will move all numbers as far down as possible and make all possible combinations in the process
     * @param col @param row starting point for the recursion
     *
     */
    public void recurseDown(int col, int row){ //TODO: Needs to be fixed
        // pass in board size - 2 for row to start from 1 before bottom side
        if ((board.getValue(row, col) == -1 && row == 0) || (row == 0 && board.getValue(row + 1, col) != board.getValue(row, col) && board.getValue(row + 1, col) != -1)){
            // Base: Tile is empty && last Tile || Last Tile && cannot shift && cannot combine
            return;
        } else if (board.getValue(row, col) == -1) {
            // Case 1: Tile is empty --> move onto next Tile
            recurseDown(col, row - 1);

        } else if (board.getValue(row + 1, col) == -1){
            // Case 2: Tile can shift --> shift, restart
            board.setTile(row + 1, col, new Tile(board.getValue(row, col))); // swap
            board.setTile(row, col, null); // reset
            recurseDown(col, board.getSize() - 2); // start over

        } else if(board.getValue(row + 1, col) == board.getValue(row, col)){
            // Case 3: Tile can combine --> combine, restart
            board.setTile(row + 1, col, new Tile(board.getValue(row, col) * 2)); // double
            board.setTile(row, col, null); // reset
            recurseDown(col, board.getSize() - 2); // start over

        } else {
            // Case 4: Tile cannot shift nor combine --> move onto next Tile
            recurseDown(col, row - 1);
        }
    }
}