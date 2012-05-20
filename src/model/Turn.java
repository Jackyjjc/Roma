package model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import model.action.Action;
import framework.Rules;
import framework.cards.Card;

public class Turn {

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

	@SuppressWarnings("unchecked")
    public Turn (Game g) {

		this.playerVPs = new int[Rules.NUM_PLAYERS];
		this.playerSestertiis = new int[Rules.NUM_PLAYERS];
		this.playerHands = new Collection[Rules.NUM_PLAYERS];
		this.playerDiscs = new Card[Rules.NUM_PLAYERS][Rules.NUM_DICE_DISCS];

        this.moves = new ArrayList<Action>();

        generateTurn(g);

	}

    public void generateTurn(Game g) {

        updateTurn(g);
        updateActionDice(g);
        updateDeck(g);
        updateDiscard(g);
        updateField(g);
        updateHand(g);
        updateMoney(g);
        updateVP(g);
        
    }


    public boolean insert (Card c, int player, int index) {

	    boolean isValidTravel = false;
	    
	    if(this.playerDiscs[player][index] != c) {
	        
	        if (this.playerDiscs[player][index] != Card.NOT_A_CARD) {
	            discards.add(this.playerDiscs[player][index]);
	        }

	        this.playerDiscs[player][index] = c;
	        isValidTravel = true;
	    }
	    
        return isValidTravel;
	}

	public void restore(Game g) {

        g.setWhoseTurn(this.whoseTurn);
        g.setTurnNum(this.turnNum);

		for (int playerNum = 0; playerNum < Rules.NUM_PLAYERS; playerNum++) {

			g.setPlayerVictoryPoints(playerNum,this.playerVPs[playerNum]);
			g.setPlayerSestertii(playerNum,this.playerSestertiis[playerNum]);
			g.setPlayerHand(playerNum,this.playerHands[playerNum]);
			g.setPlayerCardsOnDiscs(playerNum,this.playerDiscs[playerNum]);

		}

		g.setDeck(this.deck);
		g.setDiscard(this.discards);

	}

    public void run (Game g) {

        g.setActionDice(actionDie);
        Action action = null;

        for(int i = 0 ; !g.isGameCompleted() && i < moves.size(); i++) {

            action = moves.get(i);

            if(action.isValid()) {
                action.run();
            } else {
                g.setFinish(true);
            }
        }

    }

	public void addAction(Action action) {
	    moves.add(action);
	}

	public void updateField(Game g) {
        for (int playerNum = 0; playerNum < Rules.NUM_PLAYERS; playerNum++) {
            this.playerDiscs[playerNum] = g.getPlayerCardsOnDiscs(playerNum);
        }
	}
	
	public void updateDeck(Game g) {
        this.deck = g.getDeck();
	}
	
    public void updateTurn(Game g) {
        this.turnNum = g.getTurn();
        this.whoseTurn = g.getWhoseTurn();
    }
	
	public void updateDiscard(Game g) {
        this.discards = g.getDiscard();
	}
	
    public void updateMoney(Game g) {
        for (int playerNum = 0; playerNum < Rules.NUM_PLAYERS; playerNum++) {
            this.playerSestertiis[playerNum] = g.getPlayerSestertii(playerNum);
        }
    }
	
	public void updateVP(Game g) {
        for (int playerNum = 0; playerNum < Rules.NUM_PLAYERS; playerNum++) {
            this.playerVPs[playerNum] = g.getPlayerVictoryPoints(playerNum);
        }
	}
	
	public void updateHand(Game g) {
        for (int playerNum = 0; playerNum < Rules.NUM_PLAYERS; playerNum++) {
            this.playerHands[playerNum] = g.getPlayerHand(playerNum);
        }
	}
	
    public void updateActionDice(Game g) {
        this.actionDie = g.getActionDice();
    }
	
}
