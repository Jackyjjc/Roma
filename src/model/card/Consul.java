package model.card;

import model.DiceManager;
import model.Die;
import model.ICardStorage;
import model.Notifier;

class Consul extends AbstractCard implements ConsulActivator {
    
    private static final int COST = 3;
    private static final int DEFENCE = 3;
    private static final int MAX_DIE_VALUE = 6;
    
    private Die die;
    
    private DiceManager diceManager;
    
    Consul(ICardStorage grave, Notifier notifier, DiceManager diceManager) {
        super(Card.CONSUL, CardType.CHARACTER,
              COST, DEFENCE, grave, notifier);
        
        this.diceManager = diceManager;
    }

    public void activate() {
        
    }

    private boolean isValidDie(Die die) {
        
        boolean isValid = false;
        
        if(!die.isUsed() && die.getValue() < MAX_DIE_VALUE) {
            isValid = true;
        }
        
        return isValid;
    }

    public void complete() {
        //nothing to do
    }

    public void chooseConsulChangeAmount(int amount) {
        die.setValue(die.getValue() + amount);
    }

    public void chooseWhichDiceChanges(int originalRoll) {
        die = diceManager.getActionDie(originalRoll);
        
    }
    
}
