package pingball.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.Timer;
import javax.swing.filechooser.FileNameExtensionFilter;

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
    private MyTimer timer;
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
    private JButton startButton;
    private JButton openfileButton;
    private JFileChooser fc;
    
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
        restartButton.setText("Restart");
        

        startButton = new JButton();
        startButton.setName("startButton");
        startButton.setText("Start");
        
        openfileButton = new JButton();
        openfileButton.setName("openfileButton");
        openfileButton.setText("Open File");
        
        fc = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter(
                "Pingball Boards", "txt", "pb");
        fc.setFileFilter(filter);
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
       
        timer = new MyTimer(delay,taskPerformer);
    
        openfileButton.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                int returnVal = fc.showOpenDialog(PingballGUI.this);
                if (returnVal == JFileChooser.APPROVE_OPTION) {
                    File openFile = fc.getSelectedFile();
                    pingballModel.setFile(openFile);
                    //Display the file
                    boardGUI.displayFile();
                } 
            }
            
        });
        
        startButton.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent arg0) {
                //Put this in swingUtils.invokeLater ?
                timer.start();
                //LOCK other buttons
                
            }
        });
        
        pauseButton.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                timer.pause();
               //UNLOCK BUTTONS?
            }
        });

        resumeButton.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                timer.resume();
               //UNLOCK BUTTONS?
            }
        });

        restartButton.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
               timer.restart();
               //UNLOCK BUTTONS?
            }
        });
        
        taskPerformer = new ActionListener() {
           
            @Override
            public void actionPerformed(ActionEvent arg0) {
                pingballModel.evolveFrame();
                boardGUI.updateFrame();
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
    
    private class MyTimer extends Timer {

        private final long serialVersionUID = 1L;

        public MyTimer(int delay, ActionListener listener) {
            super(delay, listener);
        }

        @Override
        public void start(){
            pingballModel.start();
            super.start();
        }

        public void pause() {
            pingballModel.pause();
        }

        public void resume() {
            pingballModel.resume();
        }

        @Override
        public void stop(){
            pingballModel.stop();
            super.stop();
        }

        @Override
        public void restart(){
            pingballModel.restart();
            super.restart();
        }

    };

}
