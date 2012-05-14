package model.card.behaviour;

import java.util.ArrayList;
import java.util.List;

import model.IDisc;
import model.IField;
import model.ITurnMover;
import model.card.AbstractCard;
import model.ITurnListener;

public class EssedumBehaviour extends Behaviour implements ITurnListener {

	private List<AbstractCard> affectedCards;
	private ITurnMover turnMover;
	
    public EssedumBehaviour(AbstractCard host, ITurnMover turnMover) {
        super(host);
        affectedCards = new ArrayList<AbstractCard>();
        this.turnMover = turnMover;
    }

    public boolean lay(IDisc disc) {

        boolean succeed = super.lay(disc);

        if (succeed) {
            turnMover.addTurnListener(this);
        }

        return succeed;
    }

    public void complete() {
        addEffects();
    }

    public void disCard() {
        super.disCard();
        removeEffects();
        stopListening();
    }

    private void addEffects() {

        IField discs = getHost().getOwner().getOpponent().getField();
        AbstractCard card;

        for (IDisc disc : discs) {

            if (disc != null) {
                card = disc.getCard();
                if (card != null) {
                    card.setDefence(card.getDefence() - 2);
                    affectedCards.add(card);
                }
            }
        }
    }

    private void removeEffects() {

        for (AbstractCard card : affectedCards) {
            card.setDefence(card.getDefence() + 2);
            affectedCards.remove(card);
        }

    }

    private void stopListening() {
        turnMover.removeTurnListener(this);
    }

    public void turnChecking(int turnNum) {
        removeEffects();
    }

    public void notifyEndTurn() {
        removeEffects();
    }
}
