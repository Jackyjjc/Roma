package gui;

import java.awt.Dimension;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import model.IGameDisplayState;

public class JDeck extends JPanel implements IListener {

    private ResourceManager rm;
    
    public JDeck(ResourceManager rm) {
        JLabel label = new JLabel(new ImageIcon(rm.cardh));
        add(label);
        setPreferredSize(new Dimension(rm.cardh.getWidth(),rm.cardh.getHeight()));
        setOpaque(false);
    }

    public void updateView(IGameDisplayState state) {
        
    }
    
}
