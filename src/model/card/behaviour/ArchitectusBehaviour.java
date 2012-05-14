package model.card.behaviour;
import java.util.List;

import model.card.AbstractCard;
import model.card.CardType;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 13/05/12
 * Time: 10:44 PM
 * To change this template use File | Settings | File Templates.
 */
public class ArchitectusBehaviour extends Behaviour {

    private List<AbstractCard> buildingCards;

    public ArchitectusBehaviour(AbstractCard host) {
        super(host);
    }

    public void initialise() {

        buildingCards = getHost().getOwner().getHand().getCardsOf(CardType.BUILDING);

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


