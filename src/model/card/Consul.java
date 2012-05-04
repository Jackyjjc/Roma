package model.card;

import model.DiceManager;
import model.Die;
import model.ICardResources;
import model.Notifier;
import framework.cards.Card;
import framework.interfaces.activators.ConsulActivator;

class Consul extends AbstractCard implements ConsulActivator {
    
    private static final int COST = 3;
    private static final int DEFENCE = 3;
    
    private Die die;
    
    Consul(ICardResources cardResources, Notifier notifier) {
        super(Card.CONSUL, CardType.CHARACTER,
              COST, DEFENCE, cardResources, notifier);
        
    }

    public void activate() {
        
    }

    public void complete() {
        //nothing to do
    }

    public void chooseConsulChangeAmount(int amount) {
        die.setValue(die.getValue() + amount);
    }

    public void chooseWhichDiceChanges(int originalRoll) {
        
        DiceManager diceManager = getCardResources().getDiceManager();
        die = diceManager.getActionDie(originalRoll);
        
    }
    
}
