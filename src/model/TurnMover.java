package model;

import java.util.ArrayList;
import java.util.List;

public class TurnMover implements ITurnMover {

    private List<ITurnListener> turnListeners;
    private Game g;
    private List<Turn> turns;
    
    private Turn currentTurn;
    
    public TurnMover(Game g) {

        this.g = g;
        turnListeners = new ArrayList<ITurnListener>();
        turns = new ArrayList<Turn>();
    }
    
    public void addTurnListener(ITurnListener listener) {
        if(!turnListeners.contains(listener)) {
            turnListeners.add(listener);
        }
    }
    
    public void removeTurnListener(ITurnListener listener) {
        turnListeners.remove(listener);
    }
    
    public void startGame() {
        currentTurn = new Turn(g);
        turns.add(currentTurn);
    }
    
    public void advanceTurn() {
        
        assert(!g.isGameCompleted());
        
        g.advanceTurn();
        currentTurn = new Turn(g);
        turns.add(currentTurn);
        
        for(ITurnListener l : turnListeners) {
            l.endTurn();
        }
    }
    
    public void gameOver() {
        g.setFinish(true);
        System.out.println("Game Over");
    }

    public Turn getTurn(int numTurnsAgo) {
        
        int FIRST_TURN = 0;
        
        Turn pastTurn = null;
   
        if ((turns.size() - numTurnsAgo) > 0) {
            pastTurn = turns.get((turns.size() - 1) - numTurnsAgo);
        } else {
            pastTurn = turns.get(FIRST_TURN);
        }
        
        return pastTurn;
        
    }

    public void replay (int numTurnsAgo) {
        
        List<Turn> turnsToReplay =  new ArrayList<Turn>();
        
        int currentTurn = turns.size() - 1;
        numTurnsAgo = turns.size() - 1 - numTurnsAgo;

        for (int i = numTurnsAgo ; i <= currentTurn; i++) {
            turnsToReplay.add(turns.remove(numTurnsAgo));
        }
        
        turnsToReplay.get(0).restore(g);
        Turn restoredTurn = new Turn(g);
        turns.add(restoredTurn);
        this.currentTurn = restoredTurn;
        
        for (Turn turn : turnsToReplay) {
            turn.run(g);
        }
    }

    public Turn getCurrentTurn() {
        return currentTurn;
    }
    
}
