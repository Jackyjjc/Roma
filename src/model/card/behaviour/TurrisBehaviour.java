package model.card.behaviour;

import java.util.ArrayList;
import java.util.List;

import model.IDisc;
import model.IDiscListener;
import model.IField;
import model.card.AbstractCard;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 13/05/12
 * Time: 11:10 PM
 * To change this template use File | Settings | File Templates.
 */
public class TurrisBehaviour extends Behaviour implements IDiscListener {

    private List<AbstractCard> affectedCards;

    public TurrisBehaviour(AbstractCard host) {
        super(host);
        affectedCards = new ArrayList<AbstractCard>();
    }


    @Override
    public void complete() {

    }

    @Override
    public boolean lay(IDisc disc) {

        super.lay(disc);

        observeDiscs();

        IField discs = getHost().getOwner().getField();

        for(IDisc d : discs) {
            addEffects(d.getCard());
        }

        return true;
    }

    @Override
    public void disCard(boolean beenKilled) {

        for(AbstractCard card : affectedCards) {
            card.setDefence(card.getDefence() - 1);
        }

        affectedCards.clear();

        super.disCard(beenKilled);
    }

    public void update(IDisc disc) {

        if(affectedCards.contains(disc.getCard())) {
            affectedCards.remove(disc.getCard());
        } else {
            addEffects(disc.getCard());
        }
    }

    private void addEffects(AbstractCard card) {

        if (card != null && !affectedCards.contains(card) && card != getHost()) {
            card.setDefence(card.getDefence() + 1);
            affectedCards.add(card);
        }
    }

    private void observeDiscs() {

        IField discs = getHost().getOwner().getField();

        for(IDisc disc : discs) {
            disc.addDiscListener((IDiscListener)getHost());
        }
    }
}
