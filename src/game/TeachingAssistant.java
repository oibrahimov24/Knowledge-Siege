package game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.security.SecureRandom;

/**
 * TeachingAssistant class for TAs
 * Subclass of the class KnowledgeKeeper
 * Implements Drawable interface
 */
public class TeachingAssistant extends KnowledgeKeeper {
	
	/**
	 * Constructor for TeachingAssistant class object with given position, name, photo
	 * @param x
	 * @param y
	 * @param name
	 * @param photo
	 * @param random
	 * @param username
	 */
    public TeachingAssistant(int x, int y, String name, String photo, SecureRandom random, String username) {
        super(x, y, name, "TA", photo, random, username);
        this.speed=2.5;
        this.shotProbability=0.015;
        this.questionChance=0.6;
    }

    /**
     * Draws the object using given coordinates, size
     * @param g
     */
    @Override
    public void draw(Graphics g) {
        g.drawImage(image, x, y, WIDTH, HEIGHT, null);
    }
}
