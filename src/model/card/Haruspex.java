package model.card;

import model.ICardResources;
import model.ICardStorage;
import model.Notifier;
import framework.cards.Card;
import framework.interfaces.activators.HaruspexActivator;


class Haruspex extends AbstractCard implements HaruspexActivator {

    private static final int COST = 4;
    private static final int DEFENCE = 3;
    
    Haruspex(ICardResources cardResources, Notifier notifier) {
        super(Card.HARUSPEX, CardType.CHARACTER,
              COST, DEFENCE, cardResources, notifier);
        
    }

    public void activate() {
        
    }

    public void chooseCardFromPile(int indexOfCard) {

        ICardStorage deck = getCardResources().getDeckStorage();
        AbstractCard card = deck.getCard(indexOfCard);
        ICardStorage hand = this.getOwner().getHand();
        
        deck.removeCard(card);
        deck.shuffle();
        hand.pushCard(card);
        
    }

    public void complete() {
        
    }
    
}
