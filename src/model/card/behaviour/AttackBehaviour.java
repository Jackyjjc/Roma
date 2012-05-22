package model.card.behaviour;

import model.Die;
import model.ICardResources;
import model.card.AbstractCard;
import model.card.Action;
import model.card.ICardChecker;
import model.card.IDieChecker;

public abstract class AttackBehaviour extends Behaviour implements IDieChecker, ICardChecker {

    public AttackBehaviour(AbstractCard host, ICardResources cardResources) {
        super(host, cardResources);
    }

    public void complete() {

        AbstractCard target = getTarget();

        if (target != null && isValidCard(target)) {
            int value = getBattleDiceValue();
            Action.attack(target, value);
        }
        
    }
    
    public int getBattleDiceValue() {
        return getCardResources().getInputHandler().getBattleDieInput();
    }
    
    public abstract AbstractCard getTarget();
    
    public boolean isValidDie(Die die) {
        return !die.isUsed();
    }
    
    public boolean isValidCard(AbstractCard target) {
        boolean isValid = false;

        if (target.getOwner() != null
                && target.getOwner() != getOwner()) {
            isValid = true;
        }

        return isValid;
    }
}
