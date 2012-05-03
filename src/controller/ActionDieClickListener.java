package controller;

import gui.JDie;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.InputHandler;

public class ActionDieClickListener implements ActionListener {

    private InputHandler handler;
    
    public ActionDieClickListener(InputHandler handler) {
        this.handler = handler;
    }
    
    public void actionPerformed(ActionEvent e) {
        
        JDie die = null;
        
        die = (JDie) e.getSource();

        handler.addDieInput(die.getIndex());
    }

}
