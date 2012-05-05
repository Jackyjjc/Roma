package model.card;

import model.ICardResources;
import model.IGameIO;
import model.IPlayer;
import model.card.state.ChooseAmountState;
import framework.cards.Card;

class Mercator extends AbstractCard implements IAmountChecker {
    
    private static final int TRADING_REWARD = 1;
    private static final int COST = 7;
    private static final int DEFENCE = 2;
    private static final int TRADING_PRICE = 2;
    
    Mercator(ICardResources cardResources, IGameIO gameIO) {
        super(Card.MERCATOR, CardType.CHARACTER,
              COST, DEFENCE, cardResources, gameIO);
        
    }

    public void activate() {
        
        IPlayer player = getOwner();
        IPlayer opponent = player.getOpponent();
        
        ChooseAmountState choose = new ChooseAmountState(this, this, opponent, player);
        choose.setNextState(null);
        
        setState(choose);
    }
    
    public boolean isValidAmount(int amount) {
        
        boolean isValid = false;
        
        if (this.getOwner().getMoney() >= TRADING_PRICE * amount 
                && this.getOwner().getOpponent().getVP() >= TRADING_REWARD * amount) {
            isValid = true;
        }
        
        return isValid;
        
    }
    
}
