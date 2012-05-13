package model.card.attribute;

import framework.cards.Card;
import model.IDisc;
import model.IGameIO;
import model.IPlayer;
import model.InputHandler;
import model.card.AbstractCard;
import model.card.Action;
import model.card.CardType;

class Velites implements Attribute {

	private IGameIO gameIO;
	private IPlayer owner;
	
	public Velites (IGameIO gameIO) {
		
		this.gameIO = gameIO;
		this.owner = null;
		
	}
	
	public Card getName() {
		return Card.VELITES;
	}
	
	public CardType getType() {
		return CardType.CHARACTER;
	}

	public int getDefaultCost() {
		return 5;
	}

	public int getDefaultDefence() {
		return 3;
	}
	
	public void initialise() {
		
	}
	
	public void complete() {

        InputHandler handler = gameIO.getInputHandler();

        IDisc targetDisc = handler.getDiscInput();

        if (targetDisc != null) {
        	
        	AbstractCard targetCard = targetDisc.getCard();

        	if(isValidTarget(targetCard)) {
        		int value = handler.getBattleDieInput();
        		Action.attack(targetCard, value);
        	}
        	
        }
        
    }

    public boolean isValidTarget(AbstractCard c) {
        boolean isValid = false;

        if(c.getOwner() != null && c.getOwner() != this.owner
                && c.getType() == CardType.CHARACTER) {
            isValid = true;
        }

        return isValid;
    }

	public void setOwner(IPlayer owner) {
		this.owner = owner;		
	}

	public IPlayer getOwner() {
		return this.owner;
	}

}
