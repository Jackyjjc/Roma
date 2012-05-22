package gui;

import java.awt.Dimension;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;

@SuppressWarnings("serial")
public class JDie extends JButton implements Transferable {
    
    private DieDisplayManager ddm;
    private TransferableImp traImp;
    
    private int index;
    private int value;
    
    public JDie(int index, DieDisplayManager ddm) {
        
        this.index = index;
        this.ddm = ddm;
        this.traImp = new TransferableImp(this);
        
        setPreferredSize(new Dimension(ddm.getWidth(), ddm.getHeight()));
        setValue(6);
    }
    
    public void setValue(int value) {
        this.value = value;
        this.setIcon(new ImageIcon(ddm.getDie(value)));
    }
    
    public int getIndex() {
        return index;
    }
    
    public int getValue() {
        return value;
    }

    public Object getTransferData(DataFlavor arg0)
            throws UnsupportedFlavorException, IOException {
        return traImp.getTransferData(arg0);
    }

    public DataFlavor[] getTransferDataFlavors() {
        return traImp.getTransferDataFlavors();
    }

    public boolean isDataFlavorSupported(DataFlavor arg0) {
        return traImp.isDataFlavorSupported(arg0);
    }
}