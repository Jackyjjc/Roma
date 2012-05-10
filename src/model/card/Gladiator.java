package model.card;

import model.ICardResources;
import model.IGameIO;
import model.card.state.SendCardToHandState;
import framework.cards.Card;

class Gladiator extends AbstractCard implements ICardChecker {

    private static final int COST = 6;
    private static final int DEFENCE = 5;
    
    Gladiator(ICardResources cardResources, IGameIO gameIO) {
        super(Card.GLADIATOR, CardType.CHARACTER,
              COST, DEFENCE, cardResources, gameIO);
        
    }

    public void activate() {
        SendCardToHandState sendCard = new SendCardToHandState(this, this);
        sendCard.setNextState(null);
        
        setState(sendCard);
    }
    
    public boolean isValidCard(AbstractCard c) {
        boolean isValid = false;
        
        if(c.getOwner() != null && c.getOwner() != this.getOwner()
           && c.getType() == CardType.CHARACTER) {
            isValid = true;
        }
        
        return isValid;
    }
}
