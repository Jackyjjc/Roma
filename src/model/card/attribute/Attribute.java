package model.card.attribute;

import model.ICardResources;
import model.IGameIO;
import model.IPlayer;
import model.card.CardType;
import framework.cards.Card;

public abstract class Attribute {

	private IPlayer owner;
	private ICardResources cardResources;
	private IGameIO gameIO;
	
	public Attribute(IGameIO gameIO, ICardResources cardResources) {
		this.cardResources = cardResources;
		this.gameIO = gameIO;
		this.owner = null;
	}
	
    public abstract Card getName();
    public abstract CardType getType();
    
    public abstract int getDefaultCost();
    public abstract int getDefaultDefence();
    
    public void initialise() {
    	
    }
    
    public abstract void complete();
    
	public void setOwner(IPlayer owner) {
		this.owner = owner;			
	}

	public IPlayer getOwner() {
		return this.owner;
	}
	public ICardResources getCardResources() {
		return this.cardResources;
	}
	
	public IGameIO getGameIO() {
		return this.gameIO;
	}    
	
}
