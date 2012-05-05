package model;

import model.card.AbstractCard;

/**
 * An interface which defines a card disc on a player's field
 * It's represented as a doubly linked list
 * 
 * @author Jacky Chen
 * @author Chris Fong
 *
 */

public interface IDisc {

    public boolean isBlocked();
    
    public void block();
    
    public void unBlock();
    
    public int getIndex();
    
    public boolean isDiscEmpty();
    
    public boolean layCard(AbstractCard c);
    
    public AbstractCard getCard();
    
    public AbstractCard removeCard();
    
    public void addLayCardListener(IListener listener);
    
    public void removeLayCardListener(IListener listener);
    
    public void setOwner(IPlayer player);
    
    public IPlayer getOwner();
    
    public void setPrev(IDisc prev);
    
    public IDisc getPrev();
    
    public void setNext(IDisc next);
    
    public IDisc getNext();
}
