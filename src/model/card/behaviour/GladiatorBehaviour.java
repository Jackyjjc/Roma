package model.card.behaviour;

import model.ICardResources;
import model.ICardStorage;
import model.IDisc;
import model.InputHandler;
import model.card.AbstractCard;
import model.card.CardType;
import model.card.ICardChecker;

public class GladiatorBehaviour extends Behaviour implements ICardChecker {

    public GladiatorBehaviour(AbstractCard host, ICardResources cardResources) {
        
        super(host, cardResources);
    }

    public void complete() {

        InputHandler handler = getCardResources().getInputHandler();
        IDisc disc = handler.getDiscInput();
        AbstractCard target = disc.getCard();
        ICardStorage opponentHand;

        if (target != null && isValidCard(target)) {

            target.getDisc().removeCard();

            opponentHand = target.getOwner().getHand();
            opponentHand.pushCard(target);
        }
    }
    

    public boolean isValidCard(AbstractCard target) {
        boolean isValid = false;

        if (target != null && target.getOwner() != getOwner()
                && target.getType() == CardType.CHARACTER) {
            isValid = true;
        }

        return isValid;
    }
}
