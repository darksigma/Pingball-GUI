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
	 * only allows selecting .pb files. Then, when we selected juggler.pb, flipperWorld.pb, 
	 * sampleBoard1.pb, jugglerPortal.pb, and more, the boards loaded in the
	 * JPanel below everything else. Thus, we knew our file chooser worked.
	 * 		
	 * 		To test setting the hostname, we tried real hostnames and illegitamte hostnames. When an
	 * actual hostname was entered, the game would start in server mode. Otherwise, the game would
	 * start in single player mode. We made sure the hostname would register by hitting Enter or
	 * clicking the "Enter Host" button by displaying the name in a label.
	 *
	 * 		To test setting the port number, we tried real port numbers (0 <= portNumber <= 65535).
	 * These worked / would be registered by the label showing the port, and would start the game
	 * in server mode. If an invalid port number is given, then there is a message dialog popup 
	 * telling the user that the port is invalid. When we gave a non-numerical input, the port
	 * remained its default value of 10987. This is what we wanted to happen, and it did, so
	 * the port works.
	 * 		
	 * 		After choosing the file (juggler.pb), and setting the host and port, the GUI focus goes 
	 * to the start button. When the user hits start, the BoardGUI below updates frame by frame. 
	 * Focus is also set to the BoardGUI so that keypresses are registered in the game. We saw that the 
	 * user could only click the "pause", "Restart", and "Exit" buttons, which is what we wanted. 
	 * (Host, Port, File opener, Resume, and Start became locked). In addition, we noticed that when we 
	 * started the game by connecting to the server and entering a valid host and port, the label above
	 * the panel displayed that the game was in server mode. When we didn't provide a host or
	 * we provided an invalid host or didn't connect to the server, the label above the panel showed
	 * that we were in single-player mode. This is what we expected and saw.
	 * 		
	 * 		With clicking the Pause button, the only buttons that could be clicked were Resume,
	 * Restart, and Exit, which is what we wanted. In addition, in single-player mode, the game would
	 * pause immediately and the balls would stop exactly where they were. In server mode,
	 * the game would pause only for the board that paused. The boards would remain connected, so any
	 * balls that were passed from some board to the paused board (either by wall or portal) would
	 * disappear from the boards and appear in the paused board when it was resumed. Furthermore,
	 * while paused, the label above the BoardGUI displayed that the game was paused.
	 *		
	 *		When we would click restart, the original board file would reload. In single-player mode,
	 * the game would restart as it was supposed to. In server mode, the game would restart and
	 * close connection to any boards it was connected to. It would not affect the state of the other
	 * boards however. (If connected boards had fewer balls than they started off with, they would 
	 * still have fewer balls.) The board that restarted would reload the file as it's supposed to,
	 * still in server mode, but with no connections.
	 * 		
	 * 		When we clicked Exit, the message would display a "Hope you enjoyed!..." message, as it
	 * was supposed to. In addition, the user would only be able to choose a file, or enter host/port
	 * to start a new game. The BoardGUI JPanel would clear completely to how it was before loading
	 * any file. This is what we expected to see.
	 * 
	 * BoardGUI display accuracy:
	 * 
	 * 		See BoardGUITest.java for further details.
	 * 		
	 * The BoardGUI JPanel is in its correct location in the PingballGUI. It does not change
	 * size when we shrink or stretch the PingballGUI window, which is what we want. This is because
	 * we don't want the game objects to shrink/stretch, since they will lose their shape. This held
	 * for all the sampleBoard.pb files in the test/resources, as well as boards we created, listed
	 * above. Additionally, these boards ran for several minutes and sometimes hours, depending on
	 * their layout and the number of balls they started with. This is what was expected for the
	 * functionality of the game.
	 * 
	 * Single-Player Mode:
	 * 
	 * 		In single-player mode, we noticed our PingballGUI functioned properly. It would not
	 * be able to connect to other boards if we tried to from the server console window. Its walls
	 * would not become transparent during play, and any portals in the board wouldn't link to other
	 * boards. If they did, they would appear as light gray, and balls would pass over them during
	 * collisions. If the portals linked to other portals in the same board, then balls would
	 * be able to enter and exit through the portals based on connections. This appeared to work
	 * as expected. In addition, as mentioned above, if a hostname wasn't specified, the game
	 * would properly default to single-player mode.	
	 * 
	 * 
	 * Server Mode:
	 * 
	 * 		See above for details on starting, pausing/resuming, restarting, exiting.
	 * 		
	 * 		In terms of connections, we observed the following when testing connections between
	 *  juggler.pb, flipperWorld.pb, jugglerPortal.pb, and all of the staff sampleBoards:
	 * 
	 * Boards connected to themselves with two or four walls connected
	 * to the same board.
	 *
	 * Two boards connected to each other by a single common wall.
	 *
	 * Two boards connected to each other by two pairs of walls (in a loop shape).
	 *
	 * Two boards connected to each other by a wall and also connected to themselves
	 * (for a separate wall).
	 *
	 * Clients suddenly disconnecting from other clients to make sure
	 * that the other client receives a disconnect message from the server as soon
	 * as it is recognized that a client has terminated.
	 *
	 * Three boards being connected to each other with two common walls between each
	 * pair of boards (to form a loop).
	 *
	 * Many pairs of boards connected in random configurations (with self-joined walls,
	 * and also walls joined to other boards).
	 *
	 * Multiple pairs of clients connected to the server, but with disjoint sets of joined
	 * walls (multiple separate sets of boards interacting with each other).
	 *
	 * Clients disconnecting from and then reconnecting to the server, and then manually
	 * reattaching them to other boards.
	 *
	 * 
	 */
}
