package model.action;

import framework.cards.Card;
import framework.interfaces.GameState;
import model.InputHandler;
import model.card.AbstractCard;
import model.card.ICardChecker;
import model.runner.CardActivateManager;

public class AddCardInputAction extends InputAction {

    private int playerId;
    private Card name;

    public AddCardInputAction(GameState g, CardActivateManager manager,
                              InputHandler handler, int playerId, Card name) {
        super(g, manager, handler);

        this.playerId = playerId;
        this.name = name;
    }

    public void run() {
        getInputHandler().addCardInput(playerId, name);
    }

    public boolean isValid() {

        boolean isValid = false;

        ICardChecker checker = (ICardChecker) getCardActivateManager().getActivatedCard().getBehaviour();

        getInputHandler().addCardInput(playerId, name);
        AbstractCard card = getInputHandler().getCardInput();

        if (checker.isValidCard(card)) {
            isValid = true;
        }

        return isValid;
    }
}
