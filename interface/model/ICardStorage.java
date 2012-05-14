package model;

import java.util.Collection;
import java.util.List;

import model.card.AbstractCard;
import model.card.CardType;
import framework.cards.Card;

/**
 * 
 * This interface defines an abstract storage for Cards
 * It represents the hand and the deck in the game but not the field
 * It acts like a double ended Queue
 * 
 * @author Jacky Chen
 * @author Chris Fong
 */

public interface ICardStorage extends Iterable<AbstractCard> {

    public void pushCard(AbstractCard c);
    
    public AbstractCard popCard();
    
    public AbstractCard getCard(int index);
    
    public AbstractCard getCard(Card name);
    
    public List<AbstractCard> getCardsOf(CardType type);
    
    public void setCards(Collection<Card> names);
    
    public List<Card> getCardsWithNames();
    
    public void appendCard (AbstractCard c);
    
    public void removeCard(AbstractCard c);
    
    public void shuffle();
    
    public int size();
    
    public void setOwner(IPlayer player);
   
    public void addDiscardListener (IDiscardListener listener);
    
    public void removeDiscardListener (IDiscardListener listener);

}