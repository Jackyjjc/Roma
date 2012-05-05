package model.card;

import model.Die;
import model.ICardResources;
import model.IGameIO;
import model.IListener;
import model.IPlayer;
import model.IResourceStorage;
import model.card.state.UseDieGetVpState;
import framework.cards.Card;

class Templum extends AbstractCard implements IListener, IDieChecker {

    private static final int COST = 2;
    private static final int DEFENCE = 2;
    
    Templum(ICardResources cardResources, IGameIO gameIO) {
        
        super(Card.TEMPLUM, CardType.BUILDING,
              COST, DEFENCE, cardResources, gameIO);
        
    }

    public void activate() {
        //itself can't be activated
    }

    public void update() {
        
        IResourceStorage bank = getCardResources().getBank();
        IPlayer player = getOwner();
        
        new UseDieGetVpState(this, this, bank, player).run();
    }

    public boolean isValidDie(Die die) {

        boolean isValid = false;
        
        if(die != null && !die.isUsed()) {
            isValid = true;
        }
        
        return isValid;
    }
}
