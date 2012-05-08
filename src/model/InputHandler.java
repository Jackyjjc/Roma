package model;

import java.util.ArrayList;
import java.util.List;

import model.card.AbstractCard;
import framework.Rules;

public class InputHandler {
    
    private Game g;
    private List<IListener> inputListeners;
    private List<IListener> actionDiceInputListeners;
    
    private List<AbstractCard> cardInputQueue;
    private List<IDisc> discInputQueue;
    private List<Die> dieInputQueue;
    private int intInput;
    private boolean boolInput;
    private int battleDieInput;
    private int dieUsedInput;
    
    public InputHandler(Game g) {
        this.g = g;
        
        cardInputQueue = new ArrayList<AbstractCard>();
        discInputQueue = new ArrayList<IDisc>();
        dieInputQueue = new ArrayList<Die>();
        intInput = 0;
        boolInput = false;
        battleDieInput = 0;
        this.inputListeners = new ArrayList<IListener>();
    }
    
    public void addCardInput(int playerId, int index) {
        
        IPlayer currentPlayer = g.getCurrentPlayer();
        
        if(playerId == currentPlayer.getId()
           && index >= 0 && index < currentPlayer.getHand().size()) {
            
            cardInputQueue.add(currentPlayer.getHand().getCard(index));
            
            System.out.println(currentPlayer.getHand().getCard(index).getName());
            
            notifyInputListener();
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
            
            notifyInputListener();
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
            
            notifyDieInputListener();
            
            notifyInputListener();
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
        this.boolInput = value;
        notifyInputListener();
    }
    
    public boolean getBooleanInput() {
        return boolInput;
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

    public void addDieInputListener(IListener l) {
        inputListeners.add(l);
    }
    
    public void removeDieInputListener(IListener l) {
        inputListeners.remove(l);
    }
    
    private void notifyDieInputListener() {
        for(IListener l : actionDiceInputListeners) {
            l.update();
        }
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
        
        IPlayer current = g.getCurrentPlayer();
        AbstractCard card = current.getHand().getCard(fromIndex);
        IDisc disc = current.getField().getDisc(toIndex);
        
        card.setCost(0);
        g.getCurrentPlayer().getHand().removeCard(card);
        card.lay(disc);
        g.getNotifier().notifyListeners();
        card.setCost(card.getDefaultCost());
        card = null;
        
    }
}
