package tests.unverified;

import framework.Test;
import framework.cards.Card;
import framework.interfaces.GameState;
import framework.interfaces.MoveMaker;
import framework.interfaces.activators.ScaenicusActivator;

/**
 *
 * Test basic activation of Scaenicus on a Legat
 * 
 * @author Robert Cen
 * 
 */

public class CardActivatorScaenicusMimicLegatTest extends Test {

	@Override
	public String getShortDescription() {
		return "Testing basic Scaenicus Activation on a Legat";
	}

	@Override
	public void run(GameState gameState, MoveMaker move) throws AssertionError,
			UnsupportedOperationException, IllegalArgumentException {
		gameState.setWhoseTurn(0);
		gameState.setPlayerVictoryPoints(0, 10);
		gameState.setPlayerVictoryPoints(1, 15);
		
		//Test that the current pool of points is correct
		assert(gameState.getPoolVictoryPoints() == 11);

		//Place cards on player discs
		Card[] cardsOnCurrentPlayerDisc = {Card.NOT_A_CARD,
				Card.SCAENICUS,
				Card.AESCULAPINUM,
				Card.ARCHITECTUS,
				Card.NOT_A_CARD,
				Card.LEGAT,
				Card.NOT_A_CARD};
		gameState.setPlayerCardsOnDiscs(0, cardsOnCurrentPlayerDisc);
		Card[] cardsOnOpponentPlayerDisc = {Card.NOT_A_CARD,
				Card.LEGAT,
				Card.CONSILIARIUS,
				Card.CONSUL,
				Card.NOT_A_CARD,
				Card.NOT_A_CARD,
				Card.NOT_A_CARD};
		gameState.setPlayerCardsOnDiscs(1, cardsOnOpponentPlayerDisc);
		
		//Set the action dice to activate card
		gameState.setActionDice(new int[] {2,4,6});
		
		//Activate the scaenicus to mimic Legat
		ScaenicusActivator scaenicusActivator = (ScaenicusActivator)move.chooseCardToActivate(2);
		scaenicusActivator.getScaenicusMimicTarget(6);
		scaenicusActivator.complete();
		
		//Test that scaenicus has activated 
		assert (gameState.getPlayerVictoryPoints(0) == 14);
		assert (gameState.getPlayerVictoryPoints(1) == 15);
		assert (gameState.getPoolVictoryPoints() == 7);
	}

}
