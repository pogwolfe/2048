package project3;

public class GameController { // has association wth Board, GameStatus, Text2048, GUI2048

    private int WinVal;

    public GameController(){
        Board board = new Board();
        WinVal = 2048;
        // needs to initialize the random
        newTile();


    }

    public GameController(int size, int Val){
        Board board = new Board(size);
        WinVal = Val;
        // needs to initialize the random
        newTile();

    }

    // need getters and setter for our game status and board var

    public void newTile(){

    }
    public void reset(){

    }
    private void checkWin(){
        for (int i = 0; i < game_board.getBoard().size(); i++) {
            for (int j = 0; j < game_board.getBoard().size(); j++) {
                if (game_board.getValue(i, j) == 2048) {
                    game.getStatus() = GameStatus.WON;
                }
            }
        }

    }
    private void checkLoss(){
        for (int i = 0; i < game_board.getBoard().size(); i++) {
            for (int j = 0; j < game_board.getBoard().size(); j++) {
                if (game_board.getValue(i, j) == null) {
                    game.getStatus() = GameStatus.IN_PROGRESS;
                }
            }
        }
        game.getStatus() = GameStatus.LOST;
    }

    private boolean findSimilarNeighbors(int row, int col){
        if (row == game_board.size() - 1 && col == game_board.size() -1 ) {
            return false;
        }

    }


}
