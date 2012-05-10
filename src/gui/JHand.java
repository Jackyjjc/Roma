package gui;

import java.util.List;

import javax.swing.JPanel;

import model.IGameDisplayState;
import controller.CustomizedTransferHandler;
import controller.HandClickListener;
import controller.MouseDraggingAdapter;
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
                displayCard.addMouseListener(new MouseDraggingAdapter());
                displayCard.setTransferHandler(new CustomizedTransferHandler());
            }
            add(displayCard);
        }
        
        
        revalidate();
    }
}