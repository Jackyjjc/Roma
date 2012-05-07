package gui;

import java.awt.Dimension;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import framework.cards.Card;


public class JCard extends JButton {

    private CardDisplayManager cdm;
    
    private int index;

    public JCard(CardDisplayManager cdm, int index, Card card) {

        this.cdm = cdm;
        this.index = index;
        
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
    }
    
    public void setIndex(int index) {
        this.index = index;
    }
    
    public int getIndex() {
        return index;
    }
}
