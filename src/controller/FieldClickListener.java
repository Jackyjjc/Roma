package controller;

import gui.JCard;
import gui.JField;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.InputHandler;

public class FieldClickListener implements ActionListener {

    private InputHandler handler;
    
    public FieldClickListener(InputHandler handler) {
        this.handler = handler;
    }
    
    
    public void actionPerformed(ActionEvent e) {
        
        JCard card = null;
        JField field = null;
        
        card = (JCard) e.getSource();
        field = (JField) card.getParent();

        handler.addDiscInput(field.getPlayerId(), card.getIndex());
    }

}
