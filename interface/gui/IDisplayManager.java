package gui;

import model.InputHandler;
import model.Notifier;
import controller.ActionDieClickListener;
import controller.DiscClickListener;
import controller.FieldClickListener;
import controller.HandClickListener;
import controller.SwapConfirmListener;

public interface IDisplayManager {

    public ResourceManager getResourceManager();
    
    public CardDisplayManager getCardDisplayManager();
    
    public DiscDisplayManager getDiscDisplayManager();
    
    public DieDisplayManager getActionDiceDisplayManager();
    
    public DieDisplayManager getBattleDieDisplayManager();
    
    public int scale(int original);
    
    public ActionDieClickListener getActionDieClickListener();
    
    public DiscClickListener getDiscClickListener();
    
    public FieldClickListener getFieldClickListener();
    
    public HandClickListener getHandClickListener();
    
    public InputHandler getInputHandler();
    
    public SwapConfirmListener getConfirmListener();
    
    public Notifier getNotifier();
}
