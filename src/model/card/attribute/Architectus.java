package model.card.attribute;

import framework.cards.Card;
import model.*;
import model.card.AbstractCard;
import model.card.CardType;

import java.util.ArrayList;
import java.util.List;

class Architectus extends Attribute {

    private List<AbstractCard> buildingCards;
	
    public Architectus (IGameIO gameIO, ICardResources cardResources) {

    	super(gameIO, cardResources);
    	buildingCards = new ArrayList<AbstractCard>();
    	
    }
    
    public Card getName() {
    	return Card.ARCHITECTUS;
	}

	public CardType getType() {
		return CardType.CHARACTER;
	}

	public int getDefaultCost() {
		return 3;
	}

	public int getDefaultDefence() {
		return 4;
	}

	public void initialise() {
		
        buildingCards = getOwner().getHand().getCardsOf(CardType.BUILDING);

        for(AbstractCard card : buildingCards) {
            card.setCost(0);
        }
        
	}

	public void complete() {
	     
		for (AbstractCard card : buildingCards) {
			card.setCost(card.getDefaultCost());
			buildingCards.remove(card);
	    }
	   		
	}
 
}
