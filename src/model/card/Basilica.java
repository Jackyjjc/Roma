package model.card;

import model.ICardResources;
import model.IGameIO;
import framework.cards.Card;

class Basilica extends AbstractCard implements IForumListener {

    private static final int COST = 6;
    private static final int DEFENCE = 5;
    
    Basilica(ICardResources cardResources, IGameIO gameIO) {
        super(Card.BASILICA, CardType.BUILDING,
              COST, DEFENCE, cardResources, gameIO);
        
    }

	public void alert() {
		((IForumListener)getBehaviour()).alert();
	}
    
}
