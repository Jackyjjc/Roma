package model.card;

import model.ICardResources;
import model.IDisc;
import model.IDiscListener;
import model.IGameIO;
import framework.cards.Card;

class Turris extends AbstractCard implements IDiscListener {

    private static final int COST = 6;
    private static final int DEFENCE = 6;

    
    Turris(ICardResources cardResources, IGameIO gameIO) {
        
        super(Card.TURRIS, CardType.BUILDING,
              COST, DEFENCE, cardResources, gameIO);

    }


	public void update(IDisc disc) {
		((IDiscListener)getBehaviour()).update(disc);
	}

}
