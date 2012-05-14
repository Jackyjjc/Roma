package model.card.behaviour;

import model.IDisc;
import model.IField;
import model.card.AbstractCard;
import model.card.IDiscardListener;

public class GrimReaperBehaviour extends Behaviour implements IDiscardListener {

    public GrimReaperBehaviour(AbstractCard host) {
        super(host);
    }

    @Override
    public boolean lay(IDisc disc) {
        
        boolean succeed = super.lay(disc);
        
        observeCards();
        
        return succeed;
    }
    
    @Override
    public void disCard(boolean beenKilled) {
        
        IField discs = getHost().getOwner().getField();

        for(IDisc disc : discs) {
            if(!disc.isDiscEmpty()) {
                disc.getCard().getBehaviour().removeDiscardListener(this);
            }
        }
      
    }
    
    public void complete() {
        
    }
    
    private void observeCards() {

        IField discs = getHost().getOwner().getField();

        for(IDisc disc : discs) {
            if(!disc.isDiscEmpty()) {
                disc.getCard().getBehaviour().addDiscardListener(this);
            }
        }
    }

    public void update(AbstractCard card) {
        card.getBehaviour().setDiscardDestination(card.getOwner().getHand());
    }
}
