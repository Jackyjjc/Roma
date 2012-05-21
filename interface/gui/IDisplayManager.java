package gui;

import controller.GuiInputHandler;
import model.Notifier;

public interface IDisplayManager {

    public ResourceManager getResourceManager();

    public CardDisplayManager getCardDisplayManager();

    public DiscDisplayManager getDiscDisplayManager();

    public DieDisplayManager getActionDiceDisplayManager();

    public DieDisplayManager getBattleDieDisplayManager();

    public int scale(int original);

    public GuiInputHandler getInputHandler();

    public Notifier getNotifier();

}
