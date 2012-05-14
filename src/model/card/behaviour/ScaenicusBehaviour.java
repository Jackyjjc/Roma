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
	private AbstractCard oldHost;
	
    public ScaenicusBehaviour(AbstractCard host) {
		super(host);
	}

    public void mimic() {
    	
		AbstractCard host = getHost();
		IDisc disc = host.getGameIO().getInputHandler().getDiscInput();
		
		mimicBehaviour = disc.getCard().getBehaviour();
		
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
			behaviour = ((ScaenicusBehaviour)mimicBehaviour).getMimicBehaviour();
		} else {
			behaviour = this;
		}
		
		return behaviour;
	}
	
}
