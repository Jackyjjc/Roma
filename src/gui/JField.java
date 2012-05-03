package gui;

import java.awt.FlowLayout;
import java.awt.Insets;

import javax.swing.JPanel;

import controller.FieldClickListener;

import model.IGameDisplayState;
import model.InputHandler;
import model.card.Card;


public class JField extends JPanel implements IListener {

    private static int NUM_CARDS = 6;
    
    private CardDisplayManager cdm;
    private JCard[] cards;
    private int playerId;
    
    public JField(int playerId, CardDisplayManager cdm, FieldClickListener listener) {
        
        this.cdm = cdm;
        this.playerId = playerId;
        
        this.setLayout(new FlowLayout(FlowLayout.CENTER, 17, 0));
        
        initUI(listener);
        setOpaque(false);
    }
    
    public int getPlayerId() {
        return playerId;
    }
    
    public void updateView(IGameDisplayState state) {
        setCards(state.getPlayerCardsOnDiscs(playerId));
    }
    
    private void initUI(FieldClickListener listener) {
        
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
