package model.card.attribute;

import model.ICardResources;
import model.ICardStorage;
import model.IDisc;
import model.IGameIO;
import model.InputHandler;
import model.card.AbstractCard;
import model.card.CardType;
import framework.cards.Card;

class Gladiator extends AbstractCard {

    private static final int COST = 6;
    private static final int DEFENCE = 5;
    
    Gladiator(ICardResources cardResources, IGameIO gameIO) {
        super(Card.GLADIATOR, CardType.CHARACTER,
              COST, DEFENCE, cardResources, gameIO);
        
    }

    public void complete() {
        
        InputHandler handler = getGameIO().getInputHandler();
        IDisc disc = handler.getDiscInput();
        AbstractCard target = disc.getCard();
        ICardStorage opponentHand;
        
        if(isValidCard(target)) {
            
            target.getDisc().removeCard();
            
            opponentHand = target.getOwner().getHand();
            opponentHand.pushCard(target);
        }
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
