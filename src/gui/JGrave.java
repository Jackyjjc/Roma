package gui;

import java.awt.Dimension;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import model.IGameDisplayState;

public class JGrave extends JPanel implements IListener {

    private ResourceManager rm;
    
    public JGrave(ResourceManager rm) {
        JLabel label = new JLabel(new ImageIcon(rm.blankh));
        add(label);
        setPreferredSize(new Dimension(rm.blankh.getWidth(),rm.blankh.getHeight()));
        setOpaque(false);
    }

    public void updateView(IGameDisplayState state) {
        // TODO Auto-generated method stub
        
    }
    
    
}
