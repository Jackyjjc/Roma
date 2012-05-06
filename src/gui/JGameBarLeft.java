package gui;

import java.awt.Dimension;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JPanel;

import model.IGameDisplayState;
import model.InputHandler;
import controller.ActionDieClickListener;

public class JGameBarLeft extends JPanel implements IListener {

    private CardDisplayManager cdm;
    private DieDisplayManager ddm;
    private ResourceManager rm;

    private JStatusBar statusBar;
    private JPiles piles;
    private ActionDicePanel dicePanel;
    
    public JGameBarLeft(ResourceManager rm, DieDisplayManager ddm, 
                        InputHandler handler, CardDisplayManager cdm) {

        this.rm = rm;
        this.ddm = ddm;
        this.cdm = cdm;
        
        createElements(handler);
        initUI();
        setOpaque(false);
    }

    private void createElements(InputHandler handler) {
        
        ActionDieClickListener adListener = new ActionDieClickListener(handler);
        
        statusBar = new JStatusBar(rm,1);
        dicePanel = new ActionDicePanel(ddm, adListener);
        piles = new JPiles(rm,cdm);
    }
    
    private void initUI() {

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        
        add(statusBar);
        add(Box.createRigidArea(new Dimension(0,90)));
        add(dicePanel);
        add(piles);
        add(Box.createRigidArea(new Dimension(0,135)));
    }
    
    public void updateView(IGameDisplayState state) {
        statusBar.updateView(state);
        dicePanel.updateView(state);
        piles.updateView(state);
    }

}
