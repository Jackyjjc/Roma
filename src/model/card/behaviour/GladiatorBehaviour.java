package model.card.behaviour;

import model.ICardStorage;
import model.IDisc;
import model.InputHandler;
import model.card.AbstractCard;
import model.card.CardType;

public class GladiatorBehaviour extends Behaviour {

    public GladiatorBehaviour(AbstractCard host) {
        super(host);
        // TODO Auto-generated constructor stub
    }

    public void complete() {
        
        InputHandler handler = getHost().getGameIO().getInputHandler();
        IDisc disc = handler.getDiscInput();
        AbstractCard target = disc.getCard();
        ICardStorage opponentHand;
        
        if(isValidCard(target)) {
            
            target.getDisc().removeCard();
            
            opponentHand = target.getOwner().getHand();
            opponentHand.pushCard(target);
        }
    }
    
    public boolean isValidCard(AbstractCard c) {
        boolean isValid = false;
        
        if(c.getOwner() != null && c.getOwner() != this.getHost().getOwner()
           && c.getType() == CardType.CHARACTER) {
            isValid = true;
        }
        
        return isValid;
    }
    
}
