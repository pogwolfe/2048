package project3;

public class GameController { // has association wth Board, GameStatus, Text2048, GUI2048

    private int WinVal;

    public GameController() {
        Board board = new Board();
        WinVal = 2048;
        newTile();


    }

    public GameController(int size, int Val) {
        Board board = new Board(size);
        WinVal = Val;
        newTile();

    }

    // need getters and setter for our game status and board var

    public void newTile() {

    }

    public void reset() {
        for (int i = 0; i < game_board.getBoard().size(); i++) {
            for (int j = 0; game_board.getBoard().size(); j++) {
                game_board.get(i).set(j, null);
            }
        }
    }

    private void checkWin() { //loops through board to check if a tile is 2048 and updates game status to WON if so
        for (int i = 0; i < game_board.getBoard().size(); i++) {
            for (int j = 0; j < game_board.getBoard().size(); j++) {
                if (game_board.getValue(i, j) == 2048) { //checks for a tile == 2048
                    game.getStatus() = GameStatus.WON;
                }
            }
        }

    }

    private void checkLoss() { //checks to see if no more moves can be made and thus the game status is updated to lost
        for (int i = 0; i < game_board.getBoard().size(); i++) {
            for (int j = 0; j < game_board.getBoard().size(); j++) {
                if (game_board.getValue(i, j) == null) { //checks to see if tile is blank
                    game.getStatus() = GameStatus.IN_PROGRESS;
                    return;
                }
            }
        }
        game.getStatus() = GameStatus.LOST;
    }

    private boolean findSimilarNeighbors(int row, int col) {
        if (row == game_board.size() - 1 && col == game_board.size() - 1) { //base case, if we are at the last possible tile
            return false;
        }
        if (row == game_board.size() - 1) { //if we are in last row, only need to increment cols
            if (game_board.getTileValue(row, col) == game_board.getTileValue(row, col + 1)) {
                return true;
            }
            return findSimilarNeighbors(row, col + 1);
        }
        if (col == game_board.size() - 1) { //if we are at last col, only need to increment rows
            if (game_board.getTileValue(row, col) == game_board.getTileValue(row + 1, col)) {
                return true;
            }
            return findSimilarNeighbors(row + 1, col);
        }
        return findSimilarNeighbors(row + 1, col) || findSimilarNeighbors(row, col+1); //Creates Tree
    }
}




}
