package model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import model.action.Action;
import framework.Rules;
import framework.cards.Card;

public class Turn {

	private List<Action> moves;

	private final int PLAYER_1 = 0;
	private final int PLAYER_2 = 1;

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

	private int poolVictoryPoints;

	public Turn(Game g) {

		this.whoseTurn = g.getWhoseTurn();
        this.turnNum = g.getTurn();
		
		this.playerVPs = new int[Rules.NUM_PLAYERS];
		this.playerSestertiis = new int[Rules.NUM_PLAYERS];
		this.playerHands = new Collection[Rules.NUM_PLAYERS];
		this.playerDiscs = new Card[Rules.NUM_PLAYERS][Rules.NUM_DICE_DISCS];

		for (int playerNum = 0; playerNum < Rules.NUM_PLAYERS; playerNum++) {
		    
			this.playerVPs[playerNum] = g.getPlayerVictoryPoints(playerNum);
			this.playerSestertiis[playerNum] = g.getPlayerSestertii(playerNum);
			this.playerHands[playerNum] = g.getPlayerHand(playerNum);
			this.playerDiscs[playerNum] = g.getPlayerCardsOnDiscs(playerNum);

		}

		this.deck = g.getDeck();
		this.discards = g.getDiscard();

        this.actionDie = g.getActionDice();
		this.poolVictoryPoints = g.getPoolVictoryPoints();
		this.moves = new ArrayList<Action>();

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
			g.setPlayerSestertii(playerNum,this.playerVPs[playerNum]);
			g.setPlayerHand(playerNum,this.playerHands[playerNum]);
			g.setPlayerCardsOnDiscs(playerNum,this.playerDiscs[playerNum]);

		}

		g.setDeck(this.deck);
		g.setDiscard(this.discards);

	}

	public void run (Game g) {

	    g.setActionDice(actionDie);
	    
	    for(Action action : moves) {
	        
	        if(action.isValid()) {
	            action.run();
	        } else {
	            System.out.println("Miaowwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwww");
	            assert(false);
	        }
	    }

	}

	public void addAction(Action action) {
	    moves.add(action);
	}

    public void updateActionDice(Game g) {
        this.actionDie = g.getActionDice();
    }
	
}
