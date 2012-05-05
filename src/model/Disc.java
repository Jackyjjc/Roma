package model;

import java.util.ArrayList;
import java.util.List;

import model.card.AbstractCard;

public class Disc implements IDisc {

    private int index;
    private boolean isBlocked;
    private AbstractCard card;
    
    private List<IListener> layCardListeners;
    
    private IPlayer owner;
    
    private IDisc prev;
    private IDisc next;
    
    public Disc () {
        isBlocked = false;
        layCardListeners = new ArrayList<IListener>();
    }
    
    public boolean isDiscEmpty() {
        return (card == null);
    }
    
    public boolean layCard(AbstractCard c) {
       
        boolean succeed = false;
        
        if (c != null || !isBlocked()) {
            
            if(!isDiscEmpty()) {
                card.disCard();
            }
            
            card = c;
            c.setDisc(this);
            c.setOwner(getOwner());
            
            succeed = true;
            
            notifyAllListeners();
        }
        
        return succeed;
    }
    
    public AbstractCard removeCard() {
        
        AbstractCard returnCard = getCard();
        
        if(!isDiscEmpty()) {
            card = null;
        }
        
        return returnCard;
    
    }
    
    public AbstractCard getCard() {
        
        AbstractCard returnCard = null;
        
        if(!isDiscEmpty()) {
            returnCard = card;
        }
        
        return returnCard;
    }
    
    public int getIndex() {
        return index;
    }
    
    public boolean isBlocked() {
        return this.isBlocked;
    }
    
    public void block() {
        isBlocked = true;
    }
    
    public void unBlock() {
        isBlocked = false;
    }

    public void addLayCardListener(IListener listener) {
        layCardListeners.add(listener);
    }
    
    public void setOwner(IPlayer player) {
        this.owner = player;
    }
    
    public IPlayer getOwner() {
        return owner;
    }
    
    public IDisc getPrev() {
        return prev;
    }

    public void setPrev(IDisc prev) {
        this.prev = prev;
    }

    public IDisc getNext() {
        return next;
    }

    public void setNext(IDisc next) {
        this.next = next;
    }
    
    private void notifyAllListeners() {
        for(IListener l : layCardListeners) {
            l.update();
        }
    }
}
