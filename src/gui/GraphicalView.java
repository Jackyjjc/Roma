package gui;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import model.IGameDisplayState;
import model.InputHandler;
import controller.ActionDieClickListener;
import controller.DiscClickListener;
import controller.FieldClickListener;
import controller.HandClickListener;
import controller.SwapConfirmListener;

public class GraphicalView extends JFrame implements IListener, IDisplayManager {
    
    private static final String NAME = "Roma 2.2";
    
    private static final int WIDTH = 1366;
    private static final int HEIGHT = 768;
    
    private double scalingFactor;
    
    private CardDisplayManager cdm;
    private DieDisplayManager actionDiceManager;
    private DieDisplayManager battleDieManager;
    private DiscDisplayManager ddm;
    private ResourceManager rm;
    private JBackground background;
    private FieldClickListener fListener;
    private HandClickListener hListener;
    private DiscClickListener dListener;
    private ActionDieClickListener adListener;
    private InputHandler handler;
    private SwapConfirmListener confimrListener;

    public GraphicalView(InputHandler handler) {
        
        super(NAME);
       
        initElements(handler);
        initUI();
        
        //setExtendedState(JFrame.MAXIMIZED_BOTH);
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
    
    private void initElements(InputHandler handler) {
        
        this.scalingFactor = calculateScalingFactor();
        this.rm = new ResourceManager(scalingFactor);
        this.cdm = new CardDisplayManager(rm);
        this.actionDiceManager = new DieDisplayManager(rm, DieDisplayManager.Type.ACTION);
        this.battleDieManager = new DieDisplayManager(rm, DieDisplayManager.Type.BATTLE);
        this.ddm = new DiscDisplayManager(rm);
        this.fListener = new FieldClickListener(handler);
        this.hListener = new HandClickListener(handler);
        this.dListener = new DiscClickListener(handler);
        this.adListener = new ActionDieClickListener(handler);
        this.confimrListener = new SwapConfirmListener(handler);
        this.handler = handler;
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

    public ActionDieClickListener getActionDieClickListener() {
        return adListener;
    }

    public DiscClickListener getDiscClickListener() {
        return dListener;
    }

    public FieldClickListener getFieldClickListener() {
        return fListener;
    }

    public HandClickListener getHandClickListener() {
        return hListener;
    }

    public InputHandler getInputHandler() {
        return handler;
    }
    
    public SwapConfirmListener getConfirmListener() {
        return confimrListener;
    }
    
}
