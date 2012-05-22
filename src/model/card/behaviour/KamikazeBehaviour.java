package model.card.behaviour;

import model.ICardResources;
import model.IDisc;
import model.InputHandler;
import model.card.AbstractCard;
import model.card.CardType;
import model.card.ICardChecker;

public class KamikazeBehaviour extends Behaviour implements ICardChecker {
    
    private CardType type;
    
    public KamikazeBehaviour(AbstractCard host, CardType type,
                             ICardResources cardResources) {
        super(host, cardResources);
        this.type = type;
    }

    public void complete() {
        InputHandler handler = getCardResources().getInputHandler();
        IDisc disc = handler.getDiscInput();
        AbstractCard target = disc.getCard();

        if (target != null && isValidCard(target)) {
            target.disCard();
        }

        getHost().disCard();
    }
    
    public boolean isValidCard(AbstractCard c) {
        boolean isValid = false;

        if (c.getOwner() != null && c.getOwner() != getOwner()) {
            isValid = true;
        }

        return isValid;
    }
    
    public CardType getType() {
        return type;
    }
}
