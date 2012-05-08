package controller;

import gui.JCard;

import java.awt.datatransfer.DataFlavor;
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
    public boolean canImport(JComponent comp, DataFlavor[] transferFlavors) {
        System.out.println("let me see");
        
        return true;
    }
    
    @Override
    public boolean canImport(TransferHandler.TransferSupport support) {
        
        System.out.println("let me see");
        
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
