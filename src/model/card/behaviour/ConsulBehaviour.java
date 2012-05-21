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

public class ConsulBehaviour extends Behaviour implements IDieChecker {

    private static final int MIN_DIE_VALUE = 1;
    private static final int MAX_DIE_VALUE = 6;

    public ConsulBehaviour(AbstractCard host, ICardResources cardResources) {
        super(host, cardResources);
    }

    public void complete() {

        InputHandler handler = getCardResources().getInputHandler();

        Die input = handler.getDieInput();
        int valueChange = handler.getIntInput();

        if (isValidDie(input, valueChange)) {
            input.setValue(input.getValue() + valueChange);
        }
    }

    public boolean isValidDie(Die die) {
        return !die.isUsed();
    }

}
