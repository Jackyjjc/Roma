package controller.gameState;

import framework.cards.Card;
import framework.interfaces.MoveMaker;
import gui.GraphicalView;

import java.util.List;

import model.Game;
import model.ICardStorage;
import model.IDisc;
import model.IField;
import model.IPlayer;
import model.card.AbstractCard;
import model.card.Consiliarius;
import model.card.Forum;
import model.card.ICardChecker;
import model.card.Machina;
import model.card.TelephoneBox;
import model.card.Templum;
import model.card.behaviour.AttackSelectedTargetBehaviour;
import model.card.behaviour.Behaviour;
import model.card.behaviour.CenturioBehaviour;
import model.card.behaviour.ConsulBehaviour;
import model.card.behaviour.GladiatorBehaviour;
import model.card.behaviour.KamikazeBehaviour;
import model.card.behaviour.LayForFreeBehaviour;
import model.card.behaviour.PraetorianusBehaviour;
import model.card.behaviour.ScaenicusBehaviour;
import model.runner.CardActivateManager;
import model.runner.GameController;
import controller.GuiInputHandler;
import controller.IGameState;
import controller.IGuiDieInputListener;
import controller.IGuiDiscInputListener;
import controller.ILayCardListener;
import controller.IPassListener;
import controller.IStopEffectListener;
import controller.IUseDieInputListener;

