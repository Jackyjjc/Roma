package model.input;

import framework.cards.*;
import framework.interfaces.MoveMaker;
import framework.interfaces.activators.AesculapinumActivator;
import framework.interfaces.activators.ArchitectusActivator;
import framework.interfaces.activators.BasilicaActivator;
import framework.interfaces.activators.CardActivator;
import framework.interfaces.activators.CenturioActivator;
import framework.interfaces.activators.ConsiliariusActivator;
import framework.interfaces.activators.ConsulActivator;
import framework.interfaces.activators.EssedumActivator;
import framework.interfaces.activators.ForumActivator;
import framework.interfaces.activators.GladiatorActivator;
import framework.interfaces.activators.GrimReaperActivator;
import framework.interfaces.activators.HaruspexActivator;
import framework.interfaces.activators.KatActivator;
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
import framework.interfaces.activators.TemplumActivator;
import framework.interfaces.activators.TribunusPlebisActivator;
import framework.interfaces.activators.TurrisActivator;
import framework.interfaces.activators.VelitesActivator;
import model.Input;

public class UseCard implements Input {

	private MoveMaker move;
	private Card[][] playerDiscs;
	private int disc;
	private int playerId;
	
    public UseCard (MoveMaker move, Card[][] playerDiscs, int playerId, int disc) {
    	
    	this.move = move;
    	this.playerDiscs = playerDiscs;
    	this.disc = disc;
    	this.playerId = playerId;
    	
    }
	
	public void run() {
		
		Card c = playerDiscs[playerId][disc];
		
		
	}

	
	
}
