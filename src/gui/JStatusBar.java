package gui;

import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.omg.PortableServer.IdAssignmentPolicy;

import model.IGameDisplayState;

public class JStatusBar extends JPanel implements IListener {
    
    private static final int PADDING = 5;
    
    private IDisplayManager idm;
    private ResourceManager rm;
    
    private int panelId;
    
    private int player;
    private int money;
    private int vp;
    
    public JStatusBar(IDisplayManager idm, int player) {
        
        this.rm = idm.getResourceManager();
        this.idm = idm;
        this.player = player;
        this.panelId = player;
        this.money = 0;
        this.vp = 0;
        
        setPreferredSize(new Dimension(rm.player1.getWidth() + idm.scale(PADDING), 
                                       rm.player2.getHeight() + idm.scale(PADDING)));
        
        
        setOpaque(false);
    }
    
    public void updateView(IGameDisplayState state) {

        if(panelId == 0) {
            player = state.getWhoseTurn();
        } else {
            player = (state.getWhoseTurn() == 0) ? 1 : 0;
        }
        
        this.money = state.getPlayerSestertii(player);
        this.vp = state.getPlayerVictoryPoints(player);
        
        repaint();
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
        
        g.drawString(String.valueOf(money), idm.scale(157), idm.scale(85));
        g.drawString(String.valueOf(vp), idm.scale(197), idm.scale(85));
        
    }
    
}