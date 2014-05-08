package pingball.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

import pingball.model.PingballModel;

/**
 * This class represents the VIEW of our Model-View-Controller. Here, we implement
 * changes based on user input (choosing the pingball file, starting, restarting, 
 * pausing, port/host). This will contain the board that the user can see and play
 * with. The board is represented as a BoardGUI JPanel.
 * 
 * This class will update the GUI at each time frame, based on changes to the
 * model. These updates will be made by using a Timer. Buttons can start, pause or stop the timer.
 * Timer will always run listener code on Swing event-handling thread.
 * 
 * @author nconsul, nkbuduma, prafulla
 *
 */

public class PingballGUI extends JFrame {
    
    private PingballModel pingballModel;
    private Timer timer;
    private ActionListener taskPerformer;
    private int delay;
    private int boardWidth;
    private int boardHeight;
    private BoardGUI boardGUI;
    
    public PingballGUI(String[] args) throws IOException{
       // We add all the labels, text fields,buttons,file chooser etc.
       // We add a BoardGUI object, which is a JPanel
        pingballModel = new PingballModel(args);
        
        boardGUI = new BoardGUI(pingballModel,boardWidth,boardHeight) ;
        taskPerformer = new ActionListener() {
           
            @Override
            public void actionPerformed(ActionEvent arg0) {
                pingballModel.evolveFrame();
                boardGUI.updateFrame();
            }
            
        };
        timer = new Timer(delay,taskPerformer){
            /**
             * 
             */
            private static final long serialVersionUID = 1L;

            @Override
            public void stop(){
                pingballModel.pause();
            }
            
            @Override
            public void restart(){
                pingballModel.restart();
            }
            
            @Override
            public void start(){
                pingballModel.start();
            }
        };
        timer.start();
    }
    
    public static void main(final String[] args) {
        // set up the UI (on the event-handling thread)
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                PingballGUI main = null;
                try {
                    main = new PingballGUI(args);
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                main.setVisible(true);
            }
        });
    }
}
