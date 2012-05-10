package model;

public class Die {

    private final static int MAX_VALUE = 6;
    
    private boolean used;
    private int value;
    
    public Die() {
        value = roll();
    }
    
    public int roll() {
        
        int roll = (int) (Math.random() * MAX_VALUE + 1);
        reset();
        
        return roll;
    }
    
    public int getValue() {
        return value;
    }
    
    public void setValue(int value) {
        
        assert(isValidValue(value));
        
        this.value = value;
    }
    
    public void reset() {
        used = false;
    }
    
    public void use() {
        used = true;
    }
    
    public boolean isUsed() {
        return used;
    }
    
    /**
     * 
     * This function must be called before setting the dice value
     * 
     * @param value
     * @return if the die value is valid or not
     */
    public static boolean isValidValue(int value) {
        
        boolean isValid = false;
        
        if(value > 0 && value <= MAX_VALUE) {
            isValid = true;
        }
        
        return isValid;
    }
}
