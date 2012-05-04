package model.card;

import java.util.ArrayList;
import java.util.List;

import model.ICardResources;
import model.IDisc;
import model.IField;
import model.Notifier;
import model.TurnCards;
import framework.cards.Card;

class Turris extends AbstractCard implements TurnCards {

    private static final int COST = 6;
    private static final int DEFENCE = 6;
    
    private List<AbstractCard> affectedCards;
    
    Turris(ICardResources cardResources, Notifier notifier) {
        
        super(Card.TURRIS, CardType.BUILDING,
              COST, DEFENCE, cardResources, notifier);
        
        this.affectedCards = new ArrayList<AbstractCard>();
    }

    public void activate() {
        //do nothing
    }
    
    @Override
    public boolean lay(IDisc disc) {
        magicMethod();
        
        return true;
    }
    
    @Override
    public void disCard() {
        for(AbstractCard card : affectedCards) {
            card.setDefence(card.getDefence() - 1);
        }
        
        affectedCards.clear();
    }

    public void magicMethod() {
        
        IField discs = getOwner().getField();
        AbstractCard card;
        
        for(IDisc disc : discs) {
            
            card = disc.getCard();
            
            if(!affectedCards.contains(card) && card != this) {
                  card.setDefence(card.getDefence() + 1);
                  affectedCards.add(card);
            }
        }
    }
}
