package gui;

import java.awt.Dimension;
import java.awt.Graphics;
import java.util.List;

import javax.swing.JPanel;

import model.IGameDisplayState;
import framework.cards.Card;

@SuppressWarnings("serial")
public class JPiles extends JPanel implements IListener {

    private IDisplayManager idm;
    private ResourceManager rm;
    private CardDisplayManager cdm;
    private Card discardTopCard;
    private int deckThickness;
    private int discardThickness;
    
    public JPiles(IDisplayManager idm) {
        
        this.rm = idm.getResourceManager();
        this.cdm = idm.getCardDisplayManager();
        this.idm = idm;
        
        setPreferredSize(new Dimension(rm.blank.getWidth() * 2,rm.blank.getHeight()));
        setOpaque(false);
    }

    public void updateView(IGameDisplayState state) {
        
        List<Card> discardPile = state.getDiscard();
        List<Card> deck = state.getDeck();
        
        deckThickness = deck.size() / 8;
        discardThickness = discardPile.size() / 8;
        
        if(discardPile.size() > 0) {
            discardTopCard = discardPile.get(0);
        } else {
            discardTopCard = null;
        }
        
        revalidate();
    }
    
    @Override
    public void paintComponent(Graphics g) {
        
        //this is the deck
        
        int i = 0;
        while(i < deckThickness - 1) {
            g.drawImage(rm.card, idm.scale(30 + i*3), 0, null);
            i++;
        }
        
        g.drawImage(rm.card, idm.scale(30 + i*3), 0, null);
        
        //this is the grave yard
        
        if(discardTopCard == null) {
            g.drawImage(rm.blank, idm.scale(140), 0, null);
        } else {
            i = 0;
            while(i < discardThickness - 1) {
                g.drawImage(rm.card, idm.scale(140 + i*3), 0, null);
                i++;
            }
            g.drawImage(cdm.getCard(discardTopCard), idm.scale(140 + i*3), 0, null);
        }
        
    }
    
}
