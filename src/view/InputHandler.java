package view;

import java.io.*;


public class InputHandler {
    
    public static int getInt() {
        
        String requestLine = "";
        BufferedReader stdIn = null;

        try {
            stdIn = new BufferedReader(new InputStreamReader(System.in));

            requestLine = stdIn.readLine();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            
            try {
                stdIn.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        }
        
        return Integer.parseInt(requestLine);
    }
    
}
