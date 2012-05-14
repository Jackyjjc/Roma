package model.card.behaviour;

import model.Die;
import model.IPlayer;
import model.IResourceStorage;
import model.InputHandler;
import model.card.AbstractCard;
import model.card.IForumListener;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 13/05/12
 * Time: 11:07 PM
 * To change this template use File | Settings | File Templates.
 */
public class TemplumBehaviour extends Behaviour implements IForumListener {

    public TemplumBehaviour(AbstractCard host) {
        super(host);
    }

    public void complete() {
        //itself can't be activated
    }

    public boolean isValidDie(Die die) {

        boolean isValid = false;

        if(die != null && !die.isUsed()) {
            isValid = true;
        }

        return isValid;
    }

    @Override
    public void alert() {

        InputHandler handler = getHost().getGameIO().getInputHandler();

        IResourceStorage bank = getHost().getCardResources().getBank();
        IPlayer player = getHost().getOwner();

        bank.transferVP(player, handler.getDieInput().getValue());

    }
}
