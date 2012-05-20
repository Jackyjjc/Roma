package controller.gameState;

import framework.cards.Card;
import gui.GraphicalView;

import java.util.ArrayList;
import java.util.List;

import model.BribeDisc;
import model.Die;
import model.Game;
import model.ICardStorage;
import model.IDisc;
import model.IField;
import model.IPlayer;
import model.card.AbstractCard;
import model.card.Aesculapinum;
import model.card.Architectus;
import model.card.CardType;
import model.card.Centurio;
import model.card.Consiliarius;
import model.card.Consul;
import model.card.Gladiator;
import model.card.Haruspex;
import model.card.ICardChecker;
import model.card.Legat;
import model.card.Legionarius;
import model.card.Machina;
import model.card.Mercator;
import model.card.Mercatus;
import model.card.Nero;
import model.card.Onager;
import model.card.Praetorianus;
import model.card.Senator;
import model.card.Sicarius;
import model.card.TribunusPlebis;
import model.card.Velites;
import model.runner.CardActivateManager;
import controller.GuiInputHandler;
import controller.IGameState;
import controller.IGuiCardInputListener;
import controller.IGuiDieInputListener;
import controller.IGuiDiscInputListener;
import controller.ILayCardListener;
import controller.IPassListener;
import controller.IStopEffectListener;
import controller.IUseDieInputListener;

