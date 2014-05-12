package pingball.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.font.GlyphVector;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.util.List;

import javax.swing.JPanel;

import pingball.model.PingballModel;
import pingball.simulation.*;
import pingball.simulation.GameObject.GameObjectType;
import pingball.simulation.gadget.Gadget.TriggerState;
import pingball.simulation.gadget.SquareBumper;
import pingball.util.Pair;
import pingball.util.StringUtils;

/**
 * This class is the CONTROLLER of our Model-View-Controller. Here, the board
 * file is created as a JPanel and is passed to the PingballGUI (View). This
 * class also contains all the listener methods that update the Model and the
 * View based on user input. Whenever there is a key press, we will put the
 * key press message on the Clients's blocking queue (Model) using the sendMessage method. 
 * 
 * @author nconsul, nkbuduma, prafulla
 *
 */
public class BoardGUI extends JPanel {
    Color backgroundColor = Color.white;
    //Board board;
    PingballModel pingballModel;
    final int scale;
    
    /**
     * Constructor method that creates the JPanel representing the game board.
     * Keeps track of user input as well to modify the Model's board. 
     * @param pingballModel - contains the board that is displayed to the user
     * @param width - sets the width of the game (JPanel) in the PingballGUI
     * @param height - sets the height of the game (JPanel) in the PingballGUI
     */
    public BoardGUI(PingballModel _pingballModel,int _scale){
        this.pingballModel = _pingballModel;
        this.scale = _scale;
        //this.setPreferredSize(new Dimension(22*scale,22*scale));
        setFocusable(true);
        requestFocusInWindow();
        addKeyListener(new KeyAdapter() {
            
            @Override
            public void keyPressed(KeyEvent e) {
                pingballModel.sendMessage("keydown "+StringUtils.join("",KeyEvent.getKeyText(e.getKeyCode()).split("\\s+")).toLowerCase());
            }
            
            @Override
            public void keyReleased(KeyEvent e) {
                pingballModel.sendMessage("keyup "+StringUtils.join("",KeyEvent.getKeyText(e.getKeyCode()).split("\\s+")).toLowerCase());
            }
        });
    }
    
    
    @Override
    public Dimension getMinimumSize() {
        return getPreferredSize();
    }

    @Override
    public Dimension getMaximumSize() {
        return getPreferredSize();
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(425, 425);
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
        RenderingHints rh = new RenderingHints(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON); 
        g2.addRenderingHints(rh);
        scaleWindow(g2);
        fillWindow(g2);
        drawBoard(g2);
    }

    private void scaleWindow(Graphics2D g2) {
        g2.scale(getWidth()/22.0, getHeight()/22.0);
    }

    /**
     * Draws the current form of the board with all the objects in it
     * @param g Graphics2D used to draw the board
     */
    private void drawBoard(final Graphics2D g) {
        //This will draw the current form of all the objects on the board.
        List<Pair<GameObjectType,List<Object>>> objectData = pingballModel.getObjectData();
        for (Pair<GameObjectType,List<Object>> p: objectData){
            if(p!=null){
                GameObjectType type = p.getFirst();
                List<Object> data = p.getSecond();
                if (type.equals(GameObjectType.BALL)){
                    Pair<Double, Double> topLeft = (Pair<Double, Double>) data.get(0);
                    double radius = (double) data.get(1);
                    drawBall(g,topLeft.getFirst(),topLeft.getSecond(),radius);
                }
                //            else if (gameObject instanceof SquareBumper){
                //                SquareBumper squareBumper = (SquareBumper) gameObject;
                //                Pair<Double, Double> topLeft = squareBumper.topLeft();
                //                drawSquareBumper(g,topLeft.getFirst(),topLeft.getSecond(),1,TriggerState.UNTRIGGERED);
                //            }
            }
        }
    }

    /**
     * Sets the board's background color
     * @param g Graphics2D used to fill the background color of the board
     */
    private void fillWindow(final Graphics2D g) {
        g.setColor(backgroundColor);
        g.translate(1, 1);
        g.fillRect(-1,  -1,  21, 21);
    }

    //May change this code
    public void displayFile() {
        updateFrame();
    }
    
    public void drawBall(final Graphics2D g, double x,double y,double r){
        Ellipse2D ball = new Ellipse2D.Double(x, y, r, r);
    	GradientPaint gp = new GradientPaint(0f,0f,Color.BLUE,0f,30f,Color.GREEN);
    	g.setPaint(gp);
    	g.fill(ball);
    }
    
    public void drawCircularBumper(final Graphics2D g, Ellipse2D bumper, TriggerState state){
    	GradientPaint gp;
    	if (state == TriggerState.TRIGGERED){
	    	gp = new GradientPaint(0f,0f,Color.RED.darker(),0f,30f,Color.MAGENTA.darker());
	    } else{
	    	gp = new GradientPaint(0f,0f,Color.RED,0f,30f,Color.MAGENTA);
	    }
    	g.setPaint(gp);
    	g.fill(bumper);
    }
    
    public void drawSquareBumper(final Graphics2D g, double x, double y, int s , TriggerState state){
        Rectangle2D bumper = new Rectangle2D.Double(x, y, s, s);
    	GradientPaint gp;
    	if (state == TriggerState.TRIGGERED){
	    	gp = new GradientPaint(0f,0f,Color.ORANGE.darker(),0f,30f,Color.RED.darker());
	    } else{
	    	gp = new GradientPaint(0f,0f,Color.ORANGE,0f,30f,Color.RED);
	    }
    	g.setPaint(gp);
    	g.fill(bumper);
    }
    
    public void drawTriangularBumper(final Graphics2D g, int[] xPoints, int[] yPoints,TriggerState state){
    	GradientPaint gp;
    	if (state == TriggerState.TRIGGERED){
	    	gp = new GradientPaint(0f,0f,Color.MAGENTA.darker(),0f,30f,Color.ORANGE.darker());
	    } else{
	    	gp = new GradientPaint(0f,0f,Color.MAGENTA,0f,30f,Color.ORANGE);
	    }
    	g.setPaint(gp);
    	g.fillPolygon(xPoints, yPoints, 3);
}
    
    
    public void drawFlipper(final Graphics2D g, Line2D flipper){
    	GradientPaint gp = new GradientPaint(0f,0f,Color.CYAN,0f,30f,Color.BLUE);
    	g.setPaint(gp);
    }
    
    public void drawAbosorber(final Graphics2D g, Rectangle2D absorber, TriggerState state){
    	GradientPaint gp;
    	if (state == TriggerState.TRIGGERED){
	    	gp = new GradientPaint(0f,0f,Color.BLUE.darker(),0f,30f,Color.GREEN.darker());
	    } else{
	    	gp = new GradientPaint(0f,0f,Color.BLUE,0f,30f,Color.GREEN);
	    }
    	g.setPaint(gp);
    	g.fill(absorber);
    }
    
//    public void drawWall(final Graphics2D g, Rectangle2D wall, boolean connected, String connectedBoardName){
//    	g.setPaint(Color.GRAY);
//    	g.fill(wall);
//    	GlyphVector v = (new Font("Helvetica", Font.PLAIN, 12)).createGlyphVector(g.getFontRenderContext(), connectedBoardName);
//    	g.drawGlyphVector(v, alignmentX, alignmentX)
//    }
//    
    
}
