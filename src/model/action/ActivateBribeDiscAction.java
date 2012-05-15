package model.action;

import framework.Rules;
import framework.cards.Card;
import framework.interfaces.GameState;
import framework.interfaces.MoveMaker;

public class ActivateBribeDiscAction extends MoveMakingAction {
    
    private static final int BRIBE_INDEX = Rules.BRIBE_DISC - 1;
    
    private int diceToUse;
    private Card activatedCard;
    
    public ActivateBribeDiscAction(GameState g, MoveMaker moveMaker, int diceToUse) {
        super(g,moveMaker);
        
        this.activatedCard = getActivatedCard(BRIBE_INDEX);
        this.diceToUse = diceToUse;
    }
    
    @Override
    public void run() {
        getMoveMaker().activateBribeDisc(diceToUse);
    }
    
    public boolean isValid() {
        
        boolean valid = false;
        
        if(getActivatedCard(BRIBE_INDEX) != Card.NOT_A_CARD 
              && getActivatedCard(BRIBE_INDEX) == activatedCard) {
            valid = true;
        }
        
        return valid;
    }
    
    private Card getActivatedCard(int index) {
        
        int currentPlayer = getGameState().getWhoseTurn();
        Card[] field = getGameState().getPlayerCardsOnDiscs(currentPlayer);
        
        Card activatedCard = field[index];
        
        return activatedCard;
    }
}
