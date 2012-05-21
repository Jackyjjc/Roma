package model.turn;

import framework.Rules;
import framework.cards.Card;
import model.Game;
import model.ICardTracker;
import model.action.Action;
import model.card.AbstractCard;
import model.card.Kat;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Turn {

    private Game g;

    private List<Action> moves;

    private int whoseTurn;
    private int turnNum;

    //Two players' VPs
    private int[] playerVPs;

    //Two players' Sestertii
    private int[] playerSestertiis;

    //Two players' Hands
    private Collection<Card>[] playerHands;

    //Two players' fields
    private Card[][] playerDiscs;

    private int[] actionDie;

    private List<Card> deck;
    private List<Card> discards;

    private ICardTracker[] katLives;

    @SuppressWarnings("unchecked")
    public Turn(Game g) {

        this.g = g;

        this.playerVPs = new int[Rules.NUM_PLAYERS];
        this.playerSestertiis = new int[Rules.NUM_PLAYERS];
        this.playerHands = new Collection[Rules.NUM_PLAYERS];
        this.playerDiscs = new Card[Rules.NUM_PLAYERS][Rules.NUM_DICE_DISCS];

        this.moves = new ArrayList<Action>();

        this.katLives = new LivesTracker[Rules.NUM_PLAYERS];

        generateTurn();

    }

    public void generateTurn() {

        updateTurn();
        updateActionDice();
        updateDeck();
        updateDiscard();
        updateField();
        updateHand();
        updateMoney();
        updateVP();

    }


    public boolean insert(AbstractCard card, int player, int index) {

        boolean isValidTravel = false;

        if (this.playerDiscs[player][index] == Card.KAT && !(card instanceof Kat)) {

            g.timeParadox(player);

        } else if (this.playerDiscs[player][index] != card.getName()) {

            if (this.playerDiscs[player][index] != Card.NOT_A_CARD) {
                discards.add(this.playerDiscs[player][index]);
            }

            if (card instanceof Kat) {
                katLives[player].addKat(index, ((Kat) card).getLives());
            }

            this.playerDiscs[player][index] = card.getName();
            isValidTravel = true;
        }

        return isValidTravel;
    }

    public void restore() {

        g.setWhoseTurn(this.whoseTurn);
        g.setTurnNum(this.turnNum);

        for (int playerNum = 0; playerNum < Rules.NUM_PLAYERS; playerNum++) {

            g.setPlayerVictoryPoints(playerNum, this.playerVPs[playerNum]);
            g.setPlayerSestertii(playerNum, this.playerSestertiis[playerNum]);
            g.setPlayerHand(playerNum, this.playerHands[playerNum]);
            g.setPlayerCardsOnDiscs(playerNum, this.playerDiscs[playerNum]);
            g.getPlayer(playerNum).getField().setLives(katLives[playerNum]);
        }

        g.setDeck(this.deck);
        g.setDiscard(this.discards);

    }

    public void run() {

        g.setActionDice(actionDie);
        Action action = null;

        for (int i = 0; !g.isGameCompleted() && i < moves.size(); i++) {

            action = moves.get(i);

            if (action.isValid()) {
                action.run();
            } else {
                g.setFinish(true);
            }
        }

    }

    public void addAction(Action action) {
        moves.add(action);
    }

    public void updateField() {
        for (int playerNum = 0; playerNum < Rules.NUM_PLAYERS; playerNum++) {
            this.playerDiscs[playerNum] = g.getPlayerCardsOnDiscs(playerNum);
            this.katLives[playerNum] = new LivesTracker(g.getPlayer(playerNum).getField());
        }
    }

    public void updateDeck() {
        this.deck = g.getDeck();
    }

    public void updateTurn() {
        this.turnNum = g.getTurn();
        this.whoseTurn = g.getWhoseTurn();
    }

    public void updateDiscard() {
        this.discards = g.getDiscard();
    }

    public void updateMoney() {
        for (int playerNum = 0; playerNum < Rules.NUM_PLAYERS; playerNum++) {
            this.playerSestertiis[playerNum] = g.getPlayerSestertii(playerNum);
        }
    }

    public void updateVP() {
        for (int playerNum = 0; playerNum < Rules.NUM_PLAYERS; playerNum++) {
            this.playerVPs[playerNum] = g.getPlayerVictoryPoints(playerNum);
        }
    }

    public void updateHand() {
        for (int playerNum = 0; playerNum < Rules.NUM_PLAYERS; playerNum++) {
            this.playerHands[playerNum] = g.getPlayerHand(playerNum);
        }
    }

    public void updateActionDice() {
        this.actionDie = g.getActionDice();
    }

}
