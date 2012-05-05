package model.card;

import model.ICardResources;
import model.IGameIO;
import model.IPlayer;
import framework.cards.Card;
import framework.interfaces.activators.CenturioActivator;

class Centurio extends AbstractCard implements CenturioActivator {

    private static final int COST = 9;
    private static final int DEFENCE = 5;
    
    private int dieRoll;
    
    Centurio (ICardResources cardResources, IGameIO gameIO) {
        
        super(Card.CENTURIO, CardType.CHARACTER,
                COST, DEFENCE, cardResources, gameIO);
        
    }

    public void activate() {
        
    }

    public boolean isValidTarget(AbstractCard c) {
        boolean isValid = false;
        
        if(c.getOwner() != null && c.getOwner() != this.getOwner()) {
            isValid = true;
        }
        
        return isValid;
    }
    
    private AbstractCard getOppositeCard() {
        
        IPlayer opponent = this.getOwner().getOpponent();
        
        int index = this.getDisc().getIndex();
        
        AbstractCard target = opponent.getField().getCard(index);
        
        return target;
    }

    public void giveAttackDieRoll(int roll) {
        this.dieRoll = roll;
    }

    public void chooseActionDice(int actionDiceValue) {
        
        getCardResources().getDiceManager().getActionDie(actionDiceValue).use();
        
        this.dieRoll += actionDiceValue;
        Action.attack(getOppositeCard(), dieRoll);
    }

    public void chooseCenturioAddActionDie(boolean attackAgain) {
        if(!attackAgain) {
            Action.attack(getOppositeCard(), dieRoll);
        }       
    }
    
    public void complete() {
        //nothing to do
    }
}
