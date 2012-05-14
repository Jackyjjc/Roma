package gui;

import java.awt.FlowLayout;
import java.awt.dnd.DropTarget;

import javax.swing.JPanel;

import model.IGameDisplayState;
import controller.CustomizedTransferHandler;
import controller.DieDropTargetListener;
import controller.DiscClickListener;

public class JDiscList extends JPanel implements IListener {
    
    private static final int NUM_DISCS = 8;
    
    private IDisplayManager idm;
    private DiscDisplayManager ddm;
    private JDisc[] discs;
    
    public JDiscList (IDisplayManager idm) {
        
        this.idm = idm;
        this.ddm = idm.getDiscDisplayManager();
        this.setLayout(new FlowLayout(FlowLayout.CENTER,idm.scale(40), 0));
        
        initUI(idm.getDiscClickListener());
        setOpaque(false);
    }

    private void initUI(DiscClickListener listener) {
        
        discs = new JDisc[NUM_DISCS];
        JDisc disc;
        
        for (int i = 0; i < NUM_DISCS; i++) {
            disc = new JDisc(ddm,i);
            //disc.addActionListener(listener);
            disc.setTransferHandler(new CustomizedTransferHandler());
            disc.setDropTarget(new DropTarget(disc, new DieDropTargetListener(disc, idm.getInputHandler())));
            add(disc);
            discs[i] = disc;
        }
        
    }

    public void updateView(IGameDisplayState state) {
        repaint();
    }
    
}
