package model;

import model.card.AbstractCard;

/**
 * An interface which defines a card disc on a player's field
 * It's represented as a doubly linked list
 *
 * @author Jacky Chen
 * @author Chris Fong
 */

public interface IDisc {

    public int getIndex();

    public void block();

    public boolean isBlocked();

    public void activateCard();

    public boolean layCard(AbstractCard c);

    public boolean isEmpty();

    public AbstractCard getCard();

    public void removeCard();

    public void addLayCardListener(IDiscListener listener);

    public void removeLayCardListener(IDiscListener listener);

    public void addTimeTraveller(int travelTime);

    public IPlayer getOwner();

    public void setPrev(IDisc prev);

    public IDisc getPrev();

    public void setNext(IDisc next);

    public IDisc getNext();

}
