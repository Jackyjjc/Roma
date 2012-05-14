package model.runner;

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


public class BlockedManager implements AesculapinumActivator, ArchitectusActivator, 
CenturioActivator, ConsiliariusActivator, ConsulActivator, EssedumActivator, ForumActivator, GladiatorActivator,
HaruspexActivator, LegatActivator, LegionariusActivator, MachinaActivator, MercatorActivator, MercatusActivator,
NeroActivator, OnagerActivator, PraetorianusActivator, ScaenicusActivator, SenatorActivator, SicariusActivator,
TribunusPlebisActivator, VelitesActivator {
	
	public BlockedManager () {
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
