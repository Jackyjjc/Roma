package model.card.behaviour;

import model.ICardResources;
import model.ICardStorage;
import model.IField;
import model.InputHandler;
import model.card.AbstractCard;
import model.card.CardType;
import model.card.ICardChecker;

public class RearrangerBehaviour extends Behaviour implements ICardChecker {

    private ICardStorage affectedCards;
    private CardType pickupType;
    
    public RearrangerBehaviour(AbstractCard host, 
                               ICardResources cardResources, CardType type) {
        
        super(host, cardResources);
        this.pickupType = type;
    }

    @Override
    public void initialise() {

        IField discs = getHost().getOwner().getField();
        this.affectedCards = discs.getCardsOf(pickupType);

        for (AbstractCard c : affectedCards) {
            c.setCost(0);
        }

        getCardResources().getInputHandler().setList(affectedCards);

    }

    public void complete() {

        ICardStorage hand = getHost().getOwner().getHand();
        InputHandler handler = getCardResources().getInputHandler();
        
        for (AbstractCard c : affectedCards) {
            c.setCost(c.getDefaultCost());
        }

        handler.setList(hand);
    }
    
    public boolean isValidCard(AbstractCard c) {

        boolean isValid = false;

        if (c != null && c.getType() == pickupType) {
            isValid = true;
        }

        return isValid;

    }
}
