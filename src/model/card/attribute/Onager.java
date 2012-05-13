package model.card.attribute;

import framework.cards.Card;
import model.ICardResources;
import model.IGameIO;
import model.InputHandler;
import model.card.AbstractCard;
import model.card.Action;
import model.card.CardType;

class Onager extends AbstractCard {

    private static final int COST = 5;
    private static final int DEFENCE = 4;

    Onager(ICardResources cardResources, IGameIO gameIO) {
        super(Card.ONAGER, CardType.BUILDING,
              COST, DEFENCE, cardResources, gameIO);
        
    }
    
    public void complete() {

        InputHandler handler = getGameIO().getInputHandler();

        AbstractCard target = handler.getCardInput();

        if(isValidTarget(target)) {
            int value = handler.getBattleDieInput();
            Action.attack(target, value);
        }
    }

    public boolean isValidTarget(AbstractCard c) {
        boolean isValid = false;

        if(c.getOwner() != null && c.getOwner() != this.getOwner()
                && c.getType() == CardType.BUILDING) {
            isValid = true;
        }

        return isValid;
    }
    
}
