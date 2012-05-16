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
import model.card.AbstractCard;
import model.card.Aesculapinum;
import model.card.Haruspex;
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
import framework.interfaces.activators.TribunusPlebisActivator;
import framework.interfaces.activators.VelitesActivator;

public class CardActivateManager implements AesculapinumActivator, ArchitectusActivator,
CenturioActivator, ConsiliariusActivator, ConsulActivator, EssedumActivator, ForumActivator, GladiatorActivator,
HaruspexActivator, LegatActivator, LegionariusActivator, MachinaActivator, MercatorActivator, MercatusActivator,
NeroActivator, OnagerActivator, PraetorianusActivator, ScaenicusActivator, SenatorActivator, SicariusActivator,
TribunusPlebisActivator, VelitesActivator {

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
        
        Card card = findCardFromPile(indexOfCard);
        handler.addCardInput(manager.getCurrentPlayer().getId(), card);
        turnMover.getCurrentTurn().addAction(new ChooseCardFromPileAction(g, this, handler, card, indexOfCard));
    }
    
    public void complete() {
        activatedCard.complete();
        turnMover.getCurrentTurn().addAction(new CompleteAction(g, this, handler));
    }

    public void giveAttackDieRoll(int roll) {
        handler.addBattleDieInput(roll);
        turnMover.getCurrentTurn().addAction(new AddBattleDieInput(g, this, handler, roll));
    }

    public void chooseActionDice(int actionDiceValue) {
        handler.addDieInput(actionDiceValue);
        turnMover.getCurrentTurn().addAction(new AddDieInputAction(g, this, handler, actionDiceValue));
    }

    public void chooseCenturioAddActionDie(boolean attackAgain) {
        handler.addBooleanInput(attackAgain);
        turnMover.getCurrentTurn().addAction(new AddBooleanInputAction(g, this, handler, attackAgain));
    }

    public void placeCard(Card name, int diceDisc) {

        IPlayer player = manager.getCurrentPlayer();

        handler.addCardInput(player.getId(), name);
        turnMover.getCurrentTurn().addAction(new AddCardInputAction(g, this, handler, player.getId(), name));
        handler.addDiscInput(player.getId(), diceDisc - 1);
        turnMover.getCurrentTurn().addAction(new AddDiscInputAction(g, this, handler, player.getId(), diceDisc));
        
    }

    public void chooseConsulChangeAmount(int amount) {
        handler.addIntInput(amount);
        turnMover.getCurrentTurn().addAction(new AddIntInputAction(g, this, handler, amount));
    }

    public void chooseWhichDiceChanges(int originalRoll) {
        handler.addDieInput(originalRoll);
        turnMover.getCurrentTurn().addAction(new AddDieInputAction(g, this, handler, originalRoll));
    }

    public void chooseDiceDisc(int diceDisc) {
        
        IPlayer opponent = manager.getCurrentPlayer().getOpponent();
        
        handler.addDiscInput(opponent.getId(), diceDisc - 1);
        turnMover.getCurrentTurn().addAction(new AddDiscInputAction(g, this, handler, opponent.getId(), diceDisc));
    }

    public void chooseActivateTemplum(boolean activate) {
        handler.addBooleanInput(activate);
        turnMover.getCurrentTurn().addAction(new AddBooleanInputAction(g, this, handler, activate));
    }

    public void chooseActivateTemplum(int diceValue) {
        handler.addDieInput(diceValue);
        turnMover.getCurrentTurn().addAction(new AddDieInputAction(g, this, handler, diceValue));
    }

    public void chooseMercatorBuyNum(int VPToBuy) {
        
        for(int i = 0; i < VPToBuy; i++) {
            handler.addBooleanInput(true);
            turnMover.getCurrentTurn().addAction(new AddBooleanInputAction(g, this, handler, true));
        }
    }

    public CardActivator getScaenicusMimicTarget(int diceDisc) {
        
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
}
