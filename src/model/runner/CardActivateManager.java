package model.runner;

import model.IDisc;
import model.IGameIO;
import model.IPlayer;
import model.IPlayerManager;
import model.InputHandler;
import model.card.AbstractCard;
import model.card.behaviour.ScaenicusBehaviour;
import framework.cards.Card;
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
    
    public CardActivateManager(IGameIO gameIO, IPlayerManager manager) {
        this.handler = gameIO.getInputHandler();
        this.manager = manager;
    }

    public void chooseCardFromPile(int indexOfCard) {
        handler.addIntInput(indexOfCard);
    }
    
    public void complete() {
        activatedCard.complete();
    }

    public void giveAttackDieRoll(int roll) {
        handler.addBattleDieInput(roll);
    }

    public void chooseActionDice(int actionDiceValue) {
        handler.addDieInput(actionDiceValue);
    }

    public void chooseCenturioAddActionDie(boolean attackAgain) {
        handler.addBooleanInput(attackAgain);
    }

    public void placeCard(Card name, int diceDisc) {

        IPlayer player = manager.getCurrentPlayer();

        handler.addCardInput(player.getId(), name);
        handler.addDiscInput(player.getId(), diceDisc - 1);
        
        //TODO: this looks weird
        AbstractCard card = handler.getCardInput();
        IDisc disc = handler.getDiscInput();
        card.lay(disc);
    }

    public void chooseConsulChangeAmount(int amount) {
        handler.addIntInput(amount);
    }

    public void chooseWhichDiceChanges(int originalRoll) {
        handler.addDieInput(originalRoll);
    }

    public void chooseDiceDisc(int diceDisc) {
        
        IPlayer opponent = manager.getCurrentPlayer().getOpponent();
        
        handler.addDiscInput(opponent.getId(), diceDisc - 1);
    }

    public void chooseActivateTemplum(boolean activate) {
        handler.addBooleanInput(activate);
    }

    public void chooseActivateTemplum(int diceValue) {
        handler.addDieInput(diceValue);
    }

    public void chooseMercatorBuyNum(int VPToBuy) {
        
        for(int i = 0; i < VPToBuy; i++) {
            handler.addBooleanInput(true);
        }
    }

    public CardActivator getScaenicusMimicTarget(int diceDisc) {
        
        IPlayer player = manager.getCurrentPlayer();
        
        handler.addDiscInput(player.getId(), diceDisc - 1);
        
        ScaenicusBehaviour behaviour = (ScaenicusBehaviour)activatedCard.getBehaviour();
        behaviour.mimic();
        
        return this;
    }
    
    public void initialise(AbstractCard card) {
        this.activatedCard = card;
        card.initialise();
    }                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                           

    public void layCard(Card name, int whichDiceDisc) {
        placeCard(name, whichDiceDisc);
    }
}
