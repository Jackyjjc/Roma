package model;

import java.util.ArrayList;
import java.util.List;
import gui.IListener;

public class Notifier {

    private List<IListener> listeners;
    private Game g;
    
    public Notifier(Game g) {
        this.g = g;
        this.listeners = new ArrayList<IListener>();
    }
    
    public void addListener(IListener listener) {
        listeners.add(listener);
    }

    public void notifyListeners() {
        for(IListener l : listeners) {
            l.updateView(g);
        }
    }
}
