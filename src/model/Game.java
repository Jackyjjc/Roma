package model;

import framework.cards.Card;
import framework.interfaces.GameState;
import model.card.AbstractCard;
import model.card.CardFactory;
import model.cardcollection.CardCollectionFactory;
import model.runner.CardActivateManager;

import java.util.Collection;
import java.util.List;

public class Game implements GameState, IGameDisplayState, ICardResources, 
                             IGameIO, IPlayerManager{
	
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
	private TurnMover turnMover;
    
	private boolean isFinished;
    
	public Game (int numPlayers) {
	    
	    this.numPlayers = numPlayers;
	    this.turnMover = new TurnMover(this);
	    
	    inputHandler = new InputHandler(this);
	    this.notifier = new Notifier(this);
	    this.diceManager = new DiceManager(NUM_ACTION_DICE);
	   
        this.bank = new ResourceStorage(TOTAL_MONEY, TOTAL_VP, turnMover);
		
        this.cardFactory = new CardFactory(this);
        this.activateManager = new CardActivateManager(this, this, this, turnMover);
        
		this.deck = CardCollectionFactory.create(DECK, cardFactory);
		this.discard = CardCollectionFactory.create(!DECK, cardFactory);
		
		createPlayers(numPlayers);
		
		isFinished = false;
		turnMover.startGame();
	}
	
	public int getTurn() {
	    return turnNum;
	}

    public void setTurnNum(int turn) {
        this.turnNum = turn;
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
            players[i] = Player.createPlayer(i, cardFactory, turnMover, bank);
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
    
    public Notifier getNotifier() {
        return notifier;
    }

    public InputHandler getInputHandler() {
        return inputHandler;
    }
    
    public CardActivateManager getCardActivateManager() {
        return activateManager;
    }
    
    public TurnMover getTurnMover() {
        return turnMover;
    }
    
    public void advanceTurn() {
        
        assert(!isFinished);
        
        this.turnNum++;
        
        currentPlayer = currentPlayer.getOpponent();
        inputHandler.setList(currentPlayer.getHand());
        
        int VPdeductAmount = currentPlayer.getField().countUnoccupiedDiscs();
        currentPlayer.transferVP(bank, VPdeductAmount);
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
        turnMover.getCurrentTurn().updateTurn(this);
    }

    public List<Card> getDeck() {
        return this.deck.getCardsWithNames();
    }
    
    public void setDeck(List<Card> deck) {
        this.deck.setCards(deck);
        turnMover.getCurrentTurn().updateDeck(this);
    }

    public List<Card> getDiscard() {
        return discard.getCardsWithNames();
    }

    public void setDiscard(List<Card> discard) {
        this.discard.setCards(discard);
        turnMover.getCurrentTurn().updateDiscard(this);
    }

    public int getPlayerSestertii(int playerNum) {
        return getPlayer(playerNum).getMoney();
    }

    public void setPlayerSestertii(int playerNum, int amount) {
        getPlayer(playerNum).setMoney(amount);
        turnMover.getCurrentTurn().updateMoney(this);
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
        
        turnMover.getCurrentTurn().updateVP(this);
    }

    public Collection<Card> getPlayerHand(int playerNum) {
        return getPlayer(playerNum).getHand().getCardsWithNames();
    }

    public void setPlayerHand(int playerNum, Collection<Card> hand) {

        IPlayer p = getPlayer(playerNum);
        p.getHand().setCards(hand);
        turnMover.getCurrentTurn().updateHand(this);

    }

    public Card[] getPlayerCardsOnDiscs(int playerNum) {

        IPlayer p = getPlayer(playerNum);
        int numDiscs = p.getField().getNumDiscs();
        Card[] temp = new Card[numDiscs];

        for (int i = 0; i < numDiscs; i++) {
            if(p.getField().getDisc(i).getCard() != null) {
                temp[i] = p.getField().getDisc(i).getCard().getName();
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
                card.setCost(0);
                card.lay(p.getField().getDisc(i));
                card.setCost(card.getDefaultCost());
            } else {
                p.getField().getDisc(i).removeCard();
            }
        }

        turnMover.getCurrentTurn().updateField(this);

    }

    public int[] getActionDice() {
        return diceManager.getActionDiceValues();
    }

    public void setActionDice(int[] dice) {
        diceManager.setActionDice(dice);
        turnMover.getCurrentTurn().updateActionDice(this);
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

    public void setFinish(boolean isFinished) {
        this.isFinished = isFinished;
    }
    
    public boolean isGameCompleted() {
        return isFinished;
    }

    public void addTurnListener(ITurnListener listener) {
        turnMover.addTurnListener(listener);
    }

    public void removeTurnListener(ITurnListener listener) {
        turnMover.removeTurnListener(listener);
    }
    
}
