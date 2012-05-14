package tests.unverified;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import framework.Rules;
import framework.Test;
import framework.cards.Card;
import framework.interfaces.GameState;
import framework.interfaces.MoveMaker;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 12/05/12
 * Time: 7:57 PM
 * To change this template use File | Settings | File Templates.
 */
public class PlaythroughChrisFongTest extends Test {

    private final int PLAYER_1 = 0;
    private final int PLAYER_2 = 1;

    private List<Card> deck;
    private List<Card> discard;

    private Card[][] playerFields;
    private List<Card>[] playerHands;

    private int[] playerVPs;
    private int[] playerSestertiis;

    private GameState gameState;

    public String getShortDescription() {
        return "Playthrough of the Game";
    }

    public void run(GameState gameState, MoveMaker move) throws AssertionError,
            UnsupportedOperationException, IllegalArgumentException {

        this.gameState = gameState;

        /*
            Starting Process:
                * Initialises Deck to a pre-determined deck
                * Initialises Field to empty fields
                * Initialises Hands to empty hands
                * Initialises Players' VPs to 10
                * Initialises Players' Sestertiis to 0
         */

        initialiseDeck();
        initialiseDiscard();
        gameState.setDeck(deck);
        gameState.setDiscard(discard);

        assertDeck();

        initialisePlayerFields();
        transferFieldsToState();

        assertFields();

        initialisePlayerHands();
        transferHandsToState();

        assertHands();

        initialisePlayerVPs();
        transferVPsToState();

        initialisePlayerSestertiis();
        transferSestertiiToState();

        assertVPs();
        assertSestertiis();

        /*
            Set Hands:
                * Gives each Player their first 5 cards
                * Sets those cards in predetermined positions
                * Initialises to Player 1's turn

            Deck Indicator:
                #1

        */

        for (int i = 0; i < Rules.NUM_PLAYERS ; i++) {
            for (int j = 0; j < 5; j++) {
                playerHands[i].add(deck.remove(0));
            }
        }

        gameState.setDeck(deck);

        /*
            Hands:
                Player 1        Player 2:
                FORUM           CENTURIO
                MERCATUS        TRIBUNUSPLEBIS
                LEGAT           MACHINA
                PRAETORIANUS    CONSUL
                SCAENICUS       LEGIONARIUS

            Place on Fields:
			         			 1          2              3            4            5         6             7
            	Player 1:	<FORUM>,    <SCAENICUS>  ,<NOT_A_CARD>, <MERCATUS>,   <LEGAT>,<PRAETORIANUS>,<NOT_A_CARD>
				Player 2:	<NOT_A_CARD>,<CENTURIO>,<TRIBUNUSPLEBIS>,<MACHINA>,  <CONSUL>,<LEGIONARIUS>, <NOT_A_CARD>


        */

        playerHands[PLAYER_1] = new ArrayList<Card>();
        playerHands[PLAYER_2] = new ArrayList<Card>();

        playerFields[PLAYER_1] =
                new Card[] {
                        Card.FORUM,
                        Card.SCAENICUS,
                        Card.NOT_A_CARD,
                        Card.MERCATUS,
                        Card.LEGAT,
                        Card.PRAETORIANUS,
                        Card.NOT_A_CARD
                };

        playerFields[PLAYER_2] =
                new Card[] {
                        Card.NOT_A_CARD,
                        Card.CENTURIO,
                        Card.TRIBUNUSPLEBIS,
                        Card.MACHINA,
                        Card.CONSUL,
                        Card.LEGIONARIUS,
                        Card.NOT_A_CARD
                };

        transferHandsToState();
        transferFieldsToState();

        assertHands();
        assertFields();

        /*
            Set to Player 1's Turn
            Set Action Die:
                1 , 1 , 2
         */

        gameState.setWhoseTurn(PLAYER_1);
        gameState.setActionDice(new int[]{1,1,2});

        /*
            Use Action Die 2 to Draw 2 Cards

            Cards Drawn:
                * Nero
                * Architectus

            Select Architectus

         */

        playerHands[PLAYER_1].add(Card.ARCHITECTUS);
        discard.add(Card.NERO);
        deck.remove(Card.NERO);
        deck.remove(Card.ARCHITECTUS);

        move.activateCardsDisc(2, Card.ARCHITECTUS);
        assertHands();
        assertDeck();
        assertDiscard();

        gameState.setWhoseTurn(PLAYER_1);


    }

