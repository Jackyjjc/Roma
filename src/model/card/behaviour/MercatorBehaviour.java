package model.card.behaviour;

import model.ICardResources;
import model.IPlayer;
import model.InputHandler;
import model.card.AbstractCard;

public class MercatorBehaviour extends Behaviour {

    private static final int TRADING_PRICE = 2;
    private static final int TRADING_REWARD = 1;

    public MercatorBehaviour(AbstractCard host, ICardResources cardResources) {
        super(host, cardResources);
    }


    public void complete() {

        IPlayer owner = getHost().getOwner();
        IPlayer opponent = owner.getOpponent();

        InputHandler handler = getCardResources().getInputHandler();

        while (handler.getBooleanInput()) {

            if (isValid()) {

                owner.transferMoney(opponent, TRADING_PRICE);
                opponent.transferVP(owner, TRADING_REWARD);

            }

        }
    }

    public boolean isValid() {

        boolean isValid = false;

        if (getHost().getOwner().getMoney() >= TRADING_PRICE
                && getHost().getOwner().getOpponent().getVP() >= TRADING_REWARD) {
            isValid = true;
        }

        return isValid;

    }
}
