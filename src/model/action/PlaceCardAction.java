package model.action;

import framework.cards.Card;
import framework.interfaces.GameState;
import framework.interfaces.MoveMaker;

import java.util.Collection;

public class PlaceCardAction extends MoveMakingAction {

    private Card cardToPlace;
    private int discToPlaceOn;

    public PlaceCardAction(GameState g, MoveMaker moveMaker,
                           Card cardToPlace, int discToPlaceOn) {
        super(g, moveMaker);
        this.cardToPlace = cardToPlace;
        this.discToPlaceOn = discToPlaceOn;
    }

    public void run() {
        getMoveMaker().placeCard(cardToPlace, discToPlaceOn);
    }

    public boolean isValid() {

        boolean isValid = false;

        int currentPlayer = getGameState().getWhoseTurn();
        Collection<Card> hand = getGameState().getPlayerHand(currentPlayer);

        if (hand.contains(cardToPlace)) {
            isValid = true;
        }

        return isValid;
    }

}
