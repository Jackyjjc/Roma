package model.card.behaviour;

import model.IResourceStorage;
import model.card.AbstractCard;
import model.card.Action;
import model.card.IForumListener;

/**
 * @author Chris Fong
 * @author Jacky CHEN
 */

public class BasilicaBehaviour extends Behaviour implements IForumListener {

    private static final int ADDITIONAL_VP = 2;

    public BasilicaBehaviour(AbstractCard host) {
        super(host);
    }

    public void complete() {
        //doesn't activate itself
    }

    public void alert() {

        IResourceStorage bank = getHost().getCardResources().getBank();
        Action.attainVP(bank, getOwner(), ADDITIONAL_VP);

    }
}
