package model.card;

import framework.cards.Card;
import model.*;
import model.card.attribute.Attribute;

public class AbstractCard {
    
    private final int defaultCost;
    private final int defaultDefence;
    
    private Card name;
    private CardType type;
    
    private int cost;
    private int defence;
    private IDisc disc;
    
    private Attribute attributes;

    public AbstractCard(Attribute attributes) {
        
    	this.attributes = attributes;
    	
        this.name = attributes.getName();
        this.type = attributes.getType();
        this.defaultCost = attributes.getDefaultCost();
        this.defaultDefence = attributes.getDefaultDefence();
        this.cost = this.defaultCost;
        this.defence = this.defaultDefence;
        
    }

    public void initialise() {
        attributes.initialise();
    }

    public void complete() {
    	attributes.complete();
    }
    
    public boolean lay(IDisc disc) {
        
        boolean succeed = false;
        IResourceStorage bank = attributes.getCardResources().getBank();
        
        if(getOwner().getMoney() >= getCost()) {
            disc.layCard(this);
            getOwner().transferMoney(bank, getCost());
        }
        
        return succeed;
    }
    
    public void disCard() {
        
        if(getDisc() != null) {
            getDisc().removeCard();
        }
        
        setCost(getDefaultCost());
        setDefence(getDefaultDefence());
        
        attributes.getCardResources().getDiscardStorage().pushCard(this);
    }
    
    public Card getName() {
        return name;
    }

    public void setName(Card name) {
        this.name = name;
    }

    public CardType getType() {
        return type;
    }

    public void setType(CardType type) {
        this.type = type;
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
        return attributes.getOwner();
    }

    public void setOwner(IPlayer owner) {
        this.attributes.setOwner(owner);
    }

    public void setDisc(IDisc disc) {
        this.disc = disc;
    }
    
    public IDisc getDisc() {
        return disc;
    }
    
}
