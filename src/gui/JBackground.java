package gui;

import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

import model.IGameDisplayState;

@SuppressWarnings("serial")
public class JBackground extends JPanel implements IListener {
    
    private ResourceManager rm;
    
    private JGameBarLeft leftBar;
    private JGameArea gameArea;
    private JGameBarRight rightBar;
    
    public JBackground(IDisplayManager idm) {
        
        this.rm = idm.getResourceManager();
        
        leftBar = new JGameBarLeft(idm);
        gameArea = new JGameArea(idm);
        rightBar = new JGameBarRight(idm);
        
        setLayout(new BorderLayout());
        
        add(leftBar, BorderLayout.LINE_START);
        add(gameArea, BorderLayout.CENTER);
        add(rightBar, BorderLayout.LINE_END);
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        g.drawImage(rm.background, 0, 0, null);
    }
    
    public void updateView(IGameDisplayState state) {
        leftBar.updateView(state);
        gameArea.updateView(state);
        rightBar.updateView(state);
    }

    public void setSwapConfirmListener(ActionListener l) {
        rightBar.setSwapConfirmListener(l);
    }

    public void enableActionDiceAdapter(boolean enable) {
        leftBar.enableActionDiceAdapter(enable);
    }

    public void enableStopButton(boolean enable) {
        leftBar.enableStopButton(enable);
    }

    public JHand getHand() {
        return gameArea.getHand();
    }

}
