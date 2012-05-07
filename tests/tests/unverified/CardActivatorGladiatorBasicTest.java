package tests.unverified;

import java.util.ArrayList;
import java.util.Collection;

import framework.Rules;
import framework.Test;
import framework.cards.Card;
import framework.interfaces.GameState;
import framework.interfaces.MoveMaker;
import framework.interfaces.activators.GladiatorActivator;

/**
 * 
 * Test the functionality of Gladiator
 * 
 * @author Junjie CHEN
 *
 */

public class CardActivatorGladiatorBasicTest extends Test {

    @Override
    public String getShortDescription() {
        return "Test the functionality of Gladiator";
    }

    @Override
    public void run(GameState gameState, MoveMaker move) throws AssertionError,
            UnsupportedOperationException, IllegalArgumentException {
        
        gameState.setWhoseTurn(0);
        
        Collection<Card> hand = new ArrayList<Card>();
        
        //opponent's hand has nothing
        gameState.setPlayerHand(1, hand);
        
        hand.add(Card.GLADIATOR);
        
        gameState.setPlayerHand(0, hand);
        
        //the opponent's field would have 3 character cards
        Card[] field = new Card[Rules.NUM_DICE_DISCS];
        for(int i = 0; i < field.length; i++) {
            field[i] = Card.NOT_A_CARD;
        }
        field[0] = Card.SCAENICUS;
        field[1] = Card.NERO;
        field[3] = Card.CONSUL;
        
        gameState.setPlayerCardsOnDiscs(1, field);
        
        gameState.setPlayerSestertii(0, 10);
        gameState.setActionDice(new int[] {1,1,1});
        
        //place gladiator at dice disc 1
        move.placeCard(Card.GLADIATOR, Rules.DICE_DISC_1);
        
        assert(gameState.getPlayerSestertii(0) == 4);
        assert(gameState.getPlayerHand(0).size() == 0);
        assert(!gameState.getPlayerHand(0).contains(Card.GLADIATOR));
        
        field = gameState.getPlayerCardsOnDiscs(0);
        assert(field[0] == Card.GLADIATOR);
        
        //attack the nero
        GladiatorActivator activator = (GladiatorActivator) move.chooseCardToActivate(Rules.DICE_DISC_1);
        activator.chooseDiceDisc(Rules.DICE_DISC_2);
        activator.complete();
        
        //nero would be back to opponent's hand
        assert(gameState.getPlayerHand(1).size() == 1);
        assert(gameState.getPlayerHand(1).contains(Card.NERO));
        field = gameState.getPlayerCardsOnDiscs(1);
        assert(field[1] == Card.NOT_A_CARD);
        
        //attack the scaenicus
        activator = (GladiatorActivator) move.chooseCardToActivate(Rules.DICE_DISC_1);
        activator.chooseDiceDisc(Rules.DICE_DISC_1);
        activator.complete();
        
        //scaenicus would be back to opponent's hand
        assert(gameState.getPlayerHand(1).size() == 2);
        assert(gameState.getPlayerHand(1).contains(Card.NERO));
        assert(gameState.getPlayerHand(1).contains(Card.SCAENICUS));
        field = gameState.getPlayerCardsOnDiscs(1);
        assert(field[0] == Card.NOT_A_CARD);
        assert(field[1] == Card.NOT_A_CARD);
    }

}
