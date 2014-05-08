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
    
    public void updateFrame(){
        this.repaint();
    }
    
    @Override
    public void paintComponent(final Graphics g){
        Graphics2D g2 = (Graphics2D) g;
        fillWindow(g2);
        drawBoard(g2);
    }

    private void drawBoard(final Graphics2D g) {
        //This will draw the current form of all the objects on the board.
    }

    private void fillWindow(final Graphics2D g) {
        g.setColor(backgroundColor);
        g.fillRect(0,  0,  getWidth(), getHeight());
    }
}
