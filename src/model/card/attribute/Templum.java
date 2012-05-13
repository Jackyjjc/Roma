package model.card.attribute;

import framework.cards.Card;
import model.*;
import model.card.AbstractCard;
import model.card.CardType;
import model.card.IForumListener;

class Templum extends AbstractCard implements IForumListener {

    private static final int COST = 2;
    private static final int DEFENCE = 2;
    
    Templum(ICardResources cardResources, IGameIO gameIO) {
        
        super(Card.TEMPLUM, CardType.BUILDING,
              COST, DEFENCE, cardResources, gameIO);
        
    }

    public void complete() {
        //itself can't be activated
    }

    public boolean isValidDie(Die die) {

        boolean isValid = false;
        
        if(die != null && !die.isUsed()) {
            isValid = true;
        }
        
        return isValid;
    }

    @Override
    public void alert() {

        InputHandler handler = getGameIO().getInputHandler();

        IResourceStorage bank = getCardResources().getBank();
        IPlayer player = getOwner();

        bank.transferVP(player, handler.getDieInput().getValue());

    }
}
