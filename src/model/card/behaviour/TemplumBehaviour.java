package model.card.behaviour;

import model.Die;
import model.ICardResources;
import model.IPlayer;
import model.IResourceStorage;
import model.InputHandler;
import model.card.AbstractCard;
import model.card.IDieChecker;
import model.card.IForumListener;

/**
 *
 *@author Chris Fong
 *@author Junjie CHEN
 *
 */
public class TemplumBehaviour extends Behaviour implements IForumListener, IDieChecker {

    public TemplumBehaviour(AbstractCard host, ICardResources cardResources) {
        super(host, cardResources);
    }

    public void complete() {
        //itself can't be activated
    }

    @Override
    public void alert() {

        InputHandler handler = getCardResources().getInputHandler();

        IResourceStorage bank = getCardResources().getBank();
        IPlayer player = getHost().getOwner();

        Die dieInput = handler.getDieInput();
        
        if (dieInput != null) {
            dieInput.use();
            bank.transferVP(player, dieInput.getValue());
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
