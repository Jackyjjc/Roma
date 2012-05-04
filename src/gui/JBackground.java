package gui;

import java.awt.BorderLayout;
import java.awt.Graphics;

import javax.swing.JPanel;

import model.IGameDisplayState;
import view.InputHandler;

public class JBackground extends JPanel implements IListener {
    
    private ResourceManager rm;
    private CardDisplayManager cdm;
    private DieDisplayManager actionDice;
    private DieDisplayManager battleDice;
    
    private JGameBarLeft leftBar;
    private JGameArea gameArea;
    private JGameBarRight rightBar;
    
    public JBackground(ResourceManager rm, InputHandler handler) {
        
        this.rm = rm;
        
        cdm = new CardDisplayManager(rm);
        actionDice = new DieDisplayManager(rm, DieDisplayManager.Type.ACTION);
        battleDice = new DieDisplayManager(rm, DieDisplayManager.Type.BATTLE);
        
        leftBar = new JGameBarLeft(rm, actionDice, handler);
        gameArea = new JGameArea(rm, cdm, handler);
        rightBar = new JGameBarRight(rm, battleDice);
        
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
}
