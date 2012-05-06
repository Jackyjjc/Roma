package gui;

import java.awt.Dimension;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JPanel;

import model.IGameDisplayState;
import model.InputHandler;
import controller.ActionDieClickListener;

public class JGameBarLeft extends JPanel implements IListener {

    private IDisplayManager idm;

    private JStatusBar statusBar;
    private JPiles piles;
    private ActionDicePanel dicePanel;
    
    public JGameBarLeft(IDisplayManager idm) {
        
        this.idm = idm;
        createElements();
        initUI();
        setOpaque(false);
    }

    private void createElements() {
        

        
        statusBar = new JStatusBar(idm,1);
        dicePanel = new ActionDicePanel(idm);
        piles = new JPiles(idm);
    }
    
    private void initUI() {

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        
        add(statusBar);
        add(Box.createRigidArea(new Dimension(0,idm.scale(90))));
        add(dicePanel);
        add(piles);
        add(Box.createRigidArea(new Dimension(0,idm.scale(135))));
    }
    
    public void updateView(IGameDisplayState state) {
        statusBar.updateView(state);
        dicePanel.updateView(state);
        piles.updateView(state);
    }

}
