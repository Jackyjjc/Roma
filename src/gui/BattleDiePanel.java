package gui;

import java.awt.FlowLayout;

import javax.swing.JPanel;

import model.IGameDisplayState;

public class BattleDiePanel extends JPanel implements IListener {
    
    private JDie battleDie;
    
    public BattleDiePanel(IDisplayManager idm) {
        this.setLayout(new FlowLayout(FlowLayout.CENTER, idm.scale(15), 0));
        
        initUI(idm.getBattleDieDisplayManager());
        setOpaque(false);
    }

    private void initUI(DieDisplayManager ddm) {
        
        battleDie = new JDie(0,ddm);
        add(battleDie);
    }

    public void updateView(IGameDisplayState state) {
        battleDie.setValue(6);
        revalidate();
    }
    
}
