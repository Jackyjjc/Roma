package model.card;

import model.ICardResources;
import model.IGameIO;
import framework.cards.Card;

class Templum extends AbstractCard implements IForumListener {

    private static final int COST = 2;
    private static final int DEFENCE = 2;
    
    Templum(ICardResources cardResources, IGameIO gameIO) {
        
        super(Card.TEMPLUM, CardType.BUILDING,
              COST, DEFENCE, cardResources, gameIO);
        
    }
    
	public void alert() {
		((IForumListener)getBehaviour()).alert();
	}
    

}
