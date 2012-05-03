package model.runner;

import model.DiceManager;
import model.Game;
import model.ICardStorage;
import model.IPlayer;
import model.IResourceStorage;
import model.card.AbstractCard;
import model.card.Card;
import model.card.CardActivator;

/**
 * 
 * This class implements the MoveMaker interface
 * It makes moves the game.
 * 
 * @author Jacky Chen
 *
 */

public class GameController implements MoveMaker {

    private DiceManager diceManager;
    private Game g;
    
    public GameController(Game g) {
        this.g = g;
        this.diceManager = g.geDiceManager();
    }
    
    public CardActivator chooseCardToActivate(int disc) {
        
        IPlayer player = g.getCurrentPlayer();
        
        return (CardActivator)player.getField().getCard(disc);
    
    }

    public void activateCardsDisc(int diceToUse, Card chosen)
            throws UnsupportedOperationException {

        ICardStorage deck = g.getDeckStorage();
        ICardStorage discard = g.getDiscardStorage();
        IPlayer player = g.getCurrentPlayer();
        
        diceManager.getActionDie(diceToUse).use();
        
        AbstractCard card = null;
        
        for(int i = 0; i < diceToUse; i++) {
            
            card = deck.popCard();
            
            if(card.getName() == chosen) {
                player.getHand().appendCard(card);
            } else {
                discard.pushCard(card);
            }            
        }
    }

    public void activateMoneyDisc(int diceToUse)
            throws UnsupportedOperationException {

        IPlayer player = g.getCurrentPlayer();
        IResourceStorage bank = g.getBank();
        
        diceManager.getActionDie(diceToUse).use();
        
        bank.transferMoney(player, diceToUse);
        
    }

    public CardActivator activateBribeDisc(int diceToUse)
            throws UnsupportedOperationException {
        
        throw new UnsupportedOperationException();
    }

    public void endTurn() throws UnsupportedOperationException {
        g.advanceTurn();
    }

    public void placeCard(Card toPlace, int discToPlaceOn)
            throws UnsupportedOperationException {

        IPlayer player = g.getCurrentPlayer();
        
        AbstractCard card = player.getHand().getCard(toPlace);
        player.getHand().removeCard(card);
        
        player.getField().layCard(card, discToPlaceOn);
    }

}
