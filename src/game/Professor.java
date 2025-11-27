package game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.security.SecureRandom;


/**
 * Professor class for PROFs
 * Subclass of the class KnowledgeKeeper
 * Implements Drawable interface
 */
public class Professor extends KnowledgeKeeper {
	
	/**
	 * Constructor for Professor class object with given position, name, photo
	 * @param x
	 * @param y
	 * @param name
	 * @param photo
	 * @param random
	 * @param username
	 */
    Professor(int x, int y, String name, String photo, SecureRandom random, String username) {
        super(x, y, name, "PROF", photo, random, username);
        this.speed=3.0;
        this.shotProbability=0.02;
        this.questionChance=0.5;
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
