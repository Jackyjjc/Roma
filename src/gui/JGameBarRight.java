package gui;

import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JPanel;

import model.IGameDisplayState;

public class JGameBarRight extends JPanel implements IListener {

    private DieDisplayManager ddm;
    private ResourceManager rm;

    private JStockPile stockpile;
    private BattleDiePanel diePanel;
    private JStatusBar statusBar;
    
    public JGameBarRight(ResourceManager rm, DieDisplayManager ddm) {

        this.rm = rm;
        this.ddm = ddm;
        
        createElements();
        initUI();
        setOpaque(false);
    }

    private void createElements() {
        
        stockpile = new JStockPile(rm);
        diePanel = new BattleDiePanel(rm);
        statusBar = new JStatusBar(rm, 0);
    }
    
    private void initUI() {

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        
        add(Box.createVerticalGlue());
        
        add(Box.createRigidArea(new Dimension(0,100)));
        add(stockpile);
        add(Box.createRigidArea(new Dimension(0,115)));
        add(diePanel);
        add(Box.createRigidArea(new Dimension(0,190)));
        add(statusBar);
    }

    public void updateView(IGameDisplayState state) {
        // TODO Auto-generated method stub
        
    }
    
}
