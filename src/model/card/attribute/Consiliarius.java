package model.card.attribute;

import model.ICardResources;
import model.ICardStorage;
import model.IField;
import model.IGameIO;
import model.card.AbstractCard;
import model.card.CardType;
import framework.cards.Card;

public class Consiliarius extends Attribute {

    private ICardStorage charCards;
    
    Consiliarius(IGameIO gameIO, ICardResources cardResources) {
        super(gameIO,cardResources);

    }

    public void initialise() {

        IField discs = getOwner().getField();
        this.charCards = discs.removeCardsOf(CardType.CHARACTER);

        for (AbstractCard c : charCards) {
            c.setCost(0);
        }
        
        getGameIO().getInputHandler().setList(charCards);

    }
    
    public void complete() {
        
        for (AbstractCard c : charCards) {
            c.setCost(c.getDefaultCost());
        }
        
        getGameIO().getInputHandler().setList(getOwner().getHand());
    }

    public boolean isValidCard (AbstractCard c) {
        
        boolean isValid = false;
        
        if(c != null && c.getType() == CardType.CHARACTER) {
            isValid = true;
        }
        
        return isValid;
        
    }

	public Card getName() {
		return Card.CONSILIARIUS;
	}

	@Override
	public CardType getType() {
		return CardType.CHARACTER;
	}

	public int getDefaultCost() {
		return 4;
	}

	public int getDefaultDefence() {
		return 4;
	}
    
}
