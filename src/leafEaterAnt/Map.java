package leafEaterAnt;

import javax.swing.*;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author Alberto García Socias
 */

public class Map extends JPanel implements KeyListener{ // The map class ellaborates all the steps to make the game playable. It inherits from JPanel and implements the interface KeyListener

    private static final int DIMENSION = 20; // That's the number of sections that the map will have. It's calculated by 20*20
    private static final int MAXIMUM = 800; // Maximum size of the window
    private static final int SIDE = MAXIMUM / DIMENSION; // Side of each square portion of map that creates a section
    private final BufferedImage LEAFIMAGE; // Buffers the leaf image on it's inizialisation
    private final BufferedImage NOTHINGIMAGE; // Buffers the nothing image on it's inicialisation
    private final Ant ANT; // Sets a ant to play the game, it will be initialized in the constructor
    private final boolean[][] board = new boolean[DIMENSION][DIMENSION]; // A bidimensional array to know the status of the game. true(1) means there's a leaf, false(0) means there's nothing
    private boolean messageDisplayed = false; // This variable is used to not overlay the final victory message
    private boolean complete; // This variable actualizes after each repaint, it reveals ig the game is currently over or not


    public Map() throws IOException { // Constructor for the map class
        this.LEAFIMAGE = ImageIO.read(new File("imgs/leaf.png")); // Saves the leaf image route as a file
        this.NOTHINGIMAGE = ImageIO.read(new File("imgs/nothing.png")); // Saves the nothing image route as a file
        addKeyListener(this); // Adds a KeyListener for the user input
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        this.ANT = new Ant(); // Creates a ant
        for (int i = 0; i < DIMENSION; i++) {
            for (int j = 0; j < DIMENSION; j++) {
                board[i][j] = i*SIDE != ANT.getX()*SIDE || j*SIDE != ANT.getY()*SIDE; // Fills the bidimensional array with leaves(1), excepting where the ant is that gets a nothing(0)
            }
        }
    }
    
    @Override
    public void paintComponent(Graphics graph) { // Overrides the paintComponent() class from it's father class JPanel
        complete = true; // Sets the variable complete as true even if that staement at this point isn't true
        int yAxis = 0; // Creation of an int variable to save the i*SIDE value
        for (int i = 0; i < DIMENSION; i++) {
            int xAxis = 0; // Creation of an int variable to save the j*SIDE value
            for (int j = 0; j < DIMENSION; j++) {
                if(i == ANT.getX() && j == ANT.getY()) {
                    board[i][j] = false; // If the ant is in a section, it's value changes to nothing(0)
                    try {
                        graph.drawImage(ImageIO.read(new File(ANT.getImg())),(int) xAxis, (int) yAxis, null); // The image of the ant is loaded and displayed
                    } catch (IOException exception) {
                        System.err.println("Couldn't load Ant image");
                    }
                }
                else if(board[i][j] == true){
                    complete = false; // If the iteration of the two dimensional array finds one leave(1) value, the game is not completed and needs to continue
                    graph.drawImage(LEAFIMAGE,(int) xAxis, (int) yAxis, null); // Draws a leave on a 1 section
                }
                else if(board[i][j] == false){
                    graph.drawImage(NOTHINGIMAGE,(int) xAxis, (int) yAxis, null); // Drawss nothing on a 0 section
                }
                xAxis += SIDE; // Does the j*SIDE operation
            }
            yAxis += SIDE; // Does the i*SIDE operation
        }
        if(complete && !messageDisplayed){ // When the game is completed and the message wasn't displayed 
            this.messageDisplayed = true;  // Sets the messageDisplayed boolean to true
            JOptionPane.showMessageDialog(this,
            "You did help the ant get enough leaves to survive the whole winter!",
            "Congratulations!",
            JOptionPane.PLAIN_MESSAGE); // And displays the message
        }
    }

    @Override
    public Dimension getPreferredSize() { // returns a Dimension equal the square size of the window
        return new Dimension(MAXIMUM, MAXIMUM);
    }

    public void keyTyped(KeyEvent e) { // Method implementation from the KeyListener Interface
    }

    public void keyPressed(KeyEvent e) { // This checks the user input
        int code = e.getKeyCode();
        if(code == KeyEvent.VK_UP){ // If the user presses the UP arrow the ant changes his direction to 0
            ANT.changeDirection(0);
        }
        if(code == KeyEvent.VK_RIGHT){ // If the user presses the RIGHT arrow the ant changes his direction to 1
            ANT.changeDirection(1);
        }
        if(code == KeyEvent.VK_DOWN){ // If the user presses the DOWN arrow the ant changes his direction to 2
            ANT.changeDirection(2);
        }
        if(code == KeyEvent.VK_LEFT){ // If the user presses the LEFT arrow the ant changes his direction to 3
            ANT.changeDirection(3);
        }
        if(code == KeyEvent.VK_SPACE){ // If the user presses the SPACE key the ant moves to the setted direction
            ANT.move();
        }
    }

    public void keyReleased(KeyEvent e) { // Method implementation from the KeyListener Interface
    }
}
