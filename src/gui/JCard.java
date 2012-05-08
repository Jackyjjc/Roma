package gui;

import java.awt.Dimension;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetDragEvent;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.dnd.DropTargetEvent;
import java.awt.dnd.DropTargetListener;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import framework.cards.Card;


public class JCard extends JLabel implements DropTargetListener {

    private CardDisplayManager cdm;
    
    private int index;

    public JCard(CardDisplayManager cdm, int index, Card card) {

        this.cdm = cdm;
        this.index = index;
        
        setPreferredSize(new Dimension(cdm.getWidth(), cdm.getHeight()));
        setCard(card);

        setDropTarget(new DropTarget(this, this));
        setEnabled(true);
    }
    
    public void setCard() {
        
        setOpaque(true);
        
        setIcon(new ImageIcon(cdm.getFaceDownCard()));
    }
    
    public void setCard(Card card) {
        
        setOpaque(true);
        
        if(card == Card.NOT_A_CARD) {
            setOpaque(false);
            //setContentAreaFilled(false);
        }
        
        setIcon(new ImageIcon(cdm.getCard(card)));
    }
    
    public void setIndex(int index) {
        this.index = index;
    }
    
    public int getIndex() {
        return index;
    }

    @Override
    public void dragEnter(DropTargetDragEvent dtde) {
        System.out.println("enter");
        
    }

    @Override
    public void dragExit(DropTargetEvent dte) {
        System.out.println("exit");
        
    }

    @Override
    public void dragOver(DropTargetDragEvent dtde) {
        System.out.println("over");
    }

    @Override
    public void drop(DropTargetDropEvent dtde) {
        System.out.println("drop");
        
    }

    @Override
    public void dropActionChanged(DropTargetDragEvent dtde) {
        // TODO Auto-generated method stub
        
    }
    
}
