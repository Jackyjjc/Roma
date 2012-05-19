package model.runner;

import model.IDisc;
import model.IGameIO;
import model.IPlayer;
import model.IPlayerManager;
import model.InputHandler;
import model.TurnMover;
import model.action.AddBattleDieInput;
import model.action.AddBooleanInputAction;
import model.action.AddCardInputAction;
import model.action.AddDieInputAction;
import model.action.AddDiscInputAction;
import model.action.AddIntInputAction;
import model.action.ChooseCardFromPileAction;
import model.action.CompleteAction;
import model.action.MimicAction;
import model.card.AbstractCard;
import model.card.Aesculapinum;
import model.card.Haruspex;
import model.card.TelephoneBox;
import model.card.behaviour.ScaenicusBehaviour;
import framework.cards.Card;
import framework.interfaces.GameState;
import framework.interfaces.activators.AesculapinumActivator;
import framework.interfaces.activators.ArchitectusActivator;
import framework.interfaces.activators.CardActivator;
import framework.interfaces.activators.CenturioActivator;
import framework.interfaces.activators.ConsiliariusActivator;
import framework.interfaces.activators.ConsulActivator;
import framework.interfaces.activators.EssedumActivator;
import framework.interfaces.activators.ForumActivator;
import framework.interfaces.activators.GladiatorActivator;
import framework.interfaces.activators.HaruspexActivator;
import framework.interfaces.activators.LegatActivator;
import framework.interfaces.activators.LegionariusActivator;
import framework.interfaces.activators.MachinaActivator;
import framework.interfaces.activators.MercatorActivator;
import framework.interfaces.activators.MercatusActivator;
import framework.interfaces.activators.NeroActivator;
import framework.interfaces.activators.OnagerActivator;
import framework.interfaces.activators.PraetorianusActivator;
import framework.interfaces.activators.ScaenicusActivator;
import framework.interfaces.activators.SenatorActivator;
import framework.interfaces.activators.SicariusActivator;
import framework.interfaces.activators.TelephoneBoxActivator;
import framework.interfaces.activators.TribunusPlebisActivator;
import framework.interfaces.activators.VelitesActivator;

