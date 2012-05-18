package gui;

import java.awt.FlowLayout;
import java.awt.dnd.DropTarget;

import javax.swing.JPanel;

import model.IGameDisplayState;
import controller.CardDropTargetListener;
import controller.CustomizedTransferHandler;
import controller.FieldClickListener;
import framework.cards.Card;


public class JField extends JPanel implements IListener {

    private static int NUM_CARDS = 7;
    
    private IDisplayManager idm;
    private CardDisplayManager cdm;
    private JCard[] cards;
    private int panelId;
    private int currentPlayer;
    
    public JField(int panelId, IDisplayManager idm) {
        
        this.idm = idm;
        this.cdm = idm.getCardDisplayManager();
        this.panelId = panelId;
        this.currentPlayer = 0;
        
        this.setLayout(new FlowLayout(FlowLayout.CENTER,idm.scale(17), 0));
        
        initUI();
        setOpaque(false);
    }
    
    public int getPlayerId() {
        return currentPlayer;
    }
    
    public void updateView(IGameDisplayState state) {
        
        Card[] cards;
        this.currentPlayer = state.getWhoseTurn();
        
        if (panelId == 0) {
            cards = state.getPlayerCardsOnDiscs(currentPlayer);
        } else {
            cards = state.getPlayerCardsOnDiscs((currentPlayer + 1) % 2);
        }
        
        setCards(cards);
        repaint();
    }
    
    private void initUI() {
        
        JCard card;
        cards = new JCard[NUM_CARDS];
        
        for(int i = 0; i < cards.length; i++) {
            card = new JCard(cdm, i, Card.NOT_A_CARD);
            card.addActionListener(new FieldClickListener(idm.getInputHandler()));
            card.setTransferHandler(new CustomizedTransferHandler());
            card.setDropTarget(new DropTarget(card, new CardDropTargetListener(card, idm.getInputHandler())));
            add(card);
            cards[i] = card;
        }
    }
    
    private void setCards(Card[] cardList) {
        for(int i = 0; i < cards.length; i++) {
            cards[i].setIndex(i);
            cards[i].setCard(cardList[i]);
        }
    }
}
