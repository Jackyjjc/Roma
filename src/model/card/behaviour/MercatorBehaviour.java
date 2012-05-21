package model.card.behaviour;

import model.IPlayer;
import model.InputHandler;
import model.card.AbstractCard;

public class MercatorBehaviour extends Behaviour {

    private static final int TRADING_PRICE = 2;
    private static final int TRADING_REWARD = 1;

    public MercatorBehaviour(AbstractCard host) {
        super(host);
        // TODO Auto-generated constructor stub
    }


    public void complete() {

        IPlayer owner = getHost().getOwner();
        IPlayer opponent = owner.getOpponent();

        InputHandler handler = getHost().getCardResources().getInputHandler();

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
