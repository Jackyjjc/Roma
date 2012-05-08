package controller;

import gui.JCard;
import gui.TransferableImp;

import java.awt.Cursor;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetDragEvent;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.dnd.DropTargetEvent;
import java.awt.dnd.DropTargetListener;
import java.io.IOException;

import model.InputHandler;

public class CardDropTargetListener implements DropTargetListener {

    private static final Cursor droppableCursor = Cursor.getPredefinedCursor(Cursor.HAND_CURSOR);
    private static final Cursor notDroppableCursor = Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR);
    
    private JCard fieldCard;
    private InputHandler handler;
    
    public CardDropTargetListener(JCard fieldCard, InputHandler handler) {
        this.fieldCard = fieldCard;
        this.handler = handler;
    }
    
    public void dragEnter(DropTargetDragEvent arg0) {
    }

    public void dragExit(DropTargetEvent arg0) {
        this.fieldCard.setCursor(notDroppableCursor);
    }

    public void dragOver(DropTargetDragEvent arg0) {
        if (!fieldCard.getCursor().equals(droppableCursor)) {
            this.fieldCard.setCursor(droppableCursor);
        }
    }

    public void drop(DropTargetDropEvent event) {
        
        this.fieldCard.setCursor(Cursor.getDefaultCursor());
        
        Transferable transferable = event.getTransferable();
        Object transferableObj = null;
        DataFlavor supported = null;
        
        try {
            supported = TransferableImp.getSupportedDataFlavor();
            if (transferable.isDataFlavorSupported(supported)) {
                transferableObj = transferable.getTransferData(supported);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedFlavorException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        if(transferableObj != null) {
            
            JCard from = (JCard) transferableObj;
            
            DropTarget target = (DropTarget) event.getSource();
            JCard to = (JCard) target.getComponent();
            
            if(to.getIndex() >= 10) {
                to.setCard(from.getCard());
            } else {
                handler.placeCardInput(from.getIndex(), to.getIndex());
            }
        }
    }
    
    public void dropActionChanged(DropTargetDragEvent arg0) {
        //nothing to do here atm
    }

}
