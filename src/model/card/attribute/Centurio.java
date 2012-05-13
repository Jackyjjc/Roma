package model.card.attribute;

import model.Die;
import model.ICardResources;
import model.IGameIO;
import model.IPlayer;
import model.InputHandler;
import model.card.AbstractCard;
import model.card.Action;
import model.card.CardType;
import framework.cards.Card;

class Centurio extends Attribute {

	
    Centurio (IGameIO gameIO, ICardResources cardResources) {
        super(gameIO,cardResources);
    }

    public void complete() {
        
        AbstractCard card = getOppositeCard();
        InputHandler handler = getGameIO().getInputHandler();
        int dieValue;
        
        if(isValidTarget(card)) {
            dieValue = handler.getBattleDieInput();
            
            boolean isAddDie = handler.getBooleanInput();
            if(isAddDie) {
                Die die = handler.getDieInput();
                
                if(isValidDie(die)) {
                    dieValue += die.getValue();
                }
            }
            Action.attack(card, dieValue);
        }
    
    }

    public boolean isValidTarget(AbstractCard c) {
        boolean isValid = false;
        
        if(c.getOwner() != null && c.getOwner() != this.getOwner()) {
            isValid = true;
        }
        
        return isValid;
    }
    
    private boolean isValidDie(Die die) {
        return !die.isUsed();
    }
    
    private AbstractCard getOppositeCard() {
        
        IPlayer opponent = this.getOwner().getOpponent();
        
        int index = this.getDisc().getIndex();
        
        AbstractCard target = opponent.getField().getCard(index);
        
        return target;
        
    }

	public Card getName() {
		return Card.CENTURIO;
	}

	public CardType getType() {
		return CardType.CHARACTER;
	}

	public int getDefaultCost() {
		return 9;
	}

	public int getDefaultDefence() {
		return 5;
	}

}
