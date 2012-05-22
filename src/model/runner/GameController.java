package model.runner;

import framework.cards.Card;
import framework.interfaces.MoveMaker;
import framework.interfaces.activators.CardActivator;
import model.*;
import model.action.*;
import model.card.AbstractCard;

/**
 * This class implements the MoveMaker interface
 * It makes moves the game.
 *
 * @author Jacky Chen
 */

public class GameController implements MoveMaker {

    private DiceManager diceManager;
    private Game g;

    public GameController(Game g) {
        this.g = g;
        this.diceManager = g.getDiceManager();
    }

    public CardActivator chooseCardToActivate(int discIndex) {

        CardActivator activator = new BlockedManager();

        IPlayer player = g.getCurrentPlayer();

        CardActivateManager activateManager = g.getCardActivateManager();

        //disc is from 1 - 7
        IDisc disc = player.getField().getDisc(discIndex - 1);

        if (!disc.isBlocked()) {
            activator = activateManager;
            activateManager.activate(disc);
            g.getDiceManager().getActionDie(discIndex).use();
            g.getTurnMover().getCurrentTurn().addAction(new ActivateCardAction(g, this, discIndex));
        }

        return activator;

    }

    public void activateCardsDisc(int diceToUse, Card chosen)
            throws UnsupportedOperationException {

        ICardStorage deck = g.getDeckStorage();
        ICardStorage discard = g.getDiscardStorage();
        IPlayer player = g.getCurrentPlayer();

        diceManager.getActionDie(diceToUse).use();

        AbstractCard card = null;

        boolean found = false;
        
        for (int i = 0; i < diceToUse; i++) {

            card = deck.popCard();

            if (!found && card.getName() == chosen) {
                card.setOwner(player);
                player.getHand().appendCard(card);
                found = true;
            } else {
                discard.pushCard(card);
            }
        }

        g.getTurnMover().getCurrentTurn().addAction(new DrawCardsAction(g, this, diceToUse, chosen));
    }

    public void activateMoneyDisc(int diceToUse)
            throws UnsupportedOperationException {

        IPlayer player = g.getCurrentPlayer();
        IResourceStorage bank = g.getBank();

        diceManager.getActionDie(diceToUse).use();

        bank.transferMoney(player, diceToUse);

        g.getTurnMover().getCurrentTurn().addAction(new GetMoneyAction(g, this, diceToUse));
    }

    public CardActivator activateBribeDisc(int diceToUse)
            throws UnsupportedOperationException {

        int bribeDiscIndex = 6;
        CardActivator activator = new BlockedManager();

        IDisc bribeDisc = g.getCurrentPlayer().getField().getDisc(bribeDiscIndex);
        ((BribeDisc) bribeDisc).giveBribe(diceToUse);

        CardActivateManager activateManager = g.getCardActivateManager();

        if (!bribeDisc.isBlocked()) {
            activator = activateManager;
            activateManager.activate(bribeDisc);
            g.getDiceManager().getActionDie(diceToUse).use();
            g.getTurnMover().getCurrentTurn().addAction(new ActivateBribeDiscAction(g, this, diceToUse));
        }


        return activator;

    }

    public void endTurn() throws UnsupportedOperationException {
        g.getTurnMover().getCurrentTurn().addAction(new EndTurnAction(g, this));
        g.getTurnMover().advanceTurn();
    }

    public void placeCard(Card toPlace, int discToPlaceOn)
            throws UnsupportedOperationException {

        IPlayer player = g.getCurrentPlayer();

        AbstractCard card = player.getHand().getCard(toPlace);
        player.getHand().removeCard(card);

        //the disc to place on is from 1 - 7
        IDisc disc = player.getField().getDisc(discToPlaceOn - 1);

        card.lay(disc);

        g.getTurnMover().getCurrentTurn().addAction(new PlaceCardAction(g, this, toPlace, discToPlaceOn));
    }

}