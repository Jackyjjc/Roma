package model.card;

import java.util.ArrayList;
import java.util.List;

import model.ICardResources;
import model.IDisc;
import model.IDiscListener;
import model.IField;
import model.IGameIO;
import framework.cards.Card;

class Turris extends AbstractCard implements IDiscListener {

    private static final int COST = 6;
    private static final int DEFENCE = 6;
    
    private List<AbstractCard> affectedCards;
    
    Turris(ICardResources cardResources, IGameIO gameIO) {
        
        super(Card.TURRIS, CardType.BUILDING,
              COST, DEFENCE, cardResources, gameIO);
        
        this.affectedCards = new ArrayList<AbstractCard>();
    }

    public void activate() {
        //do nothing
    }
    
    @Override
    public boolean lay(IDisc disc) {
        
        super.lay(disc);
        
        observeDiscs();
        
        IField discs = getOwner().getField();
        
        for(IDisc d : discs) {
            addEffects(d.getCard());
        }
        
        return true;
    }
    
    @Override
    public void disCard() {
        
        for(AbstractCard card : affectedCards) {
            card.setDefence(card.getDefence() - 1);
        }
        
        affectedCards.clear();
        
        super.disCard();
    }
    
    public void update(IDisc disc) {
        
        if(affectedCards.contains(disc.getCard())) {
            affectedCards.remove(disc.getCard());
        } else {
            addEffects(disc.getCard());
        }
    }
    
    private void addEffects(AbstractCard card) {
            
        if (card != null && !affectedCards.contains(card) && card != this) {
            card.setDefence(card.getDefence() + 1);
            affectedCards.add(card);
        }
    }
    
    private void observeDiscs() {
        
        IField discs = getOwner().getField();
        
        for(IDisc disc : discs) {
            disc.addDiscListener(this);
        }
    }
}
