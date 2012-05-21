package model.card.behaviour;

import model.ICardResources;
import model.ICardStorage;
import model.IDisc;
import model.InputHandler;
import model.card.AbstractCard;
import model.card.ICardChecker;

public class GladiatorBehaviour extends Behaviour {

    private ICardChecker checker;
    
    public GladiatorBehaviour(AbstractCard host, 
                              ICardResources cardResources, ICardChecker checker) {
        
        super(host, cardResources);
        this.checker = checker;
    }

    public void complete() {

        InputHandler handler = getCardResources().getInputHandler();
        IDisc disc = handler.getDiscInput();
        AbstractCard target = disc.getCard();
        ICardStorage opponentHand;

        if (target != null && checker.isValidCard(target)) {

            target.getDisc().removeCard();

            opponentHand = target.getOwner().getHand();
            opponentHand.pushCard(target);
        }
    }
}
