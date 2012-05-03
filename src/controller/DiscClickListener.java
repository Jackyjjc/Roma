package controller;

import gui.JDisc;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.InputHandler;

public class DiscClickListener implements ActionListener {

    private InputHandler handler;
    
    public DiscClickListener(InputHandler handler) {
        this.handler = handler;
    }
    
    public void actionPerformed(ActionEvent e) {
        
        JDisc disc = null;
        
        disc = (JDisc) e.getSource();

        handler.addDieUseInput(disc.getIndex());
    }

}
