package gui;

import java.awt.FlowLayout;

import javax.swing.JPanel;

import model.IGameDisplayState;
import controller.FieldClickListener;
import framework.cards.Card;


public class JField extends JPanel implements IListener {

    private static int NUM_CARDS = 6;
    
    private FieldClickListener listener;
    private IDisplayManager idm;
    private CardDisplayManager cdm;
    private JCard[] cards;
    private int playerId;
    
    public JField(int playerId, IDisplayManager idm) {
        
        this.cdm = idm.getCardDisplayManager();
        this.listener = idm.getFieldClickListener();
        this.playerId = playerId;
        
        this.setLayout(new FlowLayout(FlowLayout.CENTER,idm.scale(17), 0));
        
        initUI();
        setOpaque(false);
    }
    
    public int getPlayerId() {
        return playerId;
    }
    
    public void updateView(IGameDisplayState state) {
        setCards(state.getPlayerCardsOnDiscs(playerId));
    }
    
    private void initUI() {
        
        JCard card;
        cards = new JCard[NUM_CARDS];
        
        for(int i = 0; i < cards.length; i++) {
            card = new JCard(cdm, i, Card.NOT_A_CARD);
            card.addActionListener(listener);
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
