package gui;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import model.IGameDisplayState;

public class JStatusBar extends JPanel implements IListener {
    
    private final ResourceManager rm;
    
    public JStatusBar(ResourceManager rm, int player) {
        
        this.rm = rm;
        
        setOpaque(false);
        initUI(player);
    }
    
    public void updateView(IGameDisplayState state) {
        
    }
    
    private void initUI(int player) {
        if(player == 0) {
            add(new JLabel(new ImageIcon(rm.player1)));
        } else {
            add(new JLabel(new ImageIcon(rm.player2)));
        }
    }
    
}