package model;

import java.util.ArrayList;
import java.util.List;

import model.card.ITurnListener;

public class TurnNotifier {

    private int turnNum;
    private List<ITurnListener> turnCards;
    
    public TurnNotifier() {
        turnCards = new ArrayList<ITurnListener>();
    }
    
    public void addTurnListener(ITurnListener listener) {
        if(!turnCards.contains(listener)) {
            turnCards.add(listener);
        }

    }
    
    public void removeTurnListener(ITurnListener listener) {
        turnCards.remove(listener);
    }
    
    public int getTurn() {
        return turnNum;
    }
    
    public void turnAdvance() {
        
        turnNum++;
        
        for(ITurnListener l : turnCards) {
            l.turnChecking(turnNum);
        }
    }
    
}
