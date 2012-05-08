package gui;

import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JPanel;

import model.IGameDisplayState;

public class JGameBarRight extends JPanel implements IListener {

    private IDisplayManager idm;

    private JStockPile stockpile;
    private BattleDiePanel diePanel;
    private JStatusBar statusBar;
    private SwapArea swapArea;
    
    public JGameBarRight(IDisplayManager idm) {
        
        this.idm = idm;
        
        createElements();
        initUI();
        setOpaque(false);
    }

    private void createElements() {
        
        stockpile = new JStockPile(idm);
        diePanel = new BattleDiePanel(idm);
        statusBar = new JStatusBar(idm, 0);
        swapArea = new SwapArea(idm);
    }
    
    private void initUI() {

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        
        add(Box.createVerticalGlue());
        
        add(Box.createRigidArea(new Dimension(0,idm.scale(100))));
        add(stockpile);
        add(Box.createRigidArea(new Dimension(0,idm.scale(115))));
        add(diePanel);
        add(Box.createRigidArea(new Dimension(0,idm.scale(60))));
        add(swapArea);
        add(statusBar);
    }

    public void updateView(IGameDisplayState state) {
        stockpile.updateView(state);
        diePanel.updateView(state);
        statusBar.updateView(state);
    }
    
}
