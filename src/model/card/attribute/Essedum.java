package model.card.attribute;

import java.util.ArrayList;
import java.util.List;

import model.ICardResources;
import model.IDisc;
import model.IField;
import model.IGameIO;
import model.ITurnListener;
import model.ITurnMover;
import model.card.AbstractCard;
import model.card.CardType;
import framework.cards.Card;

class Essedum extends Attribute implements ITurnListener {

    private static final int COST = 6;
    private static final int DEFENCE = 3;
    
    private ITurnMover turnMover;
    private List<AbstractCard> affectedCards;
     
    Essedum(ICardResources cardResources, IGameIO gameIO, ITurnMover turnMover) {
        super(gameIO,cardResources);
        
        this.affectedCards = new ArrayList<AbstractCard>();
        this.turnMover = turnMover;
    }
    
    public boolean lay(IDisc disc) {
        
        boolean succeed = super.lay(disc);
        
        if(succeed) {
            turnMover.addTurnListener(this);      
        }

        return succeed;
    }
    
    public void complete() {
        addEffects();
    }

    public void disCard() {
        super.disCard();
        removeEffects();
        stopListening();
    }
    
    private void addEffects() {
        
        IField discs = getOwner().getOpponent().getField();
        AbstractCard card;
        
        for(IDisc disc : discs) {
            
            if(disc != null) {
                card = disc.getCard();
                if (card != null) {
                	card.setDefence(card.getDefence() - 2);
                	affectedCards.add(card);
                }
            }
        }
    }
    
    private void removeEffects() {
        
        for(AbstractCard card : affectedCards) {
            card.setDefence(card.getDefence() + 2);
            affectedCards.remove(card);
        }
        
        
    }
    
    private void stopListening() {
        turnMover.removeTurnListener(this);
    }
    
    public void turnChecking(int turnNum) {
        removeEffects();
    }

    public void notifyEndTurn() {
        removeEffects();
    }

	@Override
	public Card getName() {
		return Card.ESSEDUM;
	}

	@Override
	public CardType getType() {
		return CardType.CHARACTER;
	}

	@Override
	public int getDefaultCost() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getDefaultDefence() {
		// TODO Auto-generated method stub
		return 0;
	}
   
}
