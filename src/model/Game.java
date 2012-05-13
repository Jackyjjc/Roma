package model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import model.card.AbstractCard;
import model.card.CardFactory;
import model.cardcollection.CardCollectionFactory;
import model.runner.CardActivateManager;
import framework.cards.Card;
import framework.interfaces.GameState;

public class Game implements GameState, IGameDisplayState, ICardResources, IGameIO, IPlayerManager, ITurnMover {
	
    private static final int TOTAL_MONEY = Integer.MAX_VALUE;
    private static final int TOTAL_VP = 36;
    private static final int NUM_ACTION_DICE = 3;
    
    private static final boolean DECK = true;
    
    private ICardStorage deck;
    private ICardStorage discard;
    private DiceManager diceManager;
    private CardFactory cardFactory;
    private CardActivateManager activateManager;
    
	private IResourceStorage bank;
	private IPlayer currentPlayer;
	private int turnNum;
	private int numPlayers;
	
	private InputHandler inputHandler;
    private Notifier notifier;
	List<ITurnListener> turnListeners;
    
	public Game (int numPlayers) {
	    
	    this.numPlayers = numPlayers;
	    
	    inputHandler = new InputHandler(this);
	    this.notifier = new Notifier(this);
	    this.diceManager = new DiceManager(NUM_ACTION_DICE);
	   
		this.bank = new ResourceStorage(TOTAL_MONEY, TOTAL_VP);
		
        this.cardFactory = new CardFactory(this, this, this);
        this.activateManager = new CardActivateManager(this, this);
        
		this.deck = CardCollectionFactory.create(DECK, cardFactory);
		this.discard = CardCollectionFactory.create(!DECK, cardFactory);
		
		this.turnListeners = new ArrayList<ITurnListener>();
		
		createPlayers(numPlayers);
	}
	
	public int getTurn() {
	    return turnNum;
	}
	
	public IPlayer getCurrentPlayer() {
		return currentPlayer;
	}
	
	public IResourceStorage getBank() {
		return bank;
	}

    private void createPlayers(int numPlayers) {
        
        IPlayer[] players = new IPlayer[numPlayers];
        
        for(int i = 0; i < numPlayers; i++) {
            players[i] = Player.createPlayer(this, i, bank, cardFactory);
        }
        
        //set up the relationship between players
        for(int i = 0; i < numPlayers; i++) {
            players[i].setOpponent(players[(i + 1) % numPlayers]);
        }
        
        currentPlayer = players[0];
    }

    public int getNumPlayers() {
        return numPlayers;
    }
    
    public DiceManager getDiceManager() {
        return diceManager;
    }

    public ICardStorage getDeckStorage() {
        return deck;
    }
    
    public ICardStorage getDiscardStorage() {
        return discard;
    }

    public void advanceTurn() {
        
        this.turnNum++;
        
        for(ITurnListener l : turnListeners) {
            l.notifyEndTurn();
        }
        
        currentPlayer = currentPlayer.getOpponent();
        inputHandler.setList(currentPlayer.getHand());
    }
    
    public Notifier getNotifier() {
        return notifier;
    }

    public InputHandler getInputHandler() {
        return inputHandler;
    }
    
    public CardActivateManager getCardActivateManager() {
        return activateManager;
    }
    
    public void addTurnListener(ITurnListener listener) {
        if(!turnListeners.contains(listener)) {
            turnListeners.add(listener);
        }
    }
    
    public void removeTurnListener(ITurnListener listener) {
        turnListeners.remove(listener);
    }
    
    /* =========================================================================*
     * 
     *          ACCEPTANCE TESTS IMPLEMENTATION - AS COMMANDED BY CAESAR
     * 
     * 
     * =========================================================================*/
    
    public int getWhoseTurn() {
        return currentPlayer.getId();
    }

    public void setWhoseTurn(int player) {
        currentPlayer = getPlayer(player); 
    }

    public List<Card> getDeck() {
        return this.deck.getCardsWithNames();
    }
    
    public void setDeck(List<Card> deck) {
        this.deck.setCards(deck);
    }

    public List<Card> getDiscard() {
        return discard.getCardsWithNames();
    }

    public void setDiscard(List<Card> discard) {
        this.discard.setCards(discard);
    }

    public int getPlayerSestertii(int playerNum) {
        
        return getPlayer(playerNum).getMoney();
    }

    public void setPlayerSestertii(int playerNum, int amount) {
        
        getPlayer(playerNum).setMoney(amount);
        
    }

    public int getPlayerVictoryPoints(int playerNum) {

        return getPlayer(playerNum).getVP();
        
    }

    public void setPlayerVictoryPoints(int playerNum, int points) {

        IPlayer player = getPlayer(playerNum);
        
        if(player.getVP() >= points) {
            player.transferVP(bank, player.getVP() - points);
        } else {
            bank.transferVP(player, points - player.getVP());
        }
        
    }

    public Collection<Card> getPlayerHand(int playerNum) {
        
        return getPlayer(playerNum).getHand().getCardsWithNames();
        
    }

    public void setPlayerHand(int playerNum, Collection<Card> hand) {
        
        IPlayer p = getPlayer(playerNum);
        p.getHand().setCards(hand);
        
    }

    public Card[] getPlayerCardsOnDiscs(int playerNum) {
        
        IPlayer p = getPlayer(playerNum);
        
        int numDiscs = p.getField().getNumDiscs();
        
        Card[] temp = new Card[numDiscs];
        
        for (int i = 0; i < numDiscs; i++) {
        
            if(p.getField().getCard(i) != null) {
                temp[i] = p.getField().getCard(i).getName();
            } else {
                temp[i] = Card.NOT_A_CARD;
            }
            
        }
        
        return temp;
        
    }

    public void setPlayerCardsOnDiscs(int playerNum, Card[] discCards) {
        
        IPlayer p = getPlayer(playerNum);
        int numDiscs = p.getField().getNumDiscs();
        AbstractCard card = null;
        
        for (int i = 0; i < numDiscs; i++) {
            if(discCards[i] != Card.NOT_A_CARD) {
                card = cardFactory.create(discCards[i]);
                p.getField().getDisc(i).layCard(card);
            } else {
                p.getField().getDisc(i).removeCard();
            }
        }
    }

    public int[] getActionDice() {
        return diceManager.getActionDiceValues();
    }

    public void setActionDice(int[] dice) {
        diceManager.setActionDice(dice);
    }

    public int getPoolVictoryPoints() {
        return bank.getVP();
    }
    
    public IPlayer getPlayer(int id) {
        
        IPlayer temp = currentPlayer;
        
        while (temp.getId() != id) {
          temp = temp.getOpponent();
        }
        
        return temp;
        
    }

    public boolean isGameCompleted() {
        return false;
    }
    
}
