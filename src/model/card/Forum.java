package model.card;

import java.util.ArrayList;
import java.util.List;

import model.DiceManager;
import model.Die;
import model.ICardStorage;
import model.IListener;
import model.IResourceStorage;
import model.Notifier;

class Forum extends AbstractCard implements ForumActivator {

    private static final int COST = 5;
    private static final int DEFENCE = 5;

    private DiceManager diceManager;
    private IResourceStorage bank;
    private List<IListener> basilicas;
    private List<IListener> templums;
    
    private int dieRoll;
    
    Forum(ICardStorage grave, Notifier notifier,
            IResourceStorage bank, DiceManager diceManager) {
        super(Card.FORUM, CardType.BUILDING,
              COST, DEFENCE, grave, notifier);
        
        this.bank = bank;
        this.diceManager = diceManager;
        this.basilicas = new ArrayList<IListener>();
        this.templums = new ArrayList<IListener>();
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
    
    private void notifyBasilicas() {
        for(IListener listener : basilicas) {
            listener.update();
        }
    }
    
    private void notifyTemplums() {
        for(IListener listener : templums) {
            listener.update();
        }
    }

    public void chooseActionDice(int actionDiceValue) {
        diceManager.getActionDie(actionDiceValue).use();
        dieRoll = actionDiceValue;
        bank.transferVP(getOwner(), actionDiceValue);
        notifyBasilicas();
    }

    public void chooseActivateTemplum(boolean activate) {
        if(activate) {
            notifyTemplums();
        }
    }
    
    public void complete() {
        //nothing to do here
    }
}