public class PlayGame implements IUseDieInputListener, ILayCardListener, IGameState, IPassListener, 
                                 IGuiDieInputListener, IGuiCardInputListener, IGuiDiscInputListener, 
                                 IStopEffectListener {

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
        handler.setCardInputListener(this);
        handler.setDieInputListener(this);
        handler.setDiscInputListener(this);
        handler.setStopEffectListener(this);
        
        view.showGameStarts();
        g.setPlayerVictoryPoints(1, 10);
        g.getNotifier().notifyListeners();
        
    }
    
    public void useDice(int dieValue, int discIndex) {
        
        IPlayer player = g.getCurrentPlayer();
        
        if(discIndex == 0) {
            
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
            
        } else if(discIndex == 8) {
            g.getBank().transferMoney(player, dieValue);
            g.getDiceManager().getActionDie(dieValue).use();
            g.getNotifier().notifyListeners();
        
        } else {
            
            if(dieValue == discIndex || discIndex == 7) {
                IDisc disc = player.getField().getDisc(discIndex - 1);
                
                if(!disc.isBlocked() && !disc.isEmpty()) {
                    
                    if(disc instanceof BribeDisc) {
                        BribeDisc bribe = (BribeDisc)disc;
                        bribe.giveBribe(dieValue);
                        if(g.getCurrentPlayer().getMoney() >= dieValue) {
                            activateCard(dieValue, disc);
                        }
                        
                    } else {
                        activateCard(dieValue, disc);
                    }
                }
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
        
        AbstractCard activatedCard = g.getCardActivateManager().getActivatedCard();
        ICardStorage hand = g.getCurrentPlayer().getHand();
        if(activatedCard != null && activatedCard instanceof Architectus || activatedCard instanceof Senator) {
            if((activatedCard instanceof Architectus && hand.getCardsOf(CardType.BUILDING).size() == 0) 
             || activatedCard instanceof Senator && hand.getCardsOf(CardType.CHARACTER).size() == 0) {
                view.enableStopButton(false);
                g.getCardActivateManager().complete();
            }
        }
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
            
        } else if(card instanceof Gladiator || card instanceof Sicarius 
                || card instanceof Onager || card instanceof Velites || card instanceof Nero) {
            
            view.showTargetInputDialog();
            die.use();
        
        } else if(card instanceof Legionarius || card instanceof Centurio) {
            die.use();
            g.getDiceManager().rollBattleDice();
            manager.giveAttackDieRoll(g.getDiceManager().getBattleDie().getValue());
            
            boolean addDie = false;
            g.getNotifier().notifyListeners();
            
            if(card instanceof Centurio) {
                
                if(g.getActionDice().length != 0) {
                    addDie = view.centurioAddDieDialog();
                    manager.chooseCenturioAddActionDie(addDie);
                    if(addDie) {
                        view.enableActionDiceAdapter(false);
                    }
                }
            }
            
            if(!addDie || !(card instanceof Centurio)) {
                manager.complete();
            }
            
        } else if(card instanceof Legat || card instanceof TribunusPlebis|| card instanceof Mercatus) {
            die.use();
            manager.complete();
        } else if(card instanceof Mercator) {
            
            int maxCanBuy = g.getCurrentPlayer().getMoney() / 2;
            int maxVP = g.getCurrentPlayer().getOpponent().getVP();
            
            int min = Math.min(maxCanBuy, maxVP);
            
            int amount = view.showMercatorBuyingDialog(min);
            if(amount != - 1) {
                while(amount == 0) {
                    amount = view.showMercatorBuyingDialog(min);
                }
                manager.chooseMercatorBuyNum(amount);
                die.use();
                manager.complete();
            }
        } else if(card instanceof Praetorianus) {
            view.showTargetInputDialog();
            die.use();
        } else if(card instanceof Consul) {
            if(g.getActionDice().length > 1) {
                die.use();
                view.showDieInputDialog();
                view.enableActionDiceAdapter(false);
            }
        } else if(card instanceof Architectus || card instanceof Senator) {
            die.use();
            view.layCardForFreeDialog();
            view.enableStopButton(true);
        } else if(card instanceof Machina || card instanceof Consiliarius) {
            die.use();
            ICardStorage list = g.getInputHandler().getList();
            List<Card> displayList = new ArrayList<Card>();
            for(int i = 0; i < list.size(); i++) {
                displayList.add(list.getCard(i).getName());
                System.out.println("added " + list.getCard(i).getName());
            }
            view.getHand().setHand(displayList);
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

    public void discInput(int discIndex) {
        
        CardActivateManager manager = g.getCardActivateManager();
        AbstractCard card = manager.getActivatedCard();
        
        if(card instanceof Gladiator || card instanceof Sicarius 
                || card instanceof Onager || card instanceof Velites || card instanceof Nero) {
            
            IField field = g.getCurrentPlayer().getOpponent().getField();
            IDisc disc = field.getDisc(discIndex);
            
            if(!disc.isEmpty()) {
                boolean confirm = view.showTargetConfirmDialog(disc.getCard().getName());
                if(confirm) {
                    ICardChecker checker = (ICardChecker) card.getBehaviour();
                    
                    if(checker.isValidCard(disc.getCard())) {
                        manager.chooseDiceDisc(discIndex + 1);
                        if(card instanceof Onager || card instanceof Velites) {
                            g.getDiceManager().rollBattleDice();
                            manager.giveAttackDieRoll(g.getDiceManager().getBattleDie().getValue());
                        }
                        manager.complete();
                    } else {
                        view.showTargetInputDialog();
                    }
                }
            }
            
        } else if(card instanceof Praetorianus) {
            manager.chooseDiceDisc(discIndex + 1);
            manager.complete();
        }
        
        g.getNotifier().notifyListeners();
    }

    public void cardInput(Card card) {
        
    }

    public void dieInput(int dieValue) {
        
        CardActivateManager manager = g.getCardActivateManager();
        AbstractCard card = manager.getActivatedCard();
        
        if(card instanceof Consul) {

            manager.chooseWhichDiceChanges(dieValue);
            
            Integer[] availableAmount = null;
            
            if(dieValue > 1 && dieValue < 6) {
                availableAmount = new Integer[] {-1,1};
            } else if(dieValue == 1) {
                availableAmount = new Integer[] {1};  
            } else if(dieValue == 6) {
                availableAmount = new Integer[] {-1};
            }
            
            int amount = view.showDieAmountChangeInput(availableAmount);
            manager.chooseConsulChangeAmount(amount);
            manager.complete();
            
            view.enableActionDiceAdapter(true);
        
        } else if(card instanceof Centurio) {

            g.getDiceManager().getActionDie(dieValue).use();
            manager.chooseActionDice(dieValue);
            manager.complete();
            view.enableActionDiceAdapter(true);
        }
        
        g.getNotifier().notifyListeners();
    }

    public void stopEffect() {
        
        CardActivateManager manager = g.getCardActivateManager();
        AbstractCard card = manager.getActivatedCard();
        
        view.enableStopButton(false);
        if(card instanceof Architectus || card instanceof Senator) {
            manager.complete();
        }
        
        g.getNotifier().notifyListeners();
    }
}
