package model.card.attribute;

import framework.cards.Card;
import model.*;
import model.card.AbstractCard;
import model.card.CardType;
import model.card.IForumListener;

class Forum extends AbstractCard {

    private static final int COST = 5;
    private static final int DEFENCE = 5;
    
    Forum(ICardResources cardResources, IGameIO gameIO) {
        super(Card.FORUM, CardType.BUILDING,
              COST, DEFENCE, cardResources, gameIO);

    }
    


    public void complete() {

        IDisc disc = getDisc();
        IPlayer player = getOwner();

        InputHandler handler = getGameIO().getInputHandler();
        IResourceStorage bank = getCardResources().getBank();

        Die toUse = handler.getDieInput();

        if (isValidDie(toUse)) {
            bank.transferVP(player,toUse.getValue());
        }

        if(disc.getPrev() != null && disc.getPrev().getCard() instanceof IForumListener) {
            ((IForumListener)disc.getPrev().getCard()).alert();
        }

        if (disc.getNext() != null & disc.getNext().getCard() instanceof IForumListener) {
            ((IForumListener)disc.getPrev().getCard()).alert();
        }

    }
    

    public boolean isValidDie(Die die) {
        
        boolean isValid = false;
        
        if(die != null && !die.isUsed()) {
            isValid = true;
        }
        
        return isValid;
    }

}
