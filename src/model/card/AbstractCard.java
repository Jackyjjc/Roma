package model.card;

import model.ICardResources;
import model.IDisc;
import model.IGameIO;
import model.IPlayer;
import model.card.behaviour.Behaviour;
import framework.cards.Card;

public class AbstractCard {
    
    private final int defaultCost;
    private final int defaultDefence;

    private IPlayer owner;

    private Card name;
    private CardType type;

    private int cost;
    private int defence;
    private IDisc disc;

    private ICardResources cardResources;
    private IGameIO gameIO;

    private Behaviour behaviour;

    public AbstractCard(Card name, CardType type, int cost, int defence,
                        ICardResources cardResources, IGameIO gameIO) {

        this.name = name;
        this.type = type;
        this.defaultCost = cost;
        this.defaultDefence = defence;
        this.cost = cost;
        this.defence = defence;
        this.owner = null;
        this.gameIO = gameIO;
        this.cardResources = cardResources;

    }
    
    public void disCard(boolean beenKilled) {
    	behaviour.disCard(beenKilled);
    }
    
    public void lay(IDisc disc) {
    	behaviour.lay(disc);
    }


    public Behaviour getBehaviour() {
        return behaviour;
    }

    public void setBehaviour(Behaviour behaviour) {
        this.behaviour = behaviour;
    }

    public ICardResources getCardResources () {
        return this.cardResources;
    }

    public IGameIO getGameIO () {
        return this.gameIO;
    }

    
    public Card getName() {
        return name;
    }

    public CardType getType() {
        return type;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }
    
    
    public int getDefaultCost() {
        return defaultCost;
    }

    public int getDefence() {
        return defence;
    }

    public void setDefence(int defence) {
        this.defence = defence;
    }
    
    public int getDefaultDefence() {
        return defaultDefence;
    }

    public IPlayer getOwner() {
        return this.owner;
    }

    public void setOwner(IPlayer owner) {
    	this.owner = owner;
    }

    public void setDisc(IDisc disc) {
        this.disc = disc;
    }
    
    public IDisc getDisc() {
        return disc;
    }

	public void initialise() {
		behaviour.initialise();
	}
	
	public void complete() {
		behaviour.complete();
	}
    
}
