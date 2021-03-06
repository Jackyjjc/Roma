package model.cardcollection;

import framework.cards.Card;
import model.ICardStorage;
import model.IDiscardListener;
import model.IPlayer;
import model.card.AbstractCard;
import model.card.CardFactory;
import model.card.CardType;

import java.util.*;

public class CardCollection implements ICardStorage, Iterable<AbstractCard> {

    private IPlayer owner;
    private CardFactory factory;
    private List<AbstractCard> cards;
    private List<IDiscardListener> discardListeners;

    CardCollection(CardFactory factory) {

        this.factory = factory;
        this.discardListeners = new Vector<IDiscardListener>();
        this.cards = new ArrayList<AbstractCard>();
    }

    public void setCards(Collection<Card> names) {

        cards.clear();

        for (Card cardName : names) {
            appendCard(factory.create(cardName));
        }
    }

    public void pushCard(AbstractCard c) {

        cards.add(0, c);

        for (IDiscardListener listener : discardListeners) {
            listener.alert();
        }

        if (this.cards.contains(c)) {
            c.setOwner(owner);
        }

    }

    public AbstractCard popCard() {

        AbstractCard card = null;

        if (cards.size() > 0) {
            card = cards.remove(0);
        }

        return card;
    }

    public void appendCard(AbstractCard c) {

        c.setOwner(owner);
        cards.add(c);
    }

    public void removeCard(AbstractCard c) {

        if (c != null) {
            cards.remove(c);
        }

    }

    public AbstractCard getCard(int index) {

        AbstractCard card = null;

        if (index >= 0 && index < cards.size()) {
            card = cards.get(index);
        }

        return card;
    }

    public AbstractCard getCard(Card name) {

        AbstractCard card = null;
        boolean found = false;

        for (int i = 0; i < cards.size() && !found; i++) {
            if (cards.get(i).getName() == name) {
                card = cards.get(i);
                found = true;
            }
        }

        return card;
    }

    public void shuffle() {
        Collections.shuffle(cards);
    }

    public boolean isEmpty() {
        return (cards.size() == 0);
    }

    public int size() {
        return cards.size();
    }

    /**
     * Get a list of specific types of cards
     *
     * @param type the type of card you want to get from the piles
     * @return a list of cards of the same type as the type got passed in
     */
    public List<AbstractCard> getCardsOf(CardType type) {

        int length = cards.size();
        List<AbstractCard> result = new ArrayList<AbstractCard>();

        for (int i = 0; i < length; i++) {
            if (cards.get(i).getType() == type) {
                result.add(cards.get(i));
            }
        }

        return result;
    }

    public List<Card> getCardsWithNames() {

        List<Card> temp = new ArrayList<Card>();

        for (int i = 0; i < cards.size(); i++) {

            temp.add(cards.get(i).getName());

        }

        return temp;

    }

    public void setOwner(IPlayer player) {
        this.owner = player;
    }

    public Iterator<AbstractCard> iterator() {
        return cards.iterator();
    }

    public void addDiscardListener(IDiscardListener listener) {
        this.discardListeners.add(listener);
    }

    public void removeDiscardListener(IDiscardListener listener) {
        this.discardListeners.remove(listener);
    }

}
