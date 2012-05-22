package model.card.behaviour;

import model.ICardResources;
import model.IDisc;
import model.card.AbstractCard;
import model.card.CardType;
import model.card.ICardChecker;

public class AttackSelectedTargetBehaviour extends AttackBehaviour implements ICardChecker {
    
    private CardType targetType;
    
    public AttackSelectedTargetBehaviour(AbstractCard host, 
                                         ICardResources cardResources, CardType targetType) {
        
        super(host, cardResources);
        this.targetType = targetType;
    }

    public AbstractCard getTarget() {

        IDisc targetDisc = getCardResources().getInputHandler().getDiscInput();
        return targetDisc.getCard();
    }

    @Override
    public boolean isValidCard(AbstractCard card) {
        
        boolean isValid = false;
        
        if(super.isValidCard(card) && card.getType() == targetType) {
            isValid = true;
        }
        
        return isValid;
    }
}
