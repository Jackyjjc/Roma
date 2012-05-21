package model.card.behaviour;

import model.ICardResources;
import model.IDisc;
import model.InputHandler;
import model.card.AbstractCard;
import model.card.CardType;
import model.card.ICardChecker;

public class KamikazeBehaviour extends Behaviour {
    
    private ICardChecker checker;
    private CardType type;
    
    public KamikazeBehaviour(AbstractCard host, CardType type,
                             ICardResources cardResources, ICardChecker checker) {
        super(host, cardResources);
        this.checker = checker;
        this.type = type;
    }

    public void complete() {
        InputHandler handler = getCardResources().getInputHandler();
        IDisc disc = handler.getDiscInput();
        AbstractCard target = disc.getCard();

        if (target != null && checker.isValidCard(target)) {
            target.disCard();
        }

        getHost().disCard();
    }
    
    public CardType getType() {
        return type;
    }
}
