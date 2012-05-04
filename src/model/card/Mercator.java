package model.card;

import model.ICardResources;
import model.IPlayer;
import model.Notifier;
import framework.cards.Card;
import framework.interfaces.activators.MercatorActivator;

class Mercator extends AbstractCard implements MercatorActivator {
    
    private static final int TRADING_REWARD = 1;
    private static final int COST = 7;
    private static final int DEFENCE = 2;
    private static final int TRADING_PRICE = 2;
    
    Mercator(ICardResources cardResources, Notifier notifier) {
        super(Card.MERCATOR, CardType.CHARACTER,
              COST, DEFENCE, cardResources, notifier);
        
    }

    public void activate() {
        
    }
    
    public boolean isValid() {
        
        boolean isValid = false;
        
        if (this.getOwner().getMoney() >= TRADING_PRICE 
                && this.getOwner().getOpponent().getVP() >= TRADING_REWARD) {
            isValid = true;
        }
        
        return isValid;
        
    }

    @Override
    public void complete() {
        // TODO Auto-generated method stub
        
    }

    public void chooseMercatorBuyNum(int VPToBuy) {
        IPlayer owner = this.getOwner();
        IPlayer opponent = owner.getOpponent();
        
        owner.transferMoney(opponent, TRADING_PRICE * VPToBuy);
        opponent.transferVP(owner, TRADING_REWARD * VPToBuy);
    }
    
    
}
