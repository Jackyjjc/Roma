package model.card.attribute;

import framework.cards.Card;
import model.ICardResources;
import model.IGameIO;
import model.IPlayer;
import model.card.AbstractCard;
import model.card.Action;
import model.card.CardType;

class TribunusPlebis extends AbstractCard {

    private static final int COST = 5;
    private static final int DEFENCE = 5;
    private static final int AMOUNT = 1;
    
    TribunusPlebis(ICardResources cardResources, IGameIO gameIO) {
        super(Card.TRIBUNUSPLEBIS, CardType.CHARACTER,
              COST, DEFENCE, cardResources, gameIO);
    }

    public void complete() {

        IPlayer owner = this.getOwner();
        IPlayer opponent = owner.getOpponent();

        if (isValid()) {
            Action.attainVP(opponent, owner, AMOUNT);
        }

    }

    public boolean isValid() {

        boolean isValid = false;

        if (this.getOwner().getOpponent().getVP() >= AMOUNT) {
            isValid = true;
        }

        return isValid;

    }

}
