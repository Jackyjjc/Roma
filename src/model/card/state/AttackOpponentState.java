package model.card.state;

import model.IPlayer;
import model.card.AbstractCard;
import model.card.ICardChecker;

public class AttackOpponentState extends AttackState implements ICardState {

    public AttackOpponentState(AbstractCard owner, ICardChecker checker) {
        super(owner, checker);
    }

    @Override
    public AbstractCard getTarget() {
        
        IPlayer opponent = getOwner().getOwner().getOpponent();
        
        int index = getOwner().getDisc().getIndex();
        
        AbstractCard target = opponent.getField().getCard(index);
        
        return target;
        
    }
}
