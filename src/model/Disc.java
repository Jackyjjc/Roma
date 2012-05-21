package model;

import model.card.AbstractCard;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The basics of a disc
 *
 * @author Chris Fong
 * @author Junjie CHEN
 */

public class Disc implements IDisc, ITurnListener {

    private int index;
    private IPlayer owner;
    private AbstractCard card;
    private Map<AbstractCard, Integer> timeTravellers;

    private boolean isBlocked;
    private int blockTurnCounter;

    private IDisc prev;
    private IDisc next;

    private List<IDiscListener> discListeners;

    public Disc(int index, IPlayer owner, ITurnMover turnMover) {

        this.index = index;
        this.owner = owner;

        isBlocked = false;
        discListeners = new ArrayList<IDiscListener>();

        timeTravellers = new HashMap<AbstractCard, Integer>();

        //when the turn ends, the disc would get notified
        turnMover.addTurnListener(this);
    }

    public int getIndex() {
        return index;
    }

    public void block() {
        blockTurnCounter = 0;
        isBlocked = true;
    }

    public boolean isBlocked() {
        return isBlocked;
    }

    public void activateCard() {
        if (!isEmpty() && !isBlocked()) {
            getCard().initialise();
        }
    }

    public boolean layCard(AbstractCard c) {

        boolean succeed = false;

        if (c != null) {

            if (!isEmpty()) {
                card.disCard();
            }

            card = c;
            c.setDisc(this);
            c.setOwner(getOwner());

            succeed = true;

            notifyLayCardListeners();
        }

        return succeed;
    }

    public void addTimeTraveller(int travelTime) {
        timeTravellers.put(getCard(), travelTime);
        this.card = null;
    }

    public boolean isEmpty() {
        return (card == null);
    }

    public AbstractCard getCard() {

        AbstractCard returnCard = null;

        if (!isEmpty()) {
            returnCard = card;
        }

        return returnCard;
    }

    public void removeCard() {

        if (!isEmpty()) {
            card = null;
            notifyLayCardListeners();
        }
    }

    public void addLayCardListener(IDiscListener listener) {
        discListeners.add(listener);
    }

    public void removeLayCardListener(IDiscListener listener) {
        discListeners.remove(listener);
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

    private void notifyLayCardListeners() {
        for (IDiscListener l : discListeners) {
            l.update(this);
        }
    }

    public void endTurn() {

        for (Map.Entry<AbstractCard, Integer> timeTraveller : timeTravellers.entrySet()) {
            if (timeTraveller.getValue() == 1) {
                this.layCard(timeTraveller.getKey());
                timeTravellers.remove(timeTraveller.getKey());
            } else {
                timeTraveller.setValue(timeTraveller.getValue() - 1);
            }
        }

        if (blockTurnCounter == 1) {
            unBlock();
        } else {
            blockTurnCounter++;
        }
    }

    private void unBlock() {
        blockTurnCounter = 0;
        isBlocked = false;
    }
}
