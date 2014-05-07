package pingball.gui;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;



public class PingballGUI extends JFrame {
    
    public PingballGUI(){
       // We add all the labels, text fields,buttons,file chooser etc.
       // We add a BoardGUI object, which is a JPanel
    }
    
    public static void main(String[] args) {
        // set up the UI (on the event-handling thread)
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                PingballGUI main = new PingballGUI();
                main.setVisible(true);
            }
        });
    }
}
