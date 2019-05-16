package leafEaterAnt;

/**
 *
 * @author berto
 */
public class Ant {
    private int image;
    private int[] position = new int[2];
    
    public Ant(){
        this.position[0] = (int)Math.floor(Math.random() * 20);
        this.position[1] = (int)Math.floor(Math.random() * 20);
        this.image = (int)Math.floor(Math.random() * 4);
    }
    
    public int getX(){
        return this.position[0];
    }
    
    public int getY(){
        return this.position[1];
    }
    
    public String getImg(){
        switch(image){
            case 0:
                return "imgs/ant_n.png";
            case 1:
                return "imgs/ant_e.png";
            case 2:
                return "imgs/ant_s.png";
            case 3:
                return "imgs/ant_w.png";
            default:
                return "imgs/ant_n.png";
        }
    }
}
