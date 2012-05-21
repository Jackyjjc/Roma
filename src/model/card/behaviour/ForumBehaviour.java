package model.card.behaviour;

import model.*;
import model.card.AbstractCard;
import model.card.IDieChecker;
import model.card.IForumListener;
import model.card.Templum;

public class ForumBehaviour extends Behaviour implements IDieChecker {

    public ForumBehaviour(AbstractCard host) {
        super(host);
    }


    public void complete() {

        IDisc disc = getHost().getDisc();
        IPlayer player = getHost().getOwner();

        InputHandler handler = getHost().getCardResources().getInputHandler();
        IResourceStorage bank = getHost().getCardResources().getBank();

        Die toUse = handler.getDieInput();
        Boolean useTemplum = handler.getBooleanInput();

        if (isValidDie(toUse)) {
            bank.transferVP(player, toUse.getValue());
            toUse.use();
        }

        if (disc.getPrev() != null && !disc.getPrev().isEmpty() && disc.getPrev().getCard() instanceof IForumListener) {
            if (!(disc.getPrev().getCard() instanceof Templum && !useTemplum)) {
                ((IForumListener) disc.getPrev().getCard()).alert();
            }
        }

        if (disc.getNext() != null && !disc.getNext().isEmpty() && disc.getNext().getCard() instanceof IForumListener) {
            if (!(disc.getNext().getCard() instanceof Templum && !useTemplum)) {
                ((IForumListener) disc.getNext().getCard()).alert();
            }
        }

    }


    public boolean isValidDie(Die die) {

        boolean isValid = false;

        if (die != null && !die.isUsed()) {
            isValid = true;
        }

        return isValid;
    }

}
