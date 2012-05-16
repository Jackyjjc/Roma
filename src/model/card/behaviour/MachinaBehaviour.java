package model.card.behaviour;

import model.ICardStorage;
import model.IDisc;
import model.IField;
import model.InputHandler;
import model.card.AbstractCard;
import model.card.CardType;
import model.card.ICardChecker;

public class MachinaBehaviour extends Behaviour implements ICardChecker {

    private ICardStorage buildingCards;
    
    public MachinaBehaviour(AbstractCard host) {
        super(host);
    }

    public void initialise() {

        IField field = getHost().getOwner().getField();

        buildingCards = field.getCardsOf(CardType.BUILDING);

        for (AbstractCard c : buildingCards) {
            c.setCost(0);
        }

        getHost().getGameIO().getInputHandler().setList(buildingCards);
    }
    
    public void complete() {
     
        ICardStorage hand = getHost().getOwner().getHand();
     
        layCards();
        
        for (AbstractCard c : buildingCards) {
            c.setCost(c.getDefaultCost());
        }
        
        getHost().getGameIO().getInputHandler().setList(hand);
    }
    
    private void layCards() {
        
        InputHandler handler = getHost().getGameIO().getInputHandler();
        
        AbstractCard card = handler.getCardInput();
        while(card != null) {
            
            IDisc disc = handler.getDiscInput();        
            card.lay(disc);
            
            card = handler.getCardInput();
        }
    }

    
    public boolean isValidCard(AbstractCard c) {
        
        boolean isValid = false;
        
        if(c != null && c.getType() == CardType.BUILDING) {
            isValid = true;
        }
        
        return isValid;
    }

}
