package model.card.behaviour;

import model.ICardResources;
import model.card.AbstractCard;
import model.card.Action;
import model.card.ICardChecker;

public abstract class AttackBehaviour extends Behaviour {

    private ICardChecker checker;
    
    public AttackBehaviour(AbstractCard host, 
                           ICardResources cardResources, ICardChecker checker) {
        super(host, cardResources);

        this.checker = checker;
    }

    public void complete() {

        AbstractCard target = getTarget();

        if (target != null && checker.isValidCard(target)) {
            int value = getBattleDiceValue();
            Action.attack(target, value);
        }
        
    }
    
    public int getBattleDiceValue() {
        return getCardResources().getInputHandler().getBattleDieInput();
    }
    
    public abstract AbstractCard getTarget();
    
}
