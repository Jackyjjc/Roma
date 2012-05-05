package model.card;

import java.util.ArrayList;
import java.util.List;

import model.ICardResources;
import model.IDisc;
import model.IGameIO;
import model.card.state.BlockDiscState;
import framework.cards.Card;

class Praetorianus extends AbstractCard implements ITurnCards{

    private static final int COST = 4;
    private static final int DEFENCE = 4;
    
    List<IDisc> affectedDiscs;
    
    Praetorianus(ICardResources cardResources, IGameIO gameIO) {
        super(Card.PRAETORIANUS, CardType.CHARACTER,
              COST, DEFENCE, cardResources, gameIO);
        
        this.affectedDiscs = new ArrayList<IDisc>();
    }

    public void activate() {
        
        BlockDiscState block = new BlockDiscState(this, affectedDiscs);
        block.setNextState(null);
        
        setState(block);
    }

    public void turnChecking() {
        for(IDisc disc : affectedDiscs) {
            disc.unBlock();
            affectedDiscs.remove(disc);
        }
    }
    
}
