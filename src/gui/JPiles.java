package gui;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.List;

import javax.swing.JPanel;

import model.IGameDisplayState;
import framework.cards.Card;

public class JPiles extends JPanel implements IListener {

    private ResourceManager rm;
    private CardDisplayManager cdm;
    private Card discardTopCard;
    private int deckThickness;
    private int discardThickness;
    
    public JPiles(ResourceManager rm, CardDisplayManager cdm) {
        
        this.rm = rm;
        this.cdm = cdm;
        
        setPreferredSize(new Dimension(rm.blank.getWidth() * 2,rm.blank.getHeight()));
        setOpaque(false);
    }

    public void updateView(IGameDisplayState state) {
        
        List<Card> discardPile = state.getDiscard();
        List<Card> deck = state.getDeck();
        
        deckThickness = deck.size() / 10;
        discardThickness = discardPile.size() / 10;
        
        if(discardPile.size() > 0) {
            discardTopCard = discardPile.get(0);
        } else {
            discardTopCard = null;
        }
        
    }
    
    @Override
    public void paintComponent(Graphics g) {
        
        Graphics2D g2d = (Graphics2D) g;
        
        //this is the deck
        
        int i = 0;
        while(i < deckThickness - 1) {
            g.drawImage(rm.card, 30 + i, 0, null);
            i++;
        }
        
        g.drawImage(rm.card, 30 + i, 0, null);
        
        //this is the grave yard
        
        if(discardTopCard == null) {
            g.drawImage(rm.blank, 140, 0, null);
        } else {
            i = 0;
            while(i < discardThickness - 1) {
                g.drawImage(rm.card, 140 + i, 0, null);
                i++;
            }
            g.drawImage(cdm.getCard(discardTopCard), 140 + i, 0, null);
        }
        
    }
    
}
