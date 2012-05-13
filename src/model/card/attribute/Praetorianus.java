package model.card.attribute;

import model.ICardResources;
import model.IDisc;
import model.IGameIO;
import model.InputHandler;
import model.card.AbstractCard;
import model.card.CardType;
import framework.cards.Card;

class Praetorianus extends AbstractCard {

    private static final int COST = 4;
    private static final int DEFENCE = 4;
    
    Praetorianus(ICardResources cardResources, IGameIO gameIO) {
        super(Card.PRAETORIANUS, CardType.CHARACTER,
              COST, DEFENCE, cardResources, gameIO);
    }

    public void complete() {

        InputHandler handler = getGameIO().getInputHandler();
        IDisc target = handler.getDiscInput();

        target.block();

    }

}
