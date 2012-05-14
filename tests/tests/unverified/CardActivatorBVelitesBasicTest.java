package tests.unverified;
 
import java.util.LinkedList;
 
import framework.Test;
import framework.Rules;
import framework.cards.Card;
import framework.interfaces.GameState;
import framework.interfaces.MoveMaker;
import framework.interfaces.activators.VelitesActivator;
 
/**
 *
 * Test the basic functionality of Velites
 *
 * @author Calvin Tam
 * @author Nicholas Higgins
 *
 */
 
public class CardActivatorBVelitesBasicTest extends Test {
 
    @Override
    public String getShortDescription() {
        return "Testing the basic mechanics of Velites";
    }
 
    @Override
    public void run(GameState gameState, MoveMaker move) throws AssertionError,
            UnsupportedOperationException, IllegalArgumentException {
 
      Card[] discs = new Card[Rules.NUM_DICE_DISCS];
      for (int i = 0; i < Rules.NUM_DICE_DISCS; i++) {
         discs[i] = Card.NOT_A_CARD;
      }
      for (int i = 0; i < Rules.NUM_PLAYERS; i++) {
         gameState.setPlayerCardsOnDiscs(i, discs);
      }
 
      gameState.setWhoseTurn(0);
 
      for (int i = 0; i < Rules.NUM_PLAYERS; i++) {
         gameState.setPlayerSestertii(i, 50);
         gameState.setPlayerVictoryPoints(i, 10);
 
      }
 
      // Assuming there are only 2 players
      LinkedList<Card>hand = new LinkedList<Card>();
      hand.add(Card.VELITES);
      gameState.setPlayerHand(0, hand);
 
      hand = new LinkedList<Card>();
      hand.add(Card.SICARIUS);
      gameState.setPlayerHand(1, hand);
 
      // Player 0's move
      gameState.setActionDice(new int[] {1,2,3});
      move.placeCard(Card.VELITES, Rules.DICE_DISC_1);
 
      assert(gameState.getPlayerSestertii(0) == 50 - 5);
      assert(gameState.getPlayerSestertii(1) == 50);
      assert(gameState.getPlayerHand(0).size() == 0);
 
      Card[] temp;
      temp = gameState.getPlayerCardsOnDiscs(0);
      assert(temp[0] == Card.VELITES);
 
      assert(!gameState.isGameCompleted());
 
      move.endTurn();
 
      //Player 1's move
      move.placeCard(Card.SICARIUS, Rules.DICE_DISC_2);
 
      temp = gameState.getPlayerCardsOnDiscs(Rules.DICE_DISC_1);
      assert(temp[0] == Card.NOT_A_CARD);
      assert(temp[1] == Card.SICARIUS);
      assert(temp[2] == Card.NOT_A_CARD);
      assert(temp[3] == Card.NOT_A_CARD);
      assert(temp[4] == Card.NOT_A_CARD);
      assert(temp[5] == Card.NOT_A_CARD);
      assert(temp[6] == Card.NOT_A_CARD);
 
      move.endTurn();
 
      //Player 0's move
      gameState.setActionDice(new int[] {1,2,1});
 
      //Testing that the attack did not defeat Sicarius
      VelitesActivator activator = (VelitesActivator) move.chooseCardToActivate(Rules.DICE_DISC_1);
      activator.giveAttackDieRoll(1);
      activator.chooseDiceDisc(Rules.DICE_DISC_2);
      activator.complete();
      temp = gameState.getPlayerCardsOnDiscs(1);
      assert(temp[0] == Card.NOT_A_CARD);
      assert(temp[1] == Card.SICARIUS);
      assert(temp[2] == Card.NOT_A_CARD);
      assert(temp[3] == Card.NOT_A_CARD);
      assert(temp[4] == Card.NOT_A_CARD);
      assert(temp[5] == Card.NOT_A_CARD);
      assert(temp[6] == Card.NOT_A_CARD);
 
      //Testing the corner case when the attack value is the same as defence value
      activator = (VelitesActivator) move.chooseCardToActivate(Rules.DICE_DISC_1);
      activator.giveAttackDieRoll(2);
      activator.chooseDiceDisc(Rules.DICE_DISC_2);
      activator.complete();
      temp = gameState.getPlayerCardsOnDiscs(1);
      assert(temp[0] == Card.NOT_A_CARD);
      assert(temp[1] == Card.NOT_A_CARD);
      assert(temp[2] == Card.NOT_A_CARD);
      assert(temp[3] == Card.NOT_A_CARD);
      assert(temp[4] == Card.NOT_A_CARD);
      assert(temp[5] == Card.NOT_A_CARD);
      assert(temp[6] == Card.NOT_A_CARD);
      assert(!gameState.isGameCompleted());
 
      move.endTurn();
 
 
      //Lay more cards down
      //Player 1's turn
      hand = new LinkedList<Card>();
      hand.add(Card.ARCHITECTUS);
      hand.add(Card.BASILICA);
      gameState.setPlayerHand(1, hand);
      move.placeCard(Card.ARCHITECTUS, Rules.BRIBE_DISC);
      move.placeCard(Card.BASILICA, Rules.DICE_DISC_3);
      temp = gameState.getPlayerCardsOnDiscs(1);
      assert(temp[0] == Card.NOT_A_CARD);
      assert(temp[1] == Card.NOT_A_CARD);
      assert(temp[2] == Card.BASILICA);
      assert(temp[3] == Card.NOT_A_CARD);
      assert(temp[4] == Card.NOT_A_CARD);
      assert(temp[5] == Card.NOT_A_CARD);
      assert(temp[6] == Card.ARCHITECTUS);
      move.endTurn();
 
      //Player 0's turn -  and now test that when attack value is greater
      //The card is also defeated
      gameState.setActionDice(new int[] {1,1,1});
      activator = (VelitesActivator)move.chooseCardToActivate(Rules.DICE_DISC_1);
      activator.giveAttackDieRoll(6);
      activator.chooseDiceDisc(Rules.BRIBE_DISC);
      activator.complete();
      temp = gameState.getPlayerCardsOnDiscs(1);
      assert(temp[0] == Card.NOT_A_CARD);
      assert(temp[1] == Card.NOT_A_CARD);
      assert(temp[2] == Card.BASILICA);
      assert(temp[3] == Card.NOT_A_CARD);
      assert(temp[4] == Card.NOT_A_CARD);
      assert(temp[5] == Card.NOT_A_CARD);
      assert(temp[6] == Card.NOT_A_CARD);
 
      // Making sure that Velites cannot attack a building
      activator = (VelitesActivator)move.chooseCardToActivate(Rules.DICE_DISC_1);
      activator.giveAttackDieRoll(6);
      activator.chooseDiceDisc(Rules.DICE_DISC_3);
      activator.complete();
      temp = gameState.getPlayerCardsOnDiscs(1);
      assert(temp[0] == Card.NOT_A_CARD);
      assert(temp[1] == Card.NOT_A_CARD);
      assert(temp[2] == Card.BASILICA);
      assert(temp[3] == Card.NOT_A_CARD);
      assert(temp[4] == Card.NOT_A_CARD);
      assert(temp[5] == Card.NOT_A_CARD);
      assert(temp[6] == Card.NOT_A_CARD);
 
 
    }
}