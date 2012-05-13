package model.card.attribute;

import framework.cards.Card;
import model.ICardResources;
import model.ICardStorage;
import model.IGameIO;
import model.InputHandler;
import model.card.AbstractCard;
import model.card.CardType;

class Aesculapinum extends Attribute {

	public Aesculapinum(IGameIO gameIO, ICardResources cardResources) {
		super(gameIO,cardResources);		
	}
	
	public Card getName() {
		return Card.AESCULAPINUM;
	}
	
	public CardType getType() {
		return CardType.BUILDING;
	}

	public int getDefaultCost() {
		return 5;
	}

	public int getDefaultDefence() {
		return 2;
	}
	
	public void complete() {
        
        InputHandler handler = getGameIO().getInputHandler();
        ICardStorage hand = getOwner().getHand();
        ICardStorage discard = getCardResources().getDiscardStorage();
        
        int index = handler.getIntInput();
        AbstractCard card = discard.getCard(index);
        
        if (isValidCard(card)) {
            discard.removeCard(card);
            hand.pushCard(card);       
        }
    }
        
    private boolean isValidCard(AbstractCard c) {
        
        boolean isValid = false;
        
        if(c != null && c.getType() == CardType.CHARACTER) {
            isValid = true;
        }
        
        return isValid;
    }

}
