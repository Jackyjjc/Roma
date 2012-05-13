package model.card;

import model.ICardResources;
import model.IGameIO;
import model.ITurnMover;
import model.card.attribute.Attribute;
import model.card.attribute.AttributeFactory;
import framework.cards.Card;

public class CardFactory {
    
    private ITurnMover turnMover;
    private ICardResources cardResources;
    private IGameIO gameIO;
    
    public CardFactory(ICardResources cardResources, IGameIO gameIO, ITurnMover turnMover) {
        
        this.cardResources = cardResources;
        this.gameIO = gameIO;
        this.turnMover = turnMover;
    }
    
    public AbstractCard create(Card name) {
        
        AbstractCard card = null;
        
        Attribute cardAttribute = AttributeFactory.createAttributes(name, gameIO, cardResources);
        
        card = new AbstractCard(cardAttribute);
        
        return card;
        
    }
    
}
