package model.card.behaviour;

import java.util.List;

import model.ICardResources;
import model.card.AbstractCard;
import model.card.CardType;

public class LayForFreeBehaviour extends Behaviour {

    private List<AbstractCard> affectedCards;
    private CardType type;
    
    public LayForFreeBehaviour(AbstractCard host, 
                               ICardResources cardResources, CardType type) {
        super(host, cardResources);
        this.type = type;
    }

    @Override
    public void initialise() {

        affectedCards = getHost().getOwner().getHand().getCardsOf(type);

        for (AbstractCard card : affectedCards) {
            card.setCost(0);
        }

    }

    public void complete() {

        for (AbstractCard card : affectedCards) {
            card.setCost(card.getDefaultCost());
        }

        affectedCards.clear();
    }
    
    public CardType getType() {
        return type;
    }
    
}
