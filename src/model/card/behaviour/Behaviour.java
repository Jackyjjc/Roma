package model.card.behaviour;

import java.util.List;

import model.ICardStorage;
import model.IDisc;
import model.IResourceStorage;
import model.card.AbstractCard;
import model.card.IDiscardListener;

public abstract class Behaviour {

    private AbstractCard host;
    private ICardStorage discardDestination;
    private List<IDiscardListener> discardListeners;
    
    public Behaviour(AbstractCard host){
         this.host = host;
         this.discardDestination = getHost().getCardResources().getDiscardStorage(); 
    }

    public void initialise() {
         //hook
    }

    public abstract void complete();

    public boolean lay(IDisc disc) {

        boolean succeed = false;

        AbstractCard host = getHost();
        IResourceStorage bank = getHost().getCardResources().getBank();

        if(host.getOwner().getMoney() >= host.getCost()) {
            disc.layCard(host);
            host.getOwner().transferMoney(bank, host.getCost());
        }

        return succeed;
    }

    public void disCard(boolean beenKilled) {

        AbstractCard host = getHost();
        if(host.getDisc() != null) {
            host.getDisc().removeCard();
        }

        host.setCost(host.getDefaultCost());
        host.setDefence(host.getDefaultDefence());
        host.getBehaviour().setDiscardDestination(host.getCardResources().getDiscardStorage());
        
        if(beenKilled) {
            discardDestination.pushCard(host);
            notifyDicardListeners();
        } else {
            //been covered
            host.getCardResources().getDiscardStorage().pushCard(host);
        }       

    }

    private void notifyDicardListeners() {
        for(IDiscardListener l : discardListeners) {
            l.update(getHost());
        }
    }
    
    public void setDiscardDestination (ICardStorage discardDestination) {
        this.discardDestination = discardDestination;
    }

    public ICardStorage getDiscardDestination (ICardStorage discardDestination) {
        return discardDestination;
    }

    public void setHost(AbstractCard newHost) {
        this.host = newHost;
    }

    public AbstractCard getHost() {
        return host;
    }
    
    public void addDiscardListener(IDiscardListener listener) {
        discardListeners.add(listener);
    }
    
    public void removeDiscardListener(IDiscardListener listener) {
        discardListeners.remove(listener);
    }
}
