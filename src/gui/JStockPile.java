package gui;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JPanel;

import model.IGameDisplayState;

public class JStockPile extends JPanel implements IListener {

    private int poolVP;
    private ResourceManager rm;
    private IDisplayManager idm;
    
    public JStockPile(IDisplayManager idm) {

        this.rm = idm.getResourceManager();
        this.idm = idm;
        this.poolVP = 0;
        
        setPreferredSize(new Dimension(rm.stockpile.getWidth() + idm.scale(30), rm.stockpile.getHeight()));
        
        setOpaque(false);
    }

    public void updateView(IGameDisplayState state) {
        poolVP = state.getPoolVictoryPoints();
        revalidate();
    }
    
    @Override
    public void paintComponent(Graphics g) {
        
        Graphics2D g2d = (Graphics2D)g;
        
        g.drawImage(rm.stockpile, 0, 0, null);
        
        Font font = new Font("Arial", Font.PLAIN, idm.scale(16));
        
        g2d.setFont(font);
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.drawString(String.valueOf(poolVP), rm.stockpile.getWidth() + idm.scale(15), idm.scale(55));
    }
    
}
