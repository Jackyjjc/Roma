package tests.unverified;


import java.util.ArrayList;
import java.util.Collection;

import framework.Rules;
import framework.Test;
import framework.cards.Card;
import framework.interfaces.GameState;
import framework.interfaces.MoveMaker;
import framework.interfaces.activators.GladiatorActivator;

/*
 * Template from Junjie Chen
 * Test by Patrick Wong 
 * 8/5/12
 */

public class CardActivatorGladiatorBasicTest extends Test {
 
	@Override
	public String getShortDescription() {
		return "Test the the card Gladiator";
	}

	@Override

	public void run(GameState gameState, MoveMaker move) throws AssertionError,
		UnsupportedOperationException, IllegalArgumentException {
		
		//player 1 only has two gladiator cards in hand
		Collection<Card> hand = new ArrayList<Card>();
		hand.add(Card.GLADIATOR);
		gameState.setPlayerHand(0, hand);
		assert(gameState.getPlayerHand(0).size() == 1);
		assert(gameState.getPlayerHand(0).contains(Card.GLADIATOR));

		//player 2 has an empty hand
		Collection<Card> hand2 = new ArrayList<Card>();
		gameState.setPlayerHand(1, hand2);
		assert(gameState.getPlayerHand(1).size() == 0);
		
		//no other cards on the field		
		Card[] player1Field = new Card[Rules.NUM_DICE_DISCS];
		for(int i = 0; i < player1Field.length; i++) {
			player1Field[i] = Card.NOT_A_CARD;
		}
		gameState.setPlayerCardsOnDiscs(0, player1Field);
		
		//player 2 has a velites on disc 3
		Card[] player2Field = new Card[Rules.NUM_DICE_DISCS];
		for (int i = 0; i < player2Field.length; i++) {
			if (i == 4) {
				player2Field[i] = Card.VELITES;
			} else {
				player2Field[i] = Card.NOT_A_CARD;
			}
		}
		gameState.setPlayerCardsOnDiscs(1, player2Field);
		
		gameState.setWhoseTurn(0);
		gameState.setPlayerSestertii(0, 20);
		gameState.setPlayerHand(0, hand);
		gameState.setActionDice(new int[] {3,4,4});
		 
		//================== test1 =====================
		//lay card and check the correct amount of Sestertii
		//is deducted from the player
		move.placeCard(Card.GLADIATOR, Rules.DICE_DISC_3);
		assert(gameState.getPlayerSestertii(0) == 14);
		assert(gameState.getPlayerHand(0).size() == 0);

		//check the card has been laid properly
		player1Field = gameState.getPlayerCardsOnDiscs(0);
		assert(player1Field[2] == Card.GLADIATOR);
		
		//=================== test2 =====================
		
		//initialise the card, check dice is used to initialise
		GladiatorActivator activator = (GladiatorActivator) move.chooseCardToActivate(Rules.DICE_DISC_3);
		activator.chooseDiceDisc(Rules.DICE_DISC_5);
		activator.complete();
		assert(gameState.getActionDice().length == 2);
		for (int i = 0; i < gameState.getActionDice().length; i++) {
			assert(gameState.getActionDice()[i] != 3);
		}
		
		//check card was bounced off field back into player 2's hand
		player2Field = gameState.getPlayerCardsOnDiscs(1);
		assert(player2Field[4] == Card.NOT_A_CARD);
        assert(gameState.getPlayerHand(1).size() == 1);
		assert(gameState.getPlayerHand(1).contains(Card.VELITES));
				
		//================== test3 ======================
		
		//test if player 2 is able to do the same
		move.endTurn();
		hand.clear();
		hand2.clear();
		
		//give player 2 a gladiator to play with
		hand2.add(Card.GLADIATOR);
		gameState.setPlayerHand(1, hand2);
		assert(gameState.getPlayerHand(1).size() == 1);
		assert(gameState.getPlayerHand(1).contains(Card.GLADIATOR));
		
		
		//player 2's field should be empty still
		for(int i = 0; i < player2Field.length; i++) {
			assert(player2Field[i] == Card.NOT_A_CARD);
		}
		gameState.setPlayerSestertii(1, 20);
		gameState.setActionDice(new int[] {6,4,4});
		
		//lay the card
		move.placeCard(Card.GLADIATOR, Rules.DICE_DISC_6);
		
		//test if the money get removed from the player
		assert(gameState.getPlayerSestertii(1) == 14);
		//test handsize decreased
		assert(gameState.getPlayerHand(1).size() == 0);
		
		//test if the card has been placed on the field
		player2Field = gameState.getPlayerCardsOnDiscs(1);
		assert(player2Field[5] == Card.GLADIATOR);
		
		//initialise the card
		activator = (GladiatorActivator) move.chooseCardToActivate(Rules.DICE_DISC_6);
		activator.chooseDiceDisc(Rules.DICE_DISC_3);
		activator.complete();
		assert(gameState.getActionDice().length == 2);
		for (int i = 0; i < gameState.getActionDice().length; i++) {
			assert(gameState.getActionDice()[i] != 6);
		}
		
		player2Field = gameState.getPlayerCardsOnDiscs(0);
		assert(player2Field[2] == Card.NOT_A_CARD);
        assert(gameState.getPlayerHand(0).size() == 1);
		assert(gameState.getPlayerHand(0).contains(Card.GLADIATOR));
	}
}