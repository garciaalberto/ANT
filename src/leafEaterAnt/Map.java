package leafEaterAnt;

import javax.swing.*;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Dimension;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

public class Map extends JPanel {

    private static final int DIMENSION = 20;
    private static final int MAXIMUM = 800;
    private static final int SIDE = MAXIMUM / DIMENSION;
    private final BufferedImage LEAFIMAGE;
    private final Ant ANT;

    public Map() throws IOException {
        this.LEAFIMAGE = ImageIO.read(new File("imgs/leaf.png"));
        this.ANT = new Ant();
    }
    
    
    
    @Override
    public void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        int yAxis = 0;
        for (int i = 0; i < DIMENSION; i++) {
            int xAxis = 0;
            for (int j = 0; j < DIMENSION; j++) {
                Rectangle2D.Float r =
                        new Rectangle2D.Float(xAxis, yAxis, SIDE, SIDE);
                g2d.fill(r);
                if(xAxis != ANT.getX()*SIDE || yAxis != ANT.getY()*SIDE){
                    g.drawImage(LEAFIMAGE,(int) xAxis, (int) yAxis, null);
                }
                else {
                    try {
                        g.drawImage(ImageIO.read(new File(ANT.getImg())),(int) xAxis, (int) yAxis, null);
                    } catch (IOException ex) {
                        
                    }
                }
                xAxis += SIDE;
            }
            yAxis += SIDE;
        }
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(MAXIMUM, MAXIMUM);
    }
}
