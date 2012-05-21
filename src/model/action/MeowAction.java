package model.action;

import framework.cards.Card;
import framework.interfaces.GameState;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 21/05/12
 * Time: 1:36 PM
 * To change this template use File | Settings | File Templates.
 */
public class MeowAction extends Action {

    private int diceDisc;

    public MeowAction(GameState g, int diceDisc) {
        super(g);
        this.diceDisc = diceDisc;
    }

    @Override
    public void run() {

    }

    @Override
    public boolean isValid() {
        return (getGameState().getPlayerCardsOnDiscs(getGameState().getWhoseTurn())[diceDisc] == Card.KAT);
    }
}
