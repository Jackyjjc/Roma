package controller.gameState;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JButton;

import model.Game;
import model.IPlayer;
import model.card.AbstractCard;
import controller.IGameState;
import framework.Rules;
import framework.cards.Card;
import gui.GraphicalView;
import gui.JCard;
import gui.SwapArea;

public class SwapCard implements IGameState, ActionListener {

    private static final int NUM_CARDS_TO_SWAP = 2;
    
    private Game g;
    private SwapArea swapArea;
    private GraphicalView view;
    private List<AbstractCard> cardsToSwap;
    private IGameState next;
    private List<Card> cardSwapInput;
    
    public SwapCard(Game g, GraphicalView view) {
        this.g = g;
        this.view = view;
        view.setSwapConfirmListener(this);
        this.cardsToSwap = new LinkedList<AbstractCard>();
        this.cardSwapInput = new LinkedList<Card>();
    }
    
    public void run() {
        view.showSwapDialog(1);
    }

    public void setNextState(IGameState state) {
        next = state;
    }

    public IGameState getNextState() {
        return next;
    }

    public void actionPerformed(ActionEvent event) {
        
        JButton button = (JButton) event.getSource();
        swapArea = (SwapArea)button.getParent();
        
        JCard[] cards = swapArea.getCards();
        cardSwapInput.clear();
        
        for (int i = 0; i < cards.length; i++) {
            if (cards[i].getCard() != Card.NOT_A_CARD) {
                cardSwapInput.add(cards[i].getCard());
            }
        }
        
        if(cardSwapInput.size() == NUM_CARDS_TO_SWAP) {
            swap();
            cardSwapInput.clear();
        }
    }

    private void swap() {
        
        IPlayer currentPlayer = g.getCurrentPlayer();
        AbstractCard card = null;
            
        for (Card c : cardSwapInput) {
            card = currentPlayer.getHand().getCard(c);
            cardsToSwap.add(card);
            currentPlayer.getHand().removeCard(card);
        }
        
        if(cardsToSwap.size() == NUM_CARDS_TO_SWAP * Rules.NUM_PLAYERS) {
            swapCard();
            g.getNotifier().notifySwapFinished();
            changePlayer();
            
            if(getNextState() != null) {
                getNextState().run();
            }
            
        } else {
            changePlayer();
            view.showSwapDialog(2);
        }
        
    }
    
    private void swapCard() {
        
        IPlayer player = g.getPlayer(1);
        
        for(int i = 0; i < 2; i++) {
            player.getHand().pushCard(cardsToSwap.remove(0));
        }
        
        player = player.getOpponent();

        for(int i = 0; i < 2; i++) {
            player.getHand().pushCard(cardsToSwap.remove(0));
        }
        
    }
    
    private void changePlayer() {
        
        g.getTurnMover().advanceTurn();
        g.setPlayerVictoryPoints(0, 10);
        g.setPlayerVictoryPoints(1, 10);
        g.getNotifier().notifyListeners();
        
        swapArea.clear();
    }
}
