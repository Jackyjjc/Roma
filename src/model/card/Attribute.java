package model.card;

import model.IPlayer;
import framework.cards.Card;

public abstract class Attribute {

	private IPlayer owner;

    public abstract Card getName();
    public abstract CardType getType();
    
    public abstract int getDefaultCost();
    public abstract int getDefaultDefence();

	public void setOwner(IPlayer owner) {
		this.owner = owner;			
	}

	public IPlayer getOwner() {
		return this.owner;
	}

}
