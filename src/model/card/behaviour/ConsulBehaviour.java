package model.card.behaviour;

import model.Die;
import model.InputHandler;
import model.card.AbstractCard;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 13/05/12
 * Time: 10:56 PM
 * To change this template use File | Settings | File Templates.
 */
public class ConsulBehaviour extends Behaviour {

    private static final int MAX_DIE_VALUE = 6;

    public ConsulBehaviour(AbstractCard host) {
        super(host);
    }

    public void complete() {

        InputHandler handler = getHost().getGameIO().getInputHandler();

        Die input = handler.getDieInput();
        int valueChange = handler.getIntInput();

        if(isValidDie(input)) {
            input.setValue(input.getValue() + valueChange);
        }
    }

    private boolean isValidDie(Die die) {

        boolean isValid = false;

        if(!die.isUsed() && die.getValue() < MAX_DIE_VALUE) {
            isValid = true;
        }

        return isValid;
    }

}
