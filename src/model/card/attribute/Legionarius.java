package model.card.attribute;

import framework.cards.Card;
import model.ICardResources;
import model.IGameIO;
import model.IPlayer;
import model.card.AbstractCard;
import model.card.Action;
import model.card.CardType;

class Legionarius extends AbstractCard {

    private static final int COST = 4;
    private static final int DEFENCE = 5;
    
    Legionarius (ICardResources cardResources, IGameIO gameIO) {
        
        super(Card.LEGIONARIUS, CardType.CHARACTER,
              COST, DEFENCE, cardResources, gameIO);
        
    }
    
    public void complete() {


        AbstractCard target = getOppositeCard();

        if(isValidCard(target)) {

            int value = getGameIO().getInputHandler().getBattleDieInput();
            Action.attack(target, value);

        }

    }

    public boolean isValidCard(AbstractCard c) {
        boolean isValid = false;
        
        if(c.getOwner() != null && c.getOwner() != this.getOwner()) {
            isValid = true;
        }
        
        return isValid;
    }


    private AbstractCard getOppositeCard() {

        IPlayer opponent = this.getOwner().getOpponent();

        int index = this.getDisc().getIndex();

        AbstractCard target = opponent.getField().getCard(index);

        return target;

    }
}
