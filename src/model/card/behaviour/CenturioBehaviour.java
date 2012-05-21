package model.card.behaviour;

import model.Die;
import model.ICardResources;
import model.InputHandler;
import model.card.AbstractCard;
import model.card.ICardChecker;
import model.card.IDieChecker;

public class CenturioBehaviour extends AttackOppositeBehaviour{

    private IDieChecker dieChecker;
    
    public CenturioBehaviour(AbstractCard host, ICardResources cardResources, 
                             ICardChecker cardChecker, IDieChecker dieChecker) {
        
        super(host, cardResources, cardChecker);
        this.dieChecker = dieChecker;
        
    }

    @Override
    public int getBattleDiceValue() {

        InputHandler handler = getCardResources().getInputHandler();
        
        int dieValue;

        dieValue = super.getBattleDiceValue();
        boolean isAddDie = handler.getBooleanInput();

        if (isAddDie) {
            Die die = handler.getDieInput();
            if (dieChecker.isValidDie(die)) {
                die.use();
                dieValue += die.getValue();
            }
        }
            
        return dieValue;
    }

}
