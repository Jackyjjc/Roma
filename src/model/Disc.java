package model;

import java.util.ArrayList;
import java.util.List;

import model.card.AbstractCard;

public class Disc implements IDisc, ITurnListener {

    private int index;
    private boolean isBlocked;
    private AbstractCard card;
    private int count;
    
    private List<IDiscListener> discListeners;
    private IPlayer owner;
    
    private IDisc prev;
    private IDisc next;
    
    public Disc (ITurnMover turnMover, int index) {
        this.index = index;
        isBlocked = false;
        discListeners = new ArrayList<IDiscListener>();
        turnMover.addTurnListener(this);
    }
    
    public boolean isDiscEmpty() {
        return (card == null);
    }
    
    public boolean layCard(AbstractCard c) {

        boolean succeed = false;

        if (c != null) {

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
            
            notifyAllListeners();
            
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
        count = 0;
        isBlocked = true;
    }
    
    private void unBlock() {
        count = 0;
        isBlocked = false;
    }

    public void addDiscListener(IDiscListener listener) {
        discListeners.add(listener);
    }
    
    public void removeDiscListener(IDiscListener listener) {
        discListeners.remove(listener);
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
        for(IDiscListener l : discListeners) {
            l.update(this);
        }
    }

    public void notifyEndTurn() {
        if(count == 1) {
            unBlock();
        } else {
            count++;
        }
    }
}
