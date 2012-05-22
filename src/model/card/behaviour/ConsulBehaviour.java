package model.card.behaviour;

import model.Die;
import model.ICardResources;
import model.InputHandler;
import model.card.AbstractCard;
import model.card.IDieChecker;
import model.card.IIntegerChecker;

/**
 * @author Chris Fong
 * @author Jacky CHEN
 */

public class ConsulBehaviour extends Behaviour implements IDieChecker, IIntegerChecker {

    public ConsulBehaviour(AbstractCard host, ICardResources cardResources) {
        
        super(host, cardResources);
    }

    public void complete() {

        InputHandler handler = getCardResources().getInputHandler();

        Die input = handler.getDieInput();
        int valueChange = handler.getIntInput();

        if (isValidDie(input)) {
            input.setValue(input.getValue() + valueChange);
        }
    }


    public boolean isValidInt(int input) {
        
        boolean isValid = false;
        
        if(input == 1 || input == -1) {
            isValid = true;
        }
        
        return isValid;
    }

    public boolean isValidDie(Die die) {
        return !die.isUsed();
    }
}
