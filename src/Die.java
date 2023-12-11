import java.util.concurrent.ThreadLocalRandom;
public class Die {
    private int numSides;
    private int value;

    public int getNumSides() {
        return numSides;
    }

    public void setNumSides(int numSides) {
        this.numSides = numSides;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public Die(){
        numSides = 6;
        value = 1;
    }

    public Die(int numSides){
        this.setNumSides(numSides);
        value = 1;
    }

    @Override public String toString(){
        return String.valueOf((this.getNumSides() + ", " + this.getValue()));
    }

    public void roll(){
        int randomNum = ThreadLocalRandom.current().nextInt(1, getNumSides() + 1);
        this.setValue(randomNum);
    }
}