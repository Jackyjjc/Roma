package model.card.behaviour;

import model.Die;
import model.InputHandler;
import model.card.AbstractCard;
import model.card.IDieChecker;

/**
 * @author Chris Fong
 * @author Jacky CHEN
 */

public class ConsulBehaviour extends Behaviour implements IDieChecker {

    private static final int MIN_DIE_VALUE = 1;
    private static final int MAX_DIE_VALUE = 6;

    public ConsulBehaviour(AbstractCard host) {
        super(host);
    }

    public void complete() {

        InputHandler handler = getHost().getGameIO().getInputHandler();

        Die input = handler.getDieInput();
        int valueChange = handler.getIntInput();

        if(isValidDie(input, valueChange)) {
            input.setValue(input.getValue() + valueChange);
        }
    }

    private boolean isValidDie(Die die, int change) {

        boolean isValid = false;

        if(!die.isUsed() && (die.getValue()+change) <= MAX_DIE_VALUE 
              && (die.getValue()+change >= MIN_DIE_VALUE)) {
            isValid = true;
        }

        return isValid;
    }

    public boolean isValidDie(Die die) {
        return !die.isUsed();
    }

}
