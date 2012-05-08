package model;

import java.util.LinkedList;
import java.util.List;

import model.card.AbstractCard;

public class PlayGame implements IListener {

    private Game g;
    private List<AbstractCard> cardsToSwap;
    private int count;
    
    private PlayGame(Game g) {
        this.g = g;
        this.count = 0;
        this.cardsToSwap = new LinkedList<AbstractCard>();
    }

    public static void initiate (Game g) {
        PlayGame swapCard = new PlayGame(g);
        g.getInputHandler().addInputListener(swapCard);
    }
    
    public void update() {
        
    }
}
