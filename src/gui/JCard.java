package gui;

import java.awt.Dimension;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import model.card.Card;


public class JCard extends JButton {

    private final CardDisplayManager cdm;
    
    private int index;

    public JCard(CardDisplayManager cdm, int index, Card card) {

        this.cdm = cdm;

        setPreferredSize(new Dimension(cdm.getWidth(), cdm.getHeight()));
        setCard(card);
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
