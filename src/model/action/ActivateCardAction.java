package model.action;

import framework.cards.Card;
import framework.interfaces.GameState;
import framework.interfaces.MoveMaker;

public class ActivateCardAction extends MoveMakingAction {

    private int index;
    private Card activatedCard;

    public ActivateCardAction(GameState g, MoveMaker moveMaker, int index) {
        super(g, moveMaker);
        this.index = index;
        this.activatedCard = getActivatedCard(index);
    }

    public void run() {
        getMoveMaker().chooseCardToActivate(index);
    }

    public boolean isValid() {

        boolean valid = false;

        if (getActivatedCard(index) != Card.NOT_A_CARD
                && getActivatedCard(index) == activatedCard) {
            valid = true;
        }

        return valid;
    }

    private Card getActivatedCard(int index) {

        int currentPlayer = getGameState().getWhoseTurn();
        Card[] field = getGameState().getPlayerCardsOnDiscs(currentPlayer);

        Card activatedCard = field[index - 1];

        return activatedCard;
    }
}
