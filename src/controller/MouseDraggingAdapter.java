package controller;

import gui.JDie;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JComponent;
import javax.swing.TransferHandler;

public class MouseDraggingAdapter extends MouseAdapter {

    private GuiInputHandler handler;
    private boolean draggable;
    
    public MouseDraggingAdapter(GuiInputHandler handler) {
        this.handler = handler;
        draggable = true;
    }
    
    /**
     * <p>Listener that make source draggable.</p>
     * <p>Thanks, source modified from: http://www.zetcode.com/tutorials/javaswingtutorial/draganddrop/</p>
     */
    @Override
    public void mousePressed(MouseEvent e) {      
        if(draggable) {
            JComponent c = (JComponent) e.getSource();
            TransferHandler handler = c.getTransferHandler();
            handler.exportAsDrag(c, e, TransferHandler.COPY);
        }
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
        
        if (!draggable) {
            JComponent source = (JComponent) e.getSource();

            if (source instanceof JDie) {
                JDie die = (JDie) source;
                handler.addDieInput(die.getValue());
            }
        }
    }
    
    public void setDraggable(boolean draggble) {
        this.draggable = draggble;
    }
}
