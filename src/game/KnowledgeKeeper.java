package game;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.security.SecureRandom;
import java.util.List;

/**
 * Superclass of all KnowledgeKeepers
 * Implements Drawable interface
 */
public abstract class KnowledgeKeeper {
	
	/**
	 * X-coordinate of the KnowledgeKeeper
	 */
    protected int x;
    
    /**
     * Y-coordinate of the KnowledgeKeeper
     */
    protected int y;
    
    /**
     * Name of the KnowledgeKeeper
     */
    protected String name;
    
    /**
     * Image of the KnowledgeKeeper
     */
    protected Image image;
    
    /**
     * Type of the KnowledgeKeeper(SL,TA,PROF)
     */
    protected String type;
    
    /**
     * SecureRandom object
     */
    protected SecureRandom random;
    
    /**
     * The speed of the KnowledgeKeeper
     */
    protected double speed;
    
    /**
     * The probability of the KnowledgeKeeper shooting
     */
    protected double shotProbability;
    
    /**
     * The probability of the ShotBox to be question
     */
    protected double questionChance;
    
    /**
     * The logger object to log events
     */
    protected Logger logger;
    
    /**
     * The width of the KnowledgeKeeper
     */
    protected static final int WIDTH=50;
    
    /**
     * The height of the KnowledgeKeeper
     */
    protected static final int HEIGHT=50;
    
    /**
     * The size of the game window
     */
    protected static final int GAME_WIDTH=480;

    /**
     * Constructor for the KnowledgeKeeper objects
     * @param x
     * @param y
     * @param name
     * @param type
     * @param photo
     * @param random
     * @param username
     */
    public KnowledgeKeeper(int x, int y, String name, String type, String photo, SecureRandom random, String username) {
        this.x=x;
        this.y=y;
        this.name=name;
        this.type=type;
        this.random=random;
        this.logger=new Logger(username);

        this.image=null;
        String imagePath="/images/"+photo;

        try {
            this.image=new ImageIcon(getClass().getResource(imagePath)).getImage();
            if (this.image==null || this.image.getWidth(null)<=0) {
                this.image=null;
            }
        } catch(Exception e) {
            this.image=null;
        }
        if(this.image==null) {
            String filePath="images/"+photo;
            File file=new File(filePath);
            if(file.exists()) {
                try {
                    this.image=new ImageIcon(filePath).getImage();
                    if(this.image==null || this.image.getWidth(null)<=0) {
                        this.image=null;
                    }
                } catch (Exception e) {
                    this.image=null;
                }
            }
        }
    }
    
    /**
     * Draws the KnowledgeKeeper with given characteristics
     */
    public void draw(Graphics g) {
        g.drawImage(image, x, y, WIDTH, HEIGHT, null);
    }

    /**
     * Updates the location of the KnowledgeKeeper
     * @param playerX
     * @param keepers
     */
    public void update(int playerX, List<KnowledgeKeeper> keepers) {
        boolean moveTowardPlayer=false;
        if("TA".equals(type) && random.nextDouble()<0.4) {
            moveTowardPlayer=true;
        } 
        else if("PROF".equals(type) && random.nextDouble()<0.6) {
            moveTowardPlayer=true;
        }
        double moveByX;
        if(moveTowardPlayer) {
        	moveByX=playerX > x ? speed : -speed;
        } 
        else {
        	moveByX=random.nextBoolean() ? speed : -speed;
        }
        int newX=x+(int)moveByX;
        boolean canMove=true;
        Rectangle newBounds=new Rectangle(newX, y, WIDTH, HEIGHT);

        for(KnowledgeKeeper other:keepers) {
            if(other==this) continue;
            Rectangle otherBounds=new Rectangle(other.x, other.y, WIDTH, HEIGHT);
            if(newBounds.intersects(otherBounds)) {
                canMove=false;
                break;
            }
        }
        if(canMove) {
            x=newX;
        }
        x=Math.max(0, Math.min(GAME_WIDTH-WIDTH, x));
    }

    /**
     * Getter function for the name of the KnowledgeKeeper
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     * Getter function for the X-coordinate of the KnowledgeKeeper
     * @return
     */
    public int getX() {
        return x;
    }

    /**
     * Getter function for the Y-coordinate of the KnowledgeKeeper
     * @return
     */
    public int getY() {
        return y;
    }

    /**
     * Getter function for the type of the KnowledgeKeeper
     * @return
     */
    public String getType() {
        return type;
    }

    /**
     * Getter function for the shot probability of the KnowledgeKeeper
     * @return
     */
    public double getShotProbability() {
        return shotProbability;
    }

    /**
     * Getter function for the question chance of the KnowledgeKeeper
     * @return
     */
    public double getQuestionChance() {
        return questionChance;
    }
}
