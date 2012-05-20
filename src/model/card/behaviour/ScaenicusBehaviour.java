package model.card.behaviour;

import model.IDisc;
import model.card.AbstractCard;
import model.card.CardFactory;
import model.card.CardType;
import model.card.ICardChecker;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 13/05/12
 * Time: 11:04 PM
 * To change this template use File | Settings | File Templates.
 */
public class ScaenicusBehaviour extends Behaviour implements ICardChecker {

	private Behaviour mimicBehaviour;
	private AbstractCard oldHost;
	private CardFactory factory;
	
    public ScaenicusBehaviour(AbstractCard host, CardFactory factory) {
		super(host);
		this.factory = factory;
	}

    public void mimic() {
    	
		AbstractCard host = getHost();
		IDisc disc = host.getCardResources().getInputHandler().getDiscInput();
		
		AbstractCard mimicCard = factory.create(disc.getCard().getName());
		mimicBehaviour = mimicCard.getBehaviour();
		
		oldHost = mimicBehaviour.getHost();
		
		mimicBehaviour.setHost(getHost());
		mimicBehaviour.initialise();
		
    }
    
	public void complete() {
		
		if (mimicBehaviour != null) {
			mimicBehaviour.complete();
			reset();
		}
	}
	
	public void reset() {

		if(mimicBehaviour != null) {
			mimicBehaviour.setHost(oldHost);
			mimicBehaviour = null;
		}
		
	}
	
	public Behaviour getMimicBehaviour() {
		
		Behaviour behaviour = null;
		
		if(mimicBehaviour != null && mimicBehaviour instanceof ScaenicusBehaviour) {
		    
			behaviour = (ScaenicusBehaviour) ((ScaenicusBehaviour)mimicBehaviour).getMimicBehaviour();
		} else if(mimicBehaviour != null) {
			behaviour = mimicBehaviour;
		} else {
		    behaviour = this;
		}
		
		return behaviour;
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
