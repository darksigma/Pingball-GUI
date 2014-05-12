package pingball.gui;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;

import javax.swing.GroupLayout;
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
    private int delay = 50;
    private int boardWidth;
    private int boardHeight;
    private final BoardGUI boardGUI;
    
    private final JButton resumeButton;
    private final JButton pauseButton;
    private final JButton restartButton;
    private final JButton exitButton;
    private final JButton startButton;

    private final JTextField setPort;
    private final JTextField setHost;
    private final JLabel host;
    private final JLabel port;
    
    private final JButton openfileButton;
    private final JFileChooser fc;
    private final JLabel selectFile;
    private JLabel fileName;
    
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
        resumeButton.setEnabled(false);
        
        pauseButton = new JButton();
        pauseButton.setName("pauseButton");
        pauseButton.setText("Pause");
        pauseButton.setEnabled(false);
        
        restartButton = new JButton();
        restartButton.setName("restartButton");
        restartButton.setText("Restart");
        restartButton.setEnabled(false);        

        startButton = new JButton();
        startButton.setName("startButton");
        startButton.setText("Start");
        startButton.setEnabled(false);
        
        exitButton = new JButton();
        exitButton.setName("exitButton");
        exitButton.setText("Exit");
        
        openfileButton = new JButton();
        openfileButton.setName("openfileButton");
        openfileButton.setText("Open File");
        selectFile = new JLabel();
        selectFile.setName("selectFile");
        selectFile.setText("Select File :");
        fileName = new JLabel();
        fileName.setText("File Name: ");
        
        fc = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter(
                "Pingball Boards", "txt", "pb");
        fc.setFileFilter(filter);
        //textfield and labels: set the host/port, display the host/port (is this necessary?)
        setPort = new JTextField();
        setPort.setName("setPort");
        port = new JLabel();
        port.setText("Port: ");
        
        setHost = new JTextField();
        setHost.setName("setHost");
        host = new JLabel();
        host.setText("Hostname: ");
        
        boardGUI = new BoardGUI(pingballModel,10);
        
        taskPerformer = new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent arg0) {
                System.out.println("Performed");
                pingballModel.evolveFrame();
                //TODO: Remove console output at end
                pingballModel.consoleOutput();
                boardGUI.updateFrame();
            }
            
        };
        timer = new MyTimer(delay,taskPerformer);
    
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        Container cp = this.getContentPane();
        
        GroupLayout layout = new GroupLayout(cp);
        cp.setLayout(layout);
        // get some margins around components by default
        layout.setAutoCreateContainerGaps(true);
        layout.setAutoCreateGaps(true);
        
        layout.setHorizontalGroup(
        		layout.createParallelGroup()
        			.addGroup(layout.createSequentialGroup()
        					.addComponent(selectFile)
        					.addComponent(openfileButton)
        					.addComponent(fileName))
        			.addGroup(layout.createSequentialGroup()
        					.addComponent(host)
        					.addComponent(setHost))
        			.addGroup(layout.createSequentialGroup()
        					.addComponent(port)
        					.addComponent(setPort))
        			.addGroup(layout.createSequentialGroup()
        					.addComponent(startButton)
        					.addComponent(pauseButton)
        					.addComponent(resumeButton)
        					.addComponent(restartButton)
        					.addComponent(exitButton))
        			.addGroup(layout.createSequentialGroup()
        					.addComponent(boardGUI))        			
        );
        
        layout.setVerticalGroup(
        		layout.createSequentialGroup()
        		    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
        					.addComponent(selectFile)
        					.addComponent(openfileButton)
        					.addComponent(fileName))
        		    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
        			 		.addComponent(host)
        					.addComponent(setHost))
        		    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
        					.addComponent(port)
        					.addComponent(setPort))
        		    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
        					.addComponent(startButton)
        					.addComponent(pauseButton)
        					.addComponent(resumeButton)
        					.addComponent(restartButton)
        					.addComponent(exitButton))
        		    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
        					.addComponent(boardGUI))        			
        );
        
        this.pack();
        
        
    openfileButton.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                int returnVal = fc.showOpenDialog(PingballGUI.this);
                if (returnVal == JFileChooser.APPROVE_OPTION) {
                    File openFile = fc.getSelectedFile();
                    pingballModel.setFile(openFile);
                    fileName.setText("File Name: ".concat(openFile.getName()));
                    //Display the file
                    boardGUI.displayFile();
                    startButton.setEnabled(true);                    
                } 
            }
            
        });

        setHost.addActionListener(new ActionListener(){
        	
        	@Override
        	public void actionPerformed(ActionEvent e){
        		String hostname = setHost.getText();
        		pingballModel.setHost(hostname);
        	}
        });
        
        setPort.addActionListener(new ActionListener(){
        	
        	@Override
        	public void actionPerformed(ActionEvent e){
        		int portNum = Integer.valueOf(setPort.getText());
        		pingballModel.setPort(portNum);
        	}
        });
        
        
        
        startButton.addActionListener(new ActionListener(){
        	@Override
        	public void actionPerformed(ActionEvent arg0) {
        		//Put this in swingUtils.invokeLater ?
        		timer.start();
        		//LOCK other buttons
        		setPort.setEnabled(false);
        		setHost.setEnabled(false);
        		openfileButton.setEnabled(false);
        		startButton.setEnabled(false);
        		
        		pauseButton.setEnabled(true);
        		restartButton.setEnabled(true);
        		

        	}
        });

        pauseButton.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                timer.pause();
               //UNLOCK BUTTONS?
                pauseButton.setEnabled(false);
                resumeButton.setEnabled(true);
            }
        });

        resumeButton.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                timer.resume();
               //UNLOCK BUTTONS?
                pauseButton.setEnabled(true);
                resumeButton.setEnabled(false);
            }
        });

        restartButton.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
               timer.restart();
               //UNLOCK BUTTONS?
               pauseButton.setEnabled(true);
               resumeButton.setEnabled(false);
               startButton.setEnabled(false);
            }
        });
        
        
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

        private static final long serialVersionUID = 1L;

        public MyTimer(int delay, ActionListener listener) {
            super(delay, listener);
        }

        @Override
        public void start(){
            System.out.println("Started");    
            try {
                    pingballModel.start();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    System.out.println("error starting");
                    e.printStackTrace();
                }
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
            try {
                pingballModel = new PingballModel(new String[] {""});
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            super.stop();
        }

        @Override
        public void restart(){
            //RESTART IS DIFFERENT FROM START AS IT SENDS RESTART MESSAGE
            try {
                pingballModel.restart();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            super.start();
        }

    };

}
