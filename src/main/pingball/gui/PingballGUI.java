package pingball.gui;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.Timer;
import javax.swing.filechooser.FileNameExtensionFilter;

import pingball.model.PingballModel;

/**
 * This class represents the VIEW of our Model-View-Controller. Here, we implement
 * changes based on user input (choosing the pingball file, starting, restarting, 
 * pausing, resuming, exiting, selecting port/host). 
 * This will contain the board that the user can see and play with. 
 * The board is represented as a BoardGUI JPanel.
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
    
    //components of the GUI
    
    private final JButton resumeButton;
    private final JButton pauseButton;
    private final JButton restartButton;
    private final JButton exitButton;
    private final JButton startButton;

    private final JTextField setPort;
    private final JTextField setHost;
    private final JButton hostButton;
    private final JButton portButton;
    private final JLabel host;
    private final JLabel port;
    private final JLabel displayHost;
    private final JLabel displayPort;
    private final JLabel isConnected;
    
    private final JButton openfileButton;
    private final JFileChooser fc;
    private final JLabel selectFile;
    private JLabel fileName;
    
    /**
     * Constructor for PingballGUI. Creates the View that the user sees. This will
     * contain buttons, labels, and textfields for user input, as well as a BoardGUI JPanel
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
       
        //buttons: start, restart, pause, resume, exit
        startButton = new JButton();
        startButton.setName("startButton");
        startButton.setText("Start");
        startButton.setEnabled(false);       

        restartButton = new JButton();
        restartButton.setName("restartButton");
        restartButton.setText("Restart");
        restartButton.setEnabled(false);
        
        resumeButton = new JButton();
        resumeButton.setName("resumeButton");
        resumeButton.setText("Resume");
        resumeButton.setEnabled(false);
        
        pauseButton = new JButton();
        pauseButton.setName("pauseButton");
        pauseButton.setText("Pause");
        pauseButton.setEnabled(false);        
        
        exitButton = new JButton();
        exitButton.setName("exitButton");
        exitButton.setText("Exit");
        exitButton.setEnabled(false);      
        
        //File chooser and open file button and label
        openfileButton = new JButton();
        openfileButton.setName("openfileButton");
        openfileButton.setText("Open File");
        selectFile = new JLabel();
        selectFile.setName("selectFile");
        selectFile.setText("Select File :");
        fc = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter(
                "Pingball Boards", "txt", "pb");
        fc.setFileFilter(filter);

        //Set port and host textfields, labels, buttons, displays
        setPort = new JTextField();
        setPort.setName("setPort");
        port = new JLabel();
        port.setText("Port: ");
        portButton = new JButton();
        portButton.setName("portButton");
        portButton.setText("Enter Port");
        displayPort = new JLabel();
        displayPort.setPreferredSize(getMaximumSize());
        displayPort.setName("displayPort");
        if(pingballModel.isPortSet()) displayPort.setText("Port: "+pingballModel.getPort());
        else displayPort.setText("Port: ");
        
        setHost = new JTextField();
        setHost.setName("setHost");
        host = new JLabel();
        host.setText("Hostname: ");
        hostButton = new JButton();
        hostButton.setName("hostButton");
        hostButton.setText("Enter Host");
        displayHost = new JLabel();
        displayHost.setPreferredSize(getMaximumSize());
        displayHost.setName("displayHost");
        if(pingballModel.isHostSet()) displayHost.setText("Host: "+pingballModel.getHostName());
        else displayHost.setText("Host: ");
        
        //displays message based on connectivity mode
        isConnected = new JLabel();
        isConnected.setName("isConnected");
        isConnected.setText("Welcome to Pingball!");
        
        //JPanel with game displayed
        boardGUI = new BoardGUI(pingballModel,10);
        fileName = new JLabel();
        if(pingballModel.isFileSet()) {
            boardGUI.displayFile();
            fileName.setText("File Name: "+pingballModel.getFileName());
            startButton.setEnabled(true);
            setHost.requestFocus(true);
        }
        else fileName.setText("File Name: ");
        
        
        /**
         * Timer method to update board and GUI every frame
         */
        taskPerformer = new ActionListener() {            
            @Override
            public void actionPerformed(ActionEvent arg0) {
                pingballModel.evolveFrame();             
                pingballModel.consoleOutput(); //displays output in console
                boardGUI.updateFrame();
                if(pingballModel.isConnected()){
                	if(pingballModel.isRunning()){
                		isConnected.setText("Playing in Server Mode");
                	} else {
                		isConnected.setText("Playing in Server Mode. Paused");
                	}
                }
                else {
                	if(pingballModel.isRunning()){
                		isConnected.setText("Playing in Single Player Mode");
                	} else {
                		isConnected.setText("Playing in Single Player Mode. Paused");
                	}
                }
            }
            
        };
        timer = new MyTimer(delay,taskPerformer);
    
        //sets the layout of GUI
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        Container cp = this.getContentPane();        
        GroupLayout layout = new GroupLayout(cp);
        cp.setLayout(layout);
        layout.setAutoCreateContainerGaps(true); //default margins
        layout.setAutoCreateGaps(true);
        
        layout.setHorizontalGroup(
        		layout.createParallelGroup()
        			.addGroup(layout.createSequentialGroup()
        					.addComponent(selectFile)
        					.addComponent(openfileButton)
        					.addComponent(fileName))
        			.addGroup(layout.createSequentialGroup()
        					.addComponent(host)
        					.addComponent(setHost)
        					.addComponent(hostButton))
        			.addGroup(layout.createSequentialGroup()
        					.addComponent(port)
        					.addComponent(setPort)
        					.addComponent(portButton))
        			.addGroup(layout.createSequentialGroup()        				
        					.addComponent(displayPort)
        					.addComponent(displayHost))
        			.addGroup(layout.createSequentialGroup()
        					.addComponent(isConnected))
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
        					.addComponent(setHost)
        					.addComponent(hostButton))
        		    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
        					.addComponent(port)
        					.addComponent(setPort)
        					.addComponent(portButton))
        		    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)       					
        		    		.addComponent(displayPort)
        					.addComponent(displayHost))
        			.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
        					.addComponent(isConnected))
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
        
        /**
         * Opens file chooser when user clicks button to open a game file.
         * Updates the label to show the chosen file. Allows user to start game
         * once file is chosen.
         */
        openfileButton.addActionListener(new ActionListener(){
        	@Override
            public void actionPerformed(ActionEvent e) {
                int returnVal = fc.showOpenDialog(PingballGUI.this);
                if (returnVal == JFileChooser.APPROVE_OPTION) {
                    File openFile = fc.getSelectedFile();
                    try {
                        pingballModel.setFile(openFile);
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                    fileName.setText("File Name: ".concat(openFile.getName()));
                    //Display the file
                    boardGUI.displayFile();
                    startButton.setEnabled(true);
                    setHost.requestFocus(true);
                } 
            }
        });

        /**
         * Sets the hostnmae to user input in textfield when user clicks the button to 
         * enter host.
         * Calls the helper method to update host.
         * If no host is given or an invalid host is given, game is set to 
         * single-player mode
         */
        setHost.addActionListener(new ActionListener(){
        	
        	@Override
        	public void actionPerformed(ActionEvent e){
        		updateHost();
        	}

			
        });
        
        /**
         * Sets the hostnmae to user input in textfield when user clicks the button to 
         * enter host.
         * Calls the helper method to update host.
         * If no host is given or an invalid host is given, game is set to 
         * single-player mode
         */
        hostButton.addActionListener(new ActionListener(){
        	
        	@Override
        	public void actionPerformed(ActionEvent e){
        		updateHost();
        	}
        });
        
        /**
         * Sets the port number to user input in textfield when user hits Enter/Return.
         * Calls the helper method to update the port.
         * If no port or an invalid port is given, it defaults to '10987'.
         */
        setPort.addActionListener(new ActionListener(){
        	
        	@Override
        	public void actionPerformed(ActionEvent e){
        		updatePort();

        	}
        });
        
        /**
         * Sets the port number to user input in textfield when user clicks the enter port button.
         * Calls the helper method to update the port.
         * If no port or an invalid port is given, it defaults to '10987'.
         */
        portButton.addActionListener(new ActionListener(){
        	
        	@Override
        	public void actionPerformed(ActionEvent e){
        		updatePort();

        	}
        });
        
        /**
         * Starts the timer and game when the user clicks Start button.
         * Allows the user to also click pause, restart, and exit.
         * Disallows the user to set the file, host, port, or hit start and resume.
         */
        startButton.addActionListener(new ActionListener(){
        	@Override
        	public void actionPerformed(ActionEvent arg0) {
        		timer.start();
        		//LOCK and UNLOCK other buttons
        		setPort.setEnabled(false);
        		setHost.setEnabled(false);
        		openfileButton.setEnabled(false);
        		startButton.setEnabled(false);        		
        		pauseButton.setEnabled(true);
        		restartButton.setEnabled(true);
        		exitButton.setEnabled(true);
        		
        		boardGUI.requestFocus(true);
        	}
        });

        /**
         * Pauses the timer/game, disconnecting the user from server mode
         * if the user was originally in server mode. Otherwise, user stays
         * in single-player mode.
         * Enables the user to resume the game.
         */
        pauseButton.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                timer.pause();
               //lock and unlock other buttons
                pauseButton.setEnabled(false);
                resumeButton.setEnabled(true);
            }
        });

        /**
         * Resumes the timer/game, re-connecting the user to server mode if
         * the user was originally in server mode. Otherwise, the user stays
         * in single-player mode.
         * Engables the user to pause the game.
         */
        resumeButton.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                timer.resume();
               //lock and unlock other buttons
                pauseButton.setEnabled(true);
                resumeButton.setEnabled(false);
            }
        });

        /**
         * Restarts the timer/game and reconnects the user to the server, if in server mode.
         * Re-loads and starts the game file in the BoardGUI display
         */
        restartButton.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
               timer.restart();
               //Unlock and lock buttons
               pauseButton.setEnabled(true);
               resumeButton.setEnabled(false);
               startButton.setEnabled(false);
            }
        });
        
        /**
         * Stops the timer/game and clears the BoardGUI display, host, and port
         * Enables the user to choose a new file and play a new game.
         */
        exitButton.addActionListener(new ActionListener(){
        	@Override
        	public void actionPerformed(ActionEvent e){
        		timer.stop();
        		boardGUI.updateFrame();
        		
        		//unlock and lock other buttons
        		setHost.setEnabled(true);
        		setPort.setEnabled(true);
        		openfileButton.setEnabled(true);
        		startButton.setEnabled(false);
        		pauseButton.setEnabled(false);
        		resumeButton.setEnabled(false);
        		restartButton.setEnabled(false);
        		exitButton.setEnabled(false);
        		openfileButton.requestFocus(true);
        		
        		fileName.setText("File Name: ");
        		displayHost.setText("Host: ");
        		displayPort.setText("Port: 10987");
        		isConnected.setText("Hope you enjoyed! Open file to play again.");
        		
        	}
        });
    }
    
    /**
     * Helper method to update and display the host based on user's input in textfield.
     */
    private void updateHost() {
    	String hostname = setHost.getText();
		pingballModel.setHost(hostname);
		setPort.requestFocus(true);
		displayHost.setText("Host: ".concat(hostname));
		System.out.println(hostname);
		
	}
    
    /**
     * Helper method to update and display the port number based on user's input in textfield.
     * If the port number is invalid, a message dialog is generated, giving message
     * "Invalid port number."
     */
    private void updatePort() {
		try{
			int portNum = Integer.valueOf(setPort.getText());
			if(pingballModel.isValidPort(portNum)){
				pingballModel.setPort(portNum);
        		displayPort.setText("Port: " + portNum);
			}    
			else{
				JOptionPane.showMessageDialog(null, "Invalid port number.");
			}
		}
		catch(NumberFormatException nfe){
			
		}
		startButton.requestFocus(true);
	}
    
    /**
     * main method to run the GUI.
     */
    public static void main(final String[] args) {
        // set up the UI (on the event-handling thread)
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                PingballGUI main = null;
                try {
                    main = new PingballGUI(args);
                } catch (IOException e) {
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
            //So, I keep BoardGUI still running
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
