package model;

import java.util.ArrayList;
import java.util.List;

import model.card.AbstractCard;
import framework.Rules;

public class InputHandler {

    private Game g;
    
    private List<AbstractCard> cardInputQueue;
    private List<IDisc> discInputQueue;
    private List<Die> dieInputQueue;
    private int intInput;
    private boolean boolInput;
    private int battleDieInput;
    
    public InputHandler(Game g) {
        this.g = g;
        
        cardInputQueue = new ArrayList<AbstractCard>();
        discInputQueue = new ArrayList<IDisc>();
        dieInputQueue = new ArrayList<Die>();
        intInput = 0;
        boolInput = false;
        battleDieInput = 0;
    }
    
    public void addCardInput(int playerId, int index) {
        
        IPlayer currentPlayer = g.getCurrentPlayer();
        
        if(playerId == currentPlayer.getId()
           && index >= 0 && index < currentPlayer.getHand().size()) {
            
            cardInputQueue.add(currentPlayer.getHand().getCard(index));
        
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
    }
    
    public int getIntInput() {
        return intInput;
    }
    
    public void addBooleanInput(boolean value) {
        this.boolInput = value;
    }
    
    public boolean getBooleanInput() {
        return boolInput;
    }
    
    public void addBattleDieInput(int roll) {
        this.battleDieInput = roll;
    }
    
    public int getBattleDieInput() {
        return battleDieInput;
    }

}
