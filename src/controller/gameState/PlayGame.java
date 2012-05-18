package controller.gameState;

import framework.cards.Card;
import gui.GraphicalView;

import java.util.List;

import model.BribeDisc;
import model.Die;
import model.Game;
import model.ICardStorage;
import model.IDisc;
import model.IPlayer;
import model.card.AbstractCard;
import model.card.Aesculapinum;
import model.card.CardType;
import model.card.Haruspex;
import model.card.ICardChecker;
import model.runner.CardActivateManager;
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
            
            IDisc disc = player.getField().getDisc(diceIndex - 1);
            
            if(!disc.isBlocked() && !disc.isEmpty()) {
                
                if(disc instanceof BribeDisc) {
                    BribeDisc bribe = (BribeDisc)disc;
                    bribe.giveBribe(dieValue);
                }
                
                activateCard(dieValue, disc);
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
        
        Card[] deckList = new Card[numCards];
        for(int i = 0 ; i < numCards && i < cards.size(); i++) {
            deckList[i] = cards.get(i);
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
        
        boolean rollAgain = true;;
        
        if(view.showPassDialog()) {
            g.advanceTurn();
            g.getDiceManager().rollActionDice();
            g.getNotifier().notifyListeners();
            
            while(rollAgain && g.getDiceManager().isAllSame()) {
                
                if(view.reRollDialog()) {
                   g.getDiceManager().rollActionDice(); 
                } else {
                    rollAgain = false;
                }
            }
            g.getNotifier().notifyListeners();
        }
    }
    
    private void activateCard(int dieValue, IDisc disc) {
        
        Die die = g.getDiceManager().getActionDie(dieValue);
        
        g.getCardActivateManager().activate(disc);
        
        CardActivateManager manager = g.getCardActivateManager();
        AbstractCard card = manager.getActivatedCard();
        AbstractCard target = null;
        
        if(card instanceof Aesculapinum || card instanceof Haruspex) {

            ICardStorage pileStorage;
            List<Card> pile;
            CardType type;
            
            if(card instanceof Aesculapinum) {
                pileStorage = g.getDiscardStorage();
                pile = g.getDiscard();
                type = CardType.CHARACTER;
            } else {
                pileStorage = g.getDeckStorage();
                pile = g.getDeck();
                type = null;
            }
            
            if (hasCardTypeOf(pileStorage, type) 
                 && pile.size() != 0) {

                Card c = selectCard(pile, pile.size());
                target = pileStorage.getCard(c);

                ICardChecker checker = (ICardChecker) card.getBehaviour();
                while (!checker.isValidCard(target)) {
                    c = selectCard(pile, pile.size());
                    target = pileStorage.getCard(c);
                }

                die.use();

                int index = 0;
                for (int i = 0; i < pile.size(); i++) {
                    if (pile.get(i) == c) {
                        index = i;
                    }
                }

                manager.chooseCardFromPile(index);
                manager.complete();

            }
        }
        
        g.getNotifier().notifyListeners();
    }
    
    private boolean hasCardTypeOf(ICardStorage list, CardType type) {
        
        boolean hasCard = false;
        
        for(int i = 0; i < list.size() && list.size() != 0; i++) {
            if(type == null || list.getCard(i).getType() == type) {
                hasCard = true;
            }
        }
        
        return hasCard;
        
    }
}
