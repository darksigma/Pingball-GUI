package pingball.gui;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.font.GlyphVector;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.List;

import javax.swing.JPanel;

import pingball.model.PingballModel;
import pingball.simulation.*;
import pingball.simulation.GameObject.GameObjectType;
import pingball.simulation.Wall.Side;
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
                else if (type.equals(GameObjectType.SQUAREBUMPER)){
                    Pair<Double, Double> topLeft = (Pair<Double, Double>) data.get(0);
                    double side = (double) data.get(1);
                    TriggerState state = (TriggerState) data.get(2);
                    drawSquareBumper(g,topLeft.getFirst(),topLeft.getSecond(),1,state);
                }
                else if (type.equals(GameObjectType.TRIANGLEBUMPER)){
                    int x[] = (int[]) data.get(0);
                    int y[] = (int[]) data.get(1);
                    TriggerState state = (TriggerState) data.get(2);
                    drawTriangularBumper(g,x,y,state);
                    
                }
                else if (type.equals(GameObjectType.CIRCLEBUMPER)){
                    Pair<Double, Double> topLeft = (Pair<Double, Double>) data.get(0);
                    double radius = (double) data.get(1);
                    TriggerState state = (TriggerState) data.get(2);
                    drawCircularBumper(g,topLeft.getFirst(),topLeft.getSecond(),radius, state);
                }
                else if (type.equals(GameObjectType.FLIPPER)){
                    Pair<Double, Double> end1 = (Pair<Double, Double>) data.get(0);
                    Pair<Double, Double> end2 = (Pair<Double, Double>) data.get(1);
                    TriggerState state = (TriggerState) data.get(2);
                    drawFlipper(g, end1, end2, state);
                }
                else if (type.equals(GameObjectType.WALL)){
                    Pair<Double, Double> topLeft = (Pair<Double, Double>) data.get(0);
                    Side side = (Side) data.get(1);
                    boolean connected = (boolean) data.get(2);
                    String connectedBoardName = (String) data.get(3);
                    double dimension = 22;
                    drawWall(g, topLeft.getFirst(), topLeft.getSecond(), dimension , connected, connectedBoardName, side);
                }
                else if (type.equals(GameObjectType.ABSORBER)){
                    Pair<Double, Double> topLeft = (Pair<Double, Double>) data.get(0);
                    double width = (double) data.get(1);
                    double height = (double) data.get(2);
                    TriggerState state = (TriggerState) data.get(3);
                    drawAbsorber(g,topLeft.getFirst(),topLeft.getSecond(),width,height,state);
                }
                //            else if (gameObject instanceof SquareBumper){
                //                SquareBumper squareBumper = (SquareBumper) gameObject;
                //                Pair<Double, Double> topLeft = squareBumper.topLeft();
                //                
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
        g.fillRect(-1,  -1,  22, 22);
    }

    //May change this code
    public void displayFile() {
        updateFrame();
    }
    
    private void drawBall(final Graphics2D g, double x,double y,double r){
        Ellipse2D ball = new Ellipse2D.Double(x, y, 2*r, 2*r);
    	GradientPaint gp = new GradientPaint((int)x, (int)y,Color.CYAN,(int)(x +2*r), (int)(y +2*r),Color.BLUE);
    	g.setPaint(gp);
    	g.fill(ball);
    }
    
    private void drawCircularBumper(final Graphics2D g, double x,double y,double r, TriggerState state){
        Ellipse2D bumper = new Ellipse2D.Double(x, y, 2*r, 2*r);
        GradientPaint gp;
    	if (state == TriggerState.TRIGGERED){
	    	gp = new GradientPaint((int)x, (int)y,Color.RED.darker(),(int)(x +2*r), (int)(y +2*r),Color.MAGENTA.darker());
	    } else{
	    	gp = new GradientPaint((int)x, (int)y,Color.RED,(int)(x +2*r), (int)(y +2*r),Color.MAGENTA);
	    }
    	g.setPaint(gp);
    	g.fill(bumper);
    }
    
    private void drawSquareBumper(final Graphics2D g, double x, double y, int s , TriggerState state){
        Rectangle2D bumper = new Rectangle2D.Double(x, y, s, s);
    	GradientPaint gp;
    	if (state == TriggerState.TRIGGERED){
    		gp = new GradientPaint((int)x, (int)y,Color.ORANGE.darker(),(int)(x +s), (int)(y +s),Color.RED.darker());
	    } else{
	    	gp = new GradientPaint((int)x, (int)y,Color.ORANGE,(int)(x +s), (int)(y +s),Color.RED);
	    }
    	g.setPaint(gp);
    	g.fill(bumper);
    }
    
    private void drawTriangularBumper(final Graphics2D g, int[] xPoints, int[] yPoints,TriggerState state){
    	GradientPaint gp;
    	if (state == TriggerState.TRIGGERED){
	    	gp = new GradientPaint(xPoints[0],yPoints[0],Color.MAGENTA.darker(),xPoints[1],yPoints[1],Color.RED.darker());
	    } else{
	    	gp = new GradientPaint(xPoints[0],yPoints[0],Color.MAGENTA,xPoints[1],yPoints[1],Color.RED);
	    }
    	g.setPaint(gp);
    	g.fillPolygon(xPoints, yPoints, 3);
}
    
    
    private void drawFlipper(final Graphics2D g, Pair<Double, Double> end1,
        Pair<Double, Double> end2, TriggerState state){
        Point2D pend1 = new Point2D.Double(end1.getFirst(), end1.getSecond());
        Point2D pend2 = new Point2D.Double(end2.getFirst(), end2.getSecond());
        Line2D flipper = new Line2D.Double(pend1,pend2);
    	GradientPaint gp = new GradientPaint(0f,0f,Color.PINK,0f,30f,Color.MAGENTA);
    	g.setPaint(gp);
    	g.setStroke(new BasicStroke(0.10f));
    	g.draw(flipper);
    }
    
    private void drawAbsorber(final Graphics2D g, double x,double y,double w, double h, TriggerState state){
    	Rectangle2D absorber = new Rectangle2D.Double(x, y, w, h);
    	GradientPaint gp;
    	if (state == TriggerState.TRIGGERED){
    		gp = new GradientPaint((int)x, (int)y,Color.GREEN.darker(),(int)(x +w), (int)(y +w),Color.BLUE.darker());
	    } else{
	    	gp = new GradientPaint((int)x, (int)y,Color.GREEN,(int)(x +w), (int)(y +w),Color.BLUE);
	    }
    	g.setPaint(gp);
    	g.fill(absorber);
    }
    
    public void drawWall(final Graphics2D g, double x, double y, double dimension, boolean connected, String connectedBoardName, Side side){
    	Rectangle2D wall;
        if (side.equals(Side.TOP)){
    	    wall = new Rectangle2D.Double(x, y, dimension, 1.0);    
    	} else if (side.equals(Side.BOTTOM)){
            wall = new Rectangle2D.Double(x, y, dimension, 1.0);
    	} else if (side.equals(Side.LEFT)){
            wall = new Rectangle2D.Double(x, y, 1.0, dimension);
        } else {
            wall = new Rectangle2D.Double(x, y, 1.0, dimension);
        }
    	
        g.setPaint(Color.GRAY);
        g.fill(wall);
    	FontMetrics fm = g.getFontMetrics();
    	if (connected){

    	    if(side.equals(Side.TOP)){
    	        g.drawString(connectedBoardName, 50, 0);
    	    }

    	    if(side.equals(Side.BOTTOM)){
    	        g.drawString(connectedBoardName, 50, 425);
    	    }

    	    if(side.equals(Side.RIGHT)){
    	        int curX = 0;
    	        int curY = 50;
    	        for(Character c : connectedBoardName.toCharArray()){
    	            g.drawString(c.toString(), curX, curY);
    	            curY += 1;
    	        }
    	    }

    	    if(side.equals(Side.LEFT)){
    	        int curX = 425;
    	        int curY = 50;
    	        for(Character c : connectedBoardName.toCharArray()){
    	            g.drawString(c.toString(), curX, curY);
    	            curY += 1;
    	        }
    	    }
    	}
        //GlyphVector v = (new Font("Helvetica", Font.PLAIN, 12)).createGlyphVector(g.getFontRenderContext(), connectedBoardName);

    	//g.drawGlyphVector(v, alignmentX, alignmentX)
    }
    
    
}
