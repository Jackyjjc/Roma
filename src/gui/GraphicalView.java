package gui;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import model.IGameDisplayState;
import model.IGameIO;
import model.Notifier;
import controller.GuiInputHandler;
import framework.cards.Card;

public class GraphicalView extends JFrame implements IListener, IDisplayManager {
    
    private static final String NAME = "Roma 2.2";
    
    private static final int WIDTH = 1500;
    private static final int HEIGHT = 768;
    
    private double scalingFactor;
    
    private CardDisplayManager cdm;
    private DieDisplayManager actionDiceManager;
    private DieDisplayManager battleDieManager;
    private DiscDisplayManager ddm;
    private ResourceManager rm;
    private JBackground background;
    private Notifier notifier;
    private GuiInputHandler handler;

    public GraphicalView(IGameIO gameIO, GuiInputHandler handler) {
        
        super(NAME);
        
        this.handler = handler;
        
        initElements(gameIO);
        initUI();
        
    }
    
    private void initUI() {
        
        background = new JBackground(this);
        
        setContentPane(background);
        setIconImage(rm.icon);
        
        int width = (int) (WIDTH * scalingFactor);
        int height = (int) (HEIGHT * scalingFactor);
        
        setPreferredSize(new Dimension(width,height));
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        
    }
    
    public void printTurn(int numTurn, String name) {
        JOptionPane.showMessageDialog(this, 
                                      "Turn #" + numTurn + " : It's" + name +"'s turn.",
                                      "new turn",
                                      JOptionPane.PLAIN_MESSAGE);
    }
    
    public void updateView(IGameDisplayState state) {
        background.updateView(state);
        repaint();
    }
    
    private double calculateScalingFactor() {
        
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        double scalingFactor = (double) dim.width / WIDTH;
        
        if(scalingFactor > 1) {
            scalingFactor = 1;
        }
        
        return scalingFactor;    
    }
    
    private void initElements(IGameIO gameIO) {
        
        this.notifier = gameIO.getNotifier();
        this.scalingFactor = calculateScalingFactor();
        this.rm = new ResourceManager(scalingFactor);
        this.cdm = new CardDisplayManager(rm);
        this.actionDiceManager = new DieDisplayManager(rm, DieDisplayManager.Type.ACTION);
        this.battleDieManager = new DieDisplayManager(rm, DieDisplayManager.Type.BATTLE);
        this.ddm = new DiscDisplayManager(rm);
    }

    public int scale(int original) {

        return new Double(original * scalingFactor).intValue();
    }
    
    public ResourceManager getResourceManager() {
        return rm;
    }

    public CardDisplayManager getCardDisplayManager() {
        return cdm;
    }

    public DiscDisplayManager getDiscDisplayManager() {
        return ddm;
    }

    public DieDisplayManager getActionDiceDisplayManager() {
        return actionDiceManager;
    }

    public DieDisplayManager getBattleDieDisplayManager() {
        return battleDieManager;
    }

    public GuiInputHandler getInputHandler() {
        return handler;
    }

    public Notifier getNotifier() {
        return notifier;
    }

    public void setSwapConfirmListener(ActionListener l) {
        background.setSwapConfirmListener(l);
    }
    
    public void showSwapDialog(int player) {
        JOptionPane.showMessageDialog(this,
                "Player " + player + " please choose two cards to swap and place them at the bottom right corner");
    }
    
    public void showLayCardDialog() {
        JOptionPane.showMessageDialog(this,
                "Please Lay Down All the cards (for free :P)");
    }
    
    public void showGameStarts() {
        JOptionPane.showMessageDialog(this,
                "Game Starts! Have Fun!!");
    }
    
    public Card showCards(Card[] cards) {
        
        Card s = (Card) JOptionPane.showInputDialog(this,
                                    "Choose one of these cards",
                                    "Choose Card",
                                    JOptionPane.PLAIN_MESSAGE,
                                    null,
                                    cards,
                                    cards[0]);

        return s;
    }
    
    public boolean showPassDialog() {
        boolean isPass = false;
        
        
        int n = JOptionPane.showConfirmDialog(
                this,
                "You sure you want to pass the turn?",
                "Pass",
                JOptionPane.YES_NO_OPTION);
        
        if(n == JOptionPane.YES_OPTION) {
            isPass = true;
        }
        
        return isPass;
    }
    
    public boolean reRollDialog() {
        boolean isPass = false;
        
        
        int n = JOptionPane.showConfirmDialog(
                this,
                "The three dice are the same, do you want to roll again?",
                "Roll Dice",
                JOptionPane.YES_NO_OPTION);
        
        if(n == JOptionPane.YES_OPTION) {
            isPass = true;
        }
        
        return isPass;
    }
}
