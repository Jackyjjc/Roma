package model.card.state;

import java.util.List;

import model.IListener;
import model.card.AbstractCard;

public class ForumNotifyState extends CardState implements ICardState {

    private List<IListener> listeners;
    
    public ForumNotifyState(AbstractCard owner, List<IListener> listeners) {
        super(owner);
        this.listeners = listeners;
    }

    public boolean run() {

        notifyAllListeners(listeners);
        
        changeState();
        
        return true;
    }

    private void notifyAllListeners(List<IListener> listeners) {
        
        for(IListener l : listeners) {
            l.update();
        }
        
    }
    
}
