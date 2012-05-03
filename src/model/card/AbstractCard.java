package model.card;

import model.ICardStorage;
import model.IDisc;
import model.IPlayer;
import model.Notifier;

public abstract class AbstractCard {
    
    private final int defaultCost;
    
    private Card name;
    private CardType type;
    
    private int cost;
    private int defence;
    private IPlayer owner;
    private IDisc disc;
    private Notifier notifier;
    
    private ICardStorage grave;
    
    public AbstractCard(Card name, CardType type, int cost, int defence,
                        ICardStorage grave, Notifier notifier) {
        
        this.name = name;
        this.type = type;
        this.defaultCost = cost;
        this.cost = cost;
        this.defence = defence;
        this.owner = null;
        this.notifier = notifier;
        this.grave = grave;
    }

    public abstract void activate();
    
    public void lay(IDisc disc) {
        
    }
    
    public void disCard() {
        if(this.getDisc() != null) {
            
        }
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

    public int getDefence() {
        return defence;
    }

    public void setDefence(int defence) {
        this.defence = defence;
    }
    
    public IPlayer getOwner() {
        return owner;
    }

    public void setOwner(IPlayer owner) {
        this.owner = owner;
    }
    
    public int getDefaultCost() {
        return defaultCost;
    }

    public void setDisc(IDisc disc) {
        this.disc = disc;
    }
    
    public IDisc getDisc() {
        return disc;
    }
    
    public Notifier getNotifier() {
        return notifier;
    }
    
    public ICardStorage getGrave() {
        return grave;
    }
    
}
