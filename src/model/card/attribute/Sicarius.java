package model.card.attribute;

import model.ICardResources;
import model.IDisc;
import model.IGameIO;
import model.InputHandler;
import model.card.AbstractCard;
import model.card.CardType;
import framework.cards.Card;

class Sicarius extends AbstractCard {

    private static final int COST = 9;
    private static final int DEFENCE = 2;
    
    Sicarius(ICardResources cardResources, IGameIO gameIO) {
        super(Card.SICARIUS, CardType.CHARACTER,
              COST, DEFENCE, cardResources, gameIO);
        
    }

    public void complete() {

        InputHandler handler = getGameIO().getInputHandler();
        IDisc disc = handler.getDiscInput();
        AbstractCard target = disc.getCard();

        if (isValidTarget(target)) {
            target.disCard();
            this.disCard();
        }
    }

    public boolean isValidTarget(AbstractCard c) {

        boolean isValid = false;

        if(c.getOwner() != null && c.getOwner() != this.getOwner()
                && c.getType() == CardType.CHARACTER) {
            isValid = true;
        }

        return isValid;

    }

}
