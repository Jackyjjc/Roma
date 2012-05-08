package controller;

import gui.JCard;

import java.awt.datatransfer.Transferable;

import javax.swing.JComponent;
import javax.swing.TransferHandler;

import model.InputHandler;

public class FieldTransferHandler extends TransferHandler {

    private InputHandler handler;
    
    public FieldTransferHandler(InputHandler handler) {
        this.handler = handler;
    }
    
    @Override
    public boolean canImport(TransferHandler.TransferSupport support) {
        return true;
    }
    
    @Override
    public boolean importData(JComponent comp, Transferable t) {
        
        System.out.println("hidddddd");
        
        JCard from = (JCard) t;
        JCard to = (JCard) comp;
        
        handler.placeCardInput(from.getIndex(), to.getIndex());
        
        return true;
    }
    
}
