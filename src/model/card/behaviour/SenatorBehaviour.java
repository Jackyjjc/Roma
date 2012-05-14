package model.card.behaviour;

import java.util.ArrayList;
import java.util.List;

import model.IPlayer;
import model.card.AbstractCard;
import model.card.CardType;

public class SenatorBehaviour extends Behaviour {
	
	private List<AbstractCard> charCards;
	
	public SenatorBehaviour(AbstractCard host) {
		super(host);
		charCards = new ArrayList<AbstractCard>();
	}

	public void complete() {

			for (AbstractCard c : charCards) {
	            c.setCost(c.getDefaultCost());
	        }
	        
	    }

	    public void initialise() {

	        IPlayer owner = getHost().getOwner();
	        charCards = owner.getHand().getCardsOf(CardType.CHARACTER);

	        for (AbstractCard c : charCards) {
	            c.setCost(0);
	        }

	    }
}
