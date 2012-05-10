package gui;

import java.awt.Dimension;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import framework.cards.Card;


public class JCard extends JButton implements Transferable {
    
    private CardDisplayManager cdm;
    private TransferableImp traImp;
    
    Card card = null;
    private int index;

    public JCard(CardDisplayManager cdm, int index, Card card) {

        this.cdm = cdm;
        this.index = index;
        this.card = card;
        this.traImp = new TransferableImp(this);
        
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
    
    public Card getCard() {
        return card;
    }

    public Object getTransferData(DataFlavor flavor)
            throws UnsupportedFlavorException, IOException {
        return traImp.getTransferData(flavor);
    }

    public DataFlavor[] getTransferDataFlavors() {
        return traImp.getTransferDataFlavors();
    }

    public boolean isDataFlavorSupported(DataFlavor flavor) {
        return traImp.isDataFlavorSupported(flavor);
    }
}
