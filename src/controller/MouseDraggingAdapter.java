package controller;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JComponent;
import javax.swing.TransferHandler;

public class MouseDraggingAdapter extends MouseAdapter {

    /**
     * <p>Listener that make source draggable.</p>
     * <p>Thanks, source modified from: http://www.zetcode.com/tutorials/javaswingtutorial/draganddrop/</p>
     */
    @Override()
    public void mousePressed(MouseEvent e) {
        JComponent c = (JComponent) e.getSource();
        TransferHandler handler = c.getTransferHandler();
        handler.exportAsDrag(c, e, TransferHandler.COPY);
    }
    
}
