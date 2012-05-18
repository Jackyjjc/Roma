package controller;

import gui.JDie;
import gui.JDisc;
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

public class DieDropTargetListener implements DropTargetListener {

    private static final Cursor droppableCursor = Cursor.getPredefinedCursor(Cursor.HAND_CURSOR);
    private static final Cursor notDroppableCursor = Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR);
    
    private JDisc disc;
    private GuiInputHandler handler;
    
    public DieDropTargetListener(JDisc disc, GuiInputHandler handler) {
        this.disc = disc;
        this.handler = handler;
    }
    
    public void dragEnter(DropTargetDragEvent arg0) {
    }

    public void dragExit(DropTargetEvent arg0) {
        this.disc.setCursor(notDroppableCursor);
    }

    public void dragOver(DropTargetDragEvent arg0) {
        if (!disc.getCursor().equals(droppableCursor)) {
            this.disc.setCursor(droppableCursor);
        }
    }

    public void drop(DropTargetDropEvent event) {
        
        this.disc.setCursor(Cursor.getDefaultCursor());
        
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
            
            JDie die = (JDie) transferableObj;
            
            DropTarget target = (DropTarget) event.getSource();
            JDisc disc = (JDisc) target.getComponent();
            
            handler.addUseActionDieInput(die.getValue(), disc.getIndex());
        }
    }
    
    public void dropActionChanged(DropTargetDragEvent arg0) {
        //nothing to do here atm
    }

}
