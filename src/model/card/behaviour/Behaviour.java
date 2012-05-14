package model.card.behaviour;

import model.IDisc;
import model.IResourceStorage;
import model.card.AbstractCard;

public abstract class Behaviour {

    private AbstractCard host;
    
    public Behaviour(AbstractCard host) {
         this.host = host;
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

    public void disCard() {

    	AbstractCard host = getHost();
    	
    	if(host.getDisc() != null) {
    		
    		host.getDisc().removeCard();
    		
    	}

    	host.setCost(host.getDefaultCost());
    	host.setDefence(host.getDefaultDefence());

    	host.getCardResources().getDiscardStorage().pushCard(host);

    }

    public void setHost(AbstractCard newHost) {
        this.host = newHost;
    }

    public AbstractCard getHost() {
        return host;
    }
    
}
