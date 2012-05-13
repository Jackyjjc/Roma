package model.card.attribute;

import framework.cards.Card;
import model.ICardResources;
import model.IGameIO;
import model.IResourceStorage;
import model.card.Action;
import model.card.CardType;
import model.card.IForumListener;

class Basilica extends Attribute implements IForumListener {
    
    private static final int ADDITIONAL_VP = 2;
    
    Basilica(IGameIO gameIO, ICardResources cardResources) {
        super(gameIO,cardResources);
    }

	public void complete() {
		//doesn't activate itself
	}

    public void alert() {

        IResourceStorage bank = getCardResources().getBank();
        Action.attainVP(bank, getOwner(), ADDITIONAL_VP);

    }

	public Card getName() {
		return Card.BASILICA;
	}

	public CardType getType() {
		return CardType.BUILDING;
	}

	public int getDefaultCost() {
		return 6;
	}

	public int getDefaultDefence() {
		return 5;
	}

}
