/**
 * Authors: Connor Wolfe, Parker Kuchulan, Catherine Stacey
 * course: CIS 163 03
 * Instructor: Professor Woodring
 * date: December 2, 2022
 * description: this class holds a tile, which hold the values displayed on the board and will randomly place tiles on the board for the game
 */
public class Tile {
    // holds an integer value(can only be a power of 2)
    private int value;

    /**
     * default constructor for a new tile
     */
    public Tile(){ // default value for new tiles is 2 or 4
        double a = Math.random();
        if(a > 0.5){
            value = 2;
        } else{
            value = 4;
        }
    }

    /**
     * Tile() will set the value being held by the tiles on the board
     * @param value set the value of the new tile
     */
    public Tile(int value){
        if(power2(value)) {
            this.value = value;
        } else{
            throw new IllegalArgumentException("Value is not a power of 2");
        }
    }

    /**
     * getTileValue() will see the current value of a specfic tile and return it
     * @return the value of the tile
     */
    public int getTileValue(){

        return value;
    }

    /**
     * setTileValue() will change the current value of a tile to the inputed parameter
     * @param value sets the value of the tile
     */
    public void setTileValue(int value){
        if(power2(value)) {
            this.value = value;
        } else{
            throw new IllegalArgumentException("Value is not a power of 2");
        }
    }

    /**
     * Our power2 method recursively divides to discover whether our value is a valid power of 2
     * @param N is the # we're checking for whether it's a power of 2
     * @return a boolean stating 'true' if it's a power of 2, and false otherwise
     */
    public boolean power2(double N){
        if (N < 2){
            return false;
        }
        if(N == 2){
            return true;

        }

        return power2(N/2);
    }

    /**
     * toString() creates a string will the values held by the tiles on the board
     * @return a string of the value of the tile
     */
    public String toString(){

        return "" + value;
    }

}
