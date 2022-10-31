package project3;

public class Tile {
    // holds an integer value(can only be a power of 2)
    private int value;

    public Tile(){
        value = 2; // default value is 2
    }

    public Tile(int value){
        if(power2(value)) {
            this.value = value;
        } else{
            throw new IllegalArgumentException("Value is not a power of 2");
        }
    }

    public int getValue(){
        return value;
    }

    public void setValue(int value){
        if(power2(value)) {
            this.value = value;
        } else{
            throw new IllegalArgumentException("Value is not a power of 2");
        }
    }

    /**
     * Our power2 method recursively divides to discover whether our value is a valid power of 2
     * @param value is the # we're checking for whether it's a power of 2
     * @return
     */
    public boolean power2(double value){

        if(value == 1.00){
            return true;
        }

        if(value < 1){
            return false;
        }

        return power2(value / 2); // needs to log it? backwards from exponential? inverse of 2^x?
    }

    public String toString(){
        return "" + value;
    }
}
