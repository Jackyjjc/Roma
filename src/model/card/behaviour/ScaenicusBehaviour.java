package model.card.behaviour;

import model.IDisc;
import model.card.AbstractCard;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 13/05/12
 * Time: 11:04 PM
 * To change this template use File | Settings | File Templates.
 */
public class ScaenicusBehaviour extends Behaviour {

	private Behaviour mimicBehaviour;
	
    public ScaenicusBehaviour(AbstractCard host) {
		super(host);
	}

    public void mimic() {
		AbstractCard host = getHost();
		IDisc disc = host.getGameIO().getInputHandler().getDiscInput();
		
		mimicBehaviour = disc.getCard().getBehaviour();
		
		getHost().setBehaviour(mimicBehaviour);
		mimicBehaviour.initialise();
		
    }
    
	public void complete() {
		mimicBehaviour.complete();
		reset();
	}
	
	public void reset() {
		
		getHost().setBehaviour(new ScaenicusBehaviour(getHost()));
		
	}
	
}
