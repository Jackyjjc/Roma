package controller;

import framework.cards.Card;
import gui.JCard;
import gui.SwapArea;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import model.InputHandler;

public class SwapConfirmListener implements ActionListener {

    private InputHandler handler;
    
    public SwapConfirmListener(InputHandler handler) {
        this.handler = handler;
    }
    
    public void actionPerformed(ActionEvent event) {
        
        JButton button = (JButton) event.getSource();
        SwapArea swapArea = (SwapArea)button.getParent();
        
        JCard[] cards = swapArea.getCards();
        
        boolean finished = true;
        Card[] swapCards = new Card[cards.length];
        
        for(int i = 0; i < cards.length; i++) {
            if(cards[i].getCard() == null || cards[i].getCard() == Card.NOT_A_CARD) {
                finished = false;
            } else {
                swapCards[i] = cards[i].getCard();    
            }
        }
        
        if(finished) {
            handler.addSwapCardInput(swapCards);
            swapArea.clear();
        }
    }

}
