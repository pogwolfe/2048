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

    }
    private void checkLoss(){

    }
    private boolean findSimilarNeighbors(int row, int col){

    }


}
