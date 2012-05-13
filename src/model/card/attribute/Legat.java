package model.card.attribute;

import framework.cards.Card;
import model.ICardResources;
import model.IGameIO;
import model.IPlayer;
import model.IResourceStorage;
import model.card.AbstractCard;
import model.card.Action;
import model.card.CardType;

class Legat extends AbstractCard {

    private static final int COST = 5;
    private static final int DEFENCE = 2;

    Legat(ICardResources cardResources, IGameIO gameIO) {

        super(Card.LEGAT, CardType.CHARACTER,
              COST, DEFENCE, cardResources, gameIO);
    }

    public void complete() {


        IPlayer owner = this.getOwner();
        IPlayer opponent = owner.getOpponent();
        int amount = opponent.getField().countUnoccupiedDiscs();

        IResourceStorage bank = getCardResources().getBank();

        Action.attainVP(bank, owner, amount);

    }

}
