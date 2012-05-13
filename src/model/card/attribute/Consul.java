package model.card.attribute;

import model.Die;
import model.ICardResources;
import model.IGameIO;
import model.InputHandler;
import model.card.CardType;
import framework.cards.Card;

class Consul extends Attribute {
    
    private static final int MAX_DIE_VALUE = 6;
    
    Consul(IGameIO gameIO, ICardResources cardResources) {
        super(gameIO, cardResources);
    }

    public void complete() {

        InputHandler handler = getGameIO().getInputHandler();
        
        Die input = handler.getDieInput();
        
        if(isValidDie(input)) {
            input.setValue(input.getValue() + 1);
        }
    }

    private boolean isValidDie(Die die) {
        
        boolean isValid = false;
        
        if(!die.isUsed() && die.getValue() < MAX_DIE_VALUE) {
            isValid = true;
        }
        
        return isValid;
    }

	public Card getName() {
		return Card.CONSUL;
	}

	public CardType getType() {
		return CardType.CHARACTER;
	}

	public int getDefaultCost() {
		// TODO Auto-generated method stub
		return 3;
	}

	public int getDefaultDefence() {
		// TODO Auto-generated method stub
		return 3;
	}
    
}
