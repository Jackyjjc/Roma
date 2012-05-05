package model.card.state;

import model.Die;
import model.InputHandler;
import model.card.AbstractCard;
import model.card.ICardChecker;
import model.card.IDieChecker;

public class CenturioAttackState extends AttackOpponentState implements ICardState {

    private boolean extraDie;
    private int dieValue;
    private IDieChecker dieChecker;
    
    public CenturioAttackState(AbstractCard owner, 
                               ICardChecker cardChecker,
                               IDieChecker dieChecker, int dieValue, boolean extraDie) {
        
        super(owner, cardChecker);
        this.dieValue = dieValue;
        this.extraDie = extraDie;
        this.dieChecker = dieChecker;
    }

    @Override
    public int getDieValue() {
        
        InputHandler handler = getOwner().getGameIO().getInputHandler();
        
        if(extraDie) {
            Die die = handler.getDieInput();
            
            if(dieChecker.isValidDie(die)) {
                dieValue += die.getValue();
            }
        }
        
        return dieValue;
    }

}
