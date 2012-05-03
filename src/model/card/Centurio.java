package model.card;

import model.DiceManager;
import model.Die;
import model.ICardStorage;
import model.IPlayer;
import model.Notifier;


class Centurio extends AbstractCard implements CenturioActivator {

    private static final int COST = 9;
    private static final int DEFENCE = 5;
    
    private int dieRoll;
    
    private DiceManager roller;
    
    Centurio (DiceManager roller, ICardStorage grave, Notifier notifier) {
        
        super(Card.CENTURIO, CardType.CHARACTER,
              COST, DEFENCE, grave, notifier);
        
        this.roller = roller;
    }

    public void activate() {
        
    }

    public boolean isValidTarget(AbstractCard c) {
        boolean isValid = false;
        
        if(c.getOwner() != null && c.getOwner() != this.getOwner()) {
            isValid = true;
        }
        
        return isValid;
    }
    
    private boolean isValidDie(Die die) {
        return !die.isUsed();
    }
    
    private AbstractCard getOppositeCard() {
        
        IPlayer opponent = this.getOwner().getOpponent();
        
        int index = this.getDisc().getIndex();
        
        AbstractCard target = opponent.getField().getCard(index);
        
        return target;
    }

//    private boolean isValidAddDie() {
//        
//        boolean isValid = handler.getBooleanInput();
//        boolean isUsed = true;
//        
//        Die[] actionDice = roller.getActionDice();
//        
//        for(Die die : actionDice) {
//            if(!die.isUsed()) {
//                isUsed = false;
//            }
//        }
//        
//        return (isValid && !isUsed);
//    }

    public void giveAttackDieRoll(int roll) {
        this.dieRoll = roll;
    }

    public void chooseActionDice(int actionDiceValue) {
        
        roller.getActionDie(actionDiceValue).use();
        
        this.dieRoll += actionDiceValue;
        Action.attack(getOppositeCard(), dieRoll);
    }

    public void chooseCenturioAddActionDie(boolean attackAgain) {
        if(!attackAgain) {
            Action.attack(getOppositeCard(), dieRoll);
        }       
    }
    
    public void complete() {
        //nothing to do
    }
}
