package model.action;

import framework.interfaces.GameState;
import model.IDisc;
import model.InputHandler;
import model.card.AbstractCard;
import model.card.ICardChecker;
import model.runner.CardActivateManager;

public class AddDiscInputAction extends InputAction {

    private int playerId;
    private int discIndex;

    public AddDiscInputAction(GameState g, CardActivateManager manager,
                              InputHandler handler, int playerId, int discIndex) {
        super(g, manager, handler);

        this.playerId = playerId;
        this.discIndex = discIndex;
    }

    public void run() {
        getInputHandler().addDiscInput(playerId, discIndex);
    }

    public boolean isValid() {

        boolean isValid = false;

        getInputHandler().addDiscInput(playerId, discIndex);
        IDisc disc = getInputHandler().getDiscInput();
        AbstractCard card = disc.getCard();

        if (getCardActivateManager().getActivatedCard() instanceof ICardChecker) {
            ICardChecker checker = (ICardChecker) getCardActivateManager().getActivatedCard().getBehaviour();
            if ((card == null || checker.isValidCard(card))) {
                isValid = true;
            }
        } else {
            isValid = true;
        }

        return isValid;
    }

}
