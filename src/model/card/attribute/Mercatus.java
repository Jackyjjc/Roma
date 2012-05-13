package model.card.attribute;

import framework.cards.Card;
import model.ICardResources;
import model.IField;
import model.IGameIO;
import model.IPlayer;
import model.card.AbstractCard;
import model.card.Action;
import model.card.CardType;

class Mercatus extends AbstractCard {

    private static final int COST = 6;
    private static final int DEFENCE = 3;
    
    Mercatus(ICardResources cardResources, IGameIO gameIO) {
        super(Card.MERCATUS, CardType.BUILDING,
              COST, DEFENCE, cardResources, gameIO);
    }

    public void complete() {
        
        IPlayer opponent = this.getOwner().getOpponent();
        IField discs = opponent.getField();
        AbstractCard c = null;
        
        int numOfForum = 0;
        
        for(int i = 0; i < discs.getNumDiscs(); i++) {
            c = discs.getCard(i);
            if(c != null && c instanceof Forum) {
                numOfForum++;
            }
        }

        Action.attainVP(opponent, this.getOwner(), numOfForum);

    }
    
}
