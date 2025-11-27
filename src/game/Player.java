package game;

import javax.swing.*;
import java.awt.*;

/**
 * Player class
 * Implements Drawable interface
 */
public class Player {
	/**
	 * X-coordinate of the player
	 */
    private int x;
    
    /**
     * Y-coordinate of the player
     */
    private int y;
    
    /**
     * Image of the player
     */
    private Image image;
    
    /**
     * Bounds of the player
     */
    private Rectangle bounds;
    
    /**
     * Width of the Player
     */
    private static final int WIDTH=50;
    
    /**
     * Height of the Player
     */
    private static final int HEIGHT=50;
    
    /**
     * Speed of the Player
     */
    private static final int SPEED=5;
    
    /**
     * The game window's width
     */
    private static final int GAME_WIDTH=480;

    
    /**
     * Constructor for the Player object with given coordinates and image
     * @param x
     * @param y
     * @param imagePath
     */
    public Player(int x, int y, String imagePath) {
        this.x=x;
        this.y=y;
        this.image=new ImageIcon(imagePath).getImage();
        this.bounds=new Rectangle(x, y, WIDTH, HEIGHT);
    }

    /**
     * Draws the player object using given photo, coordinates, size
     */
    public void draw(Graphics g) {
        g.drawImage(image, x, y, WIDTH, HEIGHT, null);
    }

    /**
     * Updates the bounds of the player
     */
    public void update() {
        bounds.setLocation(x, y);
    }

    /**
     * Player moves left inside the given area
     */
    public void moveLeft() {
        x=Math.max(0, x-SPEED);
    }

    /**
     * Player moves right inside the given area
     */
    public void moveRight() {
        x=Math.min(GAME_WIDTH-WIDTH, x+SPEED);
    }

    /**
     * Checks if the Player collides with the ShotBox
     * @param shot
     * @return
     */
    public boolean collidesWith(ShotBox shot) {
        return bounds.intersects(shot.getBounds());
    }

    /**
     * Getter function for the X-coordinate of the player
     * @return
     */
    public int getX() {
        return x;
    }
}
