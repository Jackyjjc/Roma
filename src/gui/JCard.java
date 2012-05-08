package gui;

import java.awt.Dimension;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import framework.cards.Card;


public class JCard extends JButton implements Transferable {

    private static DataFlavor supportedData = null;
    
    private CardDisplayManager cdm;
    
    Card card = null;
    private int index;

    public JCard(CardDisplayManager cdm, int index, Card card) {

        this.cdm = cdm;
        this.index = index;
        this.card = card;
        
        setPreferredSize(new Dimension(cdm.getWidth(), cdm.getHeight()));
        setCard(card);
    }
    
    public void setCard() {
        setOpaque(true);
        setIcon(new ImageIcon(cdm.getFaceDownCard()));
    }
    
    public void setCard(Card card) {
        
        setOpaque(true);
        
        if(card == Card.NOT_A_CARD) {
            setOpaque(false);
            setContentAreaFilled(false);
        }
        
        setIcon(new ImageIcon(cdm.getCard(card)));
        
        this.card = card;
    }
    
    public void setIndex(int index) {
        this.index = index;
    }
    
    public int getIndex() {
        return index;
    }

    public static DataFlavor getSupportedDataFlavor() throws ClassNotFoundException {
        
        if (supportedData == null) {
            supportedData = new DataFlavor(DataFlavor.javaJVMLocalObjectMimeType);
        }

        return supportedData;
    }
    
    public Object getTransferData(DataFlavor flavor) {

        Object returnObject = null;
        DataFlavor supportedData = null;
        
        try {
            supportedData = getSupportedDataFlavor();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        
        if(supportedData != null && flavor.equals(supportedData)) {
            returnObject = this;
        }
        
        return returnObject;
    }

    public DataFlavor[] getTransferDataFlavors() {
        
        DataFlavor[] flavors = new DataFlavor[1];
        
        try {
            flavors[0] = getSupportedDataFlavor();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        
        return flavors;
    }

    public boolean isDataFlavorSupported(DataFlavor flavor) {
        
        boolean isSupported = false;
        DataFlavor supportedFlavor = null;
        
        try {
            supportedFlavor = getSupportedDataFlavor();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        
        if(flavor.equals(supportedFlavor)) {
            isSupported = true;
        }
        
        return isSupported;
        
    }
    
    public Card getCard() {
        return card;
    }
}
