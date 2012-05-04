package model.card;

import model.ICardResources;
import model.IPlayer;
import model.Notifier;
import framework.cards.Card;
import framework.interfaces.activators.NeroActivator;

class Nero extends AbstractCard implements NeroActivator {
    
    private static final int COST = 8;
    private static final int DEFENCE = 9;
    
    Nero(ICardResources cardResources, Notifier notifier) {
        super(Card.NERO, CardType.CHARACTER,
              COST, DEFENCE, cardResources, notifier);

    }

    public void activate() {
        
    }
    
    public boolean isValidTarget(AbstractCard c) {
        boolean isValid = false;
        
        if(c.getOwner() != null && c.getOwner() != this.getOwner()
           && c.getType() == CardType.BUILDING) {
            isValid = true;
        }
        
        return isValid;
    }

    public void chooseDiceDisc(int diceDisc) {
        
        IPlayer opponent = getOwner().getOpponent();
        
        AbstractCard target = opponent.getField().getCard(diceDisc);

        target.disCard();
        this.disCard();
    }

    public void complete() {
        //nothing to do here
    }
}
