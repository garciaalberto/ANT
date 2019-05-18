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

public class Map extends JPanel implements KeyListener{

    private static final int DIMENSION = 20;
    private static final int MAXIMUM = 800;
    private static final int SIDE = MAXIMUM / DIMENSION;
    private final BufferedImage LEAFIMAGE;
    private final BufferedImage NOTHINGIMAGE;
    private final Ant ANT;
    private final boolean[][] board = new boolean[20][20];
    private boolean messageDisplayed = false;
    boolean complete = true;


    public Map() throws IOException {
        this.LEAFIMAGE = ImageIO.read(new File("imgs/leaf.png"));
        this.NOTHINGIMAGE = ImageIO.read(new File("imgs/nothing.png"));
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        this.ANT = new Ant();
        for (int i = 0; i < DIMENSION; i++) {
            for (int j = 0; j < DIMENSION; j++) {
                board[i][j] = i*SIDE != ANT.getX()*SIDE || j*SIDE != ANT.getY()*SIDE;
            }
        }
    }
    
    @Override
    public void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        complete = true;
        int yAxis = 0;
        for (int i = 0; i < DIMENSION; i++) {
            int xAxis = 0;
            for (int j = 0; j < DIMENSION; j++) {
                Rectangle2D.Float r =
                        new Rectangle2D.Float(xAxis, yAxis, SIDE, SIDE);
                g2d.fill(r);
                if(i == ANT.getX() && j == ANT.getY()) {
                    board[i][j] = false;
                    try {
                        g.drawImage(ImageIO.read(new File(ANT.getImg())),(int) xAxis, (int) yAxis, null);
                    } catch (IOException exception) {
                        System.err.println("Couldnn't load Ant");
                    }
                }
                else if(board[i][j] == true){
                    complete = false;
                    g.drawImage(LEAFIMAGE,(int) xAxis, (int) yAxis, null);
                }
                else if(board[i][j] == false){
                    g.drawImage(NOTHINGIMAGE,(int) xAxis, (int) yAxis, null);
                }
                xAxis += SIDE;
            }
            yAxis += SIDE;
        }
        if(complete && !messageDisplayed){
            this.messageDisplayed = true;
            JOptionPane.showMessageDialog(this,
            "You did help the ant get enough leaves to survive the whole winter!",
            "Congratulations!",
            JOptionPane.PLAIN_MESSAGE);
        }
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(MAXIMUM, MAXIMUM);
    }

    public void keyTyped(KeyEvent e) {
        
    }

    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        if(code == KeyEvent.VK_UP){
            ANT.changeDirection(0);
        }
        if(code == KeyEvent.VK_DOWN){
            ANT.changeDirection(2);
        }
        if(code == KeyEvent.VK_RIGHT){
            ANT.changeDirection(1);
        }
        if(code == KeyEvent.VK_LEFT){
            ANT.changeDirection(3);
        }
        if(code == KeyEvent.VK_SPACE){
            ANT.move();
        }
    }

    public void keyReleased(KeyEvent e) {
        
    }
}
