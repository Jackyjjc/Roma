//package model;
//
//import java.util.Collection;
//import java.util.List;
//
//import framework.Rules;
//import framework.cards.Card;
//import framework.interfaces.GameState;
//
//public class Turn {
//
//	private List<Input> moves;
//
//	private final int PLAYER_1 = 0;
//	private final int PLAYER_2 = 1;
//
//	private int whoseTurn;
//
//	//Two players' VPs
//	private int[] playerVPs;
//
//	//Two players' Sestertii
//	private int[] playerSestertiis;
//
//	//Two players' Hands
//	private Collection<Card>[] playerHands;
//
//	//Two players' fields
//	private Card[][] playerDiscs;
//
//	private int[] actionDie;
//
//	private List<Card> deck;
//	private List<Card> discards;
//
//	private int poolVictoryPoints;
//
//	public Turn(GameState g) {
//
//		this.whoseTurn = g.getWhoseTurn();
//
//		for (int playerNum = 0; playerNum < Rules.NUM_PLAYERS; playerNum++) {
//
//			this.playerVPs[playerNum] = g.getPlayerVictoryPoints(playerNum);
//			this.playerSestertiis[playerNum] = g.getPlayerSestertii(playerNum);
//			this.playerHands[playerNum] = g.getPlayerHand(playerNum);
//			this.playerDiscs[playerNum] = g.getPlayerCardsOnDiscs(playerNum);
//
//		}
//
//		this.deck = g.getDeck();
//		this.discards = g.getDiscard();
//
//		this.poolVictoryPoints = g.getPoolVictoryPoints();
//
//	}
//
//	public static void main() {
//
//		Game.run();
//		for (each turn) {
//			Stack.add(Turn);
//			Turn t = Turn.get(currentTurn-x);
//			t.insert(card,disc);
//			t.restore(g);
//			while (t != currentTurn) {
//				t.runTurn(g);
//				t = new Turn(g);
//				t = t.getNext();
//			}
//		}
//
//	}
//
//	public void insert (Card c, int player, int index) {
//
//		if (this.playerDiscs[player][index] != Card.NOT_A_CARD) {
//			discards.add(this.playerDiscs[player][index]);
//		}
//
//		this.playerDiscs[player][index] = c;
//
//	}
//
//	public void restore(GameState g) {
//
//		g.setWhoseTurn(this.whoseTurn);
//
//		for (int playerNum = 0; playerNum < Rules.NUM_PLAYERS; playerNum++) {
//
//			g.setPlayerVictoryPoints(playerNum,this.playerVPs[playerNum]);
//			g.setPlayerSestertii(playerNum,this.playerVPs[playerNum]);
//			g.setPlayerHand(playerNum,this.playerHands[playerNum]);
//			g.setPlayerCardsOnDiscs(playerNum,this.playerDiscs[playerNum]);
//
//		}
//
//		g.setDeck(this.deck);
//		g.setDiscard(this.discards);
//
//	}
//
//	public void runTurn (GameState g) {
//
//		for (Input i : moves) {
//			g.makeMove(i);
//		}
//
//	}
//
//
//}
