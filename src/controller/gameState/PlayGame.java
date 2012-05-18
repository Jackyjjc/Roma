package controller.gameState;

import framework.cards.Card;
import gui.GraphicalView;

import java.util.List;

import model.Game;
import model.IDisc;
import model.IPlayer;
import model.card.AbstractCard;
import controller.GuiInputHandler;
import controller.IGameState;
import controller.ILayCardListener;
import controller.IPassListener;
import controller.IUseDieInputListener;

public class PlayGame implements IUseDieInputListener, ILayCardListener, IGameState, IPassListener {

    private Game g;
    private GraphicalView view;
    private GuiInputHandler handler;
    private IGameState next;
    
    public PlayGame(Game g, GraphicalView view, GuiInputHandler handler) {
        this.g = g;
        this.view = view;
        this.handler = handler;
    }

    public void run() {
    
        handler.setLayCardListener(this);
        handler.setUseActionDieListener(this);
        handler.setPassListener(this);
        
        view.showGameStarts();
        g.setPlayerVictoryPoints(1, 10);
        g.getNotifier().notifyListeners();
        
    }
    
    public void useDice(int dieValue, int diceIndex) {
        
        IPlayer player = g.getCurrentPlayer();
        
        if(diceIndex == 0) {
            
            Card selected = selectCard(g.getDeck(), dieValue);
            AbstractCard card = g.getDeckStorage().getCard(selected);
            player.getHand().appendCard(card);
            
            for(int i = 0; i < dieValue && i < g.getDeckStorage().size(); i++) {
                AbstractCard c = g.getDeckStorage().popCard();
                if(c != card) {
                    g.getDiscardStorage().pushCard(c);
                }
            }

            
            g.getDiceManager().getActionDie(dieValue).use();
            
            g.getNotifier().notifyListeners();
        } else if(diceIndex == 8) {
            g.getBank().transferMoney(player, dieValue);
            g.getDiceManager().getActionDie(dieValue).use();
            g.getNotifier().notifyListeners();
        
        } else {
            
            System.out.println("disc " + diceIndex + " actived");
            
            IDisc disc = player.getField().getDisc(diceIndex - 1);
            if(g.getCardActivateManager().activate(disc)) {
                g.getCardActivateManager().complete();
                g.getDiceManager().getActionDie(dieValue).use();
                g.getNotifier().notifyListeners();
            }
        }
    }
    
    public void layCard(int fromIndex, int toIndex) {
        
        IPlayer currentPlayer = g.getCurrentPlayer();
        AbstractCard card = currentPlayer.getHand().getCard(fromIndex);
        IDisc disc = currentPlayer.getField().getDisc(toIndex);
        
        if(currentPlayer.getMoney() >= card.getCost()) {
            g.getCurrentPlayer().getHand().removeCard(card);
            card.lay(disc);
        }

        g.getNotifier().notifyListeners();
    }

    private Card selectCard(List<Card> cards, int numCards) {
        
        List<Card> deck = g.getDeck();
        Card[] deckList = new Card[numCards];
        for(int i = 0 ; i < numCards && i < deck.size(); i++) {
            deckList[i] = deck.get(i);
        }
        
        Card selected = null;
        while(selected == null) {
            selected = view.showCards(deckList);
        }
        
        return selected;
    }
    
    public void setNextState(IGameState state) {
        next = state;
    }

    public IGameState getNextState() {
        return next;
    }

    public void pass() {
        if(view.showPassDialog()) {
            g.advanceTurn();
            g.getDiceManager().rollActionDice();
            g.getNotifier().notifyListeners();
            
            while(g.getDiceManager().isAllSame()) {
                if(view.reRollDialog()) {
                   g.getDiceManager().rollActionDice(); 
                }
            }
            g.getNotifier().notifyListeners();
        }
    }
}
