package model;

public class Die {

    private final static int MAX_VALUE = 6;
    
    private boolean used;
    private int value;
    
    public Die() {
        value = roll();
    }
    
    public int roll() {
        return (int) (Math.random() * MAX_VALUE + 1);
    }
    
    public int getValue() {
        return value;
    }
    
    public void setValue(int value) {
        this.value = value;
    }
    
    public void use() {
        used = true;
    }
    
    public boolean isUsed() {
        return (used == true);
    }
    
    public static boolean isValidValue(int value) {
        
        boolean isValid = false;
        
        if(value > 0 && value <= MAX_VALUE) {
            isValid = true;
        }
        
        return isValid;
    }
}
