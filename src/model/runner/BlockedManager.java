package model.runner;

import framework.cards.Card;
import framework.interfaces.activators.*;


public class BlockedManager implements AesculapinumActivator, ArchitectusActivator,
        CenturioActivator, ConsiliariusActivator, ConsulActivator, EssedumActivator, ForumActivator, GladiatorActivator,
        HaruspexActivator, LegatActivator, LegionariusActivator, MachinaActivator, MercatorActivator, MercatusActivator,
        NeroActivator, OnagerActivator, PraetorianusActivator, ScaenicusActivator, SenatorActivator, SicariusActivator,
        TribunusPlebisActivator, VelitesActivator {

    public BlockedManager() {
        doNothing();
    }

    public void chooseCardFromPile(int indexOfCard) {
        doNothing();
    }

    public void complete() {
        doNothing();
    }

    public void giveAttackDieRoll(int roll) {
        doNothing();
    }

    public void chooseActionDice(int actionDiceValue) {
        doNothing();
    }

    public void placeCard(Card card, int diceDisc) {
        doNothing();
    }

    public void chooseDiceDisc(int diceDisc) {
        doNothing();
    }

    public CardActivator getScaenicusMimicTarget(int diceDisc) {
        return null;
    }

    public void chooseMercatorBuyNum(int VPToBuy) {
        doNothing();
    }

    public void chooseActivateTemplum(boolean activate) {
        doNothing();
    }

    public void chooseConsulChangeAmount(int amount) {
        doNothing();
    }

    public void chooseWhichDiceChanges(int originalRoll) {
        doNothing();
    }

    public void chooseCenturioAddActionDie(boolean attackAgain) {
        doNothing();
    }

    public void chooseActivateTemplum(int diceValue) {
        doNothing();
    }

    public void layCard(Card myCard, int whichDiceDisc) {
        doNothing();
    }

    private void doNothing() {

    }


}
