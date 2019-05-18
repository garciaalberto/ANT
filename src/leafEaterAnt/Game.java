package leafEaterAnt;

import java.io.IOException;
import javax.swing.*;

/**
 *
 * @author Alberto García Socias
 */

public class Game extends JFrame { // The class Game whole purpose is to run the game in a JFrame, which inherits from.

    Map map; // Creates a map variable that's were the ant will be able to pick up the leaves

    public Game() throws IOException { 
        super("Leaf eater ant"); // Setting a title to the window
        map = new Map(); // Initializes the map variable
        this.getContentPane().add(map); // Adding the freshly created map to the JFrame
        this.setSize(map.getPreferredSize()); // Setting the size of the map to it's preferred size
        this.pack(); // Packs the whole thing
        this.setResizable(false); // Unables the user to resize the window
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // The program ends when the windows is closed
    }

    public static void main(String[] args) throws IOException { // This is the main
        Game leafEaterAnt = new Game(); // Creates a game
        leafEaterAnt.setVisible(true); // Sets it visible
        
        while(true){
            leafEaterAnt.repaint(); // Repaints constantly the Game and it's map
        }
    }

}