    private void initialiseDeck () {

        deck = new ArrayList<Card>();
        deck.add(Card.FORUM);
        deck.add(Card.MERCATUS);
        deck.add(Card.LEGAT);
        deck.add(Card.PRAETORIANUS);
        deck.add(Card.SCAENICUS);
        deck.add(Card.CENTURIO);
        deck.add(Card.TRIBUNUSPLEBIS);
        deck.add(Card.MACHINA);
        deck.add(Card.CONSUL);
        deck.add(Card.LEGIONARIUS); // #1
        deck.add(Card.NERO);
        deck.add(Card.ARCHITECTUS); // #2
        deck.add(Card.LEGIONARIUS);
        deck.add(Card.HARUSPEX);
        deck.add(Card.TURRIS);
        deck.add(Card.SCAENICUS);
        deck.add(Card.VELITES);
        deck.add(Card.SENATOR);
        deck.add(Card.ARCHITECTUS);
        deck.add(Card.FORUM);
        deck.add(Card.MACHINA);
        deck.add(Card.AESCULAPINUM);
        deck.add(Card.CONSUL);
        deck.add(Card.GLADIATOR);
        deck.add(Card.CONSILIARIUS);
        deck.add(Card.ONAGER);
        deck.add(Card.TURRIS);
        deck.add(Card.BASILICA);
        deck.add(Card.MERCATOR);
        deck.add(Card.AESCULAPINUM);
        deck.add(Card.BASILICA);
        deck.add(Card.LEGIONARIUS);
        deck.add(Card.FORUM);
        deck.add(Card.TRIBUNUSPLEBIS);
        deck.add(Card.FORUM);
        deck.add(Card.MERCATUS);
        deck.add(Card.CONSILIARIUS);
        deck.add(Card.FORUM);
        deck.add(Card.SICARIUS);
        deck.add(Card.CENTURIO);
        deck.add(Card.GLADIATOR);
        deck.add(Card.TEMPLUM);
        deck.add(Card.VELITES);
        deck.add(Card.LEGAT);
        deck.add(Card.ESSEDUM);
        deck.add(Card.ONAGER);
        deck.add(Card.ESSEDUM);
        deck.add(Card.HARUSPEX);
        deck.add(Card.FORUM);
        deck.add(Card.TEMPLUM);
        deck.add(Card.PRAETORIANUS);
        deck.add(Card.SENATOR);

    }

    private void initialiseDiscard () {

        this.discard = new ArrayList<Card>();

    }

    private void initialisePlayerFields () {

        playerFields = new Card[Rules.NUM_PLAYERS][Rules.NUM_DICE_DISCS];

        for (int i = 0; i < Rules.NUM_PLAYERS; i++) {
            for (int j = 0; j < Rules.NUM_DICE_DISCS; j++) {
                playerFields[i][j] = Card.NOT_A_CARD;
            }
        }

    }

    private void initialisePlayerHands() {

        playerHands = (ArrayList<Card>[]) new ArrayList[Rules.NUM_PLAYERS];

        for (int i = 0; i < Rules.NUM_PLAYERS; i++) {
            playerHands[i] = new ArrayList<Card>();
        }

    }

    private void initialisePlayerVPs() {

        playerVPs = new int[Rules.NUM_PLAYERS];

        for (int i = 0; i < Rules.NUM_PLAYERS; i++) {
            playerVPs[i] = 10;
        }

    }

    private void initialisePlayerSestertiis() {

        this.playerSestertiis = new int[Rules.NUM_PLAYERS];

        for (int i = 0; i < Rules.NUM_PLAYERS; i++) {
            this.playerSestertiis[i] = 0;
        }

    }

    private void transferSestertiiToState() {
        for (int i = 0; i < Rules.NUM_PLAYERS; i++) {
            this.gameState.setPlayerSestertii(i, playerSestertiis[i]);
        }
    }

    private void transferVPsToState() {
        for (int i = 0; i < Rules.NUM_PLAYERS; i++) {
            this.gameState.setPlayerVictoryPoints(i, playerVPs[i]);
        }
    }

    private void transferHandsToState() {
        for (int i = 0; i < Rules.NUM_PLAYERS; i++) {
            this.gameState.setPlayerHand(i, playerHands[i]);
        }
    }

    private void transferFieldsToState() {

        for (int i = 0; i < Rules.NUM_PLAYERS; i++) {
            this.gameState.setPlayerCardsOnDiscs(i,playerFields[i]);
        }

    }

    private void assertDeck() {
        gameState.getDeck().equals(deck);
    }

    private void assertDiscard() {
        gameState.getDiscard().equals(discard);
    }

    private void assertFields() {

        for (int i = 0; i < Rules.NUM_PLAYERS; i++) {
            assert(Arrays.equals(gameState.getPlayerCardsOnDiscs(i),playerFields[i]));
        }

    }

    private void assertHands() {

        for (int i = 0; i < Rules.NUM_PLAYERS; i++) {
            assert(gameState.getPlayerHand(i).containsAll(playerHands[i]));
        }

    }

    private void assertVPs() {

        for (int i = 0; i < Rules.NUM_PLAYERS; i++) {
            assert(gameState.getPlayerVictoryPoints(i) == playerVPs[i]);
        }

    }

    private void assertSestertiis() {

        for (int i = 0; i < Rules.NUM_PLAYERS; i++) {
            assert(gameState.getPlayerSestertii(i) == playerSestertiis[i]);
        }

    }

}
