package gui;

import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JPanel;

import controller.ActionDieClickListener;

import model.IGameDisplayState;
import model.InputHandler;

public class JGameBarLeft extends JPanel implements IListener {

    private DieDisplayManager ddm;
    private ResourceManager rm;

    private JStatusBar statusBar;
    private JDeck deck;
    private JGrave grave;
    private ActionDicePanel dicePanel;
    
    public JGameBarLeft(ResourceManager rm, DieDisplayManager ddm, InputHandler handler) {

        this.rm = rm;
        this.ddm = ddm;
        
        createElements(handler);
        initUI();
        setOpaque(false);
    }

    private void createElements(InputHandler handler) {
        
        ActionDieClickListener adListener = new ActionDieClickListener(handler);
        
        statusBar = new JStatusBar(rm,1);
        dicePanel = new ActionDicePanel(ddm, adListener);
        deck = new JDeck(rm);
        grave = new JGrave(rm);
    }
    
    private void initUI() {

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        
        add(statusBar);
        add(deck);
        add(dicePanel);
        add(grave);
        add(Box.createRigidArea(new Dimension(0,180)));
    }
    
    public void updateView(IGameDisplayState state) {
        statusBar.updateView(state);
        deck.updateView(state);
        dicePanel.updateView(state);
        grave.updateView(state);
    }

}
