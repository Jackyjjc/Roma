package gui;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import model.IGameDisplayState;
import view.InputHandler;

public class GraphicalView extends JFrame implements IListener {
    
    private static final String NAME = "Roma 2.2";
    
    private static final int WIDTH = 1366;
    private static final int HEIGHT = 768;
    
    private ResourceManager rm;
    private JBackground background;

    public GraphicalView(InputHandler handler) {
        
        super(NAME);
        
        rm = new ResourceManager();
        background = new JBackground(rm, handler);
        
        setContentPane(background);
        setIconImage(rm.icon);
        
        setPreferredSize(new Dimension(WIDTH,HEIGHT));
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
    }
    
    public void printTurn(int numTurn, String name) {
        JOptionPane.showMessageDialog(this, 
                                      "Turn #" + numTurn + " : It's" + name +"'s turn.",
                                      "new turn",
                                      JOptionPane.PLAIN_MESSAGE);
    }
    
    public void updateView(IGameDisplayState state) {
        background.updateView(state);
        validate();
    }
}
