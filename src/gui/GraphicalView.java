package gui;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import model.Game;
import model.IGameDisplayState;
import model.IGameIO;
import model.Notifier;
import controller.Controller;
import controller.GuiInputHandler;
import framework.Rules;
import framework.cards.Card;

public class GraphicalView extends JFrame implements IListener, IDisplayManager {
    
    private static final String NAME = "Roma 3.2";
    
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
    
    public void enableActionDiceAdapter(boolean enable) {
        background.enableActionDiceAdapter(enable);
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
    
    public void showTargetInputDialog() {
        JOptionPane.showMessageDialog(this,
                "Please choose a target");
    }
    
    public boolean showTargetConfirmDialog(Card card) {
        
        boolean confirm = false;
        
        int n = JOptionPane.showConfirmDialog(
                this,
                "You have selected target " + card + ". Are you sure?",
                "Select Target",
                JOptionPane.YES_NO_OPTION);
        
        if(n == JOptionPane.YES_OPTION) {
            confirm = true;
        }
        
        return confirm;
    }
    
    public int showMercatorBuyingDialog(int maxAmount) {
        
        int result = -1;
        
        if(maxAmount > 0) {
            
            Integer[] availableAmount = new Integer[maxAmount];
            for(int i = 0; i < availableAmount.length; i++) {
                availableAmount[i] = i + 1;
            }
        
        Integer i = (Integer)JOptionPane.showInputDialog(this,
                "Choose the number of Victory Points you want to buy",
                "Choose Victory Point amount",
                JOptionPane.PLAIN_MESSAGE,
                null,
                availableAmount,
                availableAmount[0]);
        
            if (i == null) {
                result = 0;
            } else {
                result = i;
            }
        }
        
        return result;
    }
    
    public void showDieInputDialog() {
        JOptionPane.showMessageDialog(this,
                "Please choose an action die");
    }
    
    public int showDieAmountChangeInput(Integer[] availableAmount) {
        
        int result = (Integer)JOptionPane.showInputDialog(this,
                "Choose the number of Victory Points you want to buy",
                "Choose Victory Point amount",
                JOptionPane.PLAIN_MESSAGE,
                null,
                availableAmount,
                availableAmount[0]);
        
        return result;
    }
    
    public boolean centurioAddDieDialog() {
        
        boolean attackAgain = false;
        
        int n = JOptionPane.showConfirmDialog(
                this,
                "Do you want to attack again?",
                "Centurio",
                JOptionPane.YES_NO_OPTION);
        
        if(n == JOptionPane.YES_OPTION) {
            attackAgain = true;
        }
        
        return attackAgain;
    }
    
    public void enableStopButton(boolean enable) {
        background.enableStopButton(enable);
    }
    
    public void layCardForFreeDialog() {
        JOptionPane.showMessageDialog(this,
                "Now you can lay card for free. \n" +
                "Press the bottom right corner button to stop the effect");
    }
}
