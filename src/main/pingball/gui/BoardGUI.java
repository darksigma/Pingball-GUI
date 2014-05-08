package pingball.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JPanel;

import pingball.model.PingballModel;
import pingball.simulation.Board;

/**
 * This class is the CONTROLLER of our Model-View-Controller. Here, the board
 * file is created as a JPanel and is passed to the PingballGUI (View). This
 * class also contains all the listener methods that update the Model and the
 * View based on user input. Whenever there is a key press, we will put the
 * key press message on the Clients's blocking queue (Model). 
 * 
 * @author nconsul, nkbuduma, prafulla
 *
 */
public class BoardGUI extends JPanel {
    Color backgroundColor = Color.white;
    //Board board;
    PingballModel pingballModel;
    
    /**
     * Constructor method that creates the JPanel representing the game board.
     * Keeps track of user input as well to modify the Model's board. 
     * @param pingballModel - contains the board that is displayed to the user
     * @param width - sets the width of the game (JPanel) in the PingballGUI
     * @param height - sets the height of the game (JPanel) in the PingballGUI
     */
    public BoardGUI(PingballModel pingballModel,int width,int height){
        this.pingballModel = pingballModel;
        this.setPreferredSize(new Dimension(width, height));
        setFocusable(true);
        requestFocusInWindow();
        addKeyListener(new KeyAdapter() {
            
            @Override
            public void keyPressed(KeyEvent e) {
                //Add the keyPress message to the clients receiveQueue message queue
            }
            
            @Override
            public void keyReleased(KeyEvent e) {
                //Add the keyRelease message to the clients receiveQueue message queue
            }
        });
    }
    
    /**
     * Updates the board that the user sees / the View
     */
    public void updateFrame(){
        this.repaint();
    }
    
    /**
     * Method to repaint the board
     */
    @Override
    public void paintComponent(final Graphics g){
        Graphics2D g2 = (Graphics2D) g;
        fillWindow(g2);
        drawBoard(g2);
    }

    /**
     * Draws the current form of the board with all the objects in it
     * @param g Graphics2D used to draw the board
     */
    private void drawBoard(final Graphics2D g) {
        //This will draw the current form of all the objects on the board.
    }

    /**
     * Sets the board's background color
     * @param g Graphics2D used to fill the background color of the board
     */
    private void fillWindow(final Graphics2D g) {
        g.setColor(backgroundColor);
        g.fillRect(0,  0,  getWidth(), getHeight());
    }
}
