package model.card.behaviour;

import model.*;
import model.card.AbstractCard;
import model.card.IDieChecker;

public class TelephoneBoxBehaviour extends Behaviour implements IDieChecker {

    private ITurnMover turnMover;
    
    public TelephoneBoxBehaviour(AbstractCard host, ITurnMover turnMover) {
        super(host);
        this.turnMover = turnMover;
    }

    public void complete() {
        
        InputHandler handler = getHost().getGameIO().getInputHandler();
        
        boolean isForward = handler.getBooleanInput();
        Die die = handler.getDieInput();
        die.use();

        int travelTime = die.getValue();
        IDisc disc = handler.getDiscInput();
        
        if(isForward) {
            disc.addTimeTraveller(travelTime);
        } else {
            Turn pastTurn = turnMover.getTurn(travelTime);
            AbstractCard timeTraveller = disc.getCard();
            
            boolean isValid = pastTurn.insert(timeTraveller.getName(), timeTraveller.getOwner().getId(), disc.getIndex());
        
            if(isValid) {
                //Fingers crossed
                turnMover.replay(travelTime);
            }
        }

    }

    public boolean isValidDie(Die die) {
        return !die.isUsed();
    }
}