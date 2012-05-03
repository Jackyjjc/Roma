package model.cardcollection;

import model.card.Card;
import model.card.CardFactory;

public class CardCollectionFactory {
    
    //Alphabetical
    private static final int[] DEFAULT_NUM_CARDS = {
            2,2,2,2,2,2,2,6,2,2,2,3,2,1,2,1,2,2,2,2,1,2,2,2,2
    };
    
    public static CardCollection create(boolean isDeck, CardFactory factory) {
        
        CardCollection collection = new CardCollection(factory);
        
        if(isDeck) {
            buildDeck(collection,factory);
        }
        
        collection.shuffle();
        
        return collection;
    }
 
    private static void buildDeck(CardCollection collection, CardFactory factory) {
        
        Card[] cards = Card.values();
        
        for (int i = 0; i < DEFAULT_NUM_CARDS.length; i++) {
            for (int j = 0; j < DEFAULT_NUM_CARDS[i]; j++) {
                collection.pushCard(factory.create(cards[i]));
            }
        }
    }
    
}