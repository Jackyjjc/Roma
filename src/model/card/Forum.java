package model.card;

import java.util.ArrayList;
import java.util.List;

import model.DiceManager;
import model.ICardResources;
import model.IListener;
import model.IResourceStorage;
import model.Notifier;
import framework.cards.Card;
import framework.interfaces.activators.ForumActivator;

class Forum extends AbstractCard implements ForumActivator {

    private static final int COST = 5;
    private static final int DEFENCE = 5;

    private List<IListener> basilicas;
    private List<IListener> templums;
    
    private int dieRoll;
    
    Forum(ICardResources cardResources, Notifier notifier) {
        super(Card.FORUM, CardType.BUILDING,
              COST, DEFENCE, cardResources, notifier);
        
        this.basilicas = new ArrayList<IListener>();
        this.templums = new ArrayList<IListener>();
    }

    public void activate() {
        
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
        
        DiceManager diceManager = getCardResources().getDiceManager();
        IResourceStorage bank = getCardResources().getBank();
        
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
