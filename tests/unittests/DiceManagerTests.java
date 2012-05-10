package unittests;

import model.DiceManager;
import model.Die;

public class DiceManagerTests implements ITest {

    public void run() {
        System.out.println("Testing Dice Manager");
    }

    private void runTest() {
        
        DiceManager dm = new DiceManager(3);
        dm.rollActionDice();
        
        Die[] dice = dm.getActionDice();
        for(Die d : dice) {
            assert(!d.isUsed());
        }
        
        
    }
    
}
