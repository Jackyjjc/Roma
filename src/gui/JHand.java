package gui;

import java.util.List;

import javax.swing.JPanel;

import model.IGameDisplayState;
import controller.HandClickListener;
import framework.cards.Card;

public class JHand extends JPanel implements IListener {

    private final HandClickListener listener;
    private final CardDisplayManager cdm;
    private int handSize;
    private int playerId;
    
    public JHand(int playerId, IDisplayManager idm) {
        
        this.cdm = idm.getCardDisplayManager();
        handSize = 0;
        this.playerId = playerId;
        this.listener = idm.getHandClickListener();
        
        setOpaque(false);
    }
    
    public void updateView(IGameDisplayState state) {
        
        if(state.getPlayerHand(playerId) instanceof List) {
            List<Card> cards = (List<Card>) state.getPlayerHand(playerId);
            setHand(cards);
        }
    }
    
    public int getPlayerId() {
        return playerId;
    }
    
    public void setHand(List<Card> cards) {
        
        int inputLength = cards.size();
        int currentLength = handSize;
        JCard card;
        
        for(int i = 0; i < currentLength; i++) {
            card = (JCard) getComponent(i);
            card.setIndex(i);
            card.setCard(cards.get(i));
        }
        
        if(currentLength < inputLength) {
            
            for(int i = handSize; i < inputLength; i++) {
                card = new JCard(cdm, i, cards.get(i));
                card.addActionListener(listener);
                add(card);
                handSize++;
            }
            
        } else if(currentLength > inputLength){
            
            for(int i = inputLength; i < currentLength; i++) {
                remove(i);
                handSize--;
            }
            
        }
    }
}