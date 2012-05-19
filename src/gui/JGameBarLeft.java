package gui;

import java.awt.Component;
import java.awt.Dimension;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

import controller.StopButtonClickListener;

import model.IGameDisplayState;

public class JGameBarLeft extends JPanel implements IListener {

    private IDisplayManager idm;

    private JStatusBar statusBar;
    private JPiles piles;
    private ActionDicePanel dicePanel;
    private JButton stopButton;
    private Component rigidAreaWithoutStop;
    private Component rigidAreaWithStop;
    
    public JGameBarLeft(IDisplayManager idm) {
        
        this.idm = idm;
        createElements();
        initUI();
        setOpaque(false);
    }

    private void createElements() {
        statusBar = new JStatusBar(idm,1);
        dicePanel = new ActionDicePanel(idm);
        piles = new JPiles(idm);
    }
    
    private void initUI() {

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        
        add(statusBar);
        add(Box.createRigidArea(new Dimension(0,idm.scale(90))));
        add(dicePanel);
        add(piles);
        rigidAreaWithoutStop = Box.createRigidArea(new Dimension(0,idm.scale(135)));
        rigidAreaWithStop = Box.createRigidArea(new Dimension(0,idm.scale(115)));
        stopButton = new JButton("Stop Effect");
        stopButton.addActionListener(new StopButtonClickListener(idm.getInputHandler()));
    }
    
    public void updateView(IGameDisplayState state) {
        statusBar.updateView(state);
        dicePanel.updateView(state);
        piles.updateView(state);
    }

    public void enableActionDiceAdapter(boolean enable) {
        dicePanel.enableAdapters(enable);
    }

    public void enableStopButton(boolean enable) {
        if(enable) {
            remove(rigidAreaWithoutStop);
            add(rigidAreaWithStop);
            add(stopButton);
        } else {
            remove(stopButton);
            remove(rigidAreaWithStop);
            add(rigidAreaWithoutStop);
        }
        
        revalidate();
    }
}
