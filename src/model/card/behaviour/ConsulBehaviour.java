package model.card.behaviour;

import model.Die;
import model.ICardResources;
import model.InputHandler;
import model.card.AbstractCard;
import model.card.IDieChecker;

/**
 * @author Chris Fong
 * @author Jacky CHEN
 */

public class ConsulBehaviour extends Behaviour {

    private IDieChecker checker;
    
    public ConsulBehaviour(AbstractCard host, 
                           ICardResources cardResources, IDieChecker dieChecker) {
        
        super(host, cardResources);
        this.checker = dieChecker;
    }

    public void complete() {

        InputHandler handler = getCardResources().getInputHandler();

        Die input = handler.getDieInput();
        int valueChange = handler.getIntInput();

        if (checker.isValidDie(input)) {
            input.setValue(input.getValue() + valueChange);
        }
    }

}
