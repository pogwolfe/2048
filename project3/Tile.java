package project3;

public class Tile {
    // holds an integer value(can only be a power of 2)
    private int value;

    public Tile(){ // default value for new tiles is 2 or 4
        double a = Math.random();
        if(a > 0.5){
            value = 2;
        } else{
            value = 4;
        }
    }

    public Tile(int value){
        if(power2(value)) {
            this.value = value;
        } else{
            throw new IllegalArgumentException("Value is not a power of 2");
        }
    }

    public int getTileValue(){
        return value;
    }

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
        if (N < 1){
            return false;
        }

        if(N == 1){
            return true;

        }

        return power2(N/2);
    }

    public String toString(){
        return "" + value;
    }
}
