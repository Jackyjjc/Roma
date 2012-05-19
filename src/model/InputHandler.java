package model;

import java.util.ArrayList;
import java.util.List;

import controller.ILayCardInputListener;
import controller.IUseDieInputListener;

import model.card.AbstractCard;
import framework.Rules;
import framework.cards.Card;

public class InputHandler {
    
    private Game g;
    private List<IListener> inputListeners;
    private List<IListener> actionDiceInputListeners;
    private ISwapCardInputListener swapListener;
    private ILayCardInputListener layCardListener;
    private IUseDieInputListener useDieInputListener;
    
    private List<AbstractCard> cardInputQueue;
    private List<IDisc> discInputQueue;
    private List<Die> dieInputQueue;
    private int intInput;
    private List<Boolean> boolInput;
    private int battleDieInput;
    private int dieUsedInput;
    
    private ICardStorage list;
    
    public InputHandler(Game g) {
        this.g = g;
        cardInputQueue = new ArrayList<AbstractCard>();
        discInputQueue = new ArrayList<IDisc>();
        dieInputQueue = new ArrayList<Die>();
        intInput = 0;
        boolInput = new ArrayList<Boolean>();
        battleDieInput = 0;
        this.inputListeners = new ArrayList<IListener>();
        this.actionDiceInputListeners = new ArrayList<IListener>();
    }
    
    public void addCardInput(int playerId, Card name) {
        
        IPlayer currentPlayer = g.getCurrentPlayer();
        AbstractCard card = null;
        
        if(playerId == currentPlayer.getId()) {
            card = list.getCard(name);
            if(card != null) {
                cardInputQueue.add(card);
            }
        }
        
    }
    
    public void setList(ICardStorage list) {
        this.list = list;
    }
    
	public void addCardInput(int playerId, AbstractCard c) {
        
        IPlayer currentPlayer = g.getCurrentPlayer();
        
        if(playerId == currentPlayer.getId()) {
        	cardInputQueue.add(c);
        }
	}
    
    public AbstractCard getCardInput() {
        
        AbstractCard cardInput = null;
        
        if(!cardInputQueue.isEmpty()) {
            cardInput = cardInputQueue.remove(0);
        }
        
        return cardInput;
    }
    
    public void addDiscInput(int playerId, int index) {
        
        IPlayer player = g.getPlayer(playerId);
        IField field = player.getField();
        
        if(index >= 0 && index < Rules.NUM_DICE_DISCS) {
            discInputQueue.add(field.getDisc(index));
        }
        
    }
    
    public IDisc getDiscInput() {
        
        IDisc discInput = null;
        
        if(!discInputQueue.isEmpty()) {
            discInput = discInputQueue.remove(0);
        }
        
        return discInput;
    }
    
    public void addDieInput(int value) {
        
        DiceManager diceManager = g.getDiceManager();
        Die die = diceManager.getActionDie(value);
        
        if(die != null && !die.isUsed()) {
            dieInputQueue.add(die);
        }
    }
    
    public Die getDieInput() {
        
        Die dieInput = null;
        
        if(!dieInputQueue.isEmpty()) {
            dieInput = dieInputQueue.remove(0);
        }
        
        return dieInput;
    }
    
    public void addIntInput(int amount) {
        this.intInput = amount;
        notifyInputListener();
    }
    
    public int getIntInput() {
        return intInput;
    }
    
    public void addBooleanInput(boolean value) {
        this.boolInput.add(value);
        notifyInputListener();
    }
    
    public boolean getBooleanInput() {
    	
    	boolean input = false;
    	
    	if (!boolInput.isEmpty()) {
    		input = this.boolInput.remove(0);
    	}
    	
    	return input;
    
    }
    
    public void addBattleDieInput(int roll) {
        this.battleDieInput = roll;
        notifyInputListener();
    }
    
    public int getBattleDieInput() {
        return battleDieInput;
    }

    public void addInputListener(IListener l) {
        inputListeners.add(l);
    }
    
    public void removeInputListener(IListener l) {
        inputListeners.remove(l);
    }
    
    private void notifyInputListener() {
        for(IListener l : inputListeners) {
            l.update();
        }
    }
    
    public void addSwapListener(ISwapCardInputListener l) {
        swapListener = l;
    }
    
    public void removeSwapListener() {
        swapListener = null;
    }
    
    public void addDieUseListener(IUseDieInputListener l) {
        useDieInputListener = l;
    }
    
    public void removeDieUseListener() {
        useDieInputListener = null;
    }
    
    public void addLayCardListener(ILayCardInputListener l) {
        layCardListener = l;
    }
    
    public void removeLayCardListener() {
        layCardListener = null;
    }
    
    public void addDieInputListener(IListener l) {
        inputListeners.add(l);
    }
    
    public void removeDieInputListener(IListener l) {
        inputListeners.remove(l);
    }
    
    public void addDieUseInput(int index) {
        if(index >= 0 && index < Rules.NUM_DICE_DISCS + 2) {
            dieUsedInput = index;
        }
    }

    public int getDieUsedInput() {
        return dieUsedInput;
    }
    
    public void placeCardInput(int fromIndex, int toIndex) {
        layCardListener.layCard(fromIndex, toIndex);
    }
    
    public void addSwapCardInput(Card[] cards) {
        if(swapListener != null) {
            swapListener.update(cards);
        }
    }
    
    public void addUseActionDieInput(int dieIndex, int discIndex) {
        useDieInputListener.useDice(dieIndex, discIndex);
    }

}
