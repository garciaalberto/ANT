
package leafEaterAnt;

import java.io.IOException;
import javax.swing.*;

public class Game extends JFrame {

    Map map;

    public Game() throws IOException {
        super("Leaf eater ant");
        map = new Map();
        this.getContentPane().add(map);
        this.setSize(map.getPreferredSize());
        this.pack();
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) throws IOException {
        Game leafEaterAnt = new Game();
        leafEaterAnt.setVisible(true);
    }

}

