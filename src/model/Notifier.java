package model;

import java.util.ArrayList;
import java.util.List;
import gui.IListener;
import gui.ISwapListener;

public class Notifier {

    private ISwapListener swapListener;
    private List<IListener> listeners;
    private Game g;
    
    public Notifier(Game g) {
        this.g = g;
        this.listeners = new ArrayList<IListener>();
    }
    
    public void addListener(IListener listener) {
        listeners.add(listener);
    }

    public void addSwapListener(ISwapListener listener) {
        this.swapListener = listener;
    }
    
    public void notifyListeners() {
        for(IListener l : listeners) {
            l.updateView(g);
        }
    }
    
    public void notifySwapFinished() {
        this.swapListener.swapFinish();
    }
}
