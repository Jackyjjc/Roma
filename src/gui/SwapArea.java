package gui;

import java.awt.dnd.DropTarget;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

import controller.CardDropTargetListener;
import controller.CustomizedTransferHandler;
import framework.cards.Card;

@SuppressWarnings("serial")
public class SwapArea extends JPanel {

    private IDisplayManager idm;
    
    private InnerPanel innerPanel;
    private JButton confirm;
    
    public SwapArea(IDisplayManager idm) {
        
        this.idm = idm;
        
        setOpaque(false);
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        
        this.innerPanel = new InnerPanel(idm);
        add(innerPanel);
        
        confirm = new JButton("Confirm");
        add(confirm);
    }
    
    public JCard[] getCards() {
        return innerPanel.getCards();
    }
    
    public void clear() {
        
        JButton confirm = (JButton) getComponent(1);
        
        remove(innerPanel);
        remove(confirm);
        
        this.innerPanel = new InnerPanel(idm);
        add(innerPanel);
        add(confirm);
        
        revalidate();
    }
    
    public void setSwapCardConfirmListener(ActionListener l) {
        confirm.addActionListener(l);
    }
    
    private class InnerPanel extends JPanel {
        
        private JCard[] cards;
        
        public InnerPanel(IDisplayManager idm) {
            
            setOpaque(false);
            
            initUI(2);
        }
        
        private void initUI(int numCards) {
            
            cards = new JCard[numCards];
            
            for(int i = 0; i < numCards; i++) {
                cards[i] = initializeCard(10 + i);
                add(cards[i]);
            }
            
        }
        
        private JCard initializeCard(int id) {
            
            JCard card = new JCard(idm.getCardDisplayManager(), id, Card.NOT_A_CARD);
            card.setTransferHandler(new CustomizedTransferHandler());
            card.setDropTarget(new DropTarget(card, new CardDropTargetListener(card, idm.getInputHandler())));
            
            return card;
        }
        
        public JCard[] getCards() {
            return cards;
        }
        
    }
}
