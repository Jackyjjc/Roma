package model.card.behaviour;

import model.ICardStorage;
import model.IDisc;
import model.IPlayer;
import model.card.AbstractCard;
import model.card.CardType;
import model.IDiscardListener;

public class GrimReaperBehaviour extends Behaviour implements IDiscardListener {

	private ICardStorage discard;
	
    public GrimReaperBehaviour(AbstractCard host) {
        super(host);
        this.discard = getHost().getCardResources().getDiscardStorage();
    }

    @Override
    public boolean lay(IDisc disc) {

        discard.addDiscardListener(this);
        
        return super.lay(disc);
        
    }
    
    @Override
    public void disCard() {

    	discard.removeDiscardListener(this);
    	super.disCard();
    	
    }
    
	public void alert() {
		
		IPlayer current = getHost().getCardResources().getCurrentPlayer();
		AbstractCard toSave = discard.getCard(0);
		
		if (getHost().getOwner() == toSave.getOwner() &&
				toSave != getHost() && 
				toSave.getType() == CardType.CHARACTER && current != getHost().getOwner()) {
		
			discard.removeCard(toSave);
			toSave.getOwner().getHand().pushCard(toSave);

		}
	}

	public void complete() {

	}
  
}
