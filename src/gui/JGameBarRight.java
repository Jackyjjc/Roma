package gui;

import java.awt.Component;
import java.awt.Dimension;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JPanel;

import model.IGameDisplayState;

public class JGameBarRight extends JPanel implements ISwapListener {

    private IDisplayManager idm;

    private JStockPile stockpile;
    private BattleDiePanel diePanel;
    private JStatusBar statusBar;
    private Component rigidArea;
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
        idm.getNotifier().addSwapListener(this);
    }
    
    private void initUI() {

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        
        add(Box.createVerticalGlue());
        
        add(Box.createRigidArea(new Dimension(0,idm.scale(100))));
        add(stockpile);
        add(Box.createRigidArea(new Dimension(0,idm.scale(115))));
        add(diePanel);
        rigidArea = Box.createRigidArea(new Dimension(0,idm.scale(60)));
        add(rigidArea);
        add(swapArea);
        add(statusBar);
    }

    public void updateView(IGameDisplayState state) {
        stockpile.updateView(state);
        diePanel.updateView(state);
        statusBar.updateView(state);
    }

    public void swapFinish() {
        
        remove(statusBar);
        
        remove(swapArea);
        remove(rigidArea);
        
        rigidArea = Box.createRigidArea(new Dimension(0,idm.scale(225)));
        add(rigidArea);
        add(statusBar);
    }
    
}
