package controller;

import framework.cards.Card;

public class GuiInputHandler {

    ILayCardListener layCardListener;
    IUseDieInputListener useDieInputListener;
    IPassListener passListener;
    
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
    
    public void addCardInput(int playerId, Card card) {
        
    }

    public void addDiscInput(int playerId, int index) {
        // TODO Auto-generated method stub
        
    }

    public void addUseActionDieInput(int dieValue, int discInex) {
        if(useDieInputListener != null) {
            useDieInputListener.useDice(dieValue, discInex);
        }
    }

    public void addDieInput(int index) {
        // TODO Auto-generated method stub
        
    }

    public void addIntInput() {

    }
    
}
