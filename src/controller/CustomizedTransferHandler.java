package controller;

import gui.JCard;
import gui.JDie;

import java.awt.datatransfer.Transferable;
import java.awt.dnd.DragSourceDragEvent;
import java.awt.dnd.DragSourceMotionListener;

import javax.swing.JComponent;
import javax.swing.TransferHandler;

public class CustomizedTransferHandler extends TransferHandler implements DragSourceMotionListener {
    
    @Override
    public Transferable createTransferable(JComponent c) {
        
        Transferable transferable = null;
        
        if(c instanceof Transferable) {
            transferable = (Transferable) c;
        }
        
        return transferable;
    }
    
    public void dragMouseMoved(DragSourceDragEvent arg0) {
        //nothing to do here
    }
    
    @Override()
    public int getSourceActions(JComponent c) {
        
        if (c instanceof JCard || c instanceof JDie) {
            return TransferHandler.COPY;
        }
        
        return TransferHandler.NONE;
    }
}
