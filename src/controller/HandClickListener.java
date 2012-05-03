package controller;

import gui.JCard;
import gui.JHand;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.InputHandler;

public class HandClickListener implements ActionListener {

    private InputHandler handler;
    
    public HandClickListener(InputHandler handler) {
        this.handler = handler;
    }
    
    public void actionPerformed(ActionEvent e) {
        
        JCard card = null;
        JHand hand = null;
        
        card = (JCard) e.getSource();
        hand = (JHand) card.getParent();

        handler.addCardInput(hand.getPlayerId(), card.getIndex());
    }

}
