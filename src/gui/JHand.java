package gui;

import java.awt.GraphicsEnvironment;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.TransferHandler;

import model.IGameDisplayState;
import controller.CardTransferHandler;
import controller.HandClickListener;
import framework.cards.Card;

public class JHand extends JPanel implements IListener {

    private final boolean FACE_UP = true;
    private final boolean FACE_DOWN = false;
    
    private final HandClickListener listener;
    private final CardDisplayManager cdm;
    private IDisplayManager idm;
    private int panelId;
    private int currentPlayer;
    
    public JHand(int panelID, IDisplayManager idm) {
        
        this.idm = idm;
        this.cdm = idm.getCardDisplayManager();
        this.panelId = panelID;
        this.listener = idm.getHandClickListener();
        
        setOpaque(false);
    }
    
    public void updateView(IGameDisplayState state) {
        
        List<Card> cards;
        this.currentPlayer = state.getWhoseTurn();
        
        if (panelId == 0) {
            cards = (List<Card>) state.getPlayerHand(currentPlayer);
            setHand(cards, FACE_UP);
        } else {
            cards = (List<Card>) state.getPlayerHand((currentPlayer + 1) % 2);
            setHand(cards, FACE_DOWN);
        }
    }
    
    public int getPlayerId() {
        return currentPlayer;
    }
    
    public void setHand(List<Card> cards, boolean display) {
        
        removeAll();
        
        for(int i = 0; i < cards.size(); i++) {
            JCard displayCard = new JCard(cdm, i, cards.get(i));
            if (!display) {
                displayCard.setCard();
            } else {
                displayCard.addMouseListener(new DragMouseAdapter());
                //displayCard.addActionListener(listener);
                displayCard.setTransferHandler(new CardTransferHandler());
            }
            add(displayCard);
        }
        
        
        revalidate();
    }
    
    class DragMouseAdapter extends MouseAdapter {
        
        public void  mouseReleased(MouseEvent me) {
            System.out.println("here mouse released");
       }
        
        public void mousePressed(MouseEvent e) {
            JComponent c = (JComponent) e.getSource();
            TransferHandler handler = c.getTransferHandler();
            
            System.out.println(handler.getSourceActions(c) & TransferHandler.COPY);
            System.out.println(e instanceof MouseEvent);
            System.out.println(GraphicsEnvironment.isHeadless());
            
            handler.exportAsDrag(c, e, TransferHandler.COPY);
        }
    }
}