package model.cardcollection;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import model.ICardStorage;
import model.card.AbstractCard;
import model.card.Card;
import model.card.CardFactory;
import model.card.CardType;

public class CardCollection implements ICardStorage {

    private CardFactory factory;
    List<AbstractCard> cards;

    CardCollection(CardFactory factory) {
        
        this.factory = factory;
        this.cards = new ArrayList<AbstractCard>();
    }
    
    public void setCards(Collection<Card> names) {
        
        cards.clear();
        
        for (Card temp : names) {
            appendCard(factory.create(temp));
        }
        
    }

    public void pushCard(AbstractCard c) {
        cards.add(0,c);
    }
    
    public AbstractCard popCard() {
        
        AbstractCard card = null;
        
        if(cards.size() > 0) {
            card = cards.remove(0);
        }
        
        return card;
    }

    public void appendCard(AbstractCard c) {
        cards.add(c);
    }
    
    public void removeCard(AbstractCard c) {
        
        if(c != null) {
            cards.remove(c); 
        }

    }
    
    public AbstractCard getCard(int index) {
        
        AbstractCard card = null;
        
        if(index >= 0 && index < cards.size()) {
            card = cards.get(index);
        }
        
        return card;
    }
    
    public AbstractCard getCard(Card name) {
        
        AbstractCard card = null;
        
        for(AbstractCard temp : cards) {
            if(temp.getName() == name) {
                card = temp;
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
     * @param the type of card you want to get from the piles
     * 
     * @return a list of cards of the same type as the type got passed in
     */
    public List<AbstractCard> getCardsOf(CardType type) {
        
        int length = cards.size();
        List<AbstractCard> result = new ArrayList<AbstractCard>();
        
        for(int i = 0; i < length; i++) {
            if(cards.get(i).getType() == type) {
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
}
