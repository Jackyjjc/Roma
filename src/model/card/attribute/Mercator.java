package model.card.attribute;

import framework.cards.Card;
import model.ICardResources;
import model.IGameIO;
import model.IPlayer;
import model.InputHandler;
import model.card.AbstractCard;
import model.card.CardType;

class Mercator extends AbstractCard {
    
    private static final int TRADING_PRICE = 2;
    private static final int TRADING_REWARD = 1;
    private static final int COST = 7;
    private static final int DEFENCE = 2;
    
    Mercator(ICardResources cardResources, IGameIO gameIO) {
        super(Card.MERCATOR, CardType.CHARACTER,
              COST, DEFENCE, cardResources, gameIO);
        
    }

    public void complete() {

        IPlayer owner = this.getOwner();
        IPlayer opponent = owner.getOpponent();

        InputHandler handler = getGameIO().getInputHandler();

        while (handler.getBooleanInput()) {

            if (isValid()) {

                owner.transferMoney(opponent, TRADING_PRICE);
                opponent.transferVP(owner, TRADING_REWARD);

            }

        }
    }

    public boolean isValid() {

        boolean isValid = false;

        if (this.getOwner().getMoney() >= TRADING_PRICE
                && this.getOwner().getOpponent().getVP() >= TRADING_REWARD) {
            isValid = true;
        }

        return isValid;

    }
    
}
