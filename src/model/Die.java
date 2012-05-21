package model;

/**
 * @author Chris Fong
 * @author Junjie CHEN
 */

public class Die {

    private final static int MAX_VALUE = 6;

    private boolean used;
    private int value;

    public Die() {
        value = roll();
    }

    public int roll() {

        int roll = (int) (Math.random() * MAX_VALUE + 1);
        setValue(roll);
        reset();

        return roll;
    }

    public void setValue(int value) {

        if (value >= 0 && value <= MAX_VALUE) {
            this.value = value;
            reset();
        }
    }

    public int getValue() {
        return value;
    }

    public void use() {
        used = true;
    }

    public boolean isUsed() {
        return used;
    }

    private void reset() {
        used = false;
    }
}
