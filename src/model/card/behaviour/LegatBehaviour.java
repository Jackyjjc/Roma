package model.card.behaviour;

import model.IPlayer;
import model.IResourceStorage;
import model.card.AbstractCard;
import model.card.Action;

public class LegatBehaviour extends Behaviour {

    public LegatBehaviour(AbstractCard host) {
        super(host);
        // TODO Auto-generated constructor stub
    }

    public void complete() {


        IPlayer owner = getHost().getOwner();
        IPlayer opponent = owner.getOpponent();
        int amount = opponent.getField().countUnoccupiedDiscs();

        IResourceStorage bank = getHost().getCardResources().getBank();

        Action.attainVP(bank, owner, amount);

    }

}
