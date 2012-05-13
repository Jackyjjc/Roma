package model.card.attribute;

import java.util.List;

import model.ICardResources;
import model.IGameIO;
import model.IPlayer;
import model.card.AbstractCard;
import model.card.CardType;
import framework.cards.Card;

class Senator extends AbstractCard{

    private static final int COST = 3;
    private static final int DEFENCE = 3;
    
    private List<AbstractCard> charCards;
    
    Senator(ICardResources cardResources, IGameIO gameIO) {
        super(Card.SENATOR, CardType.CHARACTER,
              COST, DEFENCE, cardResources, gameIO);

    }

    public void complete() {

        for (AbstractCard c : charCards) {
            c.setCost(c.getDefaultCost());
        }
        
    }

    public void initialise() {

        IPlayer owner = getOwner();
        charCards = owner.getHand().getCardsOf(CardType.CHARACTER);

        for (AbstractCard c : charCards) {
            c.setCost(0);
        }

    }

}
