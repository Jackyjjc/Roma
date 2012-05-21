package model.card.behaviour;

import model.ICardResources;
import model.IResourceStorage;
import model.card.AbstractCard;
import model.card.Action;
import model.card.IForumListener;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 13/05/12
 * Time: 10:50 PM
 * To change this template use File | Settings | File Templates.
 */
public class BasilicaBehaviour extends Behaviour implements IForumListener {

    private static final int ADDITIONAL_VP = 2;

    public BasilicaBehaviour(AbstractCard host, ICardResources cardResources) {
        super(host, cardResources);
    }

    public void complete() {
        //doesn't activate itself
    }

    public void alert() {

        IResourceStorage bank = getCardResources().getBank();
        Action.attainVP(bank, getHost().getOwner(), ADDITIONAL_VP);

    }
}
