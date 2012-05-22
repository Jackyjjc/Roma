package model.card.behaviour;

import model.ICardResources;
import model.IPlayer;
import model.IResourceStorage;
import model.card.AbstractCard;
import model.card.Action;

public class LegatBehaviour extends Behaviour {

    public LegatBehaviour(AbstractCard host, ICardResources cardResources) {
        super(host, cardResources);
    }

    public void complete() {


        IPlayer owner = getHost().getOwner();
        IPlayer opponent = owner.getOpponent();
        int amount = opponent.getField().countUnoccupiedDiscs();

        IResourceStorage bank = getCardResources().getBank();

        Action.attainVP(bank, owner, amount);

    }

}
