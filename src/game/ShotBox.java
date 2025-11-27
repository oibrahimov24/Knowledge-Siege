package game;

import javax.swing.*;
import java.awt.*;

/**
 * ShotBox class for questions and informations shot by KnowledgeKeepers
 * Implements Drawable interface
 */
public class ShotBox {
	/**
	 * X-coordinate of the ShotBox
	 */
    private int x;
    
    /**
     * Y-coordinate of the ShotBox
     */
    private int y;
    
    /**
     * Defines if the ShotBox is question or information
     */
    private boolean isQuestion;
    
    /**
     * The content of the ShotBox
     */
    private String content;
    
    /**
     * The type of the ShotBox
     * Can be SL,TA or Professor
     */
    private String type;
    
    /**
     * The icon of the ShotBox
     */
    private Image image;
    
    /**
     * The bound of the ShotBox
     */
    private Rectangle bounds;
    
    /**
     * The speed of the ShotBox
     */
    private int speed;
    
    /**
     * The damage given by the ShotBox in case if it is question
     */
    private int damage;
    
    /**
     * The points given by the ShotBox in case if it is information
     */
    private int points;
    
    /**
     * The width of the ShotBox
     */
    private static final int WIDTH=30;
    
    /**
     * The height of the ShotBox
     */
    private static final int HEIGHT=30;
    
    /**
     * The height of the game window
     */
    private static final int WINDOW_HEIGHT=600;

    /**
     * The constructor for the ShotBox with given coordinates, content, type and it being a question or info
     * @param x
     * @param y
     * @param isQuestion
     * @param content
     * @param type
     */
    public ShotBox(int x, int y, boolean isQuestion, String content, String type) {
        this.x=x;
        this.y=y;
        this.isQuestion=isQuestion;
        this.content=content;
        this.type=type;
        this.image=new ImageIcon(isQuestion ? "question.png" : "info.png").getImage();
        this.bounds=new Rectangle(x, y, WIDTH, HEIGHT);

        switch (type) {
            case "SL":
                speed=2;
                damage=5;
                points=10;
                break;
            case "TA":
                speed=3;
                damage=10;
                points=20;
                break;
            case "PROF":
                speed=4;
                damage=20;
                points=30;
                break;
            default:
                speed=2;
                damage=5;
                points=10;
        }
    }

    /**
     * Draws the ShotBox
     */
    public void draw(Graphics g) {
        g.drawImage(image, x, y, WIDTH, HEIGHT, null);
    }

    /**
     * Updates the position of the ShotBox based on its speed
     * Also updates its bound based on the location
     */
    public void update() {
        y+=speed;
        bounds.setLocation(x, y);
    }

    /**
     * Getter function for the bounds of the ShotBox
     * @return
     */
    public Rectangle getBounds() {
        return bounds;
    }

    /**
     * Getter function for the ShotBox being question or info
     * @return
     */
    public boolean isQuestion() {
        return isQuestion;
    }

    /**
     * Getter function for the content of the ShotBox
     * @return
     */
    public String getContent() {
        return content;
    }

    /**
     * Getter function for the Y-coordinate of the ShotBox
     */
    public int getY() {
        return y;
    }

    /**
     * Getter function for the damage given by the ShotBox
     */
    public int getDamage() {
        return damage;
    }

    /**
     * Getter function for the points given by the ShotBox
     */
    public int getPoints() {
        return points;
    }
}