public class CardActivateManager implements AesculapinumActivator, ArchitectusActivator,
CenturioActivator, ConsiliariusActivator, ConsulActivator, EssedumActivator, ForumActivator, GladiatorActivator,
HaruspexActivator, LegatActivator, LegionariusActivator, MachinaActivator, MercatorActivator, MercatusActivator,
NeroActivator, OnagerActivator, PraetorianusActivator, ScaenicusActivator, SenatorActivator, SicariusActivator,
TribunusPlebisActivator, TelephoneBoxActivator, VelitesActivator {

    private IPlayerManager manager;
    private InputHandler handler;
    private AbstractCard activatedCard;
    private GameState g;
    private TurnMover turnMover;
    
    public CardActivateManager(GameState g, IGameIO gameIO, IPlayerManager manager, TurnMover turnMover) {
        this.handler = gameIO.getInputHandler();
        this.manager = manager;
        this.g = g;
        this.turnMover = turnMover;
    }

    public void chooseCardFromPile(int indexOfCard) {
        chooseCardFromPile(findCardFromPile(indexOfCard));
    }
    
    public void chooseCardFromPile(Card card) {
        turnMover.getCurrentTurn().addAction(new ChooseCardFromPileAction(g, this, handler, card));
        handler.addCardInput(manager.getCurrentPlayer().getId(), card);
    }
    
    public void complete() {
        turnMover.getCurrentTurn().addAction(new CompleteAction(g, this, handler));
        if(activatedCard != null) {
            activatedCard.complete();
            activatedCard = null;  
        }
    }

    public void giveAttackDieRoll(int roll) {
        turnMover.getCurrentTurn().addAction(new AddBattleDieInput(g, this, handler, roll));
        handler.addBattleDieInput(roll);
    }

    public void chooseActionDice(int actionDiceValue) {
        turnMover.getCurrentTurn().addAction(new AddDieInputAction(g, this, handler, actionDiceValue));
        handler.addDieInput(actionDiceValue);
    }

    public void chooseCenturioAddActionDie(boolean attackAgain) {
        turnMover.getCurrentTurn().addAction(new AddBooleanInputAction(g, this, handler, attackAgain));
        handler.addBooleanInput(attackAgain);
    }

    public void placeCard(Card name, int diceDisc) {

        IPlayer player = manager.getCurrentPlayer();

        turnMover.getCurrentTurn().addAction(new AddCardInputAction(g, this, handler, player.getId(), name));
        handler.addCardInput(player.getId(), name);
        turnMover.getCurrentTurn().addAction(new AddDiscInputAction(g, this, handler, player.getId(), diceDisc));
        handler.addDiscInput(player.getId(), diceDisc - 1);
        
    }

    public void chooseConsulChangeAmount(int amount) {
        turnMover.getCurrentTurn().addAction(new AddIntInputAction(g, this, handler, amount));
        handler.addIntInput(amount);
    }

    public void chooseWhichDiceChanges(int originalRoll) {
        turnMover.getCurrentTurn().addAction(new AddDieInputAction(g, this, handler, originalRoll));
        handler.addDieInput(originalRoll);
    }

    public void chooseDiceDisc(int diceDisc) {
        
        IPlayer player = null;

        if(activatedCard instanceof TelephoneBox) {
            player = manager.getCurrentPlayer();
        } else {
            player = manager.getCurrentPlayer().getOpponent();
        }
        turnMover.getCurrentTurn().addAction(new AddDiscInputAction(g, this, handler, player.getId(), diceDisc - 1));
        handler.addDiscInput(player.getId(), diceDisc - 1);
    }

    public void chooseActivateTemplum(boolean activate) {
        turnMover.getCurrentTurn().addAction(new AddBooleanInputAction(g, this, handler, activate));
        handler.addBooleanInput(activate);
    }

    public void chooseActivateTemplum(int diceValue) {
        turnMover.getCurrentTurn().addAction(new AddDieInputAction(g, this, handler, diceValue));
        handler.addDieInput(diceValue);
    }

    public void chooseMercatorBuyNum(int VPToBuy) {
        
        for(int i = 0; i < VPToBuy; i++) {
            handler.addBooleanInput(true);
            turnMover.getCurrentTurn().addAction(new AddBooleanInputAction(g, this, handler, true));
        }
    }

    public CardActivator getScaenicusMimicTarget(int diceDisc) {
        
        turnMover.getCurrentTurn().addAction(new MimicAction(g, this, handler, diceDisc));
        
        IPlayer player = manager.getCurrentPlayer();
        
        handler.addDiscInput(player.getId(), diceDisc - 1);
        
        ScaenicusBehaviour behaviour = ((ScaenicusBehaviour)activatedCard.getBehaviour()).getMimicBehaviour();
        behaviour.mimic();
        return this;
    }
    
    public void activate(IDisc disc) {
        
        this.activatedCard = disc.getCard();
        disc.activateCard();
        
    }                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                           

    public void layCard(Card name, int whichDiceDisc) {
        placeCard(name, whichDiceDisc);
    }
    
    public AbstractCard getActivatedCard() {
        return activatedCard;
    }
    
    private Card findCardFromPile(int index) {
        
        Card c = null;
        
        if(activatedCard instanceof Aesculapinum) {
            
            c = g.getDiscard().get(index);
            
        } else if(activatedCard instanceof Haruspex) {
            
            c = g.getDeck().get(index);
        }
        
        return c;
    }

    public void shouldMoveForwardInTime(boolean isForward) {
        turnMover.getCurrentTurn().addAction(new AddBooleanInputAction(g,this,handler,isForward));
        handler.addBooleanInput(isForward);
    }

    public void setSecondDiceUsed(int diceValue) {
        turnMover.getCurrentTurn().addAction(new AddDieInputAction(g,this,handler,diceValue));
        handler.addDieInput(diceValue);
    }
}
