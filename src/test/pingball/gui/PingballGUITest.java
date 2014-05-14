package pingball.gui;

public class PingballGUITest {

	/*Testing Strategy:
	 * 
	 * GUI response to user input and general display/layout:
	 * 
	 * 		To test the user input responses, we tried a variety of sequences for clicking buttons.
	 * We first made sure that when we run PingballGUI, a GUI pops up with button focus on "Open File".
	 * The default port is posted, and only possible buttons the user can click are Open File, 
	 * Enter Host, and Enter Port. When the user tries to open a file, the file chooser comes up and
	 * only allows selecting .pb files. Then, when a file is selected, the board is loaded in the
	 * JPanel below. Thus, we knew our file chooser worked.
	 * 		To test setting the hostname, we tried real hostnames and illegitamte hostnames. When an
	 * actual hostname was entered, the game would start in server mode. Otherwise, the game would
	 * start in single player mode. We made sure the hostname would register by hitting Enter or
	 * clicking the "Enter Host" button by displaying the name in a label.
	 * 		To test setting the port number, we tried real port numbers (0 <= portNumber <= 65535).
	 * These worked / would be registered by the label showing the port, and would start the game
	 * in server mode. If an invalid port number is given, then there is a message dialog popup 
	 * telling the user that the port is invalid. When we gave a non-numerical input, the port
	 * remained its default value of 10987. This is what we wanted to happen, and it did, so
	 * the port works.
	 * 		After choosing the file, and setting the host and port, the GUI focus goes to the start
	 * button. When the user hits start, the BoardGUI below updates frame by frame. Focus is also set
	 * to the BoardGUI so that keypresses are registered in the game. We saw that the user could
	 * only click the "pause", "Restart", and "Exit" buttons, which is what we wanted. (Host, Port,
	 * File opener, Resume, and Start became locked). In addition, we noticed that when we started
	 * the game by connecting to the server and entering a valid host and port, the label above
	 * the panel displayed that the game was in server mode. When we didn't provide a host or
	 * we provided an invalid host or didn't connect to the server, the label above the panel showed
	 * that we were in single-player mode. This is what we expected and saw.
	 * 		With clicking the Pause button, the only buttons that could be clicked were Resume,
	 * Restart, and Exit, which is what we wanted. In addition, in single-player mode, the game would
	 * pause immediately and the balls would stop exactly where they were. In multi-palyer mode,
	 * the game would pause only for the board that paused. The boards would remain connected, so any
	 * balls that were passed from some baord to the paused board (either by wall or portal) would
	 * disappear from the boards and appear in the paused board when it was resumed. Furthermore,
	 * while paused, the label above the BoardGUI displayed that the game was paused.
	 *		When we would click restart, the original board file would reload. In single-player mode,
	 * the game would restart as it was supposed to. In multi-player mode, the game would restart and
	 * close connection to any boards it was connected to. It would not affect the state of the other
	 * boards however. (If connected boards had fewer balls than they started off with, they would 
	 * still have fewer balls.) The board that restarted would reload the file as it's supposed to,
	 * still in server mode, but with no connections.
	 * 		When we clicked Exit, the message would display a "Hope you enjoyed!..." message, as it
	 * was supposed to. In addition, the user would only be able to choose a file, or enter host/port
	 * to start a new game. The BoardGUI JPanel would clear completely to how it was before loading
	 * any file. This is what we expected to see.
	 * 
	 * BoardGUI display accuracy:
	 * 		See BoardGUITest.java
	 * 
	 * Single-Player Mode:
	 * 
	 * Multi-Player Mode:
	 * 
	 * 	Pausing/Resuming:
	 * 
	 * 	Restarting:
	 * 
	 *	Exiting:
	 *
	 * 
	 */
}
