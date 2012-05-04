package model.cardcollection;

import model.ICardStorage;
import model.card.AbstractCard;
import model.card.CardFactory;

public class Deck extends CardCollection implements ICardStorage{

    private ICardStorage discard;
    
    Deck(CardFactory factory, ICardStorage discard) {
        super(factory);
        this.discard = discard;
    }
    
    @Override
    public AbstractCard popCard() {
        
        AbstractCard card = super.popCard();
        
        if(isEmpty()) {
            refill();
        }
        
        return card;
    }
    
    @Override
    public void removeCard(AbstractCard c) {
        
        super.removeCard(c);
        
        if(isEmpty()) {
            refill();
        }
    }
    
    private void refill() {
        
        discard.shuffle();
        
        while(discard.size() != 0) {
            appendCard(discard.popCard());
        }
        
    }

}
