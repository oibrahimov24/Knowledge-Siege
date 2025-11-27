package game;

import javax.swing.*;
import java.awt.*;

/**
 * The Main frame of the Game
 */
public class KnowledgeSiege extends JFrame {
	
	/**
	 * Constructor for game window
	 */
    public KnowledgeSiege() {
        setTitle("Knowledge Siege");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800,600);
        setLocationRelativeTo(null);
        setResizable(false);
        showRegistrationPanel();
    }

    /**
     * Shows the LoginPanel
     */
    private void showRegistrationPanel() {
        JPanel panel=new LoginPanel(this);
        setContentPane(panel);
        revalidate();
        repaint();
    }

    /**
     * Starts the game by creating a GamePanel
     * @param username
     */
    public void startGame(String username) {
        GamePanel gamePanel=new GamePanel(this,username);
        setContentPane(gamePanel);
        gamePanel.requestFocusInWindow();
        setVisible(true);
    }

    /**
     * Main class of the project
     * @param args
     */
    public static void main(String[] args) {
    	/************** Pledge of Honor ****************************************** 
    	I hereby certify that I have completed this programming project on my own without 
    	any help from anyone else. The effort in the project thus belongs completely to me. 
    	I did not search for a solution, or I did not consult any program written by others 
    	or did not copy any program from other sources. I read and followed the guidelines 
    	provided in the project description. 
    	READ AND SIGN BY WRITING YOUR NAME SURNAME AND STUDENT ID  
    	SIGNATURE: Omar Ibrahimov 88767
    	*************************************************************************/
    	new KnowledgeSiege().setVisible(true);
    }
}
