package gui;

import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;

public class TransferableImp implements Transferable {

   private static DataFlavor supportedData = null;
   
   private Object traObject;
   
   public TransferableImp(Object traObject) {
       this.traObject = traObject;
   }
   
   public static DataFlavor getSupportedDataFlavor() throws ClassNotFoundException {
        
        if (supportedData == null) {
            supportedData = new DataFlavor(DataFlavor.javaJVMLocalObjectMimeType);
        }

        return supportedData;
    }
    
    public Object getTransferData(DataFlavor flavor) {

        Object returnObject = null;
        DataFlavor supportedData = null;
        
        try {
            supportedData = getSupportedDataFlavor();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        
        if(supportedData != null && flavor.equals(supportedData)) {
            returnObject = traObject;
        }
        
        return returnObject;
    }

    public DataFlavor[] getTransferDataFlavors() {
        
        DataFlavor[] flavors = new DataFlavor[1];
        
        try {
            flavors[0] = getSupportedDataFlavor();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        
        return flavors;
    }

    public boolean isDataFlavorSupported(DataFlavor flavor) {
        
        boolean isSupported = false;
        DataFlavor supportedFlavor = null;
        
        try {
            supportedFlavor = getSupportedDataFlavor();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        
        if(flavor.equals(supportedFlavor)) {
            isSupported = true;
        }
        
        return isSupported;
        
    }

}