public class PlayGame implements IUseDieInputListener, ILayCardListener, IGameState, IPassListener, 
                                 IGuiDieInputListener, IGuiDiscInputListener, 
                                 IStopEffectListener {

    private Game g;
    private GraphicalView view;
    private GuiInputHandler handler;
    private IGameState next;
    private MoveMaker moveMaker;
    
    private boolean activateTemplum;
    
    public PlayGame(Game g, GraphicalView view, GuiInputHandler handler) {
        this.g = g;
        this.view = view;
        this.handler = handler;
        this.activateTemplum = false;
        this.moveMaker = new GameController(g);
    }

    public void run() {
    
        handler.setLayCardListener(this);
        handler.setUseActionDieListener(this);
        handler.setPassListener(this);
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
            
            moveMaker.activateCardsDisc(dieValue, selected);
            
            g.getNotifier().notifyListeners();
            
        } else if(discIndex == 8) {
            
            moveMaker.activateMoneyDisc(dieValue);
            
            g.getNotifier().notifyListeners();
        
        } else {
            
            IDisc disc = player.getField().getDisc(discIndex - 1);
            
            if(!disc.isEmpty() && (dieValue == discIndex || discIndex == 7)) {
                
                if (discIndex != 7) {
                    moveMaker.chooseCardToActivate(discIndex);
                    activation(g.getCardActivateManager().getActivatedCard().getName());
                } else {
                    if (g.getCurrentPlayer().getMoney() >= dieValue) {
                        moveMaker.activateBribeDisc(dieValue);
                        activation(g.getCardActivateManager().getActivatedCard().getName());
                    }
                }
            }
        }
    }
    
    public void layCard(int fromIndex, int toIndex) {
        
        AbstractCard activatedCard = g.getCardActivateManager().getActivatedCard();
        IPlayer currentPlayer = g.getCurrentPlayer();
        
        if(activatedCard instanceof Machina || activatedCard instanceof Consiliarius) {
            Card name = g.getInputHandler().getList().getCard(fromIndex).getName();
            g.getCardActivateManager().placeCard(name, toIndex + 1);
            if(g.getInputHandler().getList().size() == 0) {
                g.getCardActivateManager().complete();
            }
            g.getNotifier().notifyListeners();
        
        } else {
            AbstractCard card = currentPlayer.getHand().getCard(fromIndex);
            Behaviour behaviour = card.getBehaviour();
            
            if(currentPlayer.getMoney() >= card.getCost()) {
                moveMaker.placeCard(card.getName(), toIndex + 1);
            }

            g.getNotifier().notifyListeners();
            
            ICardStorage hand = g.getCurrentPlayer().getHand();
            if (behaviour instanceof LayForFreeBehaviour && 
                 hand.getCardsOf(((LayForFreeBehaviour)behaviour).getType()).size() == 0) {
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
            moveMaker.endTurn();
            
            if(g.isGameCompleted()) {
                view.gameOver();
            }
            
            g.getNotifier().notifyListeners();
            
            while(rollAgain && g.getDiceManager().isAllSame()) {
                
                if(view.reRollDialog()) {
                   g.getDiceManager().rollActionDice(); 
                   g.setActionDice(g.getActionDice());
                } else {
                    rollAgain = false;
                }
            }
            g.getNotifier().notifyListeners();
        }
    }
    
    private void activation(Card card) {
    
        CardActivateManager manager = g.getCardActivateManager();
        AbstractCard target = null;
        
        if(card == Card.AESCULAPINUM || card == Card.HARUSPEX) {

            ICardStorage pileStorage = g.getInputHandler().getList();
            List<Card> pile = pileStorage.getCardsWithNames();
            
            if (pile.size() != 0) {

                Card c = selectCard(pile, pile.size());
                target = pileStorage.getCard(c);

                ICardChecker checker = (ICardChecker) g.getCardActivateManager().getActivatedCard().getBehaviour();
                while (!checker.isValidCard(target)) {
                    c = selectCard(pile, pile.size());
                    target = pileStorage.getCard(c);
                }

                int index = 0;
                for (int i = 0; i < pile.size(); i++) {
                    if (pile.get(i) == c) {
                        index = i;
                    }
                }

                manager.chooseCardFromPile(index);
                manager.complete();

            }
            
        } else if (card == Card.GLADIATOR || card == Card.SICARIUS 
                    || card == Card.ONAGER || card == Card.VELITES
                    || card == Card.NERO || card == Card.PRAETORIANUS
                    || card == Card.SCAENICUS) {
            
            view.showTargetInputDialog();
        
        } else if (card == Card.LEGAT || card == Card.TRIBUNUSPLEBIS || card == Card.MERCATUS) {
            
            manager.complete();
            
        } else if (card == Card.CONSUL) {
            
            if(g.getActionDice().length > 1) {
                view.showDieInputDialog();
                view.enableActionDiceAdapter(false);
            }

        } else if (card == Card.ARCHITECTUS || card == Card.SENATOR) {
            
            view.layCardForFreeDialog();
            view.enableStopButton(true);
        
        } else if (card == Card.FORUM) {
            
            view.showDieInputDialog();
            view.enableActionDiceAdapter(false);
        } else if (card == Card.TELEPHONEBOX) {
            
            if(g.getActionDice().length >= 2) {
                if(view.showTelephoneBoxConfirmDialog()) {
                    manager.shouldMoveForwardInTime(true);
                } else {
                    manager.shouldMoveForwardInTime(false);
                }
                view.showTargetInputDialog();
            }
        } else if (card == Card.MERCATOR) {
            
            int maxCanBuy = g.getCurrentPlayer().getMoney() / 2;
            int maxVP = g.getCurrentPlayer().getOpponent().getVP();
            
            int min = Math.min(maxCanBuy, maxVP);
            
            int amount = view.showMercatorBuyingDialog(min);
            if(amount != - 1) {
                while(amount == 0) {
                    amount = view.showMercatorBuyingDialog(min);
                }
                manager.chooseMercatorBuyNum(amount);
                manager.complete();
            }
        } else if (card == Card.LEGIONARIUS || card == Card.CENTURIO) {
            
            g.getDiceManager().rollBattleDice();
            manager.giveAttackDieRoll(g.getDiceManager().getBattleDie().getValue());
            
            boolean addDie = false;
            g.getNotifier().notifyListeners();
            
            if(card == Card.CENTURIO) {
                
                if(g.getActionDice().length != 0) {
                    addDie = view.centurioAddDieDialog();
                    manager.chooseCenturioAddActionDie(addDie);
                    if(addDie) {
                        view.enableActionDiceAdapter(false);
                    }
                }
            }
            
            if(!addDie || !(card == Card.CENTURIO)) {
                manager.complete();
            }
            
        }
        
        g.getNotifier().notifyListeners();
    }

    public void discInput(int discIndex) {
        
        CardActivateManager manager = g.getCardActivateManager();
        AbstractCard card = manager.getActivatedCard();
        Behaviour behaviour = manager.getActivatedCard().getBehaviour();
        
        if(behaviour instanceof GladiatorBehaviour || behaviour instanceof KamikazeBehaviour 
                || behaviour instanceof AttackSelectedTargetBehaviour) {
            
            IField field = g.getCurrentPlayer().getOpponent().getField();
            IDisc disc = field.getDisc(discIndex);
            
            if(!disc.isEmpty()) {
                boolean confirm = view.showTargetConfirmDialog(disc.getCard().getName());
                if(confirm) {
                    ICardChecker checker = (ICardChecker) behaviour;
                    
                    if(checker.isValidCard(disc.getCard())) {
                        
                        if(!(behaviour instanceof KamikazeBehaviour 
                             && disc.getCard().getType() == ((KamikazeBehaviour)behaviour).getType())) {
                             
                            manager.chooseDiceDisc(discIndex + 1);
                            if(behaviour instanceof AttackSelectedTargetBehaviour) {
                                g.getDiceManager().rollBattleDice();
                                manager.giveAttackDieRoll(g.getDiceManager().getBattleDie().getValue());
                            }
                            manager.complete();
                        }
                        
                    } else {
                        view.showTargetInputDialog();
                    }
                }
            }
            
        } else if(behaviour instanceof PraetorianusBehaviour) {
            manager.chooseDiceDisc(discIndex + 1);
            manager.complete();
        } else if(card instanceof TelephoneBox) {
            manager.chooseDiceDisc(discIndex + 1);
            view.enableActionDiceAdapter(false);
            view.showDieInputDialog();
        } else if (behaviour instanceof ScaenicusBehaviour) {
            manager.getScaenicusMimicTarget(discIndex + 1);
            activation(g.getCurrentPlayer().getField().getDisc(discIndex).getCard().getName());
        }
        
        g.getNotifier().notifyListeners();
    }

    public void dieInput(int dieValue) {
        
        CardActivateManager manager = g.getCardActivateManager();
        AbstractCard card = manager.getActivatedCard();
        Behaviour behaviour = manager.getActivatedCard().getBehaviour();
        
        if(behaviour instanceof ConsulBehaviour) {

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
        
        } else if(behaviour instanceof CenturioBehaviour) {

            manager.chooseActionDice(dieValue);
            manager.complete();
            view.enableActionDiceAdapter(true);
        } else if(activateTemplum) {
        
            manager.chooseActivateTemplum(dieValue);
            manager.complete();
            activateTemplum = false;
            view.enableActionDiceAdapter(true);
            g.getNotifier().notifyListeners();
            
        } else if (card instanceof Forum) {
            
            manager.chooseActionDice(dieValue);
            IDisc disc = card.getDisc();
            if((disc.getNext() != null && disc.getNext().getCard() instanceof Templum) 
                 || (disc.getPrev() != null && disc.getPrev().getCard() instanceof Templum) 
                  && g.getActionDice().length >= 1) {
                if(view.showTemplumConfirmDialog()) {
                    manager.chooseActivateTemplum(true);
                    this.activateTemplum = true;
                    view.showDieInputDialog();
                }
            } else {
                view.enableActionDiceAdapter(true);
                manager.complete();
                g.getNotifier().notifyListeners();
            }
        } else if(card instanceof TelephoneBox) {
            manager.chooseActionDice(dieValue);
            manager.complete();
            view.enableActionDiceAdapter(true);
        }
        
        if(!(card instanceof Forum)) {
            g.getNotifier().notifyListeners();
        }

    }

    public void stopEffect() {
        
        CardActivateManager manager = g.getCardActivateManager();
        Behaviour behaviour = manager.getActivatedCard().getBehaviour();
        
        view.enableStopButton(false);
        if(behaviour instanceof LayForFreeBehaviour) {
            manager.complete();
        }
        
        g.getNotifier().notifyListeners();
    }
}
