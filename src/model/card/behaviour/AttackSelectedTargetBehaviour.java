package model.card.behaviour;

import model.ICardResources;
import model.IDisc;
import model.card.AbstractCard;
import model.card.ICardChecker;

public class AttackSelectedTargetBehaviour extends AttackBehaviour {
    
    public AttackSelectedTargetBehaviour(AbstractCard host,
            ICardResources cardResources, ICardChecker checker) {
        
        super(host, cardResources, checker);
    }

    public AbstractCard getTarget() {

        IDisc targetDisc = getCardResources().getInputHandler().getDiscInput();
        return targetDisc.getCard();
    }

}
