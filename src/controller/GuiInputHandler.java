package controller;

import framework.cards.Card;

public class GuiInputHandler {
    
    ILayCardListener layCardListener;
    IUseDieInputListener useDieInputListener;
    IPassListener passListener;
    
    volatile boolean hasInput;
    
    Card cardInput;
    int intInput;
    boolean booleanInput;
    
    public GuiInputHandler() {

    }
    
    public void layCard(int from, int to) {
        if(layCardListener != null) {
            layCardListener.layCard(from, to);
        }
    }
    
    public void setLayCardListener(ILayCardListener l) {
        layCardListener = l;
    }

    public void setUseActionDieListener(IUseDieInputListener l) {
        useDieInputListener = l;
    }
    
    public void setPassListener(IPassListener l) {
        passListener = l;
    }
    
    public void pass() {
        if(passListener != null) {
            passListener.pass();
        }
    }
    
    public void addCardInput(Card card) {
        this.cardInput = card;
        hasInput = true;
    }

    public void addDiscInput(int index) {
        this.intInput = index;
        hasInput = true;
    }

    public void addDieInput(int value) {
        this.intInput = value;
        hasInput = true;
    }

    public void addIntInput(int amount) {
        this.intInput = amount;
        hasInput = true;
    }
    
    public void addUseActionDieInput(int dieValue, int discInex) {
        if(useDieInputListener != null) {
            useDieInputListener.useDice(dieValue, discInex);
        }
    }
    
    public Card getCardInput() {
        
        hasInput = false;
        
        while(!hasInput) {
            //wait
        }
        
        return cardInput;
    }
    
    public int getDiscInput() {
        
        hasInput = false;
        
        while(!hasInput) {
            //wait
        }
        
        return intInput;
    }
    
    
    public int getDieInput() {
        
        hasInput = false;
        
        while(!hasInput) {
            //wait
        }
        
        return intInput;
    }
    
    
    public boolean getBooleanInput() {
        
        hasInput = false;
        
        while(!hasInput) {
            //wait
        }
        
        return booleanInput;
    }
    
}
