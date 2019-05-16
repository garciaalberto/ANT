package leafEaterAnt;

import javax.swing.*;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Dimension;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Map extends JPanel {

    private static final int DIMENSION = 20;
    private static final int MAXIMUM = 800;
    private static final int SIDE = MAXIMUM / DIMENSION;
    private final BufferedImage LEAFIMAGE;

    public Map() throws IOException {
        this.LEAFIMAGE = ImageIO.read(new File("imgs/hoja.png"));
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
                g.drawImage(LEAFIMAGE,(int) xAxis, (int) yAxis, null);
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
