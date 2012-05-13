package model.card.attribute;

import framework.cards.Card;
import model.ICardResources;
import model.ICardStorage;
import model.IGameIO;
import model.InputHandler;
import model.card.AbstractCard;
import model.card.CardType;

class Haruspex extends AbstractCard {

    private static final int COST = 4;
    private static final int DEFENCE = 3;
    
    Haruspex(ICardResources cardResources, IGameIO gameIO) {
        super(Card.HARUSPEX, CardType.CHARACTER,
              COST, DEFENCE, cardResources, gameIO);
        
    }

    public void complete() {
        
        InputHandler handler = getGameIO().getInputHandler();
        ICardStorage hand = getOwner().getHand();
        ICardStorage deck = getCardResources().getDeckStorage();
        
        int index = handler.getIntInput();
        AbstractCard card = deck.getCard(index);
        
        if (isValidCard(card)) {
            deck.removeCard(card);
            deck.shuffle();
            hand.pushCard(card);       
        }
    }

    public boolean isValidCard(AbstractCard card) {
        
        boolean isValid = false;
        
        if(card != null) {
            isValid = true;
        }
        
        return isValid;
    }
    
}
