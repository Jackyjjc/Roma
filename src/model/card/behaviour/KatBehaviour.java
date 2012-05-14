package model.card.behaviour;

import model.card.AbstractCard;

public class KatBehaviour extends Behaviour {

    private static final int LIVES = 9;
    
    private int livesLeft;
    
    public KatBehaviour(AbstractCard host) {
        super(host);
        reset();
    }

    @Override
    public void disCard() {
        if (getHost().getOwner() != getHost().getCardResources().getCurrentPlayer()
        		&& livesLeft > 0) {
        	livesLeft--;
        } else {
        	super.disCard();
        	reset();
        }
    }
    
    public void complete() {
    	System.out.println("http://www.youtube.com/watch?v=QH2-TGUlwu4");
    }

    private void reset() {
        livesLeft = LIVES;
    }
}
