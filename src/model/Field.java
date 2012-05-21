package model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import model.card.AbstractCard;
import model.card.CardType;
import model.card.Kat;
import model.card.behaviour.KatBehaviour;
import model.cardcollection.CardCollectionFactory;

/**
 * Store the list of discs and helper functions
 *
 * @author Chris Fong
 * @author Junjie CHEN
 */

public class Field implements IField {

    private static final int NUM_DISCS = 7;
    private static final int BRIBE_INDEX = 6;

    private IPlayer owner;
    private List<IDisc> discs;

    public static IField createField(ITurnMover turnMover,
                                     IPlayer player, IResourceStorage bank) {

        return new Field(player, turnMover, bank);
    }

    public ICardStorage getCardsOf(CardType type) {

        ICardStorage result = CardCollectionFactory.create(null);
        result.setOwner(owner);

        for (IDisc disc : discs) {
            if (!disc.isEmpty() && disc.getCard().getType() == type) {
                result.appendCard(disc.getCard());
                disc.removeCard();
            }
        }

        return result;
    }

    public IDisc getDisc(int index) {

        IDisc disc = null;

        if (index < discs.size()) {
            disc = discs.get(index);
        }

        return disc;
    }

    public int countUnoccupiedDiscs() {

        int count = 0;

        for (IDisc disc : discs) {
            if (disc.isEmpty()) {
                count++;
            }
        }

        return count;
    }

    public int getNumDiscs() {
        return NUM_DISCS;
    }

    public Iterator<IDisc> iterator() {
        return discs.iterator();
    }

    public void setLives(ICardTracker lives) {
        for (int index = 0; index < NUM_DISCS; index++) {

            AbstractCard card = discs.get(index).getCard();

            if (card instanceof Kat) {
                ((KatBehaviour) card.getBehaviour()).setLives(lives.getLives(index));
            }
        }
    }

    private Field(IPlayer player, ITurnMover turnMover, IResourceStorage bank) {

        this.owner = player;
        discs = new ArrayList<IDisc>(NUM_DISCS);

        for (int i = 0; i < NUM_DISCS - 1; i++) {
            discs.add(new Disc(i, player, turnMover));
        }

        discs.add(new BribeDisc(BRIBE_INDEX, player, turnMover, bank));

        //set up the double ended linked list
        for (int i = 0; i < NUM_DISCS - 1; i++) {
            discs.get(i).setNext(discs.get(i + 1));
        }

        for (int i = NUM_DISCS - 1; i > 0; i--) {
            discs.get(i).setPrev(discs.get(i - 1));
        }
    }
}
