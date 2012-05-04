package model.card;

import java.util.ArrayList;
import java.util.List;

import model.ICardResources;
import model.IDisc;
import model.IPlayer;
import model.Notifier;
import model.TurnCards;
import framework.cards.Card;
import framework.interfaces.activators.PraetorianusActivator;

class Praetorianus extends AbstractCard implements TurnCards, PraetorianusActivator {

    private static final int COST = 4;
    private static final int DEFENCE = 4;
    
    List<IDisc> affectedDiscs;
    
    Praetorianus(ICardResources cardResources, Notifier notifier) {
        super(Card.PRAETORIANUS, CardType.CHARACTER,
              COST, DEFENCE, cardResources, notifier);
        
        this.affectedDiscs = new ArrayList<IDisc>();
    }

    public void activate() {
        
    }

    public void magicMethod() {
        for(IDisc disc : affectedDiscs) {
            disc.unBlock();
            affectedDiscs.remove(disc);
        }
    }

    public void chooseDiceDisc(int diceDisc) {
        
        IPlayer opponent = getOwner().getOpponent();
        
        IDisc disc = opponent.getField().getDisc(diceDisc);
        
        disc.block();
        affectedDiscs.add(disc);
    }

    public void complete() {
        //nothing to do
    }
    
}
