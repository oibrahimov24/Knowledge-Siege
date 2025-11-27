package game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.security.SecureRandom;

/**
 * SectionLeader class for SLs
 * Subclass of the class KnowledgeKeeper
 * Implements Drawable interface
 */
public class SectionLeader extends KnowledgeKeeper {
	
	/**
	 * Constructor for SectionLeader class object with given position, name, photo
	 * @param x
	 * @param y
	 * @param name
	 * @param photo
	 * @param random
	 * @param username
	 */
    public SectionLeader(int x, int y, String name, String photo, SecureRandom random, String username) {
        super(x, y, name, "SL", photo, random, username);
        this.speed=2.0;
        this.shotProbability=0.01;
        this.questionChance=0.7;
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
