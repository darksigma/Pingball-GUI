package pingball.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
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
 * Thread safety argument:
 * We will ensure that any change in the GUI will be enclosed in the run() method of 
 * SwingUtilities.invokeLater. This will ensure that we don't have problems manipulating the
 * user interface. 
 * 
 * 
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
    
    private final JButton resumeButton;
    private final JButton pauseButton;
    private final JButton restartButton;
    private final JTextField setPort;
    private final JTextField setHost;
    private final JLabel host;
    private final JLabel port;
    
    /**
     * Constructor for PingballGUI. Creates the View that the user sees. This will
     * contain buttons and textfields for user input, as well as a BoardGUI JPanel
     * displaying the game that is constantly evolving. 
     * 
     * @param args
     * @throws IOException
     */
    public PingballGUI(String[] args) throws IOException{
       // We add all the labels, text fields,buttons,file chooser etc.
       // We add a BoardGUI object, which is a JPanel
        /*
         * There will be buttons to start the game, which takes in the current values
         * the host and file fields to start the game. This will essentially change the args
         * to PingballModel
         */
        super("Pingball");

        pingballModel = new PingballModel(args);
        
        //PLEASE LOOK AT THIS AND CHANGE WHAT YOU DON'T LIKE!!!
        
        //buttons: start, restart, pause
        resumeButton = new JButton();
        resumeButton.setName("resumeButton");
        resumeButton.setText("Resume");
        
        pauseButton = new JButton();
        pauseButton.setName("pauseButton");
        pauseButton.setText("Pause");
        
        restartButton = new JButton();
        restartButton.setName("restartButton");
        restartButton.setText("Start/Restart");
        
        //textfield and labels: set the host/port, display the host/port (is this necessary?)
        setPort = new JTextField();
        setPort.setName("setPort");
        port = new JLabel();
        port.setText("Port: "); //append port # to this
        
        setHost = new JTextField();
        setHost.setName("setHost");
        host = new JLabel();
        host.setText("Hostname: "); //append hostname to this
        
        //menu: select file, exit, disconnect from server
        
        
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
        //timer.start();
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
