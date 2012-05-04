package model.card;

import java.util.ArrayList;
import java.util.List;

import model.ICardResources;
import model.IDisc;
import model.IField;
import model.Notifier;
import model.TurnCards;
import framework.cards.Card;
import framework.interfaces.activators.EssedumActivator;

class Essedum extends AbstractCard implements TurnCards, EssedumActivator {

    private static final int COST = 6;
    private static final int DEFENCE = 3;
    
    private List<AbstractCard> affectedCards;
     
    Essedum(ICardResources cardResources, Notifier notifier) {
        super(Card.ESSEDUM, CardType.CHARACTER,
              COST, DEFENCE, cardResources, notifier);
        
        this.affectedCards = new ArrayList<AbstractCard>();
    }

    public void activate() {
        
        IField discs = getOwner().getOpponent().getField();
        AbstractCard card;
        
        for(IDisc disc : discs) {
            
            card = disc.getCard();

            card.setDefence(card.getDefence() - 2);
            
            affectedCards.add(card);
        }  
    }

    @Override
    public void disCard() {
        //FIXME later
    }
    
    public void magicMethod() {
        for(AbstractCard card : affectedCards) {
            card.setDefence(card.getDefence() + 2);
            affectedCards.remove(card);
        }
    }

    @Override
    public void complete() {
        // TODO Auto-generated method stub
        
    }
    
}
