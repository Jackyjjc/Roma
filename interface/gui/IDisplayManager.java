package gui;

import controller.ActionDieClickListener;
import controller.DiscClickListener;
import controller.FieldClickListener;
import controller.FieldTransferHandler;
import controller.HandClickListener;

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
    
    public FieldTransferHandler getFieldTransferHandler();
}
