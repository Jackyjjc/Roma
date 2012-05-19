package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StopButtonClickListener implements ActionListener {

    private GuiInputHandler handler;
    
    public StopButtonClickListener(GuiInputHandler handler) {
        this.handler = handler;
    }
    
    public void actionPerformed(ActionEvent e) {
        handler.stopEffect();
    }

}
