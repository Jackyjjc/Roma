package model.card.attribute;

import framework.cards.Card;
import model.ICardResources;
import model.IGameIO;
import model.InputHandler;
import model.card.AbstractCard;
import model.card.CardFactory;
import model.card.CardType;


//TODO: Change cards to CardAttributes
class Scaenicus extends AbstractCard {

    private static final int COST = 8;
    private static final int DEFENCE = 3;
    
    private CardFactory factory;
    
    Scaenicus(ICardResources cardResources, IGameIO gameIO, CardFactory factory) {
        super(Card.SCAENICUS, CardType.CHARACTER,
              COST, DEFENCE, cardResources, gameIO);
        
        this.factory = factory;
    }

    public void complete() {

        InputHandler handler = getGameIO().getInputHandler();

        AbstractCard target = handler.getCardInput();
        AbstractCard clone = factory.create(target.getName());

        clone.setDisc(this.getDisc());
        clone.complete();

    }
    
    public boolean isValidCard(AbstractCard c) {
        
        boolean isValid = false;
        
        if(c.getType() == CardType.CHARACTER 
           && c.getOwner() == this.getOwner()) {
            isValid = true;
        }
        
        return isValid;
    }
}
