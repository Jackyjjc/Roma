package model.card.behaviour;

import model.IDisc;
import model.IPlayer;
import model.IResourceStorage;
import model.card.AbstractCard;

public abstract class Behaviour {

    private static final int DEFAULT_LIVES = 1;
    
    private AbstractCard host;
    private int livesLeft;
    
    public Behaviour (AbstractCard host) {
        this.host = host;
        this.livesLeft = DEFAULT_LIVES;
    }
    
    public void initialise() {
        //hook
    }

    public abstract void complete();

    public boolean lay(IDisc disc) {

        boolean succeed = false;

        AbstractCard host = getHost();
        IResourceStorage bank = getHost().getCardResources().getBank();

        if (host.getOwner() == null || host.getOwner().getMoney() >= host.getCost()) {
            disc.layCard(host);
            if (host.getOwner() != null) {
                host.getOwner().transferMoney(bank, host.getCost());
            }
        }

        return succeed;
    }

    public void disCard() {

        AbstractCard host = getHost();
        IPlayer currentPlayer = host.getCardResources().getCurrentPlayer();
        
        if (host.getOwner() != currentPlayer
                && livesLeft > 1) {
            livesLeft--;
           
        } else {
            
            if (host.getDisc() != null) {
                host.getDisc().removeCard();
            }

            host.setCost(host.getDefaultCost());
            host.setDefence(host.getDefaultDefence());

            host.getCardResources().getDiscardStorage().pushCard(host);
            
            reset();
        }
    }

    public void setHost(AbstractCard newHost) {
        this.host = newHost;
    }

    public AbstractCard getHost() {
        return host;
    }

    public void setLives(int newLives) {
        this.livesLeft = newLives;
    }
    
    public int getLives() {
        return livesLeft;
    }
    
    void reset() {
        livesLeft = DEFAULT_LIVES;
    }
}
