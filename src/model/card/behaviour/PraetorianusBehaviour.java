package model.card.behaviour;

import model.ICardResources;
import model.IDisc;
import model.InputHandler;
import model.card.AbstractCard;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 13/05/12
 * Time: 11:03 PM
 * To change this template use File | Settings | File Templates.
 */
public class PraetorianusBehaviour extends Behaviour {

    public PraetorianusBehaviour(AbstractCard host, ICardResources cardResources) {
        super(host, cardResources);
    }

    public void complete() {

        InputHandler handler = getCardResources().getInputHandler();
        IDisc target = handler.getDiscInput();

        target.block();

    }


}
