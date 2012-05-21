package model.action;

import framework.cards.Card;
import framework.interfaces.GameState;
import framework.interfaces.MoveMaker;

import java.util.List;

public class DrawCardsAction extends MoveMakingAction {

    private int diceToUse;
    private Card name;

    public DrawCardsAction(GameState g, MoveMaker moveMaker,
                           int dice, Card name) {
        super(g, moveMaker);
        this.diceToUse = dice;
        this.name = name;
    }

    public void run() {
        getMoveMaker().activateCardsDisc(diceToUse, name);
    }

    public boolean isValid() {

        boolean isValid = false;

        List<Card> deck = getGameState().getDeck();

        if (deck.size() >= diceToUse) {
            for (int i = 0; i < diceToUse; i++) {
                if (deck.get(i) == name) {
                    isValid = true;
                }
            }
        }

        return isValid;
    }
}