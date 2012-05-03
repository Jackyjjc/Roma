package model.card;

import model.DiceManager;
import model.Die;
import model.ICardStorage;
import model.IResourceStorage;
import model.Notifier;

class Templum extends AbstractCard implements IForumListener, TemplumActivator {

    private static final int COST = 2;
    private static final int DEFENCE = 2;
    
    private DiceManager diceManager;
    private IResourceStorage bank;
    
    Templum(ICardStorage grave, Notifier notifier, 
             IResourceStorage bank, DiceManager diceManager) {
        
        super(Card.TEMPLUM, CardType.BUILDING,
              COST, DEFENCE, grave, notifier);
        
        this.diceManager = diceManager;
    }

    public void activate() {
        
    }
    
    private boolean isValidDie(Die die) {
        boolean isValid = false;
        
        if(!die.isUsed()) {
            isValid = true;
        }
        
        return isValid;
    }

    public void complete() {
        // TODO Auto-generated method stub
        
    }

    public void notifyForumActivate(Die actiondie) {
        Action.attainVP(bank, this.getOwner(), actiondie.getValue());
    }
}
