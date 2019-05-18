package leafEaterAnt;

/**
 *
 * @author Alberto García Socias
 */

public class Ant { // Class Ant is created basically to store the ant position and it's current status (North, South, West, East)
    private int image; // This integer displays the value of wich image should be displayed, it's also used to know the position it will follow
    private int[] position = new int[2]; //This small vector of integers stores both X and Y on a 0-19 range
    
    public Ant(){ // The position and direction of the ant is completely randomizated in it's creation
        this.position[0] = (int)Math.floor(Math.random() * 20); 
        this.position[1] = (int)Math.floor(Math.random() * 20);
        this.image = (int)Math.floor(Math.random() * 4);
    }
    
    public int getX(){ // Simple getter that returns the ant's X position
        return this.position[0];
    }
    
    public int getY(){ // Simple getter that returns the ant's Y position
        return this.position[1];
    }
    
    public String getImg(){ // This method uses a switch case to return a string with the location of the file with the ant picture
        switch(image){
            case 0: // Case 0: Ant looks up/north
                return "imgs/ant_n.png";
            case 1: // Case 1: Ant looks right/east
                return "imgs/ant_e.png";
            case 2: // Case 2: Ant looks down/south
                return "imgs/ant_s.png";
            case 3: // Case 3: Ant looks left/west
                return "imgs/ant_w.png";
            default: // Default: This shouldn't happen on a normal run. It returns an empty string and prints an error.
                System.err.println("Something went wrong while the ant was trying change direction");
                return "";
        }
    }
    
    public void changeDirection(int image){ // Simple getter that returns the ant's 'image' that also contains it's direction
        this.image = image; 
    }
    
    public void move(){ // This uses the direction to know where the ant should move when the user inputs the SPACE key
        switch(image){
            case 0: // Case 0: Ant moves up/north. If the ant hits the top of the screen the ant will appear at the bottom of it
                this.position[0] -= 1;
                if(this.position[0] < 0){
                    this.position[0] = 19;
                }
                break;
            case 1: // Case 1: Ant moves right/east. If the ant hits the right border of the screen the ant will appear at the left border of it
                this.position[1] += 1;
                if(this.position[1] == 20){
                    this.position[1] = 0;
                }
                break;
            case 2: // Case 2: Ant moves down/south. If the ant hits the top of the screen the ant will appear at the bottom of it
                this.position[0] += 1;
                if(this.position[0] == 20){
                    this.position[0] = 0;
                }
                break;
            case 3: // Case 3: Ant moves left/west. If the ant hits the left border of the screen the ant will appear at the right border of it
                this.position[1] -= 1;
                if(this.position[1] < 0){
                    this.position[1] = 19;
                }
                break;
            default: // Default: This shouldn't happen on a normal run. It prints an error.
                System.err.println("Something went wrong while the ant was trying to move");
        }
    }
}
