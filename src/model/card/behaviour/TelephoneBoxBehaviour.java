package model.card.behaviour;

import model.IDisc;
import model.ITurnMover;
import model.InputHandler;
import model.Turn;
import model.card.AbstractCard;

public class TelephoneBoxBehaviour extends Behaviour  {

    private ITurnMover turnMover;
    
    public TelephoneBoxBehaviour(AbstractCard host, ITurnMover turnMover) {
        super(host);
        this.turnMover = turnMover;
    }

    public void complete() {
        
        InputHandler handler = getHost().getGameIO().getInputHandler();
        
        boolean isForward = handler.getBooleanInput();
        int travelTime = handler.getDieInput().getValue();
        IDisc disc = handler.getDiscInput();
        
        if(isForward) {
            disc.addTimeTraveller(travelTime);
        } else {
            Turn pastTurn = turnMover.getTurn(travelTime);
            AbstractCard timeTraveller = disc.getCard();
            pastTurn.insert(timeTraveller.getName(), timeTraveller.getOwner().getId(), disc.getIndex());
        
            //Fingers crossed
            turnMover.replay(travelTime);
            
        }
    }

}
