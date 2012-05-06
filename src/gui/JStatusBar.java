package gui;

import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import model.IGameDisplayState;

public class JStatusBar extends JPanel implements IListener {
    
    private static final int PADDING = 5;
    private final ResourceManager rm;
    private int player;
    private int money;
    private int vp;
    
    public JStatusBar(ResourceManager rm, int player) {
        
        this.rm = rm;
        this.player = player;
        
        setPreferredSize(new Dimension(rm.player1.getWidth() + PADDING, 
                                       rm.player2.getHeight() + PADDING));
        
        System.out.println(rm.player1.getHeight());
        
        this.money = 0;
        this.vp = 0;
        
        setOpaque(false);
    }
    
    public void updateView(IGameDisplayState state) {
        
        this.money = state.getPlayerSestertii(player);
        this.vp = state.getPlayerVictoryPoints(player);
        
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        if(player == 0) {
            g.drawImage(rm.player1, 
                        0, 0, 
                        rm.player1.getWidth(), rm.player1.getHeight(), null);
        } else {
            g.drawImage(rm.player2, 
                        0, 0, 
                        rm.player2.getWidth(), rm.player2.getHeight(), null);
        }
        
        g.drawString(String.valueOf(money), 157, 85);
        g.drawString(String.valueOf(vp), 197, 85);
        
    }
    
}