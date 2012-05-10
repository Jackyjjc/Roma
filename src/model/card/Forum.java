package model.card;

import java.util.ArrayList;
import java.util.List;

import model.Die;
import model.ICardResources;
import model.IDisc;
import model.IDiscListener;
import model.IField;
import model.IGameIO;
import model.IListener;
import model.IPlayer;
import model.IResourceStorage;
import model.card.state.DecisionState;
import model.card.state.ForumNotifyState;
import model.card.state.UseDieGetVpState;
import framework.cards.Card;

class Forum extends AbstractCard implements IDieChecker, IDiscListener {

    private static final int COST = 5;
    private static final int DEFENCE = 5;

    private List<IListener> basilicas;
    private List<IListener> templums;
    
    Forum(ICardResources cardResources, IGameIO gameIO) {
        super(Card.FORUM, CardType.BUILDING,
              COST, DEFENCE, cardResources, gameIO);
        
        this.basilicas = new ArrayList<IListener>();
        this.templums = new ArrayList<IListener>();
    }
    
    @Override
    public boolean lay(IDisc disc) {
        
        boolean succeed = super.lay(disc);
        
        IField field = getOwner().getField();
        
        for(IDisc d : field) {
            d.addDiscListener(this);
        }

        return succeed;
    }
    
    @Override
    public void disCard() {
        
        IField field = getOwner().getField();
        
        for(IDisc d : field) {
            d.removeDiscListener(this);
        }
        
        super.disCard();
    }

    public void activate() {
        
        IResourceStorage bank = getCardResources().getBank();
        IPlayer player = getOwner();
        
        UseDieGetVpState getVP = new UseDieGetVpState(this, this, bank, player);
        
        ForumNotifyState trueState = new ForumNotifyState(this, basilicas);
        ForumNotifyState notifyTemplums = new ForumNotifyState(this, templums);
        trueState.setNextState(notifyTemplums);
        notifyTemplums.setNextState(null);
        
        ForumNotifyState falseState = new ForumNotifyState(this, basilicas);
        falseState.setNextState(null);
        
        DecisionState decision = new DecisionState(this, trueState, falseState);
        getVP.setNextState(decision);
        
        setState(getVP);
    }
    

    public boolean isValidDie(Die die) {
        
        boolean isValid = false;
        
        if(die != null && !die.isUsed()) {
            isValid = true;
        }
        
        return isValid;
    }

    public void update(IDisc disc) {
        addCards();
    }
    
    private void addCards() {
        
        IDisc prev = getDisc().getPrev();
        IDisc next = getDisc().getNext();
        
        checkCards(prev);
        checkCards(next);
    }
    
    private void checkCards(IDisc disc) {
        
        if(disc != null) {
            if(disc.getCard() != null) {
                if(disc.getCard() instanceof Basilica 
                     && !basilicas.contains(disc.getCard())) {
                    basilicas.add((IListener)disc.getCard());
                }
                if(disc.getCard() instanceof Templum
                     && !templums.contains(disc.getCard())) {
                    templums.add((IListener)disc.getCard());
                }
            }
        }  
    }
}
