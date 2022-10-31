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
     * @param N is the # we're checking for whether it's a power of 2
     * @return a boolean stating 'true' if it's a power of 2, and false otherwise
     */
    public boolean power2(double N){ // In creating this I realized it's technically not recursive

        if(N < 1){
            return false; // if N is < 1, it failed the whole number check-
                          // from the previous power2 call, and N must not be a power of 2
        }

        N = (Math.log(N) / Math.log(2)); // reverses the 2^x operation to find the power

        if(N % 1 == 0){
            return true; // checks whether the power is a whole number, then returns true
        }

        return power2(N);
    }

    public String toString(){
        return "" + value;
    }
}
