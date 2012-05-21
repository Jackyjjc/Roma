package model.card.behaviour;

import java.util.ArrayList;
import java.util.List;

import model.IPlayer;
import model.card.AbstractCard;
import model.card.CardType;
import model.card.ICardChecker;

public class SenatorBehaviour extends Behaviour implements ICardChecker {

    private List<AbstractCard> charCards;

    public SenatorBehaviour(AbstractCard host) {
        super(host);
        charCards = new ArrayList<AbstractCard>();
    }

    public void complete() {

        for (AbstractCard c : charCards) {
            c.setCost(c.getDefaultCost());
        }

        charCards.clear();

    }

    public void initialise() {

        IPlayer owner = getHost().getOwner();
        charCards = owner.getHand().getCardsOf(CardType.CHARACTER);

        for (AbstractCard c : charCards) {
            c.setCost(0);
        }

    }

    public boolean isValidCard(AbstractCard card) {

        boolean isValid = false;

        if (card != null && card.getOwner() == getHost().getOwner()
                && card.getType() == CardType.CHARACTER) {

            isValid = true;
        }

        return isValid;
    }
}
