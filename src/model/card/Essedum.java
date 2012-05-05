package model.card;

import java.util.ArrayList;
import java.util.List;

import model.ICardResources;
import model.IDisc;
import model.IField;
import model.IGameIO;
import framework.cards.Card;

class Essedum extends AbstractCard implements ITurnCards {

    private static final int COST = 6;
    private static final int DEFENCE = 3;
    
    private List<AbstractCard> affectedCards;
     
    Essedum(ICardResources cardResources, IGameIO gameIO) {
        super(Card.ESSEDUM, CardType.CHARACTER,
              COST, DEFENCE, cardResources, gameIO);
        
        this.affectedCards = new ArrayList<AbstractCard>();
    }
    
    public void activate() {
        addEffects();
    }

    public void disCard() {
        super.disCard();
        removeEffects();
    }
    
    private void addEffects() {
        
        IField discs = getOwner().getOpponent().getField();
        AbstractCard card;
        
        for(IDisc disc : discs) {
            
            if(disc != null) {
                card = disc.getCard();
                card.setDefence(card.getDefence() - 2);
                affectedCards.add(card);
            }
        }  
    }
    
    private void removeEffects() {
        
        for(AbstractCard card : affectedCards) {
            card.setDefence(card.getDefence() + 2);
            affectedCards.remove(card);
        }
    }

    public void turnChecking() {
        removeEffects();
    }
   
}
