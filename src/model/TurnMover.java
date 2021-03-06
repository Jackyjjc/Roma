package model;

import framework.cards.Card;
import model.action.MeowAction;
import model.turn.Turn;

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

    public void startGame() {
        currentTurn = new Turn(g);
        turns.add(currentTurn);
    }

    public void advanceTurn() {

        assert (!g.isGameCompleted());

        g.advanceTurn();
        currentTurn = new Turn(g);
        turns.add(currentTurn);

        meow();

        for (ITurnListener l : turnListeners) {
            l.endTurn();
        }
    }

    /**
     * The cats meow at the beginning of each turn
     */
    private void meow() {
        Card[] field = g.getPlayerCardsOnDiscs(g.getWhoseTurn());

        for (int diceDisc = 0; diceDisc < field.length; diceDisc++) {
            if (field[diceDisc] == Card.KAT) {
                currentTurn.addAction(new MeowAction(g, diceDisc));
            }
        }
    }

    public Turn getTurn(int numTurnsAgo) {

        final int FIRST_TURN = 0;

        Turn pastTurn = null;

        if ((turns.size() - numTurnsAgo) > 0) {
            pastTurn = turns.get((turns.size() - 1) - numTurnsAgo);
        } else {
            pastTurn = turns.get(FIRST_TURN);
        }

        return pastTurn;

    }

    public void replay(int numTurnsAgo) {

        final int FIRST_TURN = 0;
        
        List<Turn> turnsToReplay = new ArrayList<Turn>();

        int currentPlayer = g.getCurrentPlayer().getId();
        int currentTurn = turns.size() - 1;
        numTurnsAgo = turns.size() - 1 - numTurnsAgo;

        if (numTurnsAgo < FIRST_TURN) {
            numTurnsAgo = FIRST_TURN;
        }

        for (int i = numTurnsAgo; i <= currentTurn; i++) {
            turnsToReplay.add(turns.remove(numTurnsAgo));
        }

        turnsToReplay.get(0).restore();
        Turn restoredTurn = new Turn(g);
        turns.add(restoredTurn);
        this.currentTurn = restoredTurn;

        for (int i = 0; !g.isGameCompleted() && i < turnsToReplay.size(); i++) {
            Turn turn = turnsToReplay.get(i);
            turn.run();
        }

        if (g.isGameCompleted()) {
            g.timeParadox(currentPlayer);
        }

    }

    public void addTurnListener(ITurnListener listener) {
        if (!turnListeners.contains(listener)) {
            turnListeners.add(listener);
        }
    }

    public void removeTurnListener(ITurnListener listener) {
        turnListeners.remove(listener);
    }
    
    @Override
    public void gameOver() {
        g.setFinish(true);
    }

    public Turn getCurrentTurn() {
        return currentTurn;
    }

}
