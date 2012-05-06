package model.card;

import java.util.ArrayList;
import java.util.List;

import model.ICardResources;
import model.IDisc;
import model.IGameIO;
import model.TurnNotifier;
import model.card.state.BlockDiscState;
import framework.cards.Card;

class Praetorianus extends AbstractCard implements ITurnListener {

    private static final int COST = 4;
    private static final int DEFENCE = 4;
    
    private TurnNotifier turnNotifier;
    private int lastBlockTurn;
    List<IDisc> affectedDiscs;
    
    Praetorianus(ICardResources cardResources, IGameIO gameIO) {
        super(Card.PRAETORIANUS, CardType.CHARACTER,
              COST, DEFENCE, cardResources, gameIO);
        
        this.affectedDiscs = new ArrayList<IDisc>();
        this.turnNotifier = cardResources.getTurnNotifier();
        this.lastBlockTurn = 0;
    }

    public void activate() {
        
        BlockDiscState block = new BlockDiscState(this, affectedDiscs);
        block.setNextState(null);
        
        setState(block);
        
        turnNotifier.addTurnListener(this);
    }

    public void turnChecking(int turnNum) {
        if(turnNum - lastBlockTurn >= 2) {
            for(IDisc disc : affectedDiscs) {
                disc.unBlock();
                affectedDiscs.remove(disc);
            }
        }
    }
    
}
