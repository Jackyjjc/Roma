package controller;

import framework.cards.Card;

public class GuiInputHandler {
    
    ILayCardListener layCardListener;
    IUseDieInputListener useDieInputListener;
    IPassListener passListener;
    IGuiCardInputListener cardInputListener;
    IGuiDieInputListener dieInputListener;
    IGuiDiscInputListener discInputListener;
    
    public GuiInputHandler() {

    }
    
    public void layCard(int from, int to) {
        if(layCardListener != null) {
            layCardListener.layCard(from, to);
        }
    }
    
    public void pass() {
        if(passListener != null) {
            passListener.pass();
        }
    }
    
    public void addCardInput(Card card) {
        if(cardInputListener != null) {
            cardInputListener.cardInput(card);
        }
    }

    public void addDiscInput(int index) {
        if(discInputListener != null) {
            discInputListener.discInput(index);
        }
    }

    public void addDieInput(int value) {
        if(dieInputListener != null) {
            dieInputListener.dieInput(value);
        }
    }
    
    public void addUseActionDieInput(int dieValue, int discInex) {
        if(useDieInputListener != null) {
            useDieInputListener.useDice(dieValue, discInex);
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
    
    public void setDieInputListener(IGuiDieInputListener l) {
        dieInputListener = l;
    }
    
    public void setDiscInputListener(IGuiDiscInputListener l) {
        discInputListener = l;
    }
    
    public void setCardInputListener(IGuiCardInputListener l) {
        cardInputListener = l;
    }
}
