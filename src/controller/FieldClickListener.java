package controller;

import gui.JCard;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FieldClickListener implements ActionListener {

    private GuiInputHandler handler;
    
    public FieldClickListener(GuiInputHandler handler) {
        this.handler = handler;
    }
    
    public void actionPerformed(ActionEvent event) {
        
        if(event.getSource() instanceof JCard) {
            JCard field = (JCard) event.getSource();
            handler.addDiscInput(field.getIndex());
        }
        
    }

}
