package model.card;

import model.ICardResources;
import model.IDisc;
import model.IGameIO;
import model.IPlayer;
import model.IResourceStorage;
import model.card.state.ICardState;
import framework.cards.Card;

public abstract class AbstractCard {
    
    private final int defaultCost;
    
    private Card name;
    private CardType type;
    
    private int cost;
    private int defence;
    private IPlayer owner;
    private IDisc disc;
    
    private ICardState state;
    private ICardResources cardResources;
    private IGameIO gameIO;
    
    public AbstractCard(Card name, CardType type, int cost, int defence,
                        ICardResources cardResources, IGameIO gameIO) {
        
        this.name = name;
        this.type = type;
        this.defaultCost = cost;
        this.cost = cost;
        this.defence = defence;
        this.owner = null;
        this.gameIO = gameIO;
        this.cardResources = cardResources;
    }

    public abstract void activate();
    
    public boolean lay(IDisc disc) {
        
        boolean succeed = false;
        IResourceStorage bank = cardResources.getBank();
        
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
        
        cardResources.getDiscardStorage().pushCard(this);
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
    
    public void setState(ICardState state) {
        this.state = state;
    }
    
    public ICardState getState() {
        return state;
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
    
    public IGameIO getGameIO() {
        return gameIO;
    }
    
    public ICardResources getCardResources() {
        return cardResources;
    }
    
}
