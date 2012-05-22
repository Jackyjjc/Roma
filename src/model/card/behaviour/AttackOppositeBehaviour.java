package model.card.behaviour;

import model.ICardResources;
import model.IDisc;
import model.IPlayer;
import model.card.AbstractCard;
import model.card.ICardChecker;

public class AttackOppositeBehaviour extends AttackBehaviour implements ICardChecker {

    public AttackOppositeBehaviour(AbstractCard host, ICardResources cardResources) {
        super(host, cardResources);
    }

    public AbstractCard getTarget() {
        
        IPlayer opponent = getHost().getOwner().getOpponent();

        int index = getHost().getDisc().getIndex();

        IDisc disc = opponent.getField().getDisc(index);
        AbstractCard target = disc.getCard();

        return target;
    }

}
