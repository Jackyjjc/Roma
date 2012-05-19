package model.card.behaviour;

import java.util.ArrayList;
import java.util.List;

import model.IDisc;
import model.IPlayer;
import model.InputHandler;
import model.card.AbstractCard;
import model.card.CardType;
import model.card.ICardChecker;

public class SenatorBehaviour extends Behaviour implements ICardChecker {
	
	private List<AbstractCard> charCards;
	
	public SenatorBehaviour(AbstractCard host) {
		super(host);
		charCards = new ArrayList<AbstractCard>();
	}

	public void complete() {

	    layCards();
	    
        for (AbstractCard c : charCards) {
            c.setCost(c.getDefaultCost());
        }

        charCards.clear();
        
    }

    public void initialise() {

        IPlayer owner = getHost().getOwner();
        charCards = owner.getHand().getCardsOf(CardType.CHARACTER);

        for (AbstractCard c : charCards) {
            c.setCost(0);
        }

    }
    
    private void layCards() {
        
        InputHandler handler = getHost().getCardResources().getInputHandler();
      
        AbstractCard card = handler.getCardInput();
        
        IPlayer owner = getHost().getOwner();
       
        while(card != null) {
            
            owner.getHand().removeCard(card);
            
            IDisc disc = handler.getDiscInput();        
            card.lay(disc);
            
            card = handler.getCardInput();
        }
    }

    public boolean isValidCard(AbstractCard card) {
        
        boolean isValid = false;
        
        if(card != null && card.getOwner() == getHost().getOwner() 
                && card.getType() == CardType.CHARACTER) {
            
            isValid = true;
        }
        
        return isValid;
    }
}
