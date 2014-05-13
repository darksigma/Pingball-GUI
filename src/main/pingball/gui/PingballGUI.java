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
import javax.swing.JOptionPane;
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
        exitButton.setEnabled(false);
        
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
        //setPort.setText("10987");
        port = new JLabel();
        port.setText("Port: ");
        portButton = new JButton();
        portButton.setName("portButton");
        portButton.setText("Enter Port");
        displayPort = new JLabel();
        displayPort.setPreferredSize(getMaximumSize());
        displayPort.setName("displayPort");
        displayPort.setText("Port: 10987");
        
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
        displayHost.setText("Host: ");
        
        isConnected = new JLabel();
        isConnected.setName("isConnected");
        isConnected.setText("Welcome to Pingball!");
        
        boardGUI = new BoardGUI(pingballModel,10);
        
        taskPerformer = new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent arg0) {
                pingballModel.evolveFrame();
                //TODO: Remove console output at end
                pingballModel.consoleOutput();
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
        
        
        openfileButton.addActionListener(new ActionListener(){

        	@Override
            public void actionPerformed(ActionEvent e) {
                int returnVal = fc.showOpenDialog(PingballGUI.this);
                if (returnVal == JFileChooser.APPROVE_OPTION) {
                    File openFile = fc.getSelectedFile();
                    try {
                        pingballModel.setFile(openFile);
                    } catch (IOException e1) {
                        // TODO Auto-generated catch block
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

        setHost.addActionListener(new ActionListener(){
        	
        	@Override
        	public void actionPerformed(ActionEvent e){
        		String hostname = setHost.getText();
        		pingballModel.setHost(hostname);
        		setPort.requestFocus(true);
        		displayHost.setText("Host: ".concat(hostname));
        		System.out.println(hostname);
        	}
        });
        
        hostButton.addActionListener(new ActionListener(){
        	
        	@Override
        	public void actionPerformed(ActionEvent e){
        		String hostname = setHost.getText();
        		pingballModel.setHost(hostname);
        		setPort.requestFocus(true);
        		displayHost.setText("Host: ".concat(hostname));
        		System.out.println(hostname);
        	}
        });
        
        setPort.addActionListener(new ActionListener(){
        	
        	@Override
        	public void actionPerformed(ActionEvent e){
        		updatePort();

        	}
        });
        
        portButton.addActionListener(new ActionListener(){
        	
        	@Override
        	public void actionPerformed(ActionEvent e){
        		updatePort();

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
        		
        		boardGUI.requestFocus(true);
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
        
        exitButton.addActionListener(new ActionListener(){
        	@Override
        	public void actionPerformed(ActionEvent e){
        		timer.stop();
        		//UNLOCK buttons
        		boardGUI.updateFrame();
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
        
           //timer.start();
    }
    
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
