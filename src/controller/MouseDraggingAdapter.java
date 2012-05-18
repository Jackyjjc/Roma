package controller;

import gui.JCard;
import gui.JDie;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JComponent;
import javax.swing.TransferHandler;

public class MouseDraggingAdapter extends MouseAdapter {

    private GuiInputHandler handler;
    
    public MouseDraggingAdapter(GuiInputHandler handler) {
        this.handler = handler;
    }
    
    /**
     * <p>Listener that make source draggable.</p>
     * <p>Thanks, source modified from: http://www.zetcode.com/tutorials/javaswingtutorial/draganddrop/</p>
     */
    @Override
    public void mousePressed(MouseEvent e) {
        JComponent c = (JComponent) e.getSource();
        TransferHandler handler = c.getTransferHandler();
        handler.exportAsDrag(c, e, TransferHandler.COPY);
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
        
        JComponent source = (JComponent) e.getSource();
        
        if(source instanceof JCard) {
            
            JCard card = (JCard) source;
            
            handler.addCardInput(card.getCard());
        } else if(source instanceof JDie) {
            
        }
        
    }
    
}
