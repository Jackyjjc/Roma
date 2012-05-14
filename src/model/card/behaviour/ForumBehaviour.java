package model.card.behaviour;

import model.Die;
import model.IDisc;
import model.IPlayer;
import model.IResourceStorage;
import model.InputHandler;
import model.card.AbstractCard;
import model.card.IForumListener;

public class ForumBehaviour extends Behaviour {

    public ForumBehaviour(AbstractCard host) {
        super(host);
    }


    public void complete() {

        IDisc disc = getHost().getDisc();
        IPlayer player = getHost().getOwner();

        InputHandler handler = getHost().getGameIO().getInputHandler();
        IResourceStorage bank = getHost().getCardResources().getBank();

        Die toUse = handler.getDieInput();

        if (isValidDie(toUse)) {
            bank.transferVP(player,toUse.getValue());
        }

        if(disc.getPrev() != null && disc.getPrev().getCard() instanceof IForumListener) {
            ((IForumListener)disc.getPrev().getCard()).alert();
        }

        if (disc.getNext() != null & disc.getNext().getCard() instanceof IForumListener) {
            ((IForumListener)disc.getNext().getCard()).alert();
        }

    }
    

    public boolean isValidDie(Die die) {
        
        boolean isValid = false;
        
        if(die != null && !die.isUsed()) {
            isValid = true;
        }
        
        return isValid;
    }
    
}
