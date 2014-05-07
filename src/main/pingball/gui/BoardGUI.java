package pingball.gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

/**
 * This class is the CONTROLLER of our Model-View-Controller. Here, the board
 * file is created as a JPanel and is passed to the PingballGUI (View). This
 * class also contains all the listener methods that update the Model and the
 * View based on user input.
 * 
 * @author nconsul, nkbuduma, prafulla
 *
 */
public class BoardGUI extends JPanel {
    Color backgroundColor = Color.white;
    
    public BoardGUI(){
        
    }
    
    public void updateFrame(){
        this.repaint();
    }
    
    @Override
    public void paintComponent(Graphics g){
        Graphics2D g2 = (Graphics2D) g;
        fillWindow(g2);
        drawBoard(g2);
    }

    private void drawBoard(Graphics2D g2) {
        
    }

    private void fillWindow(Graphics2D g2) {
        g.setColor(backgroundColor);
        g.fillRect(0,  0,  getWidth(), getHeight());
    }
}
